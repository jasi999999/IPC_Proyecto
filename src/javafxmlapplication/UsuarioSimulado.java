package javafxmlapplication;

public class UsuarioSimulado {
    private final String nick;
    private final String email;
    private final String password;

    public UsuarioSimulado(String nick, String email, String password) {
        this.nick = nick;
        this.email = email;
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
