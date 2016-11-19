/**
 *
 */
package com.marco.data;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author marco.rotondi
 *
 */
public class SchedulerMappingData {
	private String id;

	@NotEmpty
	private String description;

	@NotEmpty
	private String location = "";

	@NotEmpty
	private String subject = "";

	@NotEmpty
	private String calendar;

	@NotNull
	private LocalDateTime start;

	@NotNull
	private LocalDateTime end;

	private Boolean draggable = Boolean.FALSE;

	private Boolean resizable = Boolean.FALSE;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the calendar
	 */
	public String getCalendar() {
		return calendar;
	}

	/**
	 * @param calendar the calendar to set
	 */
	public void setCalendar(String calendar) {
		this.calendar = calendar;
	}

	/**
	 * @return the start
	 */
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
	 * @return the draggable
	 */
	public Boolean getDraggable() {
		return draggable;
	}

	/**
	 * @param draggable the draggable to set
	 */
	public void setDraggable(Boolean draggable) {
		this.draggable = draggable;
	}

	/**
	 * @return the resizable
	 */
	public Boolean getResizable() {
		return resizable;
	}

	/**
	 * @param resizable the resizable to set
	 */
	public void setResizable(Boolean resizable) {
		this.resizable = resizable;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format(
				"SchedulerMappingData [id=%s, description=%s, location=%s, subject=%s, calendar=%s, start=%s, end=%s]",
				id, description, location, subject, calendar, start, end);
	}
}
