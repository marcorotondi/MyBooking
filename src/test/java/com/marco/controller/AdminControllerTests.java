/**
 *
 */
package com.marco.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.marco.MybookingApplicationTests;
import com.marco.model.Resource;
import com.marco.service.ResourceRepository;
import com.marco.type.ResourceType;
import com.marco.utils.TestUtils;

/**
 * @author marco.rotondi
 *
 */
public class AdminControllerTests extends MybookingApplicationTests {

	@Autowired
	private AdminController adminController;

	@Autowired
	private ResourceRepository resourceRepo;

	@Before
	public void setUp(){
		final Resource resource = new Resource();
		final long resourceToDelete = 1L;
		final long resourceKoDelete = -1L;

		resource.setId(999L);
		resource.setDescription("This is for Test");
		resource.setType(ResourceType.CAR);

		Mockito.when(resourceRepo.save(resource)).thenReturn(new Resource());

		Mockito.when(resourceRepo.findOne(resourceToDelete)).thenReturn(new Resource());

		Mockito.doNothing().when(resourceRepo).delete(resourceToDelete);

		Mockito.doThrow(new RuntimeException()).when(resourceRepo).delete(resourceKoDelete);
	}

	@Test
	public void testCrudAdminController() throws Exception {
		final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
		final Resource resource = new Resource();
		resource.setId(999L);
		resource.setDescription("This is for Test");
		resource.setType(ResourceType.CAR);

		mockMvc.perform(post("/admin/api/crudResource")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtils.asJsonString(resource)))
		.andDo(print())
		.andExpect(status().isOk());
	}

	@Test
	public void testDeleteUserController() throws Exception {
		MockMvc mokMvc = MockMvcBuilders.standaloneSetup(adminController).build();
		mokMvc.perform(delete("/admin/api/delete/resource/1"))
		.andDo(print())
		.andExpect(status().is2xxSuccessful());

		mokMvc.perform(delete("/admin/api/delete/resource/-1"))
		.andDo(print())
		.andExpect(status().is4xxClientError());
	}
}