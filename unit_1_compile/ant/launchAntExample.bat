@echo off
rem clean file from last compile
call ant clean
rem create jar
call ant jar
rem run jar
call ant run