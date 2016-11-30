/**
 *
 */
package com.marco.service;

import java.time.LocalDateTime;
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

	@Query("select cb from CalendarBook cb left join cb.resource where cb.start >= :start and cb.end <= :end and cb.resource.id = :resId")
	List<CalendarBook> findByStartToEnd(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("resId") Long resId);

	@Query("select cb from CalendarBook cb join fetch cb.resource join fetch cb.userRef where cb.id = :id")
	CalendarBook reloadCalendar(@Param("id") Long id);

	default boolean existOverlapping(CalendarBook ncb) {
		List<CalendarBook> overlappingList = findByStartToEnd(ncb.getStart(), ncb.getEnd(), ncb.getResource().getId());	
		return !overlappingList.isEmpty();
	}
}
