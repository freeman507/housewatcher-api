package br.com.felipezanella.housewatcher.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DatabaseRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<Map<String, Object>> executeQuery(String query) {
        return jdbcTemplate.queryForList(query);
    }

    public String executeInsert(String command) {
        int update = jdbcTemplate.update(command);
        return update + " record(s) was(were) inserted";
    }

    public Object executeUpdate(String command) {
        int update = jdbcTemplate.update(command);
        return update + " record(s) was(were) updated";
    }

    public Object executeDelete(String command) {
        int update = jdbcTemplate.update(command);
        return update + " record(s) was(were) deleted";
    }
}
