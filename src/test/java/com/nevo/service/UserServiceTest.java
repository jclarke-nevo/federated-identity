package com.nevo.service;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

import com.nevo.domain.User;
import com.nevo.repository.IUserRepository;
import com.nevo.service.UserService;

public class UserServiceTest {
	
	private UserService objectUnderTest = new UserService();
	
	@Test
	public void findByUsername_should_return_repository_user_with_matching_username() {
		// arrange
		User expectedUser = createTestUser_Alpha();

		IUserRepository repositoryMock = mock(IUserRepository.class);
		when(repositoryMock.findByUsername(expectedUser.getUsername()))
		       .thenReturn(expectedUser);
		
		objectUnderTest.setUserRepository(repositoryMock);
		
		// act
		User result = objectUnderTest.findByUsername(expectedUser.getUsername());
		
		// assert
		Assert.assertEquals("Username values do not match", 
				expectedUser.getUsername(), 
				result.getUsername());
	}
	
	@Test
	public void createUser_should_return_user_with_new_id_and_matching_properties() {
		// arrange
		User expectedUser = createTestUser_Alpha();

		IUserRepository repositoryMock = mock(IUserRepository.class);
		when(repositoryMock.save(expectedUser))
		       .thenReturn(expectedUser);
		
		objectUnderTest.setUserRepository(repositoryMock);

		// act
		User result = objectUnderTest.create(expectedUser);
		
		// assert
		Assert.assertNotNull("id value was not set", 
				result.getId());
		Assert.assertEquals("username values do not match", 
				expectedUser.getUsername(), 
				result.getUsername());
		Assert.assertEquals("password values do not match", 
				expectedUser.getPassword(), 
				result.getPassword());
	}
	
	@Test
	public void createUser_should_result_in_repository_result()
	{
		// arrange
		User expectedUser = createTestUser_Alpha();

		IUserRepository repositoryMock = mock(IUserRepository.class);
		when(repositoryMock.save(expectedUser))
		       .thenReturn(expectedUser);
		when(repositoryMock.findByUsername(expectedUser.getUsername()))
	       .thenReturn(expectedUser);
		
		objectUnderTest.setUserRepository(repositoryMock);

		// act
		User result = objectUnderTest.create(expectedUser);
		result = objectUnderTest.findByUsername(expectedUser.getUsername());
		
		// assert
		Assert.assertNotNull("id value was not set", 
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
