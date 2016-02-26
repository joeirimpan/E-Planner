package com.planit.beans;

import java.io.InputStream;

public class DocumentVO {
	private InputStream inputStream;

	private String id;

	private String path;

	private String folderId;

	public DocumentVO() {
	}

	/**
	 * *
	 * 
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * *
	 * 
	 * @param id
	 *            The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * *
	 * 
	 * @return Returns the is.
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * *
	 * 
	 * @param is
	 *            The is to set.
	 */
	public void setInputStream(InputStream is) {
		this.inputStream = is;
	}

	/**
	 * *
	 * 
	 * @return Returns the path.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * *
	 * 
	 * @param path
	 *            The path to set.
	 */
	public void setPath(String path) {
		this.path = path;
	}

	public String getFolderId() {
		return folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}
}