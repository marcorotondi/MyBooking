/**
 *
 */
package com.marco.type;

/**
 * @author Marco
 *
 */
public enum ResourceType {
	ROOM("Room"),
	OBJECT("Object"),
	CAR("Car");

	private String defaultName;

	private ResourceType(String nameType) {
		this.defaultName = nameType;
	}

	/**
	 * @return the defaultName
	 */
	public String getDefaultName() {
		return defaultName;
	}
}
