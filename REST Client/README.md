# REST Client
The REST Client directory contains a project which, based on information from 
Discogs, checks whether members (current or past) of the given music band played 
together in any other bands. Google Gson was used to deserialize a JSON to Java 
object. 

### Discogs website 
https://www.discogs.com/

## Content
* ```Artist.java```, ```Band.java```, ```Groups.java```, ```Members.java```, 
    ```Result.java``` - classes that map to a JSON structure;
* ```Discogs.java``` - main;
* ```Pair.java``` - generic class that provides a way to store two heterogeneous  
    objects as a single unit.

## Technologies
Project is created with ```Java 11```.

## Setup
1. To run this project, install it locally;
2. Register as a developer at Discogs. Generate a token and then paste it into the  
    ```token.txt``` file.
3. The following command will help you in compiling the project into JAR file:
    ```
    $ bash build.sh
    ```
