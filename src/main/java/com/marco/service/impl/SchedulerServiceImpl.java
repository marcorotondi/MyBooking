/**
 *
 */
package com.marco.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.marco.data.CalendarData;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CalendarData> findByDate(LocalDateTime currentDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
