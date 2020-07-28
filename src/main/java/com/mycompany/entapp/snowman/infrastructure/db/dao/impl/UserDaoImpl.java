/*
 * |-------------------------------------------------
 * | Copyright © 2017 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.entapp.snowman.infrastructure.db.dao.impl;

import com.mycompany.entapp.snowman.infrastructure.db.dao.UserDao;
import com.mycompany.entapp.snowman.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@EnableTransactionManagement
public class UserDaoImpl implements UserDao {

    private static final String GET_USER_WITH_USERID_QUERY = "SELECT * FROM user where id = ?";

    private static final String DELETE_USER_WITH_USERID = "DELETE FROM user where id = ?";

    private static final String GET_ALL_USERS = "SELECT * FROM user";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public User findUser(int userId) {

        return (User) jdbcTemplate.queryForObject(GET_USER_WITH_USERID_QUERY, new Object[]{userId}, new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                User user = new User();
                user.setUserId(rs.getInt("id"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                return user;
            }
        });
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        // TODO implement
        throw new RuntimeException("Not Yet Implemented");
    }

    @Override
    public void removeUser(int userId) {
        jdbcTemplate.update(DELETE_USER_WITH_USERID, userId);
    }

    @Override
    @Transactional
    public List<User> findAllUsers() {

        return (List<User>) jdbcTemplate.query(GET_ALL_USERS, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int i) throws SQLException {
                User user = new User();
                user.setUserId(rs.getInt("id"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                return user;
            }
        });
    }
}
