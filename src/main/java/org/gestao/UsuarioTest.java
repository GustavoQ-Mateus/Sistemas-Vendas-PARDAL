package org.gestao;
import PRDLdao.UsuarioDao;
import PRDLdominio.Perfil;
import PRDLdominio.Usuario;

public class UsuarioTest {
    public static void main(String[] args){
        Usuario usuario = new Usuario(0L, "Gustavo Queiroz", "879231", "pardal", Perfil.ADMIN, null, null);

        UsuarioDao usuarioDao = new UsuarioDao();
        String mensagem = usuarioDao.salvar(usuario);
        System.out.println(mensagem);
}
}
