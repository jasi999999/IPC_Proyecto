package javafxmlapplication;

public class UsuarioSimulado {
    private final String nick;
    private final String email;
    private final String password;
    private final String birthDate;

    public UsuarioSimulado(String nick, String email, String password, String birthDate) {
        this.nick = nick;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
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
    
    public String getBirthDate() {
        return birthDate;
    }
}
