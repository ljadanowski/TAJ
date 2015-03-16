import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	   Cyfrokradtest.class,
	   Hultajchochla.class,
	   Nieksztaltek.class,
	   //CyfrokradParameterizedtest.class //odkomentowac, gdy zakomentujemy Cyfrokradtest.class. To ma na celu pokazanie ze potrafie wywolywac parametryczne testy
	})

public class GraTest {
	//To jest glowna klasa testujaca.
	//Aby uruchomiæ wszystkie testy nalezy uruchomic ja
}
