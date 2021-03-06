@echo off
echo clean last compile files
rd /s /q build
rem build directory for compile file
mkdir build\classes
rem compile project with libs
echo compile  terminal example
javac -sourcepath ./src -d build/classes ^
-cp ./libs/lombok-1.18.18.jar;./libs/commons-lang3-3.11.jar ^
src/ua/com/zinchenko/test/Cat.java src/ua/com/zinchenko/test/Test.java src/ua/com/zinchenko/Main.java
rem run project
echo run terminal example
java -cp build/classes/;./libs/commons-lang3-3.11.jar;./libs/lombok-1.18.18.jar;. ua.com.zinchenko.Main