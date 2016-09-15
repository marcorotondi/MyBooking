/**
 *
 */
package com.marco.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * @author Marco
 *
 */
@Entity
@Table(name = "CALENDAR_BOOK")
public class CalendarBook implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Calendar start;

	private Calendar end;

	private Resource resource;

	private User userRef;

	private int version;

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the start
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START")
	public Calendar getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(Calendar start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END")
	public Calendar getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(Calendar end) {
		this.end = end;
	}

	/**
	 * @return the resource
	 */
	@ManyToOne
	@JoinColumn(name = "RESOURCE_ID")
	public Resource getResource() {
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(Resource resource) {
		this.resource = resource;
	}

	/**
	 * @return the resource
	 */
	@ManyToOne
	@JoinColumn(name = "USER_REF_ID")
	public User getUserRef() {
		return userRef;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setUserRef(User userRef) {
		this.userRef = userRef;
	}

	/**
	 * @return the version
	 */
	@Version
	@Column(name = "VERSION")
	public int getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("CalendarBook [id=%s, start=%s, end=%s]", id,
				start, end);
	}
}
