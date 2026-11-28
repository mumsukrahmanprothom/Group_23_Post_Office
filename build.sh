#!/bin/bash
JC="C:/Users/hp/.jdks/temurin-21.0.13.1/bin/javac"
BASE="C:/Users/hp/.m2/repository/org/openjfx"
CP="$BASE/javafx-base-21.0.2-win.jar;$BASE/javafx-controls-21.0.2-win.jar;$BASE/javafx-fxml-21.0.2-win.jar;$BASE/javafx-graphics-21.0.2-win.jar"
eval "$JC -d target/classes -cp $CP src/main/java/com/postoffice/*.java"