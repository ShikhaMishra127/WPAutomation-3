package utilities.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DatePicker {

    public void setDate(String Date){
        SimpleDateFormat a;
        a = new SimpleDateFormat("mm/dd/yyyy");
        try{
            System.out.println(a.parse(Date).getDate());
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    public static String getCurrentDate(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    public static String getPastDate(){
        return new SimpleDateFormat("MM/dd/yyyy").format(java.sql.Date.valueOf(LocalDate.now().minusDays(7)));
    }

}
