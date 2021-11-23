echo set java home to be java 11

set JAVA_HOME=C:\Program Files\Java\jdk-11.0.11

call mvn clean

call mvn compile assembly:single

call mvn package


