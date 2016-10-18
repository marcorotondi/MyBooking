/**
 *
 */
package com.marco.service;

import java.time.LocalDateTime;
import java.util.List;

import com.marco.data.CalendarData;

/**
 * @author marco.rotondi
 *
 */
public interface SchedulerService {

	List<CalendarData> findAllScheduler();

	List<CalendarData> findAllByRangeDate(LocalDateTime startDate, LocalDateTime endDate);

	List<CalendarData> findByDate(LocalDateTime currentDate);

}
