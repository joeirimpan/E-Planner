package com.planit.service;


public interface BaseService {
	
	/**
     * Initializes the service.
     */
    void load();

    /**
     * De-initializes the service.
     */
    void save();

    /**
     * Returns the service manager that owns this service implementation.
     *
     * @return The service manager.
     */
    ServiceManager getServiceManager();
}
