javac -cp gson-2.2.2.jar Artist.java Band.java Discogs.java Groups.java Members.java Pair.java Results.java
jar cmf MANIFEST.MF Discogs.jar ./com/ Artist.class Band.class Discogs.class Groups.class Members.class Pair.class Results.class
java -jar Discogs.jar
rm -f *.class
