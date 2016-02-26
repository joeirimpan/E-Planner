package com.planit.configuration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;


/**
 * @author Agaton
 *
 */
public class Log4jInitServlet extends HttpServlet {


private static final long serialVersionUID = 3256726165010986295L;

public void init() {
    System.out.println("Init Log4j started");
  	String prefix =  getServletContext().getRealPath("/");
    String file = getInitParameter("log4j-init-file");
    // if the log4j-init-file is not set, then no point in trying
    if(file != null) {
      PropertyConfigurator.configure(prefix+file);
      System.out.println("Init Log4j finished");
    }
    else System.out.println("Log4j configuration is not found");
    
    }

  public void doGet(HttpServletRequest req, HttpServletResponse res) {
  }
}

