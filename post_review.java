package wallethub_1;


import static org.junit.Assert.assertTrue;

import java.util.List;
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
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

public class post_review {

	
	public static void main(String[] args) throws InterruptedException{
		
		 //please change the filepath for chromedriver.exe accordingly
		 //had to use chrome because firefox quantum was having compatibility issues with selenium's firefox driver
		 System.setProperty("webdriver.chrome.driver", "C:/Users/nomad/Documents/selenium files/chromedriver_win32/chromedriver.exe");
		 WebDriver driver1 = new ChromeDriver();
		
		 //wait time for an element to appear is set to 15 seconds
		 WebDriverWait waitTime = new WebDriverWait(driver1, 15);
		 String walletURL = "https://wallethub.com/profile/test_insurance_company/";
		 String strUsername = "halg.p.avisado@gmail.com";
		 String strPassword = "Crimson_1";
		 
		 //please consider that the printlines can indicate different sections of the script. this is for debugging purposes.
		 driver1.get(walletURL);
		 System.out.println("Script initiated. Loading page.");
		 waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));
		 System.out.println("Page loaded. Logging in.");
		 
		 //enter login credentials
		 driver1.findElement(By.className("login")).click();
		 waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.className("touch-element-cl")));
		 System.out.println("Login page loaded. Entering credentials");
		 Thread.sleep(1000);
		 driver1.findElement(By.xpath("/html/body/main/div/form/div[1]/input")).sendKeys(strUsername);
		 driver1.findElement(By.xpath("/html/body/main/div/form/div[2]/input")).sendKeys(strPassword);
		 driver1.findElement(By.className("blue")).click();
		 System.out.println("Login button clicked. Loading main page.");
		 
		 //main page, hover over stars		 
		 waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.className("whbl-link")));
		 System.out.println("Main page loaded. Hovering over stars.");
		 
		 WebElement element1 = driver1.findElement(By.className("rating_5"));
		 Actions hover1 = new Actions(driver1);
		 hover1.moveToElement(element1).perform();
		 //inserting sleep 2000 to see if the stars can be hovered over
		 Thread.sleep(2000);
		 
		 
		 //listing down the contents of 1-5 stars
		 List<WebElement> stars = driver1.findElements(By.className("wh-rating-choices-holder"));
		 System.out.println("Currently hovering over stars.");
		 int count = stars.size();
		 
		 for(int i = 0;i<count;i++)
		 {
			 //elementList will be the list of indices in the list of "stars" previously delcared
			 WebElement elementList = stars.get(i);
			 String rating = elementList.getAttribute("innerHTML");
			 //will be printing out the content of what we're about to see in innerHTML. this is to help me debug 
			 System.out.println("We're on star "+rating);
			 
			 if(rating.contains("<a href=\"#\">4</a>"))
			 {
				 /**in this condition, "4" indicates the 4th star, as stated in the structions. hover2 makes it possible
				 *to hover over the 4th star.
				 */
				 WebElement element2 = driver1.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[3]/div[1]/div/a[4]"));
				 Actions hover2 = new Actions(driver1);
				 hover2.moveToElement(element2).perform();
				 //sleeping this thread so we can see if 4 stars are being hovered over
				 Thread.sleep(2000);
			 }
			 
			 if(rating.contains("<a href=\"#\">5</a>"))
			 {
				 /**rating.contains means if the selected innerHTML contains the mentioned string, proceed with this condition
				 * instructions did not mention that 5 stars should be shown as being hovered over. should they have been?
				 * need clarification for this one. going with the simplest solution for now.
				 */
				 WebElement element3 = driver1.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[3]/div[1]/div/a[5]"));
				 JavascriptExecutor executor1 = (JavascriptExecutor)driver1;
				 executor1.executeScript("arguments[0].click();", element3);
				 System.out.println("5th star clicked. Loading the review page.");
				 break;
			 }
			 		 
		 }
		 
		 //"blue" indicates the submit button's class name
		 waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.className("blue")));
		 
		 //the excessively long div below indicates the Health element in the review dropdown
		 System.out.println("Clicking dropdown to select Health");
		 driver1.findElement(By.className("dropdown-list-new")).click();
		 waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/main/div/div/div[2]/form/div[1]/div/ul/li[2]/a")));
		 driver1.findElement(By.xpath("/html/body/div[2]/main/div/div/div[2]/form/div[1]/div/ul/li[2]/a")).click();
		 
		 //putting a sleep here so the form loads for Health
		  /**
		  *observation: instructions mentioned clicking 5 stars,
		  *implying that the rating should be 5 stars-- but after selecting Heatlh, the rating restarts to 0 stars
		  *the instructions mentioned clicking the submit button, not submitting a 5 star review. need clarification.
		  */
		 Thread.sleep(3000);
		 driver1.findElement(By.xpath("/html/body/div[2]/main/div/div/div[2]/form/div[2]/div/span[1]/a[5]")).click();
		 System.out.println("Health selected. Entering review text of 200 characters.");
		 String strReview = "selenium test review textselenium test revie"
			 		+ "w textselenium test review textselenium test review textselenium testsele"
			 		+ "nium test review textseleenium t"
			 		+ "est review textseleenium test review textseleasdasd";
		 driver1.findElement(By.id("review-content")).click();
		 driver1.findElement(By.id("review-content")).sendKeys(strReview);
		 
		 System.out.println("200 characters entered. Clicking the submit button.");
		 driver1.findElement(By.className("blue")).click();
		 
		 //review posted page loaded. clicking post link
		 waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.className("big-title")));
		 System.out.println("Review confirmed posted. Clicking post link.");
		 driver1.findElement(By.xpath("/html/body/div[2]/main/div/div/div[1]/h1/span/a")).click();
		 
		 //sleeping thread for all of the elements to load. for this account, 15078121 is the indicator of the review posted
		 //we extract text from the distinctive element 'data-uid' with a value of 15078121
		 Thread.sleep(2000);
		 waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.className("reviews")));
		 String reviewpage = driver1.findElement(By.xpath("//div[@data-uid='15078121']")).getText();
		 System.out.println("Review found. Feed entry contains "+ reviewpage);
		 
		 //assertTrue for both reviewpage and strReview
		 //checking to see if reviewpage cotains the string declared in strReview
		 assertTrue(reviewpage.contains(strReview));
			 
		 System.out.println("If the assertion is not successful, there will be an error "
		 		+ "in the console otherwise the script has successfully been completed. Terminating script.");
		 
		 
		 driver1.close();
	     System.exit(0);
		
	}
	
}
