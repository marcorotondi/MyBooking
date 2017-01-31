/**
 *
 */
package com.marco.service;

import java.util.List;

import javax.mail.MessagingException;

import com.marco.data.SchedulerMappingData;

/**
 * @author marco.rotondi
 *
 */
public interface SchedulerService {

	List<SchedulerMappingData> findAllScheduler();

	List<SchedulerMappingData> findAllResource();

	SchedulerMappingData createScheduler(SchedulerMappingData scheduler) throws MessagingException, IllegalStateException;

	void deleteScheduler(String appointnemtID, String checkCode) throws IllegalStateException;
}
