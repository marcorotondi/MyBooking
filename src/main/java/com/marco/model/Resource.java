/**
 *
 */
package com.marco.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.marco.type.ResourceType;

/**
 * @author Marco
 *
 */
@Entity
@Table(name = "resource")
public class Resource implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank
	private String description;

	@NotNull
	private ResourceType type;

	private Set<CalendarBook> calendarBooks = new HashSet<>();

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
	 * @return the description
	 */
	@Column(name = "DESCRIPTION")
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
	 * @return the type
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE")
	public ResourceType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ResourceType type) {
		this.type = type;
	}

	/**
	 * @return the calendarBooks
	 */
	@OneToMany(mappedBy = "resource", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<CalendarBook> getCalendarBooks() {
		return calendarBooks;
	}

	/**
	 * @param calendarBooks the calendarBooks to set
	 */
	public void setCalendarBooks(Set<CalendarBook> calendarBooks) {
		this.calendarBooks = calendarBooks;
	}

	public void addCalendarBook(CalendarBook calendarBook) {
		calendarBook.setResource(this);
		getCalendarBooks().add(calendarBook);
	}

	public void removeCalendarBook(CalendarBook calendarBook) {
		getCalendarBooks().remove(calendarBook);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Resource [id=%s, description=%s, type=%s]", id,
				description, type);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (id == null ? 0 : id.hashCode());
		result = prime * result + (type == null ? 0 : type.hashCode());
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
		Resource other = (Resource) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		return true;
	}


}
