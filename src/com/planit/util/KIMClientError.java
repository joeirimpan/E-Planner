package com.planit.util;

/**
 * <p>Title: SearchError</p>
 * <p>Description: Defines error codes for exceptions which extend SearchException </p>
 * <p>Company: E.D</p>
 * @author Agaton
 * @version 1.0
 */

public class KIMClientError implements java.io.Serializable {

private static final long serialVersionUID = 3616731569302221365L;

  protected static final String INDEX_CREATE_ERROR = "Failed to create new search index";
  protected static final String GENERAL_ERROR = "General Error occured";
  protected static final String INDEX_INCONSISTENCY_ERROR= "Index inconsistency error";
  protected static final String INDEX_ERROR= "Failed to index item";
  protected static final String PARSE_CONTENT_ERROR= "Exception during parsing occured";
  protected static final String SEARCH_ERROR= "Exception in search occured";
  protected static final String REPOSITORY_ERROR= "Exception in repository occured";

  private String _code;

  public KIMClientError(String code) {
    _code = code;
  }
}