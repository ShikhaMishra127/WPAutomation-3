package buyer.testcases.registration;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class CurrentDateTime {
    public static String FeinGenerator1() {
        long timeSeed = System.nanoTime(); // to get the current date time value

        double randSeed = Math.random() * 1000; // random number generation

        long midSeed = (long) (timeSeed * randSeed); // mixing up the time and
        // rand number.

        String s = midSeed + "";
        String subStr = s.substring(0, 9);
        int finalSeed = Integer.parseInt(subStr);    // integer value

        return String.valueOf(finalSeed);

    }
}