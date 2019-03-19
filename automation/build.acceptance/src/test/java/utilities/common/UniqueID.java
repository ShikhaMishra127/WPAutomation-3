package utilities.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UniqueID {

	public enum IDType{ SSNFEIN, DATE }

	private String number;
	private String date;
	private LocalDateTime time;

	public UniqueID(IDType type) {
		if (type == IDType.SSNFEIN) {
			generateFEINSSN();
		} else {
			generateDateID();
		}
	}

	public UniqueID(String newvalue) { setNumber(newvalue); }

	public String getNumber() { return number; }
	public String getDate() { return date; }

	public String getDuns() { return number; }

	public String getFeinPt1() { return number.substring(0,2); }
	public String getFeinPt2() { return number.substring(2,9); }

	public String getSSNPt1() { return number.substring(0,3); }
	public String getSSNPt2() { return number.substring(3,5); }
	public String getSSNPt3() { return number.substring(5,9); }

	public void setNumber(String newvalue) { number = newvalue; }

	public int Compare(String input) {
		return (number.compareTo(input));
	}

	public void generateFEINSSN() {

		long timeSeed = System.nanoTime(); 			// to get the current date time value
		double randSeed = Math.random() * 1000; 	// random number generation
		long midSeed = (long)(timeSeed * randSeed);	// mixing up the time and rand number.

		String s = midSeed + "";
		String subStr = s.substring(0, 9);

		number = subStr;
	}

	public void generateDateID() {

		time = LocalDateTime.now();

		number = time.format(DateTimeFormatter.ofPattern("yyMMdd.hhmmss"));
		date = time.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy hh:mm:ss a"));

	}
}
