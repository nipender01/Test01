package stepDefinitions;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
import java.io.IOException;
import resources.APIresource;
import resources.TestData;
import resources.Utils;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.*;

public class StepDefinitions extends Utils{

	RequestSpecification req;
	ResponseSpecification res1;
	Response res;
	static String placeId;
	
	@Given("AddPlace payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String address, String language) throws IOException
		{			
			req = given().spec(requestSpecification()).body(TestData.AddPlacePayload(name,address,language));			
		}
		
	@When("User calls {string} API using {string} http method")
	public void user_calls_api_using_http_method(String apiName, String httpMethod) 
		{
			APIresource ress=APIresource.valueOf(apiName);
			String resource=ress.getResource();
			
			System.out.println(resource);
			
			if(httpMethod.equalsIgnoreCase("POST"))
			{
			   res= req.when().post(resource).then().spec(responseSpecification()).extract().response();
			   placeId=getJsonPath(res,"place_id");
			}
			else if(httpMethod.equalsIgnoreCase("GET"))
				res= req.when().get(resource).then().spec(responseSpecification()).extract().response();
			else if(httpMethod.equalsIgnoreCase("DELETE"))
				res= req.when().delete(resource).then().spec(responseSpecification()).extract().response();
			
		}
		
		@Then("Request is successfully executed with statusCode {int}")
		public void request_is_successfully_executed_with_status_code(int statuscd) 
		{
			assertEquals(res.getStatusCode(),statuscd);
		}
		
		@Then("{string} is returned in response body as {string}")
		public void is_returned_in_response_body_as(String key, String value) 
		{
		    String actualValue=getJsonPath(res,key);
			assertEquals(actualValue,value);
		    
		}
		
		@Then("verify if the place added with {string} using {string} API")
		public void verify_if_the_place_added_with_using_api(String name, String apiName) throws IOException {
						
			req = given().spec(requestSpecification()).queryParam("place_id",placeId);
			user_calls_api_using_http_method(apiName, "GET");	
			String actualName=getJsonPath(res,"name");
			assertEquals(actualName,name);
		}
		
		@Given("DeletePlace payload")
		public void delete_place_payload() throws IOException {
		    req=given().spec(requestSpecification()).body(TestData.DeletePlacePayload(placeId));
		}

}
