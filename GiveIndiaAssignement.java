import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class GiveIndiaAssignment {

	public static WebDriver d;
	public static int pdfcounter=0;//pdf counter variable
	public static void step1() {
		d.get("https://en.wikipedia.org/wiki/Selenium");
	}
	public static void step2() {
		List<WebElement> extlinks = d.findElements(By.xpath("/html/body/div[3]/div[3]/div[4]/div/ul[2]/li")); //list of webelements which contains all the external links 
        
        	for (WebElement extlink: extlinks) {
        		if(extlink.isDisplayed() && extlink.isEnabled()) {
        			System.out.println(extlink.getText() + " is a Valid Link");
        			}
        		else{
        		System.out.println(extlink.getText() + "Link is not enabled");
        		}
        }
	}
	public static void step3() {
		WebElement w3 =d.findElement(By.xpath("/html/body/div[3]/div[3]/div[4]/div/div[19]/table/tbody/tr[2]/td/div/table/tbody/tr[3]/td[7]/a/span"));
	       w3.click();	
	       System.out.println("-----Navigating to the Oxygen Page ");
	       if(d.getCurrentUrl().equals("https://en.wikipedia.org/wiki/Oxygen")) {
	    	   System.out.println(d.getTitle() + " is a valid article in the wikipedia");
	       }
	       else {
	    	   System.out.println("There is no Article on Oxygen in Wikipedia");
	       }
		
	}
	public static void step5() {
		List<WebElement> links = d.findElements(By.xpath("/html/body/div[3]/div[3]/div[4]/div/div[17]/ol/li"));//list of webelements which contains reference links
		System.out.println(links.size());
		for(WebElement we: links) {
			if(we.getText().contains("PDF")) {
				pdfcounter=pdfcounter+1;
			}
		}
		System.out.println("Total number of pdf files in the references section are " +pdfcounter);
	}
	public static void step6() {
		d.findElement(By.id("searchInput")).sendKeys("pluto");
	       WebDriverWait wait = new WebDriverWait(d,5);
	       try {
	    	   wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[7]/div/a")));
	           List<WebElement> ll=d.findElements(By.xpath("/html/body/div[7]/div/a"));//list of webelements which contains suggestions list
	           WebElement result = ll.get(1);
	           if(result.getText().equals("Plutonium")) {
	        	   System.out.println("second suggestion is plutonium");
	           }
	           else {
	        	   System.out.println("Second Suggestion is " + result.getText());
	           }
	       }catch(Throwable t){
	    	   System.out.println(t);
	       }
	       
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WebDriverManager.firefoxdriver().setup();
		//System.setProperty("webdriver.gecko.driver","/home/bhargavinkollu/workspace/geckodriver");
          d = new FirefoxDriver();
        
        //d.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
       step1();
       System.out.println("Step 1 Navigated to the URL:" + d.getCurrentUrl());
       System.out.println("-------------STEP2----------");
       step2();
       System.out.println("-------------STEP3----------");
       System.out.println("-------------STEP4----------");
       step3();
       System.out.println("------------- Navigating back to the selenium page----");
       step1();
       System.out.println("--------- STEP 5 Taking the Screenshot of Element Properties--- ");
       WebElement w5 = d.findElement(By.className("infobox"));//step4
       Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(d,w5);
	   ImageIO.write(screenshot.getImage(), "jpg", new File("/home/bhargavinkollu/workspace/element_properties.jpg"));
       //end of step4
	   System.out.println("-------------STEP6----------");
       step5();
       System.out.println("-------------STEP7----------");
       step6();
       
    }
	}

	
