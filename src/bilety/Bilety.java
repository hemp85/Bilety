
package bilety;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class Bilety {
static WebDriver driver = new FirefoxDriver();
   
    public static void main(String[] args) throws InterruptedException, IOException {
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
        }
        Thread.sleep(2000);
        
        String javascript = "return arguments[0].innerHTML";
        String pageSource=(String)((JavascriptExecutor)driver).executeScript(javascript, driver.findElement(By.tagName("body")));
             
        List<WebElement> lista =driver.findElements(By.className("rm-rzlt_block"));
        int a=lista.size();
        //test przesiadek
        /*List<WebElement> test1 =lista.get(15).findElements(By.tagName("span"));
       System.out.println(test1.get(4).getText());
       System.out.println(test1.get(5).getText());
       System.out.println(test1.get(7).getText());
       System.out.println(test1.get(8).getText());*/
     
      for (int i=0;i<a;i++){
        String przes1="";
        String przes2="";
        List<WebElement> test1 =lista.get(i).findElements(By.tagName("span"));
        int sp=0;
        CharSequence cs="пересадка в";
        for(WebElement x:test1){
            if (x.getText().contains(cs)==false){
                sp++;
            } else if (sp<14){
                przes1=test1.get(sp).getText();
                sp++;
               
            } else if (sp>=14) {
                przes2=test1.get(sp).getText();
                
            } 
        }
         
        System.out.println("Opcja nr: "+(i+1));
        String alt = lista.get(i).findElement(By.tagName("img")).getAttribute("alt");
        System.out.println(alt);
        String skad = (lista.get(i).findElements(By.className("rzlt_way_to")).get(0).getText())+" "+przes1;
        System.out.println(skad);
        String hour1 = lista.get(i).findElements(By.className("rzlt_way_time")).get(0).getText();
        System.out.println(hour1);
        String dokad = (lista.get(i).findElements(By.className("rzlt_way_to")).get(1).getText())+" "+przes2;
        System.out.println(dokad);
        String  hour2= lista.get(i).findElements(By.className("rzlt_way_time")).get(1).getText();
        System.out.println(hour2);
        String cena= lista.get(i).findElement(By.className("buttonext_digits")).getText();
        System.out.println(cena);
        System.out.println();
        
       } //koniec petli for
      
       /* for(int j =0;j<lista.size();j++){
        String first = lista.get(j).getText();
        InputStream is = new ByteArrayInputStream(first.getBytes());
            BufferedReader bf = new BufferedReader(new InputStreamReader(is));
            Stream<String> lines = bf.lines();
            Object[] lin = lines.toArray();
            
            int i = 0;
            for(Object x:lin){
                
            System.out.println("Line"+i+": "+x.toString());
            i++;
            }
            bf.close();
        }*/
       
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
    

