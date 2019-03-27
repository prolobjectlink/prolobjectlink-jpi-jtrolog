@echo off

SET CURRENT_DIRECTORY=%~dp0
SET CLASSPATH=%CURRENT_DIRECTORY%lib\*

: default jdk
java -classpath %CLASSPATH% org.prolobjectlink.prolog.jtrolog.JTrologConsole -g
