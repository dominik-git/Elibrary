package sk.paz1c.Elibrary.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import sk.paz1c.Elibrary.interfaces.CategoryDao;
import sk.paz1c.Elibrary.model.Book;
import sk.paz1c.Elibrary.model.Category;

public class MysqlCategoryDao implements CategoryDao {
	private JdbcTemplate jdbcTemplate;

	public MysqlCategoryDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;

	}
private void sysout() {
	// TODO Auto-generated method stub

}

	@Override
	public List<Category> getAllCategories() {
		String sql = "select * from category;";
		List<Category> result = jdbcTemplate.query(sql, new ResultSetExtractor<List<Category>>() {

			@SuppressWarnings("null")
			@Override
			public List<Category> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Category> result = new ArrayList<Category>();

				Category category = null;
				while (rs.next()) {
					category = new Category();
					category.setId(rs.getLong("id"));
					category.setName(rs.getString("name"));
					result.add(category);
				}
				return result;
			}

		});

		return result;
	}

	@Override
	public Category addCategory(Category category) {
		if (category == null)
			return null;
		if (category.getId() == null) {
			// INSERT
			SimpleJdbcInsert sjinsert = new SimpleJdbcInsert(jdbcTemplate);
			sjinsert.withTableName("Category");
			sjinsert.usingColumns("name");
			sjinsert.usingGeneratedKeyColumns("id");

			Map<String, Object> values = new HashMap<String, Object>();
			values.put("name", category.getName());
			long id = sjinsert.executeAndReturnKey(values).longValue();
			category.setId(id);

			return category;
		}
		return null;

	}
}
