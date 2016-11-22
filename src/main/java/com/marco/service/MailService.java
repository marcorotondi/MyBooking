/**
 * 
 */
package com.marco.service;

import javax.mail.MessagingException;

import com.marco.model.CalendarBook;

/**
 * @author marco.rotondi
 *
 */
public interface MailService {
	
	void sendCreationConfirmation(CalendarBook appoitment) throws MessagingException;
}
