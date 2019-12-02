package com.example.springyamlpropsam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepository {
    @Autowired
    @Qualifier("testDb2JdbcTemplate")
    private JdbcTemplate dbServer1Jdbc;

    public List<User> getUsers(){
        List<User> users = dbServer1Jdbc.query("select * from user", new BeanPropertyRowMapper(User.class));
        return users;
    }

    public User getUser(String email) {
        try{
            User user = (User)dbServer1Jdbc.queryForObject(String.format("select * from user where email='%s' limit 1", email),
                    new BeanPropertyRowMapper(User.class));
            return user;
        }
        catch(EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    public void createUser(User user) {
        String sql = String.format("insert into user(name, email) values('%s', '%s')", user.getName(), user.getEmail());
        dbServer1Jdbc.execute(sql);
    }
}
