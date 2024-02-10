package com.api.tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.endpoints.UserEndPoints;
import com.api.endpoints.UserEndPoints2;
import com.api.payload.User;
import com.github.javafaker.Faker;

import io.restassured.response.Response;

public class UserTest2 {
	
	Faker faker;
	User userPayload;
	
	
	@BeforeClass
	public void setUp()
	{
		faker=new Faker();
		userPayload=new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUserName(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
 	    
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		Response response=UserEndPoints2.createUSer(userPayload);
	    response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
	}
	

	@Test(priority=2)
	public void testGetUserByNae()
	{
		Response response=UserEndPoints2.getUSer(this.userPayload.getUserName());
	    response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),404);
	}
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPoints2.updateUSer(this.userPayload.getUserName(),userPayload);
		response.then().log().body();
	
		Assert.assertEquals(response.getStatusCode(),200);
		
		Response responseAfterUpdate=UserEndPoints2.updateUSer(this.userPayload.getUserName(),userPayload);
		response.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
	
	}
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		Response response=UserEndPoints2.deleteUSer(this.userPayload.getUserName());
		response.then().log().all();
	
		Assert.assertEquals(response.getStatusCode(),404);
	}

}
