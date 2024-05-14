package com.specbuilder;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilderTest {
	
	public static RequestSpecification getUsersRequestSpec() {
		RequestSpecification request_spec =	new RequestSpecBuilder()
		.setBaseUri("https://gorest.co.in/")
		.addHeader("Authorization","Bearer b1deb003822d56cf57b72d9acf37c585663330409ca61bdc6a33419541edadb4")
		.build();
		
		return request_spec;
	}
	
	@Test
	public void getAllUsers() {
		
		given().log().all()
			.spec(getUsersRequestSpec())
				.when().log().all()
					.get("public/v2/users/")
						.then().log().all()
							.assertThat()
								.statusCode(200)
									.body("$.size()", equalTo(10));
		
	}

}
