package stepDefinitions;
import java.io.IOException;
import io.cucumber.java.*;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeDeletePlace() throws IOException
	{
		StepDefinitions s=new StepDefinitions();
		if (StepDefinitions.placeId== null)
		{
			s.add_place_payload_with("Henry", "52, Hampton Street", "French");
			s.user_calls_api_using_http_method("AddPlace", "POST");
		}
		
	}
	

}
