Aby uruchomi� projekt wykonaj poni�sze instrukcje:

Wejd� do katalogu projektu /restservicedemo

Uruchom serwer poleceniem:
java -cp ./scripts/hsqldb-2.2.4.jar org.hsqldb.server.Server --database.0 mem:mydb --dbname.0 workdb

Uruchom klienta poleceniem:
java -cp ./scripts/hsqldb-2.2.4.jar org.hsqldb.util.DatabaseManagerSwing --url jdbc:hsqldb:hsql://localhost/workdb

Ustaw zmienn� JAVA_HOME poleceniem:
set JAVA_HOME=C:\Program Files\Java  

Na r�nych komputerach mo�e by� inaczej, zale�y gdzie masz zainstalowan� Jave

Nast�pnie wywo�aj polecenie:
mvn jetty:run -Djetty.port=9999

Wtedy to mo�na przeglac, np.
http://localhost:9999/restservicedemo/persons/{ID}