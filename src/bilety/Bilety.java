
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Iterator;
import jxl.Workbook;
import jxl.read.biff.BiffException;
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
    public static void main(String[] args) throws InterruptedException, IOException, WriteException, BiffException {
        
        
        List<String[]> daty = new ArrayList<>();
        String cityFrom=Test.getCity();
        String cityTo =Test.getCity();
        String dateFrom = Test.checkDate();
        String decision;
        String dateTo=null;
        String mulitple_days;
        int days=1;
       
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
        
      do {
            mulitple_days=Test.multipleDays();
        switch(mulitple_days){
            case "y": 
            days= Test.daysAdvance()+1;
            break;
            case "n":
            break;
            default: System.out.println("Some error. Try again!");
            mulitple_days ="blad";
            break;
        }
        } while(!(mulitple_days.toLowerCase().equals("y")||mulitple_days.toLowerCase().equals("n")));
      
        String[] lot1 =new String[days];
        String[] lot2=new String[days];
        lot1[0]=dateFrom;
        lot2[0]=dateTo;
        daty.add(0, lot1);
        daty.add(1, lot2);
        
        if(mulitple_days.toLowerCase().equals("y")){
            switch(decision.toLowerCase()){
                case "y": daty=Test.countDates(days, lot1[0],lot2[0]);
                    break;
                default : daty=Test.countDates(days, lot1[0]);
            }
        }
        System.out.println("Searching started. Please wait");
        
        for(int z=0;z<days;z++){
        driver = new FirefoxDriver();
        driver.get("http://avia.tutu.ru/");
        
        WebElement element1 = driver.findElement(By.name("city_from"));
        element1.sendKeys(cityFrom);
        WebElement element2 = driver.findElement(By.name("city_to"));
        element2.sendKeys(cityTo);
        dateFrom =daty.get(0)[z];
        WebElement element3 = driver.findElement(By.name("date_from"));
        element3.click();
        element3.sendKeys(dateFrom);
        if(decision.toLowerCase().equals("y")){
           dateTo =daty.get(1)[z];
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
            System.out.println("Some errors on website. Wrong city name or date probably. System quits");
            System.exit(0);
        } 
        } catch(NoSuchElementException e){}
        
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
        //System.out.print(option+"\n"+alt+"\n"+skad+"\n"+hour1+"\n"+cena+"\n"+"\n");
        wiersze[0]=option;
        wiersze[1]=alt;
        wiersze[2]=skad;
        wiersze[3]=hour1; 
        wiersze[4]=cena;
        }
      
        else {
        //System.out.print(option+"\n"+alt+"\n"+skad+"\n"+hour1+"\n"+dokad+"\n"+hour2+"\n"+cena+"\n"+"\n");
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
      WritableWorkbook workbook=null;
      WritableSheet sheet=null;
      Workbook exisitngWorkbook =null;
    if (z<1){
    workbook = Workbook.createWorkbook(new File("D:\\java\\przyklad.xls"));
    sheet = workbook.createSheet(daty.get(0)[z], z);
   
    }
    if (z>=1){
    exisitngWorkbook = Workbook.getWorkbook(new File("D:\\java\\przyklad.xls"));
    workbook= Workbook.createWorkbook(new File("D:\\java\\przyklad.xls"), exisitngWorkbook);
    sheet = workbook.createSheet(daty.get(0)[z], (z));
    }
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
        driver.close();
        System.out.println("Info about day "+(z+1)+ " saved!");
        }
       
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
    

