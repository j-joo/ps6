package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person_Test {
		
	private static PersonDomainModel person1;
	private static UUID person1UUID = UUID.randomUUID();			
	
	@BeforeClass
	public static void personInstance() throws Exception{
		
		Date person1Birth = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		 person1 = new PersonDomainModel();
		 
		try {
			person1Birth = dateFormat.parse("1994-11-27");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		person1.setPersonID(person1UUID);
		person1.setFirstName("Mingkun");
		person1.setMiddleName("a");
		person1.setLastName("Chen");
		person1.setBirthday(person1Birth);
		person1.setCity("Elkton");
		person1.setStreet("702 Stone Gate Blvd");
		person1.setPostalCode(21921);
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addPersonTest() {
		
		System.out.println(person1);
		PersonDomainModel testPerson;
		testPerson = PersonDAL.getPerson(person1.getPersonID());
		System.out.println(testPerson);
		assertNull("This person shouldn't be listed here", testPerson);
		
		PersonDAL.addPerson(person1);
		testPerson = PersonDAL.getPerson(person1.getPersonID());
		assertTrue("This person could not be found", testPerson != null);
		
		PersonDAL.deletePerson(person1.getPersonID());
		testPerson = PersonDAL.getPerson(person1.getPersonID());
		assertNull("This person shouldn't be listed here", testPerson);
	}

	@Test
	public void getPersonTest() {
		PersonDomainModel testPerson;
		testPerson = PersonDAL.getPerson(person1.getPersonID());
		assertNull("This person shouldn't be listed here", testPerson);
		
		PersonDAL.addPerson(person1);
		testPerson = PersonDAL.getPerson(person1.getPersonID());
		assertTrue("This person could not be found", testPerson != null);
			
		PersonDAL.deletePerson(person1.getPersonID());
		testPerson = PersonDAL.getPerson(person1.getPersonID());
		assertNull("This person shouldn't be listed here", testPerson);
	}
	
	@Test
	public void deletePersonTest() {
		
		PersonDomainModel testPerson;
		testPerson = PersonDAL.getPerson(person1.getPersonID());
		assertNull("This person shouldn't be listed here", testPerson);
		
		PersonDAL.addPerson(person1);
		testPerson = PersonDAL.getPerson(person1.getPersonID());
		assertTrue("This person could not be found", testPerson != null);
		
		PersonDAL.deletePerson(person1.getPersonID());
		testPerson = PersonDAL.getPerson(person1.getPersonID());
		assertNull("This person shouldn't be listed here", testPerson);
	}

	@Test
	public void getPersonsTest() {
		PersonDomainModel testPerson;
		testPerson = PersonDAL.getPerson(person1.getPersonID());
		assertNull("This person shouldn't be listed here", testPerson);
		
		PersonDAL.addPerson(person1);
		testPerson = PersonDAL.getPerson(person1.getPersonID());
		assertTrue("This person could not be found", testPerson != null);
	
		ArrayList<PersonDomainModel> persons = PersonDAL.getPersons();
		System.out.println(persons.size() + " records retrieved");
		for (PersonDomainModel p : persons) {
			System.out.println(p);
		}
		
		PersonDAL.deletePerson(person1.getPersonID());
		testPerson = PersonDAL.getPerson(person1.getPersonID());
		assertNull("This person shouldn't be listed here", testPerson);
	}

	@Test
	public void updatePersonTest() {		
		
		PersonDomainModel testPerson;
		testPerson = PersonDAL.getPerson(person1.getPersonID());
		assertNull("This person shouldn't be listed here", testPerson);
		
		PersonDAL.addPerson(person1);
		testPerson = PersonDAL.getPerson(person1.getPersonID());
		assertTrue("This person could not be found", testPerson != null);
		
		String newFirstName = "RyRy";
		testPerson.setFirstName(newFirstName);
		PersonDAL.updatePerson(testPerson);
		testPerson = PersonDAL.getPerson(testPerson.getPersonID());
		assertTrue("First name did not change",testPerson.getFirstName().equals(newFirstName));
		
		PersonDAL.deletePerson(person1.getPersonID());
		testPerson = PersonDAL.getPerson(person1.getPersonID());
		assertNull("This person shouldn't be listed here", testPerson);
	}
}
