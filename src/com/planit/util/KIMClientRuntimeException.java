
package com.planit.util;

import java.io.PrintStream;
import java.io.PrintWriter;

/** Base exception for all runtime exceptions in the KIMClient.

*/

public class KIMClientRuntimeException extends RuntimeException{

   private static final long serialVersionUID = 3257571693731264561L;
   // JDK1.4 introduced chained throwables, and printStackTrace() now traverses
   // the chain to the root cause, printing all stack traces.
   private static final boolean JDK1_4;

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

   protected Throwable _cause;

   /** Construct a new KIMClientRuntimeException. */

   public KIMClientRuntimeException(){
       super();
   }

   /** Construct a new KIMClientRuntimeException with the given message.

       @param message The message
   */

   public KIMClientRuntimeException(String message){
       super(message);
   }

   /** Construct a new KIMClientRuntimeException with the given nested error.

       @param t The nested error
   */

   public KIMClientRuntimeException(Throwable t){
       super(t.getMessage());
       _cause = t;
   }

   /** Construct a new KIMClientRuntimeException with the given messager and nested
       error.

       @param message The message
       @param t The nested error
   */

   public KIMClientRuntimeException(String message, Throwable t){
       super(message);
       _cause = t;
   }

   /** Get the nested error.

       @return The nested error
   */

   public Throwable getCause(){
       return _cause;
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
               if (t instanceof KIMClientRuntimeException)
                   t = ((KIMClientRuntimeException)t).getCause();
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
               if (t instanceof KIMClientRuntimeException)
                   t = ((KIMClientRuntimeException)t).getCause();
               else
                   break;
           }
       }
   }
}

