package tryMe;

import java.util.Locale;
import java.util.ResourceBundle;

public class tryResource {

	public static void main(String[] args) {

		// en_US
		System.out.println("Current Locale: " + Locale.getDefault());
		ResourceBundle mybundle = ResourceBundle.getBundle("MyLabels");

		// read MyLabels_en_US.properties
		System.out.println("Say how are you in US English: " + mybundle.getString("how_are_you"));

		Locale.setDefault(new Locale("ms", "MY"));

		// read MyLabels_ms_MY.properties
		System.out.println("Current Locale: " + Locale.getDefault());
		mybundle = ResourceBundle.getBundle("MyLabels");
		System.out.println("Say how are you in Malaysian: " + mybundle.getString("how_are_you"));


	}

}
