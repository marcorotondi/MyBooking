/**
 *
 */
package com.marco.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.marco.MybookingApplicationTests;
import com.marco.model.CalendarBook;

/**
 * @author Marco
 *
 */
public class ServiceTests extends MybookingApplicationTests {

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
