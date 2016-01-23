
package bilety;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.Number;
import org.openqa.selenium.NoSuchElementException;

public class Bilety {
static WebDriver driver;
  static List<String[]> tabelki = new ArrayList<>();
    public static void main(String[] args) throws InterruptedException, IOException, WriteException {
       
        String cityFrom=Test.getCity();
        String cityTo =Test.getCity();
        String dateFrom = Test.checkDate();
        String decision;
        String dateTo=null;
        
        do {
            decision=Test.decision();
        switch(decision){
            case "y": 
            dateTo= Test.checkDate();
            break;
            case "n":
            break;
            default: System.out.println("Some error. Try again!");
            decision ="blad";
            break;
        }
        } while(!(decision.toLowerCase().equals("y")||decision.toLowerCase().equals("n")));
        System.out.println("Searching started. Please wait");
        driver = new FirefoxDriver();
        driver.get("http://avia.tutu.ru/");
        
        WebElement element1 = driver.findElement(By.name("city_from"));
        element1.sendKeys(cityFrom);
        WebElement element2 = driver.findElement(By.name("city_to"));
        element2.sendKeys(cityTo);
        WebElement element3 = driver.findElement(By.name("date_from"));
        element3.click();
        element3.sendKeys(dateFrom);
        if(decision.toLowerCase().equals("y")){
           WebElement element4 = driver.findElement(By.name("date_back"));
           element4.click();
           element4.sendKeys(dateTo); 
        }
        
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        WebElement element5 = buttons.get(3);
        element5.click();
        try {
        boolean clickChecker =driver.findElement(By.className("qtip-content")).isDisplayed();
        
        if (clickChecker==true){
            System.out.println("Some errors on website. Wrong city name or date, probably. System quits");
            System.exit(0);
        } 
        } catch(NoSuchElementException e){
            
        } finally {
            
        }
        
        checkIfLoaded();
        
        
          
        if((driver.findElement(By.className("b-price_message")).getText())!="Цена за одного пассажира"){
        Thread.sleep(2000);
        }
        List<WebElement> lista =driver.findElements(By.className("rm-rzlt_block"));
        int a=lista.size();
        
     
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
        String dokad=null;
        String hour2=null;
        String[] wiersze=new String[7];
        String option = "Option nr: "+(i+1);
        String alt = lista.get(i).findElement(By.tagName("img")).getAttribute("alt");
        String skad = (lista.get(i).findElements(By.className("rzlt_way_to")).get(0).getText())+" "+przes1;
        String hour1 = lista.get(i).findElements(By.className("rzlt_way_time")).get(0).getText();
        
        if(decision.toLowerCase().equals("y")){
        dokad = (lista.get(i).findElements(By.className("rzlt_way_to")).get(1).getText())+" "+przes2;
        hour2= lista.get(i).findElements(By.className("rzlt_way_time")).get(1).getText();
        }
        String cena= lista.get(i).findElement(By.className("buttonext_digits")).getText();
        
        if(decision.toLowerCase().equals("n")){
        System.out.print(option+"\n"+alt+"\n"+skad+"\n"+hour1+"\n"+cena+"\n"+"\n");
        wiersze[0]=option;
        wiersze[1]=alt;
        wiersze[2]=skad;
        wiersze[3]=hour1; 
        wiersze[4]=cena;
        }
      
        else {
        System.out.print(option+"\n"+alt+"\n"+skad+"\n"+hour1+"\n"+dokad+"\n"+hour2+"\n"+cena+"\n"+"\n");
        wiersze[0]=option;
        wiersze[1]=alt;
        wiersze[2]=skad;
        wiersze[3]=hour1;
        wiersze[4]=dokad;
        wiersze[5]=hour2;
        wiersze[6]=cena;
       
        }
        
        tabelki.add(i, wiersze);
       } //koniec petli for
    WritableWorkbook workbook = Workbook.createWorkbook(new File("D:\\java\\przyklad.xls"));
    WritableSheet sheet = workbook.createSheet("First Sheet", 0);
    int k=0;
    Label label;
    for(int j=0;j<a;j++){
        String[] tab =tabelki.get(j);
                for(int i=0;i<tab.length;i++){
                    label = new Label(j, i, tab[i]);
                    sheet.addCell(label);
                    
                }
                k++;
        } 
        workbook.write();
        workbook.close();
        //driver.close();
       
    } 
              
    private static void checkIfLoaded(){
        try {
        WebElement myDynamicElement = (new WebDriverWait(driver, 5)).until(ExpectedConditions.presenceOfElementLocated(By.className("b-price_message")));
        
        } catch(TimeoutException t) {
              checkIfNoTickets();
        } finally {
            
        }
    } 
     private static void checkIfNoTickets(){
         try {
        WebElement myDynamicElement = (new WebDriverWait(driver, 5)).until(ExpectedConditions.presenceOfElementLocated(By.className("mssg-txt")));
        } catch(TimeoutException t) {
              checkIfLoaded();
        } finally {
            boolean checker=driver.findElement(By.className("mssg-txt")).getText().contains("мы не нашли билеты");
            if(checker==true){
            System.out.println("No tickets available. System quits");
            System.exit(0);
            }
            checkIfLoaded();
        }
     }
}
    

