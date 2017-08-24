package springrestful_example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import springrestful_example.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException{
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public List<User> listAllUser() {
		List<User> list = new ArrayList<User>();
		
		String sql = "SELECT id, firstname, lastname, address FROM users";
		
		list = namedParameterJdbcTemplate.query(sql, getSqlParameterSourceByModel(null), new UserMapper());
		
		return list;
	}
	
	private SqlParameterSource getSqlParameterSourceByModel(User user) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		if(user != null) {
			paramSource.addValue("id", user.getId());
			paramSource.addValue("firstname", user.getFirstname());
			paramSource.addValue("lastname", user.getLastname());
			paramSource.addValue("address", user.getAddress());
		}
		
		return paramSource;
	}
	
	private static final class UserMapper implements RowMapper<User> {
		
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setFirstname(rs.getString("firstname"));
			user.setLastname(rs.getString("lastname"));
			user.setAddress(rs.getString("address"));
			
			return user;
		}
		
	}

	public void addUser(User user) {
		String sql =  "INSERT INTO users(firstname, lastname, address) VALUES(:firstname,:lastname,:address)";
		
		namedParameterJdbcTemplate.update(sql, getSqlParameterSourceByModel(user));
	}

	public void updateUser(User user) {
		String sql =  "UPDATE users SET firstname = :firstname, lastname = :lastname, address = :address where id = :id";
		
		namedParameterJdbcTemplate.update(sql, getSqlParameterSourceByModel(user));
	}

	public void delete(User user) {
		String sql = "DELETE FROM users WHERE id = :id";
		
		namedParameterJdbcTemplate.update(sql, getSqlParameterSourceByModel(user));
	}

	public User findById(User user) {
		String sql = "SELECT * FROM users WHERE id = :id";
		
		return namedParameterJdbcTemplate.queryForObject(sql, getSqlParameterSourceByModel(user), new UserMapper());
	}

}
