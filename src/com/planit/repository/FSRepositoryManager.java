package com.planit.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.planit.beans.DocumentVO;
import com.planit.beans.FolderVO;
import com.planit.service.ServiceManager;
import com.planit.util.FileUtil;
import com.planit.util.RepositoryException;

/**
 * @author Agaton
 */
public class FSRepositoryManager implements RepositoryManager {
    
	static Logger log = Logger.getLogger(FSRepositoryManager.class);
	private final ServiceManager serviceManager;
	private final String workingDir;

	public void load() {
	}

	public void save() {
	}
	
    public  ServiceManager getServiceManager() {
        return serviceManager;
    }

    public FSRepositoryManager(ServiceManager svcMgr) {
    	
		workingDir = FileUtil.getWorkingDir();
		log.debug("Repository Initialized");
		serviceManager = svcMgr;		
	}

	public void storeDocument(DocumentVO document) throws RepositoryException {
		
		try{
			FileUtil.redirectInputStream(document.getInputStream(), new StringBuffer(this.workingDir).append(document.getFolderId()).toString());			
		 }
		 catch(FileNotFoundException e){
		 	throw new RepositoryException(e);
		 }
		 catch(IOException ie){
		 	throw new RepositoryException(ie);
		 }
      }
		
	
	/**
	 * TODO The complete path on FS should not be visible.
	 * For example: ROOT_DIR/milan.html should be displayed as /milan.html
	 * Some filePathEncode() and filePathDecode() should be added
	 */
	
	public DocumentVO loadDocument(String id) throws RepositoryException {
		DocumentVO document = new DocumentVO();
		document.setId(id);
		String path = new StringBuffer(workingDir).append(id).toString();
		document.setPath(path);
		return document;
    }
	
	
	public boolean deleteDocument(String id) throws RepositoryException {
		
  		return FileUtil.deleteDir(new StringBuffer(workingDir).append(id).toString());
    }
	
	public boolean deleteDocuments(String folderId, String[] docNames) throws RepositoryException {
		return FileUtil.deleteDocs(new StringBuffer(workingDir).append(folderId).toString(), docNames);
    }
	
	
    /**
     * Retrieves the collection of documents stored in ROOT_DIR/{folderPath}
     */
	/**
	 * TODO remove dependancy from java.io. Use FileUtil, make new method for listing files and handle exceptions correctly)
	 */
	public Collection getDocuments(String folderId) throws RepositoryException {
		Collection retValue = null;
		retValue = FileUtil.listFiles(new StringBuffer(workingDir).append(folderId).toString());
		return retValue;
	}
	
    public void storeFolder(FolderVO folder) throws RepositoryException{
    	 FileUtil.mkdir(new StringBuffer(workingDir).append(folder.getId()).toString());
    }
	
	//public boolean deleteFolder(String id) throws RepositoryException{
	//	return false;
	//}

	public Collection getFolders(String folderId) throws RepositoryException{
		
		Collection retValue = null;
		retValue = FileUtil.listFolders(new StringBuffer(workingDir).append(folderId).toString());
		return retValue;
	}

}
