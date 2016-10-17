/**
 * 
 */
package com.marco.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.marco.service.SchedulerService;

/**
 * @author marco.rotondi
 *
 */
@Service
@Qualifier("schedulerService")
public class SchedulerServiceImpl implements SchedulerService {

}
