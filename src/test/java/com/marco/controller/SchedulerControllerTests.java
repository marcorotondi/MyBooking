/**
 *
 */
package com.marco.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import javax.mail.MessagingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.marco.data.SchedulerMappingData;
import com.marco.service.SchedulerService;
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

	@Before
	public void setUp() throws IllegalStateException, MessagingException {
		final SchedulerMappingData scheduler = new SchedulerMappingData();
		scheduler.setId("999");
		scheduler.setCalendar("5");
		scheduler.setDescription("username");
		scheduler.setDraggable(Boolean.FALSE);
		scheduler.setLocation("Unit Test");
		scheduler.setResizable(Boolean.FALSE);
		scheduler.setSubject("Test-Test");
		scheduler.setStart(LocalDateTime.now());
		scheduler.setEnd(LocalDateTime.now().plusHours(3));

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		Mockito.when(schedulerService.createScheduler(any(SchedulerMappingData.class))).thenReturn(scheduler);
	}

	@Test
	public void testCreateScheduler() throws Exception {
		mockMvc.perform(post("/public/api//appointment/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtils.asJsonString(new SchedulerMappingData())))
		.andDo(print())
		.andExpect(status().is4xxClientError());

		final SchedulerMappingData scheduler = new SchedulerMappingData();
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
		.andExpect(status().is2xxSuccessful())
		.andExpect(jsonPath("$.id", is("999")))
		.andExpect(jsonPath("$.subject", is("Test-Test")));
	}

	@Test
	public void testDeleteScheduler() {

	}

}
