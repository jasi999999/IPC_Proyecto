package javafxmlapplication;

import java.time.LocalDate;

public class Usuario {
    private String nick;
    private String email;
    private String password;
    private LocalDate fechaNacimiento;

    public Usuario(String nick, String email, String password, LocalDate fechaNacimiento) {
        this.nick = nick;
        this.email = email;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
}