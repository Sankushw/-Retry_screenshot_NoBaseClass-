package generics;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

public class Screenshots {
	public WebDriver driver; 

	// the driver information will be given by selenium test case 
	public Screenshots(WebDriver driver){
		this.driver = driver; 
	}
	public void ScreenShot_Shakedown_US(){

		/* code to capture screenshot */
		//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\test-output\\snaps\\us\\");
	}

	public void ScreenShot_Shakedown_UK(){

		/* code to capture screenshot */
		//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\test-output\\snaps\\uk\\");
	}

	public void ScreenShot_Shakedown_IN(){

		/* code to capture screenshot */
		//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\test-output\\snaps\\in\\");
	}

	 public void TakeSnapOnFailure(String className,String methodName) {
    	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         //The below method will save the screen shot in project location with test class and method name 
    	 try {
    		 //get date and time to be used in name of screenshot
 			Date date=new Date();
 			SimpleDateFormat sdf=new SimpleDateFormat("dd-M-yyyy'T'hh-mm-ss.SSS");
 			String dateNtime=sdf.format(date);

 			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\test-output\\snaps\\"+dateNtime+className+"___"+methodName+".png"));
 			System.out.println("***Placed failed screenshot in \\test-output\\snaps folder with name "+dateNtime+className+"____"+methodName+".png"+" ***");
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
    }
}