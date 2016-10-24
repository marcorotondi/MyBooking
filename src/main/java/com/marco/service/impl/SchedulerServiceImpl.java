/**
 *
 */
package com.marco.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.marco.data.CalendarData;
import com.marco.model.CalendarBook;
import com.marco.service.CalendarBookRepository;
import com.marco.service.SchedulerService;

/**
 * @author marco.rotondi
 *
 */
@Service
@Qualifier("schedulerService")
public class SchedulerServiceImpl implements SchedulerService {

	@Autowired
	private CalendarBookRepository calendarBookRepo;

	@Override
	public List<CalendarData> findAllScheduler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CalendarData> findAllByRangeDate(LocalDateTime startDate, LocalDateTime endDate) {
		final List<CalendarData> resourceCalendars = new LinkedList<>();
		if (Objects.isNull(endDate)) {
			endDate = startDate.plusDays(5L);
		}
		// Date.from(ZonedDateTime.of(start, ZoneId.systemDefault()).toInstant())
		List<CalendarBook> appointments = calendarBookRepo.findByStartToEnd(GregorianCalendar.from(ZonedDateTime.of(startDate, ZoneId.systemDefault())),
				GregorianCalendar.from(ZonedDateTime.of(endDate, ZoneId.systemDefault())));


		return resourceCalendars;
	}

	@Override
	public List<CalendarData> findByDate(LocalDateTime currentDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
