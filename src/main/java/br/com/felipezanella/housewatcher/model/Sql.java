package br.com.felipezanella.housewatcher.model;

import lombok.Data;

@Data
public class Sql {

    private TipoSqlEnum tipoSQL;

    private String command;

}
