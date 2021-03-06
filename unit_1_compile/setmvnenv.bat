@echo off
set MAVEN_HOME=%~dp0\libs\apache-maven-3.6.3
set PATH=%MAVEN_HOME%\bin;%PATH%
rem deleting CLASSPATH as a workaround for PLA-8702
set CLASSPATH=
echo Setting maven home to: %MAVEN_HOME%
call mvn -version