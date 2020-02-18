#!/bin/bash
./gradlew jar
./gradlew cleanTest test -i
java -jar ./build/libs/toy-robot-1.0-SNAPSHOT.jar test test1 test2