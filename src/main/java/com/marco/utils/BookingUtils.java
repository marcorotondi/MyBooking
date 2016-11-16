/**
 * 
 */
package com.marco.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import com.marco.data.SchedulerMappingData;
import com.marco.model.CalendarBook;
import com.marco.model.Resource;
import com.marco.model.User;

/**
 * @author marco.rotondi
 *
 */
public final class BookingUtils {
	
	public static SchedulerMappingData prepareCalendarData(final CalendarBook calendarBook) {
		return null;
	}

	public static void mappingResourceData(final List<SchedulerMappingData> resourceCalendars, final List<Resource> resources) {
		resources.forEach(resource -> {
			resourceCalendars.add(GenericBuilder.of(SchedulerMappingData::new)
					.with(SchedulerMappingData::setId, String.valueOf(resource.getId()))
					.with(SchedulerMappingData::setCalendar, resource.getDescription())
					.build());
		});
	}
	
	public static CalendarBook mappingCalendar(final SchedulerMappingData schedulerData) {
		final CalendarBook calendarBook = new CalendarBook();
		final Resource resource = new Resource();
		final User user = new User();
		
		calendarBook.setStart(schedulerData.getStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		calendarBook.setEnd(schedulerData.getEnd().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		
		resource.setId(Long.valueOf(schedulerData.getCalendar()));
		
		user.setName(schedulerData.getDescription());
		user.setSurname(schedulerData.getLocation());
		user.setEmail(schedulerData.getSubject());
		user.setCheckSum(UUID.randomUUID().toString());
		
		calendarBook.setResource(resource);
		calendarBook.setUserRef(user);
		
		return calendarBook;
	}
}
