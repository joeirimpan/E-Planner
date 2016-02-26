package com.planit.service;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.planit.repository.RepositoryManager;
import com.planit.repository.SemanticRepositoryManager;
import com.planit.util.KIMClientRuntimeException;
import com.planit.util.PropertyManager;

public class ServiceManager {
	
	
	static Logger log = Logger.getLogger(ServiceManager.class);
	private static final Properties defaultProps = new Properties();
	private static final Properties configProps = new Properties(defaultProps);
	private static final String CONFIG_FILE = "ServiceManager.properties"; 
	private static final Class[] SERVICE_CTOR_SIG = { ServiceManager.class };
	
	private final RepositoryManager repositoryManager;
	static final String REPOSITORY_MANAGER_TAG = "repositoryManager"; 
	
	private final SemanticRepositoryManager semanticRepositoryManager;
	static final String SEMANTIC_REPOSITORY_MANAGER_TAG = "semanticRepositoryManager"; 

	
	private static final String[][] _defaults = { 
        {REPOSITORY_MANAGER_TAG, "com.bsh.kimclient.repository.FSRepositoryManager"},
        {SEMANTIC_REPOSITORY_MANAGER_TAG,"com.bsh.kimclient.repository.JenaSemanticRepositoryManager"}
	};
	private boolean loaded;
	
	static {
         for (int i = 0; i < _defaults.length; i++) {
            defaultProps.setProperty(_defaults[i][0], _defaults[i][1]);
         }
		 // Configuration file ServiceManager.properties overrides defaults.
         InputStream in = PropertyManager.openInputStream(CONFIG_FILE);
         if (in != null) {
            try {
                configProps.load(in);
                log.info("configProp"+configProps.toString());
            } catch (IOException e) {
                log.error("Error loading configuration", e);
            } finally {
                try {
                    if (in != null)
                        in.close();
                } catch (IOException e) {
                	e.printStackTrace();
                }
            }
        }
   }
	
   
	public ServiceManager() {
        Properties props = new Properties(configProps);
        //props.putAll(overrides);
        //System.setProperties(props);
        try {
            
          
            // Create all the services.
            repositoryManager = (RepositoryManager)createService(
                    props, REPOSITORY_MANAGER_TAG);
            semanticRepositoryManager = (SemanticRepositoryManager)createService(
                    props, SEMANTIC_REPOSITORY_MANAGER_TAG);
        	
  
        } catch (Exception e) {
            throw new KIMClientRuntimeException(e);
        }
    }

    /**
     * Loads the various services and repositories.  Should only be called once.
     */
    public synchronized void load() {
        if (loaded) {
            log.warn("Already initialized - ignoring load() call");
            return;
        }

        // Call load() for each service.
        if (log.isDebugEnabled()){
            log.debug("Loading services");
        }    
        repositoryManager.load();
        semanticRepositoryManager.load();
        loaded = true;
    }

    // Loads and instantiates the named WorkflowService implementation class.
    private BaseService createService(Properties props, String key)
        throws ClassNotFoundException, NoSuchMethodException,
        InstantiationException, IllegalAccessException,
        InvocationTargetException {

        String svcClassName = props.getProperty(key);
        Class svcClass = Class.forName(svcClassName);
        Constructor ctor = svcClass.getConstructor(SERVICE_CTOR_SIG);
        return (BaseService)ctor.newInstance(new Object[]{this});
    }

    public final RepositoryManager getRepositoryManager() {
        return repositoryManager;
    }
    
    
    public final SemanticRepositoryManager getSemanticRepositoryManager() {
        return semanticRepositoryManager;
    }
	
 
}
