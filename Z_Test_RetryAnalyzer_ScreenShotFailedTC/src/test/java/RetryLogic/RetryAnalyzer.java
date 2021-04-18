package RetryLogic;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{

	int counter=0;
	int retrylimit=3;
	@Override
	public boolean retry(ITestResult result) {

		if(counter<retrylimit)
		{
			counter++;
			System.out.println("Trying execution again for failed TC ");
			return true;
		}

		return false;
	}
}
