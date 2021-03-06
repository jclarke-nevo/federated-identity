package com.nevo.controller;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.nevo.domain.User;
import com.nevo.service.IUserService;
import com.nevo.viewModel.UserVm;

public class UserApiControllerTest {
	private UserApiController objectUnderTest = new UserApiController();
	
	@Test
	public void getUser_should_return_viewModel_matching_service_result(){
		// arrange
		User expectedUser = createTestUser_Alpha();

		IUserService serviceMock = Mockito.mock(IUserService.class);
		Mockito.when(serviceMock.findByUsername(expectedUser.getUsername()))
	       .thenReturn(expectedUser);
		
		objectUnderTest.setUserService(serviceMock);
		
		// act
		UserVm result = objectUnderTest.getUser(expectedUser.getUsername());
		
		// assert
		Assert.assertEquals("id values do not match", 
				expectedUser.getId(), 
				result.getId());
		Assert.assertEquals("username values do not match", 
				expectedUser.getUsername(), 
				result.getUsername());
		Assert.assertEquals("password values do not match", 
				expectedUser.getPassword(), 
				result.getPassword());		
	}
	
	
	private User createTestUser_Alpha(){
		User result = new User();
		result.setId(UUID.randomUUID().toString());
		result.setUsername("TestUserAlpha");
		result.setPassword("TestUserAlpha_Password");
		
		return result;
	}
}
