/**
 *
 */
package com.marco.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.marco.model.CalendarBook;

/**
 * @author Marco
 *
 */
public interface CalendarBookRepository extends JpaRepository<CalendarBook, Long> {

	@Query("select cb from CalendarBook cb left join cb.resource left join cb.userRef where cb.start >= :start and cb.end <= :end")
	List<CalendarBook> findByStartToEnd(@Param("start") Calendar start, @Param("end") Calendar end);

	@Query("select cb from CalendarBook cb join fetch cb.resource join fetch cb.userRef where cb.id = :id")
	CalendarBook reloadCalendar(@Param("id") Long id);
}
