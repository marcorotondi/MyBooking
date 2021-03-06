/**
 *
 */
package com.marco.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * @author Marco
 *
 */
@Entity
@Table(name = "CALENDAR_BOOK", schema = "booking")
public class CalendarBook implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private LocalDateTime start;

	private LocalDateTime end;

	private Resource resource;

	private User userRef;

	private String checkSum;

	@Version
	@Column(name = "VERSION")
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
	@Column(name = "START")
	public LocalDateTime getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	@Column(name = "END")
	public LocalDateTime getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	/**
	 * @return the resource
	 */
	@ManyToOne(fetch = FetchType.LAZY)
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
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
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
	 * @return the checkSum
	 */
	@Column(name = "CHECK_SUM", nullable = false)
	public String getCheckSum() {
		return checkSum;
	}

	/**
	 * @param checkSum the checkSum to set
	 */
	public void setCheckSum(String checkSum) {
		this.checkSum = checkSum;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("CalendarBook [id=%s, start=%s, end=%s, check=%s]", id, start, end, checkSum);
	}
}
