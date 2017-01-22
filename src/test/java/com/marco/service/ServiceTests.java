/**
 *
 */
package com.marco.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.marco.configuration.MyBookingTestConfiguration;
import com.marco.model.CalendarBook;

/**
 * @author Marco
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {MyBookingTestConfiguration.class})
public class ServiceTests {

	@Autowired
	private CalendarBookRepository calendarBookRepo;

	@Before
	public void SetUp() {
		final CalendarBook calendarBookOver = new CalendarBook();
		calendarBookOver.setId(1L);
		Mockito.when(calendarBookRepo.existOverlapping(calendarBookOver)).thenReturn(Boolean.TRUE);

		final CalendarBook calendarBookNoOver = new CalendarBook();
		calendarBookNoOver.setId(-1L);
		Mockito.when(calendarBookRepo.existOverlapping(calendarBookNoOver)).thenReturn(Boolean.FALSE);
	}

	@Test
	public void testVerifyCalendarOverlapping() {
		final CalendarBook booking = new CalendarBook();
		booking.setId(1L);

		assertEquals(Boolean.TRUE, calendarBookRepo.existOverlapping(booking));

		booking.setId(-1L);
		assertEquals(Boolean.FALSE, calendarBookRepo.existOverlapping(booking));
	}

}
