Aby uruchomiæ projekt wykonaj poni¿sze instrukcje:

WejdŸ do katalogu projektu /restservicedemo

Uruchom serwer poleceniem:
java -cp ./scripts/hsqldb-2.2.4.jar org.hsqldb.server.Server --database.0 mem:mydb --dbname.0 workdb

Uruchom klienta poleceniem:
java -cp ./scripts/hsqldb-2.2.4.jar org.hsqldb.util.DatabaseManagerSwing --url jdbc:hsqldb:hsql://localhost/workdb

Ustaw zmienn¹ JAVA_HOME poleceniem:
set JAVA_HOME=C:\Program Files\Java  

Na ró¿nych komputerach mo¿e byæ inaczej, zale¿y gdzie masz zainstalowan¹ Jave

Nastêpnie wywo³aj polecenie:
mvn jetty:run -Djetty.port=9999

Wtedy to mo¿na przeglac, np.
http://localhost:9999/restservicedemo/persons/{ID}