package com.example.edunext.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;


@Transactional
@Repository
public class UserDaoImpl {

    @Autowired
    @Qualifier("jdbcTemplate1")
    private JdbcTemplate jdbcTemplate1;

    @Autowired
    @Qualifier("jdbcTemplate2")
    private Map<String,JdbcTemplate> jdbcTemplate2;

    public List getAllUser() {

        String sql2 = "select name,address from studentprofiles";
        //get users list from db2
        JdbcTemplate jdbcTemplatelocal = jdbcTemplate2.get("lfis.edunext1.com");
        List list2 = jdbcTemplatelocal.query(sql2, new UserRowMapper());
        return list2;
    }
    public User getUserByUserName(HttpServletRequest request, String username) {
        User user=new User();
        String url=request.getRequestURL().toString();
        String sql2 = "select userid,password from app_users where userid="+username;
        //get users list from db2
        JdbcTemplate jdbcTemplatelocal = jdbcTemplate2.get("lfis.edunext1.com");
        List list2 = jdbcTemplatelocal.query(sql2, new AppUserRowMapper());
        if(list2!=null) {
            return (User) list2.get(0);
        }
        else
        {
            return null;
        }

    }


    public static class UserRowMapper implements RowMapper{

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUsername(rs.getString("name"));

            return user;
        }

    }public static class AppUserRowMapper implements RowMapper{

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUsername(rs.getString("userid"));
            user.setPassword(rs.getString("password"));

            return user;
        }

    }
    public static class DBRowMapper implements RowMapper{

        @Override
        public Domains mapRow(ResultSet rs, int rowNum) throws SQLException {
            Domains domains= new Domains();
            domains.setDomain(rs.getString("domain"));
            domains.setDatabase(rs.getString("database"));
            return domains;
        }

    }

}