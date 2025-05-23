package javafxmlapplication;

import java.time.LocalDate;

public class Usuario {
    private String nick;
    private String email;
    private String password;
    private LocalDate fechaNacimiento;
    private byte[] imagen;
    
    public Usuario(String nick, String email, String password, LocalDate fechaNacimiento, byte[] imagen) {
        this.nick = nick;
        this.email = email;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
        this.imagen = imagen;
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

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}