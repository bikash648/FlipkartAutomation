set projectLocation=G:\JSpider\ProjectAutomation
cd %projectLocation%
set classpath=%projectLocation%\target\classes;%projectLocation%\lib\*
java org.testng.TestNG %projectLocation%\testng.xml
pause