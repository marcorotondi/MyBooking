/**
 * 
 */
package com.marco.utils;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.util.NumberUtils;

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
		return GenericBuilder.of(SchedulerMappingData::new)
				.with(SchedulerMappingData::setId, String.valueOf(calendarBook.getId()))
				.with(SchedulerMappingData::setSubject, calendarBook.getUserRef().getEmail())
				.with(SchedulerMappingData::setDescription, calendarBook.getUserRef().getName())
				.with(SchedulerMappingData::setLocation, calendarBook.getUserRef().getSurname())
				.with(SchedulerMappingData::setCalendar, calendarBook.getResource().getDescription())
				.with(SchedulerMappingData::setStart, calendarBook.getStart()) 
				.with(SchedulerMappingData::setEnd, calendarBook.getEnd()) 
				.build();
	}

	public static void mappingResourceData(final List<SchedulerMappingData> resourceCalendars, final List<Resource> resources) {
		resources.forEach(resource -> {
			resourceCalendars.add(GenericBuilder.of(SchedulerMappingData::new)
					.with(SchedulerMappingData::setId, String.valueOf(resource.getId()))
					.with(SchedulerMappingData::setCalendar, resource.getDescription())
					.build());
		});
	}
	
	public static CalendarBook mappingCalendar(final SchedulerMappingData schedulerData, Resource selectedResource, User user) {
		final String checkCode = generateCheckCode(schedulerData.getSubject(), 
				schedulerData.getCalendar(), 
				schedulerData.getStart(), 
				schedulerData.getEnd());
		final CalendarBook calendarBook = new CalendarBook();
		
		if (null == selectedResource) {
			selectedResource = new Resource();
			selectedResource.setId(NumberUtils.parseNumber(schedulerData.getCalendar(), Long.class));
		}
		
		if (null == user) {
			user = new User();
			user.setName(schedulerData.getDescription());
			user.setSurname(schedulerData.getLocation());
			user.setEmail(schedulerData.getSubject());
			user.setCheckSum(checkCode);
		}
		
		calendarBook.setStart(schedulerData.getStart());
		calendarBook.setEnd(schedulerData.getEnd());
		
		calendarBook.setResource(selectedResource);
		calendarBook.setUserRef(user);
		
		return calendarBook;
	}
	
	public static String generateCheckCode(final String email, final String resourceId, 
			final LocalDateTime start, final LocalDateTime end) {
		final StringBuilder hasCode = new StringBuilder();
		
		hasCode.append((char) email.hashCode())
			.append((char) resourceId.hashCode())
			.append((char) start.hashCode())
			.append((char) end.hashCode());
		
		return hasCode.toString();
	}
}
