package javafxmlapplication;

import java.util.ArrayList;
import java.util.List;

public class UsuarioManager {
    private static final List<UsuarioSimulado> listaUsuarios = new ArrayList<>();

    public static boolean existeNick(String nick) {
        return listaUsuarios.stream().anyMatch(u -> u.getNick().equalsIgnoreCase(nick));
    }

    public static void registrarUsuario(UsuarioSimulado usuario) {
        listaUsuarios.add(usuario);
    }

    public static UsuarioSimulado autenticar(String nick, String pass) {
        return listaUsuarios.stream()
            .filter(u -> u.getNick().equals(nick) && u.getPassword().equals(pass))
            .findFirst()
            .orElse(null);
    }
}
