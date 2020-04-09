package Hospital.HealthManagement;

import org.testng.annotations.DataProvider;

public class TestData {

	@DataProvider(name="inputdata")
	  public Object[][] getdataforeditfield()
	  {
		  Object[][] obj = new Object[][]
				  {
			  {"Komal"},{"1234@#$"}
			  
				  };
				  
				  return obj;
	  }
}
