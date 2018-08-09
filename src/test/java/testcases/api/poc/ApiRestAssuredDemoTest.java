package testcases.api.poc;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiRestAssuredDemoTest {
	String searchCity = "Chicago";

	public ApiRestAssuredDemoTest() {
		super();
	}

	@Test
	public void GetWeatherDetails() {
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();

		// Make a request to the server by specifying the method Type and the method
		// URL.
		// This will return the Response from the server. Store the response in a
		// variable.
		Response response = httpRequest.request(Method.GET, "/" + searchCity);

		// Now let us print the body of the message to see what response
		// we have received from the server
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);

		// Get the status code from the Response. In case of
		// a successful interaction with the web service, we
		// should get a status code of 200.
		int statusCode = response.getStatusCode();

		// Assert that correct status code is returned.
		Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */, "Correct status code returned");

		// Get the status line from the Response and store it in a variable called
		// statusLine
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine /* actual value */, "HTTP/1.1 200 OK" /* expected value */,
				"Correct status code returned");

	}

	@Test
	public void GetWeatherHeaders1() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/" + searchCity);

		// Reader header of a give name. In this line we will get
		// Header named Content-Type
		String contentType = response.header("Content-Type");
		System.out.println("Content-Type value: " + contentType);

		// Reader header of a give name. In this line we will get
		// Header named Server
		String serverType = response.header("Server");
		System.out.println("Server value: " + serverType);

		// Reader header of a give name. In this line we will get
		// Header named Content-Encoding
		String acceptLanguage = response.header("Content-Encoding");
		System.out.println("Content-Encoding: " + acceptLanguage);
	}

	@Test
	public void IteratingOverHeaders() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/" + searchCity);

		// Get all the headers. Return value is of type Headers.
		// Headers class implements Iterable interface, hence we
		// can apply an advance for loop to go through all Headers
		// as shown in the code below
		Headers allHeaders = response.headers();

		// Iterate over all the Headers
		for (Header header : allHeaders) {
			System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
		}
	}

	@Test
	public void GetWeatherHeaders2() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/" + searchCity);

		// Reader header of a give name. In this line we will get
		// Header named Content-Type
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType /* actual value */, "application/json" /* expected value */);

		// Reader header of a give name. In this line we will get
		// Header named Server
		String serverType = response.header("Server");
		Assert.assertEquals(serverType /* actual value */, "nginx/1.14.0" /* expected value */);

		// Reader header of a give name. In this line we will get
		// Header named Content-Encoding
		String contentEncoding = response.header("Content-Encoding");
		Assert.assertEquals(contentEncoding /* actual value */, "gzip" /* expected value */);
	}
}
