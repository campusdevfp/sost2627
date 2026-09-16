#!/bin/bash
set -e
W=/tmp/retos; rm -rf $W; mkdir -p $W/src/sostenibilidad/retos $W/cls
cp /home/claude/curso/sol/sostenibilidad/*.java $W/src/sostenibilidad/
cp /home/claude/curso/proyecto/src/main/java/sostenibilidad/retos/*.java $W/src/sostenibilidad/retos/
javac -d $W/cls $(find $W/src -name "*.java")
java -cp $W/cls sostenibilidad.retos.$1
