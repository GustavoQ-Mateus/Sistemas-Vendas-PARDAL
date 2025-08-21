package org.dao;

import org.Modelo.LoginDto;
import org.dominio.Perfil;
import org.dominio.Usuario;
import org.exception.NegocioException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.swing.*;

public class AutenticacaoDao {
    private final UsuarioDao usuarioDao;
    public AutenticacaoDao() {
        this.usuarioDao = new UsuarioDao();
    }

    public boolean temPermissao(Usuario usuario) {
        try {
            permissao(usuario);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Usuario sem permissao", 0);
            return false;

        }
    }
    private void permissao(Usuario usuario) {
        if (!Perfil.ADMIN.equals(usuario.getPerfil())) {
            throw new NegocioException("Sem permissao para realizar essa acao");
        }
    }

    public Usuario login(LoginDto login) {
        Usuario usuario = usuarioDao.buscarUsuarioPeloUsuario(login.getUsuario());

        if (usuario == null || !usuario.isEstado())
            return null;
        if(usuario.isEstado() && validaSenha(usuario.getSenha(), login.getSenha())) {
            return usuario;
        }
        return null;
    }
    //Sem a utilização do Spring Security
//    private boolean validaSenhaSemSeguranca(String senhaUsuario, String senhaLogin) {
//        return senhaUsuario.equals(senhaLogin);
//    }
    private boolean validaSenha(String senhaUsuario, String senhaLogin) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return passwordEncoder.matches(senhaLogin, senhaUsuario);
    }
}






























