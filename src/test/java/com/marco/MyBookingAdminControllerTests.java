/**
 * 
 */
package com.marco;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.marco.controller.AdminController;
import com.marco.model.Resource;
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
	
	@Before
	public void setUp(){
		final Resource resource = new Resource();
		final long userDelete = 1L;
		final long userDeleteKO = -1L;
		
		resource.setDescription("This is for Test");
		resource.setType(ResourceType.CAR);
		
		Mockito.when(adminController.crudResource(resource)).thenReturn(
				new ResponseEntity<Void>(OK));
		
		Mockito.when(adminController.deleteUser(userDelete)).thenReturn(
				new ResponseEntity<Resource>(NO_CONTENT));
		
		Mockito.when(adminController.deleteUser(userDeleteKO)).thenReturn(
				new ResponseEntity<Resource>(NOT_FOUND));
	}

	@Test
	public void crudResourceAdminControllerTest() {
	}

	@Test
	public void deleteUserAdminControllerTest() {

	}
}
