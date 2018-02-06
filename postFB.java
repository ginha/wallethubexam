package facebook_login;

import java.awt.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.Keys;

public class postFB {

	public static void main(String[] args) throws InterruptedException{
		
		System.setProperty("webdriver.chrome.driver", "C:/Users/nomad/Documents/selenium files/chromedriver_win32/chromedriver.exe");
		WebDriver driver1 = new ChromeDriver();
		
		//establish starting URL, username, password and string to be posted
        WebDriverWait waitTime = new WebDriverWait(driver1, 15);
        String facebookURL = "https://www.facebook.com/";
        String strUsername = "hal.avisado@vroomvroomvroom.com.au";
        String strPassword = "Pa$$sw0rd";
        String strInputStatus = "Hello World";
        
        //load login page
        driver1.get(facebookURL);
        System.out.println("Facebook script login script initiated. Loading login page.");
        waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginbutton")));
        System.out.println("Facebook login page loaded. Typing in login credentials.");
        
        //enter credentials
        driver1.findElement(By.id("email")).sendKeys(strUsername);
        System.out.println("Username entered. Typing in password.");
        driver1.findElement(By.id("pass")).sendKeys(strPassword);
        System.out.println("Password entered. Logging in.");
        
        //logged in. loading news feed
        driver1.findElement(By.id("loginbutton")).click();
        System.out.println("Login button clicked. Loading news feed.");
        
        //xhpc_message is the id of the textbox where the user types in their status
        //_3ixn is supposedly a chrome issue with facebook. it is shown as a black layer on top of the main page
        //_3ixn needs to be clicked first before the news feed is visible
        waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.className("_3ixn")));
        driver1.findElement(By.className("_3ixn")).click();
        waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.name("xhpc_message")));
        System.out.println("News feed loaded. Selecting status textbox.");
       
        //entering Hello World into the text area, "navigationFocus" is the highlighted status textbox where text will be entered
        driver1.findElement(By.name("xhpc_message")).click();
        System.out.println("Textbox selected. Typing in string Hello World.");
        driver1.findElement(By.className("navigationFocus")).click();
        driver1.findElement(By.className("navigationFocus")).sendKeys(strInputStatus);
        System.out.println("Hello World entered. Posting status.");
       
        //post hello world. for this section, selenium was having difficulty detecting the button using cssSelector, 
        //className and id so i had to use xpath instead 
        waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='_1mf7 _4jy0 _4jy3 _4jy1 _51sy selected _42ft']")));
        driver1.findElement(By.xpath("//button[@class='_1mf7 _4jy0 _4jy3 _4jy1 _51sy selected _42ft']")).click();       
        System.out.println("Next button clicked. Finding timeline filter to click the post button.");
        waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='_1mf7 _4jy0 _4jy3 _4jy1 _51sy selected _42ft']")));
        WebElement element1 = driver1.findElement(By.xpath("//button[@class='_1mf7 _4jy0 _4jy3 _4jy1 _51sy selected _42ft']"));
        JavascriptExecutor executor1 = (JavascriptExecutor)driver1;
        executor1.executeScript("arguments[0].click()", element1);
        System.out.println("Hello World posted. Verifying post.");
       
        //verify hello world. _1dwg is the class name of the most recently posted status in the news feed
        waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.className("userContent")));
        String statusContent = driver1.findElement(By.className("userContent")).getText();
        System.out.println("Posted status content: " +statusContent);
        
        if(statusContent.contentEquals(strInputStatus))
        System.out.println("Hello World verified.");
        	else
        System.out.println("Hello World not detected.");
       
       //will sleep this thread for a few seconds so we can actually see the hello world post before the script terminates. 
       Thread.sleep(3000);
       System.out.println("Terminating script.");
       
       
       driver1.close();
       System.exit(0);


        
    }
    
    
}
