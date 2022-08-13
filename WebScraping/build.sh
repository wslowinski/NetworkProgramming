javac -cp gson-2.2.2.jar Weather.java KrakowWeather.java
jar cmf MANIFEST.MF BBCWeather.jar ./com/ KrakowWeather.class KrakowWeather\$Detailed.class KrakowWeather\$Forecasts.class KrakowWeather\$Location.class KrakowWeather\$Reports.class Weather.class
java -jar BBCWeather.jar
rm *.class