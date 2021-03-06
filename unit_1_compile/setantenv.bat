@echo off
set ANT_HOME=%~dp0\libs\apache-ant-1.10.9
set PATH=%ANT_HOME%\bin;%PATH%
rem deleting CLASSPATH as a workaround for PLA-8702
set CLASSPATH=
echo Setting ant home to: %ANT_HOME%
call ant -version