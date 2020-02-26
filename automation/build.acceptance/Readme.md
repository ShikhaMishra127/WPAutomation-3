# WebProcure build.acceptance

**Follow the steps below to run tests on your local machine:**

### 1. Install
* Download [IntelliJ IDE, Community Edition](https://www.jetbrains.com/idea/)

* Download [Github Desktop](https://desktop.github.com/)

### 2. Configure
* In Github Desktop, select File > Clone Repository > perfectcommerce/qatools

* Fully download source onto your target machine's hard drive

* In IntelliJ, select File > New > Project from Existing Sources...

* Select `POM.xml` file on local GIT folder and import project

### 3. Run

* From the Project tab, right-click on test case folder and select 'Run'

### File Locations
Please adhere to the standard directory structure when adding test cases:

* Selenium Test Cases go in [src/test/java/acceptance](src/test/java/acceptance)
* Data (Usernames/passwords, URLs, etc.) go in [src/test/resources](src/test/resources)
* Page Objects go in [src/main/java/pageobjects](src/main/java/pageobjects)
* Utilities and helpers go in  [src/main/java/utilities](src/main/java/utilities)

---

A Wiki containing more information about SQA Automation can be found [here](https://wiki.hubwoo.com/display/PR/QA+Automation)

---
