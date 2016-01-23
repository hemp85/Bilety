
package bilety;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.Date;
import java.util.Scanner;




public class Test {
    
  static public String checkDate(){
        ParseException f=null;
        String text;
    do {
        if(f!=null){
            f=null;
        }
        System.out.println("Specify date in \"dd.MM.yyyy\" format please: ");
        Scanner scn =new Scanner(System.in);
        text = scn.nextLine();

        try{
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        df.setLenient(false);
        df.parse(text);
        
        } catch(ParseException e){
            System.out.println("Wrong format. Try again");
            f=e;
        } 
        
    } while(f!=null);
  return text;
  }
  public static String getCity(){
       System.out.println("Type city name in russian or english, please: ");
        Scanner scn =new Scanner(System.in);
        String text = scn.nextLine();
        return text;
  }
  public static String decision(){
      System.out.println("Would You like to search back flight? ");
        Scanner scn =new Scanner(System.in);
        String text = scn.nextLine();
        return text;
  }
  
 
  
 }