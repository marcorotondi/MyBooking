/**
 *
 */
package com.marco.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import javax.mail.MessagingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.marco.data.SchedulerMappingData;
import com.marco.service.MailService;
import com.marco.service.ResourceRepository;
import com.marco.service.SchedulerService;
import com.marco.service.UserRepository;
import com.marco.utils.TestUtils;

/**
 * @author marco.rotondi
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = SchedulerController.class)
public class SchedulerControllerTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private SchedulerService schedulerService;
	
	@MockBean
	private ResourceRepository resourceRepo;
	
	@MockBean
	private UserRepository userRepo;
	
	@MockBean
	private MailService mailService;
	
	private SchedulerMappingData scheduler;

	@Before
	public void setUp() throws IllegalStateException, MessagingException {
		scheduler = new SchedulerMappingData();
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		Mockito.when(schedulerService.createScheduler(scheduler)).then(new Answer<Object>() {

			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				SchedulerMappingData result = invocation.getArgumentAt(0,  SchedulerMappingData.class);
				result.setId("AAAA");
				return result;
			}
		});
	}

	@Test
	public void testCreateScheduler() throws Exception {
		mockMvc.perform(post("/public/api//appointment/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtils.asJsonString(scheduler)))
		.andDo(print())
		.andExpect(status().is4xxClientError());
		
		scheduler.setCalendar("5");
		scheduler.setDescription("username");
		scheduler.setDraggable(Boolean.FALSE);
		scheduler.setLocation("Unit Test");
		scheduler.setResizable(Boolean.FALSE);
		scheduler.setSubject("Test-Test");
		scheduler.setStart(LocalDateTime.now());
		scheduler.setEnd(LocalDateTime.now().plusHours(3));
		
		mockMvc.perform(post("/public/api//appointment/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtils.asJsonString(scheduler)))
		.andDo(print())
		.andExpect(status().isOk());
	}

	@Test
	public void testDeleteScheduler() {

	}

}
