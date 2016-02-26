package com.planit.repository;

import java.util.Collection;

import com.planit.beans.DocumentVO;
import com.planit.beans.FolderVO;
import com.planit.service.BaseService;
import com.planit.util.RepositoryException;



public interface RepositoryManager extends BaseService {

	public void storeDocument(DocumentVO doc) throws RepositoryException;
	
	public DocumentVO loadDocument(String id) throws RepositoryException;
	
	public boolean deleteDocument(String id) throws RepositoryException;
	
	public boolean deleteDocuments(String folderId, String[] docNames) throws RepositoryException;

	public Collection getDocuments(String folderId) throws RepositoryException;

	public void storeFolder(FolderVO folder) throws RepositoryException;
	
	//public boolean deleteFolder(String id) throws RepositoryException;

	public Collection getFolders(String folderId) throws RepositoryException;

	}
