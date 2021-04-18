package BasicGoogleTest;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import generics.Screenshots;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Listeners(generics.TestNG_Listeners.class)
public class GoogleWord_Test
{
	public WebDriver driver;

	//IRetryAnalyzer Interface--used to retry a test again(in script at test level or in xml at suite level)

	//2 ways of running failed test cases:
	//1)IAnnotationTransformer Interface--find failed tests at runtime and execute them again( declared in xml at suite level-it will call IRetryAnalyzer interface internally)
	//2)Retry logic of failed test cases can also be achieved by running 	testng-failed.xml file after test is run.
	//When these are implemented in TestNG.xml file, then run through xml file to apply retry logic

	//2 ways of taking screenshot of failed test case:
	//1--write Screen capture logic in TestNG listener under onTestFailure method.
	//2--Write Screen capture logic in @AfterMethod in this class itself

	@BeforeTest
	public void setup() throws ClassNotFoundException
	{
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +"\\geckodriver.exe");
		FirefoxOptions op=new FirefoxOptions();
		op.setHeadless(false);
		driver=new FirefoxDriver(op);

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(160, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.google.com");
		driver.manage().window().maximize();
	}	


	@Test//(retryAnalyzer=RetryLogic.RetryAnalyzer.class)  //call RetryAnalyzer at test level
	public void T1_SearchWord() throws InterruptedException{
		//Enter text in google search bar

		driver.findElement(By.xpath("//input[@title='Search']")).sendKeys("Wiki");
		Thread.sleep(500);
		//driver.findElement(By.xpath("//input[@value='Google Search']")).click();
		Actions actions=new Actions(driver);
		actions.sendKeys(Keys.ENTER).build().perform();

		System.out.println("In P1 method");



	}



	@Test//(retryAnalyzer=RetryLogic.RetryAnalyzer.class) //call RetryAnalyzer at test level
	public void T2_ClickOnWikiLink() throws InterruptedException{
		//Click on 1st result link
		Assert.assertEquals(true, true);
		driver.findElement(By.xpath("//h3[contains(text(),'Wiki - Wikipedia')]")).click();
		System.out.println("In P2 method");

		//Extra: to print all files/folders present in a directory----can be used to check if files are downloaded or not in runtime.
		File file =new File(System.getProperty("user.dir")+"//src//test//java//generics");
		String []liStDir= file.list();
		System.out.println("number of files/folder present in dir= "+liStDir.length);

		for(int i=0; i<liStDir.length; i++)	{
			//print all files/folders present in given dir
			System.out.println(liStDir[i]);
			//check if specific file/folder is present in given dir
			if(liStDir[i].equals("Screenshots.java")){
				System.out.println("Screenshots.java is present in this dir");
			}
		}

	}


	@Test//(retryAnalyzer=RetryLogic.RetryAnalyzer.class) //call RetryAnalyzer at test level
	public void T3_VerifyTextOnPage() throws InterruptedException{

		Assert.assertEquals(true, false);
		//Click on main page link after that website loads
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'Main page')]")).click();
		System.out.println("In P3 method----->Assert is susscessfully");

	}

	@AfterTest
	public void TakeSnapOnFailure(ITestResult result) throws IOException{

		if(ITestResult.FAILURE==result.getStatus())
		{
			//call screenshot method
			Screenshots sc=new Screenshots(driver);
			sc.TakeSnapOnFailure(result.getTestClass().toString(), result.getName());

			//OR write screenshot logic here only
			File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File DestFile=new File(System.getProperty("user.dir")+"\\test-output\\snaps\\"+result.getTestClass()+"___"+result.getName()+".png");
			FileUtils.copyFile(file, DestFile);
		}

	}			
	/*// Actions Class::Select the Current Address using CTRL + A
    actions.keyDown(Keys.CONTROL);
    actions.sendKeys("a");
    actions.keyUp(Keys.CONTROL);
    actions.build().perform();

    // Copy the Current Address using CTRL + C
    actions.keyDown(Keys.CONTROL);
    actions.sendKeys("c");
    actions.keyUp(Keys.CONTROL);
    actions.build().perform();

    //Press the TAB Key to Switch Focus to Permanent Address
    actions.sendKeys(Keys.TAB);
    actions.build().perform();

    //Paste the Address in the Permanent Address field using CTRL + V
    actions.keyDown(Keys.CONTROL);
    actions.sendKeys("v");
    actions.keyUp(Keys.CONTROL);
    actions.build().perform();*/

	/*to handle hidden
1 To handle hidden webElements:-ElementNotVisibleException
-Store the elements with common attribute in a list using driver.findElements().	
-iterate through list in for loop and inside put if condition-
    int x=element.get(i).getLocation().getX();
	if(x!=0){
	element.get(i).click();
	break;	}

2 to handle ElementNotClickable at a point or ElementNotInteractableException
-Move to the element or scroll using JavascriptExecuter and then click on it.
-Use JavaScript Executer

---differences : explicit wait and fluent wait--
In explicit wait, you can use some set of existing precondition to wait like Wait till element is not visible, wait till element is not clickable, Wait till presence of element located and so on.
In Fluent wait, you can customize the apply method and you can write your own conditions based on your requirement.


--handle tooltip(element is visible only for short time or only till mouse is on some element
Press F8(debugger tab), execution is halted and you can inspect that element.

-----------To set Proxy to access some secure or censored websites:

  Proxy p=new Proxy();
   // Set HTTP Port to 7777(ip address of proxy server in real time)
  p.setHttpProxy("localhost:7777");
  DesiredCapabilities cap=new DesiredCapabilities();
  cap.setCapability(CapabilityType.PROXY, p);
  WebDriver driver=new FirefoxDriver(cap);

-- -------To capture System Date and time
 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
  //get current date time with Date()
 Date date = new Date();
 String date1= dateFormat.format(date);
 System.out.println(date1);

-------IllegalstateException in Selenium Webdriver---->when not specified the path of the driver with the system property.
-------InvalidElementStateException--->When we perform some operation which is not applicable fpr that element: eg sendKeys on button/checkbox etc 
-------WebDriverException---->when the driver is performing the action after immediately closing the browser
-------SessionNotFoundException-->when the driver is performing the action after immediately quitting the browser.
-------StaleElementReferenceException--> The element is no longer attached to the DOMThe user has navigated away to another page.
Solution 1–You can refresh the page and again try for the same element.
Solution 2-Sometimes it takes the time to attach element on Dom so you can retry using for loop and try catch
---------WebDriverException: f.QueryInterface is not a function:
Solution: While passing URL we have to mention HTTP also.









-----------check if dropdown options are sirted or not
-store options in dropdown list using getOptions() of Select class.
-make 2 empty Lists= Original and temp list
-Use for loop and put all dropdown List contents into empty Original and temp lists using--->
 originalList.add(dropdownList.get(i).getText());
 tempList.add(dropdownList.get(i).getText());
-Now apply sort on temp List using---> Collections.sort(tempList);
-Now compare templist with Original list using if clause.



	 */



}