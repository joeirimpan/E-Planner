package com.planit.configuration;

import org.apache.log4j.Logger;

import com.planit.service.ServiceManager;

public class AppConfig {
	 static Logger log = Logger.getLogger(AppConfig.class);
	 public static final ServiceManager serviceManager;
	 private static boolean _initialized;

	    static {
	    	serviceManager = new ServiceManager();
	    }

	    private AppConfig() {
	    }

	    public static synchronized void init() {
	        if (!_initialized) {
	        	serviceManager.load();
	            _initialized = true;
	            log.debug("KIMClient initialization complete");
	        }
	    }


	    public static boolean isInitialized() {
	        return _initialized;
	    }


}
