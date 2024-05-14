import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAPIWithoutBDD {

	@Test
	public void getAllUsers() {

		RestAssured.baseURI = "https://gorest.co.in/";
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer b1deb003822d56cf57b72d9acf37c585663330409ca61bdc6a33419541edadb4");
		Response resp = request.get("public/v2/users/");

		int statusCode = resp.statusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200);

		String statusLine = resp.statusLine();
		System.out.println(statusLine);
		String statusLineValues[] = statusLine.split(" ");
		String statusMessage = statusLineValues[2];
		System.out.println(statusMessage);
		Assert.assertEquals(statusMessage, "OK");

		String contentType = resp.header("Content-Type");
		System.out.println(contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");

		System.out.println("---------------");
		List<Header> headers = resp.headers().asList();
		for (Header h : headers) {
			System.out.println(h);
		}

		System.out.println("Total Headers: " + headers.size());

		System.out.println("---------------");
		String responseBody = resp.prettyPrint();
		System.out.println(responseBody);
	}

	@Test
	public void getUserWithQueryParm() {

		RestAssured.baseURI = "https://gorest.co.in/";
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer b1deb003822d56cf57b72d9acf37c585663330409ca61bdc6a33419541edadb4");
		request.queryParam("name", "Deeptendu");
		request.queryParam("status", "active");
		Response resp = request.get("public/v2/users/");

		System.out.println(resp.prettyPrint());
	}

	@Test
	public void getUserWithQueryParms() {

		RestAssured.baseURI = "https://gorest.co.in/";
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer b1deb003822d56cf57b72d9acf37c585663330409ca61bdc6a33419541edadb4");

		Map<String, String> queryParms = new HashMap<String, String>();
		queryParms.put("name", "Deeptendu");
		queryParms.put("status", "active");
		queryParms.put("gender", "male");

		request.queryParams(queryParms);

		Response resp = request.get("public/v2/users/");

		System.out.println(resp.prettyPrint());
	}

}
