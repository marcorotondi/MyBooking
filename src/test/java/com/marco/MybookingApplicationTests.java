package com.marco;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.marco.model.CalendarBook;
import com.marco.service.CalendarBookRepository;
import com.marco.utils.BookingUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybookingApplicationTests {
	
	@Autowired
	private CalendarBookRepository calendarRepo;

	@Test
	public void CheckCode() {
		final List<CalendarBook> cbs = calendarRepo.findAll();
		assertNotNull(cbs);
		
		final CalendarBook cb = cbs.get(0);
		String checkCode = BookingUtils.generateCheckCode(cb.getUserRef().getEmail(), 
				String.valueOf(cb.getResource().getId()), 
				cb.getStart(), 
				cb.getEnd());
		
		assertNotEquals("", checkCode);	
	}

}
