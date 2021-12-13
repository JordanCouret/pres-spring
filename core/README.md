Pour lancer la classe Java:

```shell
mvn dependency:copy-dependencies 
mvn package
export CLASSPATH=${PWD}/target/core-1.0.0:${PWD}/target/dependency/checker-qual-3.5.0.jar:${PWD}/target/dependency/postgresql-42.3.1.jar:${PWD}/target/dependency/spring-aop-5.3.13.jar:${PWD}/target/dependency/spring-beans-5.3.13.jar:${PWD}/target/dependency/spring-context-5.3.13.jar:${PWD}/target/dependency/spring-core-5.3.13.jar:${PWD}/target/dependency/spring-expression-5.3.13.jar:${PWD}/target/dependency/spring-jcl-5.3.13.jar
java io.jcorporation.core.CoreApplication jco_login
```
