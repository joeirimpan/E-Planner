/**
 * 
 */
package com.planit.configuration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;


public class ServiceInitServlet extends HttpServlet {

	private static final long serialVersionUID = 3256726165010986295L;
	
	public void init() {
	    System.out.println("Init service started");
	  
	}
	  public void doGet(HttpServletRequest req, HttpServletResponse res) {
	  }

}
