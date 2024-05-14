import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetAPIWithBDD {

	@Test
	public void getAllProducts() {
		given().log().all()
			.when().log().all()
				.get("https://simple-grocery-store-api.glitch.me/products")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.and()
									.body("$.size()", equalTo(20));
	}
	
	@Test
	public void getAllUsers() {
		RestAssured.baseURI = "https://gorest.co.in/";
		
		given().log().all()
			.header("Authorization","Bearer b1deb003822d56cf57b72d9acf37c585663330409ca61bdc6a33419541edadb4")
				.when().log().all()
					.get("public/v2/users/")
						.then()
							.assertThat()
								.statusCode(200)
									.and()
										.header("Connection", "keep-alive")
											.and()
												.header("Content-Type", "application/json; charset=utf-8")
													.and()
														.body("email", hasItem("arora_ganaka@ritchie.example"));
	}
	
	@Test
	public void getAllUsersWithQueryParm() {
		RestAssured.baseURI = "https://gorest.co.in/";
		
		given().log().all()
			.header("Authorization","Bearer b1deb003822d56cf57b72d9acf37c585663330409ca61bdc6a33419541edadb4")
				.queryParam("name", "Deeptendu")
					.when().log().all()
						.get("public/v2/users/")
							.then().log().all()
								.statusCode(200)
									.and()
										.body("$.size()", equalTo(3));
		}
	
	@Test
	public void getAllUsersWithQueryParms() {
		RestAssured.baseURI = "https://gorest.co.in/";
		
		given().log().all()
			.header("Authorization","Bearer b1deb003822d56cf57b72d9acf37c585663330409ca61bdc6a33419541edadb4")
				.queryParam("name", "Deeptendu")
				.queryParam("status", "active")
					.when().log().all()
						.get("public/v2/users/")
							.then().log().all()
								.statusCode(200)
									.and()
										.body("$.size()", equalTo(2))
											.contentType("application/json; charset=utf-8");
}
	@Test
	public void getAllUsersExtractBody() {
		RestAssured.baseURI = "https://gorest.co.in/";
		
		Response response = given().log().all()
			.header("Authorization","Bearer b1deb003822d56cf57b72d9acf37c585663330409ca61bdc6a33419541edadb4")
				.queryParam("name", "Deeptendu")
					.when()
						.get("public/v2/users/");
		JsonPath js = response.jsonPath();
		
		int secondRecordId = js.getInt("id[1]");
		System.out.println(secondRecordId);
		
		String secondRecordName = js.getString("name[1]");
		System.out.println(secondRecordName);
		
	}	
	
	@Test
	public void getAllUsersExtractBodyJSONArray() {
		RestAssured.baseURI = "https://gorest.co.in/";
		
		Response response = given().log().all()
			.header("Authorization","Bearer b1deb003822d56cf57b72d9acf37c585663330409ca61bdc6a33419541edadb4")
				.queryParam("name", "Deeptendu")
					.when()
						.get("public/v2/users/");
		JsonPath js = response.jsonPath();
		
		List<Integer> userIds = js.getList("id");
		List<String> userNames = js.getList("name");
		System.out.println(userIds);
		System.out.println(userNames);
		
		for(int i=0; i<userIds.size();i++) {
			int id = userIds.get(i);
			String name = userNames.get(i);
			
			System.out.println("Id: "+id+" "+"Name: "+name);
		}
	}
	
	@Test
	public void getAllUsersExtractBodyWithExtractMethod() {
		RestAssured.baseURI = "https://gorest.co.in/";
		
		Response response = given().log().all()
			.header("Authorization","Bearer b1deb003822d56cf57b72d9acf37c585663330409ca61bdc6a33419541edadb4")
					.when()
						.get("public/v2/users/5816513")
							.then()
								.extract()
									.response();
		
		int userId = response.path("id");
		System.out.println(userId);
}
}