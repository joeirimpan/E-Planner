package com.planit.util;

/*
 * Created on Jan 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;


/**
 * Util class for persisting java.util.Properties,
 * allowing for pluggable parsing strategies.
 *
 * <p>Allows for reading from any Reader and writing to any Writer,
 * for example to specify a charset for a properties file. This is a
 * capability that standard java.util.Properties unfortunately lacks:
 * You can only load files using the ISO-8859-1 charset there.
 *
 * <p>Due to the fact that java.util.Properties' own load and store
 * methods are implemented in a completely unextensible fashion,
 * the persistence code had to be copied and pasted into this class,
 * allowing to specify any Reader or Writer.
 *
 * <p>All persistence code in this class is subject to the license
 * of the original java.util.Properties file. The unextensible code
 * there should be permission enough to copy and paste it here.
 *
 * @author Juergen Hoeller
 * @since 10.03.2004
 * @see java.util.Properties
 */
public class PropertyManager {

	static Logger log = Logger.getLogger(PropertyManager.class);
	
	public static final String keyValueSeparators = "= \t\r\n\f";

	public static final String strictKeyValueSeparators = "=";

	public static final String specialSaveChars = "=\t\r\n\f#!";

	public static final String whiteSpaceChars = " \t\r\n\f";

	/** A table of hex digits */
	protected static final char[] hexDigit = {
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
	};


	/**
	 * Load properties from the given InputStream into the given
	 * Properties object.
	 * @param props the Properties object to load into
	 * @param is the InputStream to load from
	 * @throws IOException in case of I/O errors
	 * @see java.util.Properties#load
	 */
	public static void load(Properties props, InputStream is) throws IOException {
		props.load(is);
	}

	/**
	 * Load properties from the given Reader into the given
	 * Properties object.
	 * @param props the Properties object to load into
	 * @param reader the Reader to load from
	 * @throws IOException in case of I/O errors
	 */
	public static void load(Properties props, Reader reader) throws IOException {
		BufferedReader in = new BufferedReader(reader);
		while (true) {
			// Get next line
			String line = in.readLine();
			if (line == null)
				return;

			if (line.length() > 0) {

				// Find start of key
				int len = line.length();
				int keyStart;
				for (keyStart = 0; keyStart < len; keyStart++)
					if (whiteSpaceChars.indexOf(line.charAt(keyStart)) == -1)
						break;

				// Blank lines are ignored
				if (keyStart == len)
					continue;

				// Continue lines that end in slashes if they are not comments
				char firstChar = line.charAt(keyStart);
				if ((firstChar != '#') && (firstChar != '!')) {
					while (continueLine(line)) {
						String nextLine = in.readLine();
						if (nextLine == null)
							nextLine = "";
						String loppedLine = line.substring(0, len - 1);
						// Advance beyond whitespace on new line
						int startIndex;
						for (startIndex = 0; startIndex < nextLine.length(); startIndex++)
							if (whiteSpaceChars.indexOf(nextLine.charAt(startIndex)) == -1)
								break;
						nextLine = nextLine.substring(startIndex, nextLine.length());
						line = new String(loppedLine + nextLine);
						len = line.length();
					}

					// Find separation between key and value
					int separatorIndex;
					for (separatorIndex = keyStart; separatorIndex < len; separatorIndex++) {
						char currentChar = line.charAt(separatorIndex);
						if (currentChar == '\\')
							separatorIndex++;
						else if (keyValueSeparators.indexOf(currentChar) != -1)
							break;
					}

					// Skip over whitespace after key if any
					int valueIndex;
					for (valueIndex = separatorIndex; valueIndex < len; valueIndex++)
						if (whiteSpaceChars.indexOf(line.charAt(valueIndex)) == -1)
							break;

					// Skip over one non whitespace key value separators if any
					if (valueIndex < len)
						if (strictKeyValueSeparators.indexOf(line.charAt(valueIndex)) != -1)
							valueIndex++;

					// Skip over white space after other separators if any
					while (valueIndex < len) {
						if (whiteSpaceChars.indexOf(line.charAt(valueIndex)) == -1)
							break;
						valueIndex++;
					}
					String key = line.substring(keyStart, separatorIndex);
					String value = (separatorIndex < len) ? line.substring(valueIndex, len) : "";

					// Convert then store key and value
					key = loadConvert(key);
					value = loadConvert(value);
					props.put(key, value);
				}
			}
		}
	}

	/**
	 * Return true if the given line is a line that must be appended
	 * to the next line.
	 */
	protected static boolean continueLine(String line) {
		int slashCount = 0;
		int index = line.length() - 1;
		while ((index >= 0) && (line.charAt(index--) == '\\'))
			slashCount++;
		return (slashCount % 2 == 1);
	}

	/**
	 * Convert encoded &#92;uxxxx to unicode chars and changes special
	 * saved chars to their original forms.
	 */
	protected static String loadConvert(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);

		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
							case '0':
							case '1':
							case '2':
							case '3':
							case '4':
							case '5':
							case '6':
							case '7':
							case '8':
							case '9':
								value = (value << 4) + aChar - '0';
								break;
							case 'a':
							case 'b':
							case 'c':
							case 'd':
							case 'e':
							case 'f':
								value = (value << 4) + 10 + aChar - 'a';
								break;
							case 'A':
							case 'B':
							case 'C':
							case 'D':
							case 'E':
							case 'F':
								value = (value << 4) + 10 + aChar - 'A';
								break;
							default:
								throw new IllegalArgumentException(
								    "Malformed \\uxxxx encoding.");
						}
					}
					outBuffer.append((char) value);
				}
				else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f') aChar = '\f';
					outBuffer.append(aChar);
				}
			}
			else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}

	/**
	 * Write the contents of the given Properties object to the
	 * given OutputStream.
	 * @param props the Properties object to store
	 * @param os the OutputStream to write to
	 * @param header the description of the property list
	 * @throws IOException in case of I/O errors
	 * @see java.util.Properties#store
	 */
	public static void store(Properties props, OutputStream os, String header) throws IOException {
		props.store(os, header);
	}

	/**
	 * Write the contents of the given Properties object to the
	 * given Writer.
	 * @param props the Properties object to store
	 * @param writer the Writer to write to
	 * @param header the description of the property list
	 * @throws IOException in case of I/O errors
	 */
	public static void store(Properties props, Writer writer, String header) throws IOException {
		BufferedWriter out;
		out = new BufferedWriter(writer);
		if (header != null)
			writeln(out, "#" + header);
		writeln(out, "#" + new Date().toString());
		for (Enumeration e = props.keys(); e.hasMoreElements();) {
			String key = (String) e.nextElement();
			String val = (String) props.get(key);
			key = saveConvert(key, true);

			/* No need to escape embedded and trailing spaces for value, hence
			* pass false to flag.
			*/
			val = saveConvert(val, false);
			writeln(out, key + "=" + val);
		}
		out.flush();
	}

	/**
	 * Write the given string to the given writer, following it up with a new line.
	 */
	protected static void writeln(BufferedWriter bw, String s) throws IOException {
		bw.write(s);
		bw.newLine();
	}

	/**
	 * Convert unicodes to encoded &#92;uxxxx and writes out any of the
	 * characters in specialSaveChars with a preceding slash
	 */
	protected static String saveConvert(String theString, boolean escapeSpace) {
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len * 2);

		for (int x = 0; x < len; x++) {
			char aChar = theString.charAt(x);
			switch (aChar) {
				case ' ':
					if (x == 0 || escapeSpace)
						outBuffer.append('\\');

					outBuffer.append(' ');
					break;
				case '\\':
					outBuffer.append('\\');
					outBuffer.append('\\');
					break;
				case '\t':
					outBuffer.append('\\');
					outBuffer.append('t');
					break;
				case '\n':
					outBuffer.append('\\');
					outBuffer.append('n');
					break;
				case '\r':
					outBuffer.append('\\');
					outBuffer.append('r');
					break;
				case '\f':
					outBuffer.append('\\');
					outBuffer.append('f');
					break;
				default:
					if ((aChar < 0x0020) || (aChar > 0x007e)) {
						outBuffer.append('\\');
						outBuffer.append('u');
						outBuffer.append(toHex((aChar >> 12) & 0xF));
						outBuffer.append(toHex((aChar >> 8) & 0xF));
						outBuffer.append(toHex((aChar >> 4) & 0xF));
						outBuffer.append(toHex(aChar & 0xF));
					}
					else {
						if (specialSaveChars.indexOf(aChar) != -1)
							outBuffer.append('\\');
						outBuffer.append(aChar);
					}
			}
		}
		return outBuffer.toString();
	}

	/**
	 * Convert a nibble to a hex character.
	 * @param	nibble the nibble to convert
	 */
	protected static char toHex(int nibble) {
		return hexDigit[(nibble & 0xF)];
	}
	
	 public static URL findResource(String resource) {
        URL url = null;
     
        url = PropertyManager.class.getResource("/"+resource);
        log.info("FOUND URL "+url);
        
        if (url != null) {
            log.info("Located resource at " + url);
        } else {
            log.error("Resource '" + resource + "' not found ");
        }
        return url;
    }
	 
	 
	 /**
     * Opens an input stream for a resource.  The caller is responsible for
     * closing the input stream afterwards.
     *
     * @param resource The resource name.
     * @return The input stream, or <code>null</code> if the resource could not
     * be located.
     */
     public static InputStream openInputStream(String resource) {
        InputStream in = null;
        URL url = findResource(resource);
        if (url != null) {
            try {
                in = url.openStream();
            } catch (IOException e) {
            }
        }
        return in;
    }

    /**
     * Opens a SAX input source for a resource.  The caller is responsible for
     * closing the associated byte stream afterwards.
     *
     * @param resource The resource name.
     * @return The input source, or <code>null</code> if the resource could not
     * be located.
     */
     public static InputSource openInputSource(String resource) {
        InputSource src = null;
        InputStream in = openInputStream(resource);
        if (in != null) {
            src = new InputSource(in);
            src.setSystemId(resource);
        }
        return src;
     }

    /**
     * Opens a character stream for a resource.  The caller is responsible for
     * closing the reader afterwards.
     *
     * @param resource The resource name.
     * @return The character stream, or <code>null</code> if the resource could
     * not be located.
     */
    public static Reader openReader(String resource) {
        Reader reader = null;
        InputStream in = openInputStream(resource);
        if (in != null)
            reader = new InputStreamReader(in);
        return reader;
    } 

}