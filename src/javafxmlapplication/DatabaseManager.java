package javafxmlapplication;

import java.sql.*;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:usuarios.db";

    static {
        crearTablaSiNoExiste();
    }

    public static void crearTablaSiNoExiste() {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
                     "nick TEXT PRIMARY KEY, " +
                     "email TEXT NOT NULL, " +
                     "password TEXT NOT NULL)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Error creando tabla: " + e.getMessage());
        }
    }

    public static boolean registrarUsuario(String nick, String email, String password) {
        if (usuarioExiste(nick)) return false;
        String sql = "INSERT INTO usuarios(nick, email, password) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nick);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error registrando usuario: " + e.getMessage());
            return false;
        }
    }

    public static boolean usuarioExiste(String nick) {
        String sql = "SELECT nick FROM usuarios WHERE nick = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nick);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("Error verificando usuario: " + e.getMessage());
            return false;
        }
    }

    public static boolean autenticarUsuario(String nick, String password) {
        String sql = "SELECT nick FROM usuarios WHERE nick = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nick);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("Error autenticando: " + e.getMessage());
            return false;
        }
    }
}
