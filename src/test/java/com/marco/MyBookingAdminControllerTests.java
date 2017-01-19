/**
 * 
 */
package com.marco;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.marco.controller.AdminController;
import com.marco.model.Resource;
import com.marco.service.ResourceRepository;
import com.marco.type.ResourceType;

/**
 * @author marco.rotondi
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {ControllerConfigTestConfiguration.class})
public class MyBookingAdminControllerTests {
	
	@Autowired
	private AdminController adminController;
	
	@Autowired
	private ResourceRepository resourceRepo;
	
	@Before
	public void setUp(){
		final Resource resource = new Resource();
		final long userDelete = 1L;
		final long userDeleteKO = -1L;
		
		resource.setDescription("This is for Test");
		resource.setType(ResourceType.CAR);
		
		Mockito.when(resourceRepo.save(resource)).thenReturn(resource);
		
		Mockito.doNothing().when(resourceRepo).delete(userDelete);
		
		Mockito.doThrow(new RuntimeException()).when(resourceRepo).delete(userDeleteKO);
	}

	@Test
	public void crudResourceAdminControllerTest() {
	}

	@Test
	public void deleteUserAdminControllerTest() {

	}
}
