package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import io.restassured.builder.*;
import io.restassured.filter.log.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.*;

public class Utils {
	
	public static RequestSpecification req;
	public static ResponseSpecification res;	
	
	public RequestSpecification requestSpecification() throws IOException
	{
		if (req==null)
		{
		PrintStream ps = new PrintStream(new FileOutputStream("log.txt"));
		req = new RequestSpecBuilder().setBaseUri(getGlobalProperty("baseURL")).setContentType(ContentType.JSON)
				.addQueryParam("key","qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(ps))
				.addFilter(ResponseLoggingFilter.logResponseTo(ps))
				.build();
		}
		return req;
	}
	
	public ResponseSpecification responseSpecification()
	{
		res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		return res;
	}
	
	public static String getGlobalProperty(String b) throws IOException
	{
		Properties p = new Properties();
		FileInputStream f = new FileInputStream("C:\\Users\\nipander.yadav\\eclipse-workspace\\Maps_Automation\\src\\test\\java\\resources\\global.properties");
		p.load(f);
		//return(p.getProperty("baseURL"));      //actual parameter in properties file should be in ""
		return(p.getProperty(b));                //property required in call is returned
	}
	
	public String getJsonPath(Response res, String key)
	{
		String resp=res.asString();
		JsonPath js = new JsonPath(resp);
		return js.getString(key);
	}

}
