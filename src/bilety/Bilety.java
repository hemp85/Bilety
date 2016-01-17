
package bilety;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.sql.rowset.spi.XmlReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Bilety {
static WebDriver driver = new FirefoxDriver();
   
    public static void main(String[] args) throws InterruptedException, IOException {
        
        //WebDriver driver = new FirefoxDriver();
      
        driver.get("http://avia.tutu.ru/");

        
        WebElement element1 = driver.findElement(By.name("city_from"));
       
        element1.sendKeys("Kaliningrad");
        WebElement element2 = driver.findElement(By.name("city_to"));
        
        element2.sendKeys("Moskwa");
        WebElement element3 = driver.findElement(By.name("date_from"));
             
        element3.click();
        element3.sendKeys("01.05.2016");
        WebElement element4 = driver.findElement(By.name("date_back"));
        element4.click();
        //action.moveToElement(element4).click().perform();
        element4.sendKeys("08.05.2016");
        
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        
            WebElement element5 = buttons.get(3);
           // action.moveToElement(element5).click().perform();
            element5.click();
            //Thread.sleep(5000);
          String currentURL = driver.getCurrentUrl();
          //Thread.sleep(10000);
        //System.out.println(currentURL);
          checkIfLoaded();
          
        if((driver.findElement(By.className("b-price_message")).getText())!="Цена за одного пассажира"){
                
        Thread.sleep(2000);
        //System.out.println("po usypiaczu");
        
        }
        //System.out.println("przed pobraniem");
        Thread.sleep(2000);
        
        String javascript = "return arguments[0].innerHTML";
        String pageSource=(String)((JavascriptExecutor)driver).executeScript(javascript, driver.findElement(By.tagName("body")));
       
        
        List<WebElement> lista =driver.findElements(By.className("rm-rzlt_block"));
        for(WebElement x:lista){
             //System.out.println(x.getText());
             File plik = new File("D:/java/sciezka.txt");
            FileWriter druk = new FileWriter(plik,true);
            druk.write(x.getText());
            druk.close();
        }
        
            
    }
    private static void checkIfLoaded(){
        try {
            
        WebElement myDynamicElement = (new WebDriverWait(driver, 5)).until(ExpectedConditions.presenceOfElementLocated(By.className("b-price_message")));
          } catch(TimeoutException t) {
              //System.out.println(t.getMessage());
              checkIfLoaded();
          } finally {
            
            }
    }
    }
    

