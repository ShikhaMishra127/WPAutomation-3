# build.acceptance

**Follow the steps below to run tests on your local machine:**

1. Download Eclipse IDE for Java Developers 
 https://www.eclipse.org/

2. Install the JDK 
http://www.oracle.com/technetwork/java/javase/downloads/index.html

3. Install ChromeDriver and move it to the automation base directory
http://chromedriver.chromium.org/downloads

4. Open Eclipse

5. Select the drop-down next to the Run icon on the top menu bar

6. Select TestSuite and click Run as TestNG to run test

---

Please adhere to the standard directory structure when adding test cases

* Test Cases go in [src/test/java/testcases](src/test/java/testcases)
* Page Objects go in [src/test/java/pageobjects](src/test/java/pageobjects)
* Utilities and helpers go in  [src/test/java/utilities](src/test/java/utilities)
* Data (Usernames, passwords, browser names, etc.) go in [src/test/resources](src/test/resources)