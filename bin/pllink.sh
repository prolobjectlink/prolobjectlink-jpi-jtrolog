#!/usr/bin/bash
java -classpath "$(dirname "$(pwd)")/lib/*" org.prolobjectlink.prolog.jtrolog.JTrologConsole ${1+"$@"}