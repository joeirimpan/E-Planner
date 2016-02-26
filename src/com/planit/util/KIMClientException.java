package com.planit.util;


import java.io.PrintStream;
import java.io.PrintWriter;


	/**
	 * Base exception for all exceptions in the KIM Client.
	 */
	public class KIMClientException extends Exception {

		private static final long serialVersionUID = 3617570518165960757L;
		
		// JDK1.4 introduced chained throwables, and printStackTrace() now traverses
	    // the chain to the root cause, printing all stack traces.
	    private static final boolean JDK1_4;
	    private Throwable cause;
	    private KIMClientError error;

	    static {
	        boolean jdk1_4;
	        try {
	            Throwable.class.getMethod("getCause", new Class[]{});
	            jdk1_4 = true;
	        } catch (NoSuchMethodException e) {
	            jdk1_4 = false;
	        }
	        JDK1_4 = jdk1_4;
	    }


	    /**
	     * Construct a new PISException.
	     */
	    public KIMClientException() {
	        super();
	    }

	    /**
	     * Construct a new KIMClientException with the given message.
	     * @param message The message
	     */
	    public KIMClientException(String message) {
	        super(message);
	    }

	    /**
	     * Construct a new KIMClientException with the given nested error.
	     * @param t The nested error
	     */
	    public KIMClientException(Throwable t) {
	        super(t.getMessage());
	        cause = t;
	    }
	    
	   /**
	     * Construct a new KIMClientException with the default error.
	     * @param e root Exception
	    */
	    public KIMClientException(Exception e) {
	        this(new KIMClientError(KIMClientError.GENERAL_ERROR), e);
	    }
	    
	    
	    protected KIMClientException(KIMClientError err) {
	        error = err;
	    }

	    
	    protected KIMClientException(KIMClientError err, Throwable e) {
	        super(e.getMessage());
	        cause = e;
	        error = err;
	    }
	    

	    /**
	     * Construct a new KIMClientException with the given messager and nested
	     * error.
	     * @param message The message
	     * @param t The nested error
	     */
	    public KIMClientException(String message, Throwable t) {
	        super(message);
	        cause = t;
	    }

	    /**
	     * Get the nested error.
	     * @return The nested error
	     */
	    public Throwable getCause() {
	        return cause;
	    }

	    public final void printStackTrace() {
	        printStackTrace(System.err);
	    }

	    public final void printStackTrace(PrintStream stream) {
	        super.printStackTrace(stream);
	        // Only print causal stack traces if pre-JDK 1.4.
	        if (!JDK1_4) {
	            Throwable t = getCause();
	            while (t != null) {
	                stream.println("Caused by: " + t);
	                t.printStackTrace(stream);
	                if (t instanceof KIMClientException)
	                    t = ((KIMClientException)t).getCause();
	                else
	                    break;
	            }
	        }
	    }

	    public final void printStackTrace(PrintWriter writer) {
	        super.printStackTrace(writer);
	        // Only print causal stack traces if pre-JDK 1.4.
	        if (!JDK1_4) {
	            Throwable t = getCause();
	            while (t != null) {
	                writer.println("Caused by: " + t);
	                t.printStackTrace(writer);
	                if (t instanceof KIMClientException)
	                    t = ((KIMClientException)t).getCause();
	                else
	                    break;
	            }
	        }
	    }
	}

