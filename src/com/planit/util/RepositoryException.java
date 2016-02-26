package com.planit.util;

public class RepositoryException extends KIMClientException {
    

	private static final long serialVersionUID = 3617858568653386801L;
	
	public RepositoryException(Exception e) {
        super(KIMClientError.INDEX_ERROR, e);
    }
	
}
