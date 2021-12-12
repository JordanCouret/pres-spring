# Native

Pour lancer la classe Java:

```shell
mvn dependency:copy-dependencies 
mvn package
export CLASSPATH=${PWD}/target/native-1.0.0.jar:${PWD}/target/dependency/postgresql-42.3.1.jar
java io.jcorporation.NativeApplication jco_login
```