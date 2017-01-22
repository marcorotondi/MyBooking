/**
 *
 */
package com.marco.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.marco.model.CalendarBook;

/**
 * @author Marco
 *
 */
@RunWith(SpringRunner.class)
public class ServiceTests {

	@MockBean
	private CalendarBookRepository calendarBookRepo;

	private final CalendarBook calendarBookOver = new CalendarBook();

	private final CalendarBook calendarBookNoOver = new CalendarBook();

	@Before
	public void SetUp() {
		calendarBookOver.setId(1L);
		calendarBookOver.setStart(LocalDateTime.now());
		calendarBookOver.setEnd(LocalDateTime.now().plusHours(2));
		Mockito.when(calendarBookRepo.existOverlapping(calendarBookOver)).thenReturn(Boolean.TRUE);

		calendarBookNoOver.setId(-1L);
		calendarBookNoOver.setStart(LocalDateTime.now());
		calendarBookNoOver.setEnd(LocalDateTime.now().plusHours(5));
		Mockito.when(calendarBookRepo.existOverlapping(calendarBookNoOver)).thenReturn(Boolean.FALSE);
	}

	@Test
	public void testVerifyCalendarOverlapping() {
		assertEquals(Boolean.TRUE, calendarBookRepo.existOverlapping(calendarBookOver));

		assertEquals(Boolean.FALSE, calendarBookRepo.existOverlapping(calendarBookNoOver));
	}

}
