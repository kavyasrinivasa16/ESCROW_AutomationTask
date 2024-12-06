package Amazon_TestCase;

import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import junit.framework.Assert;

public class Amazon_Test {
	@Test
	public void Amazon_TC_01()
	{
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	
	driver.get("https://www.amazon.in");
	
	//In the search bar, enter: "Wrist Watches" and search.
	driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("Wrist Watches");
	driver.findElement(By.xpath("//input[@value='Go']")).click();
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,200)");
	
	//Filter the results by selecting "Leather" under Watch Band Material.
	driver.findElement(By.xpath("//span[text()='Leather']")).click();
	
	//Select "Fastrack" as the watch brand.
	driver.findElement(By.xpath("//span[text()='Brands']/ancestor::div[@id='brandsRefinements']/descendant::span[text()='Fastrack']")).click();
	
	js.executeScript("window.scrollBy(0,5000)");
	
	//Navigate to the 2nd page of the search results
	driver.findElement(By.xpath("//a[text()='Next']")).click();
	
	//From the 1st product in the 1st row, add the item to your cart.
    driver.findElement(By.xpath("(//div[@class='a-section a-spacing-base a-text-center'])[1]")).click();
    
    String pa = driver.getWindowHandle();
    Set<String> child = driver.getWindowHandles();
    child.remove(pa);
    for(String lkm:child)
    {
    	driver.switchTo().window(lkm);
    }
    
	driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
	
	//Assertion
	String ActualResult = driver.findElement(By.xpath("//h1[@class='a-size-medium-plus a-color-base sw-atc-text a-text-bold']")).getText();
	Assert.assertEquals("Added to cart", ActualResult);
	
	//Close browser
	driver.quit();
	}
}
