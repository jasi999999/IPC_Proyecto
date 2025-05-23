package javafxmlapplication;

import java.util.ArrayList;
import java.util.List;

public class UsuarioManager {
    private static final List<Usuario> listaUsuarios = new ArrayList<>();

    public static boolean existeNick(String nick) {
        return listaUsuarios.stream().anyMatch(u -> u.getNick().equalsIgnoreCase(nick));
    }

    public static void registrarUsuario(Usuario usuario) {
        listaUsuarios.add(usuario);
    }

    public static Usuario autenticar(String nick, String pass) {
        return listaUsuarios.stream()
            .filter(u -> u.getNick().equals(nick) && u.getPassword().equals(pass))
            .findFirst()
            .orElse(null);
    }
}
