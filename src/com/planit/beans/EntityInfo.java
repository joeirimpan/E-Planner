/*
 * Created on Dec 28, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.planit.beans;
import java.util.List;
/**
 * @author Agaton
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EntityInfo {
 
 private String label = "";
 private String uri = "";
 private String[] classData;
 private String origin = "";
 private List properties;
 private List relatedEntities;
 
 
 
/**
 * @return Returns the classData.
 */
public String[] getClassData() {
	return classData;
}
/**
 * @param classData The classData to set.
 */
public void setClassData(String[] classData) {
	this.classData = classData;
}
/**
 * @return Returns the label.
 */
public String getLabel() {
	return label;
}
/**
 * @param label The label to set.
 */
public void setLabel(String label) {
	this.label = label;
}
/**
 * @return Returns the origin.
 */
public String getOrigin() {
	return origin;
}
/**
 * @param origin The origin to set.
 */
public void setOrigin(String origin) {
	this.origin = origin;
}
/**
 * @return Returns the properties.
 */
public List getProperties() {
	return properties;
}
/**
 * @param properties The properties to set.
 */
public void setProperties(List properties) {
	this.properties = properties;
}
/**
 * @return Returns the relatedEntities.
 */
public List getRelatedEntities() {
	return relatedEntities;
}
/**
 * @param relatedEntities The relatedEntities to set.
 */
public void setRelatedEntities(List relatedEntities) {
	this.relatedEntities = relatedEntities;
}
/**
 * @return Returns the uri.
 */
public String getUri() {
	return uri;
}
/**
 * @param uri The uri to set.
 */
public void setUri(String uri) {
	this.uri = uri;
}
}
