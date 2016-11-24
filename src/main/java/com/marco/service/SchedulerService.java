/**
 *
 */
package com.marco.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.mail.MessagingException;

import com.marco.data.SchedulerMappingData;

/**
 * @author marco.rotondi
 *
 */
public interface SchedulerService {

	List<SchedulerMappingData> findAllScheduler();

	List<SchedulerMappingData> findAllByRangeDate(LocalDateTime startDate, LocalDateTime endDate);

	List<SchedulerMappingData> findByDate(LocalDateTime currentDate);

	List<SchedulerMappingData> findAllResource();

	SchedulerMappingData createScheduler(SchedulerMappingData scheduler) throws MessagingException;

	void deleteScheduler(String appointnemtID, String checkCode) throws IllegalStateException;

}
