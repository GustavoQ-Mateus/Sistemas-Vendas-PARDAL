package org.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMysql implements Conexao {

    private final String USUARIO = "root";
    private final String SENHA = "879231";
    private final String URL = "jdbc:mysql://127.0.0.1:3306/system_sales";
    private Connection conectar;

    @Override
    public Connection obteConexao() throws SQLException {
        if (conectar == null) {
            conectar = DriverManager.getConnection(URL, USUARIO, SENHA);
        }
        return conectar;
    }
}














