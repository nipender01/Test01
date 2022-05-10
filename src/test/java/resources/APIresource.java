package resources;

//Enum is special class which have collection of constants and methods

public enum APIresource {	

	
	AddPlace("/maps/api/place/add/json"),
	GetPlace("/maps/api/place/get/json"),
	DeletePlace("/maps/api/place/delete/json");
	private String res;
	
	APIresource(String resource)
	{
		this.res = resource;
	}
	
	public String getResource()
	{
		return res;
	}
	

}
