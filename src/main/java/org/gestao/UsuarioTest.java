package org.gestao;
import org.dao.UsuarioDao;
import org.dominio.Perfil;
import org.dominio.Usuario;

public class UsuarioTest {
    public static void main(String[] args){
        Usuario usuario = new Usuario(1L, "Gustavo Mateus", "1234", "mateus", Perfil.ADMIN, null, null);

        UsuarioDao usuarioDao = new UsuarioDao();
        String mensagem = usuarioDao.salvar(usuario);
        System.out.println(mensagem);
}
}
