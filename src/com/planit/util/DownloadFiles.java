package com.planit.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;

public class DownloadFiles {

	// Proxy Authentication
	private static class MECProxyAuthenticator extends Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("mec", "mec".toCharArray());
		}
	}

	public void downloadFiles() {

//		try {
//
//			System.setProperty("http.proxyHost", "192.168.0.4");
//			System.setProperty("http.proxyPort", "3128");
//			Authenticator.setDefault(new MECProxyAuthenticator());

			URL url = null;
			BufferedReader in = null;
			try {
				url = new URL("http://www.cultdeadcow.com/cDc_files/cDc-0226.txt");
				in = new BufferedReader(new InputStreamReader(url.openStream()));
				String inputLine;
				while ((inputLine = in.readLine()) != null)
					System.out.println(inputLine);
				in.close();

			} catch (IOException ioException) {
				ioException.printStackTrace();
			}

//		} 
//		catch (Exception e) {
//			e.printStackTrace();
//		}

	}

	/*
	 * public ArrayList<String> runSPARQL(){
	 * 
	 * //SPARQL String filename = "F:/Linux/desktop/Main Project/e-tourism.owl";
	 * String samplequery =
	 * "PREFIX : <http://e-tourism.deri.at/ont/e-tourism.owl#>"+ "SELECT *"+
	 * "{"+ "{SELECT ?s WHERE { ?s :hasCountry \"Austria\" .}}"+ "MINUS"+
	 * "{SELECT ?s WHERE { ?s :hasCity  \"Neustift\" .}}"+ "}";
	 * ArrayList<String> queryResult = new ArrayList<String>(); OntModel
	 * ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
	 * InputStream in = null; try { in = new FileInputStream(filename);
	 * ontModel.read(in, null);
	 * 
	 * Query query= QueryFactory.create(samplequery); QueryExecution
	 * qexec=QueryExecutionFactory.create(query, ontModel); ResultSet results =
	 * qexec.execSelect();
	 * 
	 * while ( results.hasNext() ){ QuerySolution soln = results.nextSolution();
	 * System.out.print(soln);
	 * queryResult.add(HtmlUtils.htmlEscape(soln.toString())); }
	 * 
	 * in.close(); } catch (IOException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return queryResult;
	 * 
	 * }
	 */

}
