package commonutils.pageobjects.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class log4jClass {

	private Logger log = Logger.getLogger(this.getClass());

	public log4jClass() {

		DOMConfigurator.configure("Log4J.xml");

	}

	public void info(String strInfo) {
		log.info(strInfo);

	}

	public void debug(String strDebug) {
		log.debug(strDebug);
	}
}
