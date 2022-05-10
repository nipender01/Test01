package resources;

import java.util.ArrayList;
import java.util.List;
import pojo.AddPlace;
import pojo.Location;

public class TestData {
	
	public static AddPlace AddPlacePayload(String name,String address,String language)
	{
		AddPlace place = new AddPlace();
		Location l = new Location();
		
		l.setLat(-38.38);
		l.setLng(33.43);
		place.setLocation(l);
		place.setAccuracy(50);
		place.setName(name);
		place.setPhone_number("(+91) 983 893 3947");
		place.setAddress(address);
		place.setWebsite("https://google.com");
		place.setLanguage(language);
		List<String> myList = new ArrayList<String>();
		myList.add("Happy");
		myList.add("test");
		place.setTypes(myList);
		
		return place;
	}
	
	public static String DeletePlacePayload(String placeId)
	{
		return ("{\r\n"
				+ "    \"place_id\":\""+placeId+"\"\r\n"
				+ "}");
	}

}
