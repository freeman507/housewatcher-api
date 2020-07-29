package br.com.felipezanella.housewatcher.controller;

import br.com.felipezanella.housewatcher.model.Sql;
import br.com.felipezanella.housewatcher.model.TipoSqlEnum;
import br.com.felipezanella.housewatcher.repository.DatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("database")
public class DatabaseController {

    @Autowired
    private DatabaseRepository databaseRepository;

    @PostMapping
    public ResponseEntity<?> query(@RequestBody Sql sql) {

        ResponseEntity responseEntity = ResponseEntity.noContent().build();

        switch (sql.getTipoSQL()) {
            case QUERY:
                responseEntity = ResponseEntity.ok(databaseRepository.executeQuery(sql.getCommand()));
                break;
            case INSERT:
                responseEntity = ResponseEntity.ok(databaseRepository.executeInsert(sql.getCommand()));
                break;
            case UPDATE:
                responseEntity = ResponseEntity.ok(databaseRepository.executeUpdate(sql.getCommand()));
                break;
            case DELETE:
                responseEntity = ResponseEntity.ok(databaseRepository.executeDelete(sql.getCommand()));
                break;
        }

        return responseEntity;
    }
}
