
package bilety;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Bilety {

   
    public static void main(String[] args) throws InterruptedException {
      // Create a new instance of the html unit driver
        // Notice that the remainder of the code relies on the interface, 
        // not the implementation.
        WebDriver driver = new FirefoxDriver();
        

        // And now use this to visit Google
        driver.get("http://avia.tutu.ru/");

        // Find the text input element by its name
        WebElement element1 = driver.findElement(By.name("city_from"));
        //Thread.sleep(5000);
        element1.sendKeys("Kaliningrad");
        WebElement element2 = driver.findElement(By.name("city_to"));
        //Thread.sleep(5000);
        element2.sendKeys("Moskwa");
        WebElement element3 = driver.findElement(By.name("date_from"));
               
        element3.click();
        element3.sendKeys("01.05.2016");
        WebElement element4 = driver.findElement(By.name("date_back"));
        element4.click();
        element4.sendKeys("08.05.2016");
        
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        
            WebElement element5 = buttons.get(3);
            element5.click();
            Thread.sleep(5000);
          String currentURL = driver.getCurrentUrl();
          
        System.out.println(currentURL);
        
       // WebElement element4 = driver.findElement(By.name("date_to"));
        /*for(WebElement j:n){
            System.out.println(j.getAttribute("class"));
        }*/
        // Enter something to search for
       // element1.sendKeys("Kaliningrad");
        //Thread.sleep(5000);
      //  element2.sendKeys("Moskwa");
        //Thread.sleep(5000);
        //element4.click();
        //Thread.sleep(5000);
        //element3.click();
        //element3.sendKeys("1.05.2016");
        //Thread.sleep(5000);
        //element4.click();
        //element3.sendKeys("1.05.2015");
        //element4.sendKeys("8.05.2015");
        
        //System.out.println("Page title is: " + driver.getTitle());

      System.out.println(driver.getPageSource());  
    }
    }
    

