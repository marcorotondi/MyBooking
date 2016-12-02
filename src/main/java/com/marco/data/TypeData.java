/**
 * 
 */
package com.marco.data;

import java.io.Serializable;

/**
 * @author marco.rotondi
 *
 */
public class TypeData implements Serializable {

	private static final long serialVersionUID = -4787785908365983771L;
	
	private final String key;
	
	private final String value;
	
	public TypeData(final String key, final String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
	

}
