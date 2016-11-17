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

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(SchedulerServiceImpl.class);

	@Autowired
	private CalendarBookRepository calendarBookRepo;
	
	@Autowired
	private ResourceRepository resourceRepo; 

	@Override
	public List<SchedulerMappingData> findAllScheduler() {
		final List<SchedulerMappingData> appointmentCalendars = new LinkedList<>();
		List<CalendarBook> appointments = calendarBookRepo.findAll();
		
		if (!appointments.isEmpty()) {
			appointments.forEach(calendarBook -> appointmentCalendars.add(BookingUtils.prepareCalendarData(calendarBook)));
		}
		
		return appointmentCalendars;
	}

	@Override
	public List<SchedulerMappingData> findAllByRangeDate(LocalDateTime startDate, LocalDateTime endDate) {
		final List<SchedulerMappingData> appointmentCalendars = new LinkedList<>();
		if (Objects.isNull(endDate)) {
			endDate = startDate.plusDays(5L);
		}

		List<CalendarBook> appointments = calendarBookRepo.findByStartToEnd(GregorianCalendar.from(ZonedDateTime.of(startDate, ZoneId.systemDefault())),
				GregorianCalendar.from(ZonedDateTime.of(endDate, ZoneId.systemDefault())));

		if (!appointments.isEmpty()) {
			appointments.forEach(calBook -> appointmentCalendars.add(BookingUtils.prepareCalendarData(calBook)));
		}
		return appointmentCalendars;
	}

	@Override
	public List<SchedulerMappingData> findByDate(LocalDateTime currentDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SchedulerMappingData> findAllResource() {
		final List<SchedulerMappingData> resourceCalendars = new LinkedList<>();
		BookingUtils.mappingResourceData(resourceCalendars, resourceRepo.findAllByOrderByTypeDescIdAsc());
		
		return resourceCalendars;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public SchedulerMappingData createScheduler(SchedulerMappingData scheduler) {
		CalendarBook newCalendarBook = BookingUtils.mappingCalendar(scheduler);
		newCalendarBook = calendarBookRepo.save(newCalendarBook);
		LOGGER.info("Generate new Calendar: {}", newCalendarBook.toString());
		
		return BookingUtils.prepareCalendarData(newCalendarBook);
	}
	
	

}
