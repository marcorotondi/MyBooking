/**
 *
 */
package com.marco.data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Marco
 *
 */
public class BookingBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	private String user;

	private String resource;

	private String resourceType;

	private LocalDateTime from;

	private LocalDateTime to;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the resource
	 */
	public String getResource() {
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}

	/**
	 * @return the resourceType
	 */
	public String getResourceType() {
		return resourceType;
	}

	/**
	 * @param resourceType the resourceType to set
	 */
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	/**
	 * @return the from
	 */
	public LocalDateTime getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(LocalDateTime from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public LocalDateTime getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(LocalDateTime to) {
		this.to = to;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ id >>> 32);
		result = prime * result + (resource == null ? 0 : resource.hashCode());
		result = prime * result + (resourceType == null ? 0 : resourceType.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BookingBean other = (BookingBean) obj;
		if (id != other.id) {
			return false;
		}
		if (resource == null) {
			if (other.resource != null) {
				return false;
			}
		} else if (!resource.equals(other.resource)) {
			return false;
		}
		if (resourceType == null) {
			if (other.resourceType != null) {
				return false;
			}
		} else if (!resourceType.equals(other.resourceType)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("bookingBean [id=%s, user=%s, resource=%s, resourceType=%s, from=%s, to=%s]", id, user,
				resource, resourceType, from, to);
	}
}
