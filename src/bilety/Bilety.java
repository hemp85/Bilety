
package bilety;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        System.out.println("po usypiaczu");
        
        }
        System.out.println("przed pobraniem");
        driver.getPageSource();
        
        
        //System.out.println(driver.getPageSource());
        File plik = new File("D:/java/sciezka.xml");
        FileWriter druk = new FileWriter(plik,false);
        druk.write(driver.getPageSource());
        druk.close();
        Document doc = Jsoup.parse(plik,"UTF-8");
        Elements elem = doc.select(".b-rzlt").not(".b-rzlt.m-train_card.s-hidden").not("div[class=rzlt_way_total i-clock_small i-clock_small]");
        //Elements elem = doc.select(".b-rzlt.rzlt_way_tim");
        System.out.println(elem.toString());
        
        File plik2 = new File("D:/java/sciezka1.xml");
        FileWriter druk2 = new FileWriter(plik2,false);
        druk2.write("<table><tbody>"+elem.toString()+"</tbody></table>");
        druk2.close();
    }
    private static void checkIfLoaded(){
        try {
            
        WebElement myDynamicElement = (new WebDriverWait(driver, 5)).until(ExpectedConditions.presenceOfElementLocated(By.className("b-price_message")));
          } catch(TimeoutException t) {
              //System.out.println(t.getMessage());
              checkIfLoaded();
          } finally {
            //System.out.println("Koniec czekania");
            }
    }
    }
    

