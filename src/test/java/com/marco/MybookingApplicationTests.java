package com.marco;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.marco.utils.BookingUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybookingApplicationTests {

	@Test
	public void resourceRepositoryTest() {

	}

	@Test
	public void verifyCalendarOverlapping() {

	}

	@Test
	public void createSchedulerTest() {

	}

	@Test
	public void deleteSchedulerTest() {

	}

	@Test
	public void verifyGenerationCheckCode() {
		String checkCode = BookingUtils.generateCheckCode();

		assertNotEquals("", checkCode);
	}

}
