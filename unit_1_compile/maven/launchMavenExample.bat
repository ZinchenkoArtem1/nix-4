@echo off
rem clean file from last compile
call mvn clean
rem create jar
call mvn package
rem run jar
java -jar target/maven-1.0-SNAPSHOT.jar