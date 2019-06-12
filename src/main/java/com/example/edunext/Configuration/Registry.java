package com.example.edunext.Configuration;

import com.example.edunext.model.Domains;
import com.example.edunext.model.User;
import com.example.edunext.model.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class Registry {
    private static final Map<String, Object> registry = new HashMap<String, Object>();

    @Autowired
    @Qualifier("jdbcTemplate1")
    private JdbcTemplate jdbcTemplate1;


    @Autowired
    @Qualifier("jdbcTemplate2")
    @Lazy
    private Map<String,JdbcTemplate> jdbcTemplate2;

    public Map<String, String> getdb()
    {
        Map<String,String> databases= new HashMap<String, String>();
            String sql1 = "select domain,`database` from domains";
            List<Domains> list1 = jdbcTemplate1.query(sql1, new UserDaoImpl.DBRowMapper());
            for(Domains domains: list1)
            {
                databases.put(domains.getDomain(),domains.getDatabase());
            }
            return databases;
    }
    @Bean(name = "db2")
    @DependsOn("db1")
    @ConditionalOnBean(Registry.class)
    public Map<String,DataSource> dataSource2() {
//        return DataSourceBuilder.create().build();
        Map<String,DataSource> ds= new HashMap<String,DataSource>();
        Map<String,String> databses= getdb(); //Get All the dbs from here
        for(Map.Entry entry: databses.entrySet())
        {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            String jdbcurl="jdbc:mysql://localhost:3306/"+entry.getValue();
            dataSource.setUrl(jdbcurl);
            dataSource.setUsername("root");
            dataSource.setPassword("hrhk");
            ds.put(String.valueOf(entry.getKey()),dataSource);
        }

        return ds;
    }

    @Bean(name = "jdbcTemplate2")
    @DependsOn("db2")
    public Map<String,JdbcTemplate> jdbcTemplate2(@Qualifier("db2") Map<String,DataSource> ds) {
        Map<String,JdbcTemplate> jdbcTemplateMap= new HashMap<String, JdbcTemplate>();
        for(Map.Entry entry : ds.entrySet())
        {
            jdbcTemplateMap.put((String) entry.getKey(),new JdbcTemplate((DataSource) entry.getValue()));
        }
        return jdbcTemplateMap;
    }


}
