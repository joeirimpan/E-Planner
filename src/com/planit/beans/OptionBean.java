/**
 * 
 */
package com.planit.beans;

/**
 * @author danica
 *
 */
public class OptionBean {

	String label;
	String value;
	
	public OptionBean(String label, String value){
		
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public String getValue() {
		return value;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
