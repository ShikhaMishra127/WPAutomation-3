package utilities.common;

public class ssnFeinGenerator {

	public static String generateSSN() {
		long timeSeed = System.nanoTime(); // to get the current date time value

		double randSeed = Math.random() * 1000; // random number generation

		long midSeed = (long) (timeSeed * randSeed); // mixing up the time and
														// rand number.

		String s = midSeed + "";
		String subStr = s.substring(0, 9);
		System.out.println(subStr);
		return subStr;
	}

	public static String FeinGenerator() {
		long timeSeed = System.nanoTime(); // to get the current date time value

		double randSeed = Math.random() * 1000; // random number generation

		long midSeed = (long) (timeSeed * randSeed); // mixing up the time and
														// rand number.

		String s = midSeed + "";
		String subStr = s.substring(0, 9);

		return subStr;

	}

	public static String dunsNumberGenerator() {
		long timeSeed = System.nanoTime(); // to get the current date time value

		double randSeed = Math.random() * 1000; // random number generation

		long midSeed = (long) (timeSeed * randSeed); // mixing up the time and
														// rand number.

		String s = midSeed + "";
		String subStr = s.substring(0, 9);

		return subStr;

	}

}
