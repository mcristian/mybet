# mybet
my bets aggregator with some nice reporting strategies

# build it
mvn clean assembly:assembly

# run it, unzip from target\mybet-0.0.1-SNAPSHOT-dist.zip
java -jar libs\mybet-app-0.0.1-SNAPSHOT.jar

# debug it
java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005 -jar libs\mybet-app-0.0.1-SNAPSHOT.jar

# check strategies
http://localhost:8080//api/v1/config/strategies


DONE
1. read CSV and prints to console the aggregated bets based on required grouping and sorting strategy
2. list, update, create, edit, delete strategies with REST

TODO
1. REST for bets
2. print to XML the aggregated bets based on a specific strategy
3. update exchange rate 
