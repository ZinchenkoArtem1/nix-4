@echo off
call setantenv.bat

call setmvnenv.bat

cd terminal
echo Launch terminal example:
call launchTerminalExample.bat

cd ../ant
echo Launch ant example:
call launchAntExample.bat

cd ../maven
echo Launch maven example:
call launchMavenExample.bat