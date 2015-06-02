package restservicedemo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.PersonManager;

public class PersonTest {
	
	private PersonManager pm;
	private Person person;
	
	@Before
	public void setUp() throws Exception {
		pm = new PersonManager();
	}

	@After
	public void tearDown() throws Exception {
		pm = null;
	}

	@Test
	public void Persontest() {
		person = new Person(4, "Bolek", 1962);
		pm.addPerson(person);
		Person p = pm.getPerson(4L);
		Assert.assertEquals("Bolek", p.getFirstName());
		Assert.assertEquals(1962, p.getYob());
	}

}
