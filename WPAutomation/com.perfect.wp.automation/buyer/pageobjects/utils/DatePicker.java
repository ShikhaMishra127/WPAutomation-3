package pageobjects.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DatePicker {

	public void setDateAndTime(String Date) {

		SimpleDateFormat a = new SimpleDateFormat("MM/dd/yyyy, hh:mm a");
		try {
			System.out.println(a.parse(Date).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public static String getCurrentLocalDateTimeStamp() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy, hh:mm a"));
	}

	public static String getPastDate() {
		return new SimpleDateFormat("MM/dd/yyyy, hh:mm a").format(Date.valueOf(LocalDate.now().minusDays(1)));

	}

	public static String getFutureDate() {
		return new SimpleDateFormat("MM/dd/yyyy, hh:mm a").format(Date.valueOf(LocalDate.now().plusDays(1)));

	}

	public static String getCustomDate(String str, int days) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy, h:mm a");
		LocalDateTime datetime = LocalDateTime.parse(str, formatter);
		java.util.Date convertedDatetime = Date
				.from(datetime.minusDays(days).atZone(ZoneId.systemDefault()).toInstant());
		return new SimpleDateFormat("MM/dd/yyyy, h:mm a").format(convertedDatetime);

	}

	public static Boolean checkDateTimeRange(String str, String strStartDate, String strEndDate) throws ParseException {
		java.util.Date startDate = new SimpleDateFormat("MM/dd/yyyy, hh:mm a").parse(strStartDate);
		java.util.Date endDate = new SimpleDateFormat("MM/dd/yyyy, hh:mm a").parse(strEndDate);
		java.util.Date date3 = new SimpleDateFormat("MM/dd/yyyy, hh:mm a").parse(str);
		return !(date3.before(startDate) || date3.after(endDate));
	}
	
	public static Boolean checkDateRangeOnly(String str, String strStartDate, String strEndDate) throws ParseException {
		java.util.Date startDate = new SimpleDateFormat("MM/dd/yyyy").parse(strStartDate);
		java.util.Date endDate = new SimpleDateFormat("MM/dd/yyyy").parse(strEndDate);
		java.util.Date date3 = new SimpleDateFormat("MMMM dd,yyyy").parse(str);
		return !(date3.before(startDate) || date3.after(endDate));
	}

	public static void main(String[] args) {
		System.out.println(DatePicker.getCustomDate("11/01/2017, 8:15 AM", -100));
	}

}
