call mvn clean
call mvn install
cd consolecalculator/target
java -jar consolecalculator-1.0-SNAPSHOT-jar-with-dependencies.jar
