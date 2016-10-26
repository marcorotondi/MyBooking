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

import com.marco.data.SchedulerMappingData;
import com.marco.model.CalendarBook;
import com.marco.service.CalendarBookRepository;
import com.marco.service.ResourceRepository;
import com.marco.service.SchedulerService;
import com.marco.utils.BookingUtils;

/**
 * @author marco.rotondi
 *
 */
@Service
@Qualifier("schedulerService")
public class SchedulerServiceImpl implements SchedulerService {

	@Autowired
	private CalendarBookRepository calendarBookRepo;
	
	@Autowired
	private ResourceRepository resourceRepo; 

	@Override
	public List<SchedulerMappingData> findAllScheduler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SchedulerMappingData> findAllByRangeDate(LocalDateTime startDate, LocalDateTime endDate) {
		final List<SchedulerMappingData> resourceCalendars = new LinkedList<>();
		if (Objects.isNull(endDate)) {
			endDate = startDate.plusDays(5L);
		}

		List<CalendarBook> appointments = calendarBookRepo.findByStartToEnd(GregorianCalendar.from(ZonedDateTime.of(startDate, ZoneId.systemDefault())),
				GregorianCalendar.from(ZonedDateTime.of(endDate, ZoneId.systemDefault())));

		if (!appointments.isEmpty()) {
			appointments.forEach(calBook -> resourceCalendars.add(BookingUtils.prepareCalendarData(calBook)));
		} else {
			BookingUtils.fillEmptyCalendar(resourceCalendars, resourceRepo.findAllByOrderByTypeDescDescriptionDesc());
		}

		return resourceCalendars;
	}

	@Override
	public List<SchedulerMappingData> findByDate(LocalDateTime currentDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
