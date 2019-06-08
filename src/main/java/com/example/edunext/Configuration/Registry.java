package com.example.edunext.Configuration;

import com.example.edunext.model.User;
import com.example.edunext.model.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class Registry {
    private static final Map<String, Object> registry = new HashMap<String, Object>();

    @Autowired
    @Qualifier("jdbcTemplate1")
    private JdbcTemplate jdbcTemplate1;

    public String getdb()
    {

            String sql1 = "select email from user1";
            List<User> list1 = jdbcTemplate1.query(sql1, new UserDaoImpl.UserRowMapper());
            return list1.get(0).getEmail();
    }
    @Bean(name = "db2")
    @DependsOn("db1")
    @ConfigurationProperties(prefix = "spring.second-db")
    public DataSource dataSource2() {
//        return DataSourceBuilder.create().build();
        try {


            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            String jdbcurl="jdbc:mysql://localhost:3306/"+getdb();
            dataSource.setUrl(jdbcurl);
            dataSource.setUsername("root");
            dataSource.setPassword("hrhk");
            return dataSource;
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    @Bean(name = "jdbcTemplate2")
    public JdbcTemplate jdbcTemplate2(@Qualifier("db2") DataSource ds) {
        if(ds==null)
        {
            return null;
        }
        else {
            return new JdbcTemplate(ds);
        }
    }

}
