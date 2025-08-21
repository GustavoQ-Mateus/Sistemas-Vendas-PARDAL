package org.dao;

import org.conexao.Conexao;
import org.conexao.ConexaoMysql;
import org.dominio.Perfil;
import org.dominio.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    private final Conexao conexao;
    public UsuarioDao() {
        this.conexao = new ConexaoMysql();
    }

    public String salvar(Usuario usuario) {
        return usuario.getId() == 0L ? adicionar(usuario) : editar(usuario);
    }

    private String adicionar(Usuario usuario) {
        String sql = "INSERT INTO usuario(nome, usuario, senha, perfil, estado) VALUES (?, ?, ?, ?, ?)";

        Usuario usuarioTemp = buscarUsuarioPeloUsuario(usuario.getUsuario());

        if(usuarioTemp != null){
            return String.format("Error1: usuario %s ja existe no banco de dados. ", usuario.getUsuario());
        }
        try {
        PreparedStatement preparedStatement = conexao.obteConexao().prepareStatement(sql);
        preencherValoresPreperedStatment(preparedStatement, usuario);
        int resultado = preparedStatement.executeUpdate();
        return resultado == 1 ? "Usuario adicionado com sucesso" : "Não foi possivel adicionar usuario";
    }catch (SQLException e) {
        return String.format("Error2: %s", e.getMessage());
    }
    }

    private String editar(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, usuario = ?, senha = ?, perfil = ?, estado = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conexao.obteConexao().prepareStatement(sql);

            preencherValoresPreperedStatment(preparedStatement, usuario);

            int resultado = preparedStatement.executeUpdate();
            return resultado == 1 ? "Usuario editado com sucesso" : "Não foi possivel editar usuario";
        }catch (SQLException e) {
            return String.format("Error3: %s", e.getMessage());
        }
    }

    private void preencherValoresPreperedStatment(PreparedStatement preparedStatement, Usuario usuario) throws SQLException {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String senhaCripto = passwordEncoder.encode(usuario.getSenha());

        preparedStatement.setString(1, usuario.getNome());
        preparedStatement.setString(2, usuario.getUsuario());
        preparedStatement.setString(3, senhaCripto);
        preparedStatement.setString(4, usuario.getPerfil().name());
        preparedStatement.setBoolean(5, usuario.isEstado());

        if (usuario.getId() != 0L) {
            preparedStatement.setLong(6, usuario.getId());
        }
    }
    public List<Usuario>  buscarTodosUsuario(){
        String sql = "SELECT * FROM usuario";
        List<Usuario> usuarios = new ArrayList<>();
        try{
            ResultSet result = conexao.obteConexao().prepareStatement(sql).executeQuery();
            while(result.next()){
                usuarios.add(getUsuario(result));
            }
        }catch (SQLException e){
            System.out.println(String.format("Error4: %s", e.getMessage()));
        }
        return  usuarios;
    }
    private Usuario getUsuario(ResultSet result) throws SQLException {
    Usuario usuario = new Usuario();
    usuario.setId(result.getLong("id"));
    usuario.setNome(result.getString("nome"));
    usuario.setUsuario(result.getString("usuario"));
    usuario.setSenha(result.getString("senha"));
    usuario.setPerfil(result.getObject("perfil", Perfil.class));
    usuario.setEstado(result.getBoolean("estado"));
    usuario.setDatahoraCriacao(result.getObject("data hora criacao", LocalDateTime.class));
    usuario.setUltimoLogin(result.getObject("ultimo login", LocalDateTime.class));

    return usuario;
    }
    public Usuario buscarUsuarioPeloId(Long Id){
        String sql = String.format("SELECT * FROM usuario WHERE id = %s", Id);

        try{
            ResultSet result = conexao.obteConexao().prepareStatement(sql).executeQuery();
            if(result.next()){
                return getUsuario(result);
            }
        }catch (SQLException e){
            System.out.println(String.format("Error5: %s", e.getMessage()));
        }
        return null;
    }
    public Usuario buscarUsuarioPeloUsuario(String usuario){
        String sql = "SELECT * FROM usuario WHERE usuario = ?";
        try {
            PreparedStatement ps = conexao.obteConexao().prepareStatement(sql);
            ps.setString(1, usuario);
            ResultSet result = ps.executeQuery();
            if(result.next()){
                return getUsuario(result);
            }
        } catch (SQLException e){
            System.out.println(String.format("Error6: %s", e.getMessage()));
        }
        return null;
    }


}














