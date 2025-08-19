package org.conexao;

import java.sql.Connection;
import java.sql.SQLException;

public interface Conexao {
    public Connection obteConexao() throws SQLException;


}
