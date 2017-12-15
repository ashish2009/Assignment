# Assignment: Generic Framework for API Automation

Prerequisite:
1. Java 8
2. Maven
3. Eclipse(eclipse-jee-luna-R-linux-gtk-x86_64)(support Maven project)

Test Trigger: Test will be triggered through testng.xml file by directly running it or
                      by invoking maven command in terminal to run testng.xml file.
Location of Testng.xml: src/test/java/com/plivo/sendMessage/testng.xml
              
Trigger test from command line: Go to project directory and invoke below command.
mvn clean -DsuiteXmlFile=src/test/java/com/plivo/sendMessage/testng.xml test site

Test Configuration:All configuration of test should be mentioned in         src/main/resources/config.properties.

Location of Send Message Test: src/test/java/com/plivo/sendMessage/SendMessageTest.java

Test Data for Send Message: src/test/java/com/plivo/sendMessage/TestData

Default payload for Send Message Test: 
Payload json: src/main/resources/request/Message.json
Payload pojo: src/main/java/com/plivo/requestObject/SendMessage.java

Framework Design:

Test class has method to send message and get message.Send message will set value of message uuid to global variable and same variable is passed to get message.

Generic class to Post/Get any API request:

ApiBase: Has common method to set parameter needed to send any api request(auth/header/URI)

GetRequest: Has method specific to GET request.This class extends ApiBase, so common parameters of GET and POST can be accessed using SendRequest object

SendRequest: Has method specific to POST request.This class extends ApiBase, so common parameters of GET and POST can be accessed using SendRequest object

BuildPayload:Has method to build payload for POST request

PropertyReader: This class is used to read config.property file.config.property file contain configuration needed to send/get request using api

FileDataProvider: Read line from test data file and return Iterator which can be attached with Test method to run test method for each line.

TestListener: This will attach data provider with class at run time.It is used in testng.xml file with class

When Test class is run using testng.xml, listener in testng.xml attach data provider with test method.First BeforeClass is get executed which create api request based on config parameter present in config.properties.After this Test method get execute for every line in Test data. 
Test data update default payload and then method to Post request with new payload get called.
We get messageUUID from response and same is passed to Get Request.