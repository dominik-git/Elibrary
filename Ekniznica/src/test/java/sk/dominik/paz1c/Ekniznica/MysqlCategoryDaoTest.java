//package sk.dominik.paz1c.Ekniznica;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.List;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import sk.paz1c.Elibrary.model.Book;
//import sk.paz1c.Elibrary.model.Category;
//import sk.paz1c.Elibrary.mysql.DaoFactory;
//
//class MysqlCategoryDaoTest {
//	Category category = new Category(Long.getLong("20"), "Hora");
//	boolean vytvaraliSme = false;
//	List<Category> kategorie;
//	int pocet;
//	
//	
//	@BeforeEach
//	void setUp() throws Exception {
//		kategorie = DaoFactory.INSTANCE.getCategoryDao().getAllCategories();
//		kategorie.add(category);
//		pocet = kategorie.size();
//	}
//
//	@AfterEach
//	void tearDown() throws Exception {
//		if (vytvaraliSme) {
//			DaoFactory.INSTANCE.getCategoryDao().deleteCategoryById(category.getId());
//			vytvaraliSme = false;
//		}
//	}
//	
//	@Test
//	void getAllCategoriesTest() {
//		assertEquals(pocet, DaoFactory.INSTANCE.getCategoryDao().getAllCategories().size()+1);
//	}
//	@Test
//	void deleteCategoryByIdTest() {
//		DaoFactory.INSTANCE.getCategoryDao().addCategory(category);
//		int pocet1 = DaoFactory.INSTANCE.getCategoryDao().getAllCategories().size();
//		DaoFactory.INSTANCE.getCategoryDao().deleteCategoryById(category.getId());
//		int pocet2 = DaoFactory.INSTANCE.getCategoryDao().getAllCategories().size();
//		assertEquals(pocet1, pocet2+1);
//	}
//	@Test
//	void updateCategoryTest() {
//		DaoFactory.INSTANCE.getCategoryDao().addCategory(category);
//		String meno = "Hory";
//		category.setName(meno);
//		DaoFactory.INSTANCE.getCategoryDao().updateCategory(category);
//		vytvaraliSme = true;
//		assertEquals(meno, DaoFactory.INSTANCE.getCategoryDao().getCategoryById(category.getId()).getName());
//	}
//	@Test
//	void addCategoryTest() {
//		DaoFactory.INSTANCE.getCategoryDao().addCategory(category);
//		vytvaraliSme = true;
//		assertTrue(pocet == DaoFactory.INSTANCE.getCategoryDao().getAllCategories().size() );
//	}
//	@Test
//	void getCategoryByIdTest() {
//		DaoFactory.INSTANCE.getCategoryDao().addCategory(category);
//		vytvaraliSme = true;
//		DaoFactory.INSTANCE.getCategoryDao().getCategoryById(category.getId());
//		assertEquals(category.getId(), DaoFactory.INSTANCE.getCategoryDao().getCategoryById(category.getId()).getId());
//	}
//
//}
