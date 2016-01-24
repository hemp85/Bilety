
package bilety;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;




public class Test {
    
  static public String checkDate(){
        ParseException f=null;
        ArrayIndexOutOfBoundsException a=null;
        DateTimeParseException c=null;
        String text;
    do {
        if(f!=null||a!=null||c!=null){
            f=null;
            a=null;
            c=null;
        }
        System.out.println("Specify date in \"dd.MM.yyyy\" format please: ");
        Scanner scn =new Scanner(System.in);
        text = scn.nextLine();

        try{
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String text2=text.replaceAll("\\.", "/");
        String[] ddd3=text2.split("/");
        String text3=new StringBuilder(ddd3[2]+"-"+ddd3[1]+"-"+ddd3[0]).toString();
        CharSequence dotDate=text3;
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern(df);
        LocalDate ld1= LocalDate.parse(dotDate);
        LocalDate ld2=LocalDate.now();
        int dateSubtract = Period.between(ld1, ld2).getDays();
        if(dateSubtract>0){
            text="n";
        }
        df.setLenient(false);
        df.parse(text);
        
        
        } catch(ParseException e){
            System.out.println("Wrong format. Try again");
            f=e;
        } catch(ArrayIndexOutOfBoundsException b){
            a=b;
            System.out.println("Wrong format. Try again");
        } catch(DateTimeParseException dtp){
            c=dtp;
            System.out.println("Wrong format. Try again");
        }
        
    } while(f!=null||a!=null||c!=null);
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
  public static String multipleDays(){
      System.out.println("Would You like to search for several dates  advance? ");
        Scanner scn =new Scanner(System.in);
        String text = scn.nextLine();
        return text;
    }
  public static int daysAdvance(){
      NumberFormatException f=null;
      int days=0;
      
      do {
          if(f!=null){
            f=null;
        }
      System.out.println("How many days in advance? ");
      Scanner scn =new Scanner(System.in);
      try{
       days = Integer.parseInt(scn.nextLine());
      } catch(NumberFormatException e){
          System.out.println("Wrong format. Try again");
          f=e;
      }
      } while (f!=null);
      return days;
  }
  
  public static List<String[]> countDates(int days, String... dates){
      List<String> daty =Arrays.asList(dates);
      List<String[]> listaDat = new ArrayList<>();
      CharSequence dotDate;
      
         for (int i=0;i<daty.size();i++){
         dotDate=daty.get(i);
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
         LocalDate ld= LocalDate.parse(dotDate,formatter);
         
         String[] list1= new String[days+1];
         list1[0] =daty.get(i);
         for(int j=1;j<(days+1);j++){
             list1[j]=ld.plusDays(j).format(formatter);
         }
         listaDat.add(i,list1);
      }
         return listaDat;
  }
 }