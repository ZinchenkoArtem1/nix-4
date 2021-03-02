rem go to project with terminal compile
cd terminal
rem clean file from last compile
rd /s /q build
rem build directory for compile file
mkdir build\classes
rem compile project with libs
javac -sourcepath ./src -d build/classes ^
-cp ./libs/lombok-1.18.18.jar;./libs/commons-lang3-3.11.jar ^
src/ua/com/zinchenko/test/Cat.java src/ua/com/zinchenko/test/Test.java src/ua/com/zinchenko/Main.java
rem run project
java -cp build/classes/;./libs/commons-lang3-3.11.jar;./libs/lombok-1.18.18.jar;. ua.com.zinchenko.Main

rem go to project with maven compile
cd ../maven
rem clean file from last compile
call mvn clean
rem create jar
call mvn package
rem run jar
java -jar target/maven-1.0-SNAPSHOT.jar

rem go to project with ant compile
cd ../ant
rem clean file from last compile
call ant clean
rem create jar
call ant jar
rem run jar
call ant run

