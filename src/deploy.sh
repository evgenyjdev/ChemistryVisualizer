#!/bin/bash

mvn clean package

cd target

java -jar ChemistryVisualizer-1.0-SNAPSHOT.jar # required java version >= 17
