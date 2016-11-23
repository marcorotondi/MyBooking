/**
 * 
 */
package com.marco.service.impl;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.marco.model.CalendarBook;
import com.marco.service.MailService;

/**
 * @author marco.rotondi
 *
 */
@Scope("prototype")
@Service
public class MailServiceImpl implements MailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine emailTemplateEngine;

	/* (non-Javadoc)
	 * @see com.marco.service.MailService#sendCreationConfirmation(com.marco.model.CalendarBook)
	 */
	@Override
	public void sendCreationConfirmation(final CalendarBook appointment) throws MessagingException  {
		// Prepare the evaluation context
        final Context ctx = new Context(Locale.getDefault());
        ctx.setVariable("name", appointment.getUserRef().getName());
        ctx.setVariable("surname", appointment.getUserRef().getSurname());
        ctx.setVariable("resource", appointment.getResource().getDescription());
        ctx.setVariable("start", appointment.getStart());
        ctx.setVariable("end", appointment.getEnd());
        ctx.setVariable("checkCode",appointment.getCheckSum());

        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setSubject("Appoitment Created");
        message.setFrom("scheduler@marco.com");
        message.setTo(appointment.getUserRef().getEmail());

        // Create the HTML body using Thymeleaf
        final String htmlContent = this.emailTemplateEngine.process("appoitment_created", ctx);
        message.setText(htmlContent, true /* isHtml */);

        // Send email
        this.mailSender.send(mimeMessage);
	}

}
