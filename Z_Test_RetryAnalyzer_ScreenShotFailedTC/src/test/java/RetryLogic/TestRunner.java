package RetryLogic;


import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class TestRunner {
// running the testng-failed.xml file through below code instead of manually right clicking on it and run.
	public static void main(String[] args) {
		TestNG ng=new TestNG();
		List<String> ls=new ArrayList<String>();
		ls.add(System.getProperty("user.dir")+"\\test-output\\testng-failed.xml");
		ng.setTestSuites(ls);
		ng.run();
	}

}
