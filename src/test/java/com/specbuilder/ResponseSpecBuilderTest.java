package com.specbuilder;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecBuilderTest {
	
	public static ResponseSpecification  responseSpec() {
		ResponseSpecification  responseSpec = new ResponseSpecBuilder()
		.expectStatusCode(200)
		.expectBody("$.size()", equalTo(10))
		.expectBody("id", equalTo(10))
		.build();
		
		return responseSpec;
	}
	
	public void getAllUsers() {
		RestAssured.baseURI = "https://gorest.co.in/";
		
		given()
		  
	}

}
