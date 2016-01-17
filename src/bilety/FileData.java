
package bilety;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FileData {
   
    public static void main(String[] args)throws IOException{
    
      /*  Path path = Paths.get("D:\\java\\sciezka1.xml");
        Charset charset = StandardCharsets.UTF_8;

        String content = new String(Files.readAllBytes(path), charset);
        String znajdz= "<img alt=\"Уральские авиалинии\" src=\"http://cdn2.tu-tu.ru/image/source/5/a70009c490cb9f22b586557ab618b8ff/\" class=\"rzlt_top_logo\">";
        String oddaj="<img alt=\"Уральские авиалинии\" src=\"http://cdn2.tu-tu.ru/image/source/5/a70009c490cb9f22b586557ab618b8ff/\" class=\"rzlt_top_logo\"></img>";

   
   content = content.replaceAll( znajdz, oddaj);
   Files.write(path, content.getBytes(charset));*/
        File plik = new File("D:\\java\\sciezka.xml"); 
        Document doc = Jsoup.parse(plik,"UTF-8");
        Elements elem = doc.getElementsByClass("rm-rzlt_block");
        System.out.println(elem);
    }
    
}
//<?xml version=\"1.0\" encoding=\"UTF-8\"?>