# Web Scraping
Web scraping is data scraping used for extracting data from websites. This 
directory contains a Java program that downloads the current weather in Krakow 
(Poland) from the BBC website:

https://weather-broker-cdn.api.bbci.co.uk/en/forecast/aggregated/3094802

Google Gson was used to deserialize a JSON to Java object.

## Content
* ```KrakowWeather.java``` - class that maps to a JSON structure;
* ```Weather.java``` - main;

## Technologies
Project is created with ```Java 11```.

## Setup
1. To run this project, install it locally;
2. The following command will help you in compiling the project into JAR file (```BBCWeather.jar```):
    ```
    $ bash build.sh
    ```
3. Finally, ```WeatherKrakow.txt``` file contains the current weather in Krakow.
