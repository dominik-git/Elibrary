package sk.dominik.paz1c.Ekniznica;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sk.paz1c.Elibrary.model.Reader;
import sk.paz1c.Elibrary.mysql.DaoFactory;

class MysqlReaderDaoTest {
	Reader reader = new Reader();
	Reader reader2 = new Reader();
	List<Reader> citatelia;
	boolean pridavaliSme = false;
	int pocet;
	
	
	@BeforeEach
	void setUp() throws Exception {
	citatelia = DaoFactory.INSTANCE.getReaderDao().getAllReaders();
	pocet = citatelia.size();
	reader.setName("Janko");
	reader.setPassword("Ok");
	Date date = java.sql.Date.valueOf(LocalDate.now());
	reader.setBirthDate(date);
	reader.setAdmin(0);
	reader.setSurname("Dedo");
	reader.setGender("X");
	reader.setUsername("jano123");
	}

	@AfterEach
	void tearDown() throws Exception {
		if (pridavaliSme) {
			DaoFactory.INSTANCE.getReaderDao().deleteReaderById(reader.getId());	
			pridavaliSme = false;
		}
	}

	
	@Test
	void saveReaderTest() {
	DaoFactory.INSTANCE.getReaderDao().saveReader(reader);
	pridavaliSme = true;
	assertEquals(pocet + 1, DaoFactory.INSTANCE.getReaderDao().getAllReaders().size());
	DaoFactory.INSTANCE.getReaderDao().saveReader(reader2);
	assertEquals(pocet + 1, DaoFactory.INSTANCE.getReaderDao().getAllReaders().size());
	}
	
	
	@Test
	void updateReaderTest() {
	DaoFactory.INSTANCE.getReaderDao().saveReader(reader);
	pridavaliSme = true;
	reader.setName("UzJoko");
	DaoFactory.INSTANCE.getReaderDao().updateReader(reader);
	assertEquals("UzJoko" ,DaoFactory.INSTANCE.getReaderDao().getReaderById(reader.getId()).getName());
	}
	
	@Test
	void getAllReadersTest() {
	int pocet1 = DaoFactory.INSTANCE.getReaderDao().getAllReaders().size();
	DaoFactory.INSTANCE.getReaderDao().saveReader(reader);
	pridavaliSme = true;
	int pocet2 = DaoFactory.INSTANCE.getReaderDao().getAllReaders().size();
	assertEquals(pocet1 + 1, pocet2);
	}
	
	@Test
	void changePasswordByIdTest() {
	DaoFactory.INSTANCE.getReaderDao().saveReader(reader);
	pridavaliSme = true;	
	String password = "JanoJozo";
	Long idR = reader.getId();
	DaoFactory.INSTANCE.getReaderDao().changePasswordById(idR, password);
	assertEquals(password, DaoFactory.INSTANCE.getReaderDao().getReaderById(reader.getId()).getPassword());
	}
	
	@Test
	void deleteReaderByIdTest() {
	DaoFactory.INSTANCE.getReaderDao().saveReader(reader);
	int pocet1 = DaoFactory.INSTANCE.getReaderDao().getAllReaders().size();
	DaoFactory.INSTANCE.getReaderDao().deleteReaderById(reader.getId());
	int pocet2 = DaoFactory.INSTANCE.getReaderDao().getAllReaders().size();
	assertEquals(pocet1-1, pocet2);
	}
	
	@Test
	void getReaderByIdtest() {
	DaoFactory.INSTANCE.getReaderDao().saveReader(reader);
	pridavaliSme = true;
	assertEquals(DaoFactory.INSTANCE.getReaderDao().getReaderById(reader.getId()).getId(), reader.getId());	
	}
	
	@Test
	void getReaderByNametest() {
	DaoFactory.INSTANCE.getReaderDao().saveReader(reader);
	pridavaliSme = true;
	assertEquals(DaoFactory.INSTANCE.getReaderDao().getReaderByName(reader.getName()).getId(), reader.getId());	
	}
	
	@Test
	void getAllReadersByFullNameTest() {
	assertEquals(0, DaoFactory.INSTANCE.getReaderDao().getAllReadersByFullName("Ja").size());
	DaoFactory.INSTANCE.getReaderDao().saveReader(reader);
	pridavaliSme = true;
	assertEquals(1, DaoFactory.INSTANCE.getReaderDao().getAllReadersByFullName("Ja").size());
	assertEquals(5,DaoFactory.INSTANCE.getReaderDao().getAllReadersByFullName("a").size());	
	}
	
	@Test
	void getReaderByUsernameAndPasswordTest() {
		DaoFactory.INSTANCE.getReaderDao().saveReader(reader);
		pridavaliSme = true;
		assertEquals(reader.getId(), DaoFactory.INSTANCE.getReaderDao().getReaderByUsernameAndPassword("jano123", "Ok").getId());
		
	}
}
