package org.gestao;

import org.conexao.Conexao;
import org.conexao.ConexaoMysql;
import org.dominio.Categoria;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        String sql = "SELECT * FROM categoria";
        Conexao conexao = new ConexaoMysql();
        Categoria categoria = new Categoria(null, "Bebida","java");

        String inserirSQL = "INSERT INTO categoria(nome, descricao) VALUES (?, ?)";

        PreparedStatement prepareStatement = conexao.obteConexao().prepareStatement(inserirSQL);

        prepareStatement.setString(1, categoria.getNome());
        prepareStatement.setString(2, categoria.getDescricao());

        int resultadoDoCadastro = prepareStatement.executeUpdate();
        System.out.println(resultadoDoCadastro);

        ResultSet result = conexao.obteConexao().prepareStatement(sql).executeQuery();
        while (result.next()) {
            System.out.println(result.getString("nome"));
        }
    }
}


















