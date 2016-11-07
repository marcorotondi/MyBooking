/**
 * 
 */
package com.marco.utils;

import java.util.List;

import com.marco.data.SchedulerMappingData;
import com.marco.model.CalendarBook;
import com.marco.model.Resource;

/**
 * @author marco.rotondi
 *
 */
public final class BookingUtils {
	
	public static SchedulerMappingData prepareCalendarData(final CalendarBook calendarBook) {
		return null;
		
	}

	public static void fillEmptyCalendar(final List<SchedulerMappingData> resourceCalendars, final List<Resource> resources) {
		resources.forEach(resource -> {
			resourceCalendars.add(GenericBuilder.of(SchedulerMappingData::new)
					.with(SchedulerMappingData::setId, String.valueOf(resource.getId()))
					.with(SchedulerMappingData::setCalendar, resource.getDescription())
					.build());
		});
	}
}
