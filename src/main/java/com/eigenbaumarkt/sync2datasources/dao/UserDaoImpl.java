package com.eigenbaumarkt.sync2datasources.dao;

import com.eigenbaumarkt.sync2datasources.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Transactional
@Repository
public class UserDaoImpl {

    @Autowired
    @Qualifier("jdbcTemplatePg")
    private JdbcTemplate jdbcTemplatePg;

    @Autowired
    @Qualifier("jdbcTemplateMs")
    private JdbcTemplate jdbcTemplateMs;

    public List getAllUser() {
        String sql1 = "select username,email from test";
        //get users list from Postgresql-DB
        List list1 = jdbcTemplatePg.query(sql1, new UserRowMapper());

        String sql2 = "select username,email from test";
        //get users list from MS-SQL-DB
        List list2 = jdbcTemplateMs.query(sql2, new UserRowMapper());

        List listAll = (List) Stream.concat(list1.stream(), list2.stream())
                .collect(Collectors.toList());
        return listAll;
    }

    class UserRowMapper implements RowMapper {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));

            return user;
        }

    }

}
