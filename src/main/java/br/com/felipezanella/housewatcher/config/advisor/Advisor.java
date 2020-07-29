package br.com.felipezanella.housewatcher.config.advisor;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class Advisor {

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public ResponseEntity<Map<String, Object>> advisor(SQLException exception) {
        exception.printStackTrace();
        Map<String, Object> map = new HashMap<>();
        map.put("sqlErro", exception.getMessage());

        return ResponseEntity.badRequest().body(map);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Map<String, Object>> advisor(Exception exception) {
        exception.printStackTrace();
        Map<String, Object> map = new HashMap<>();
        map.put("erro", exception.getMessage());

        return ResponseEntity.badRequest().body(map);
    }


}
