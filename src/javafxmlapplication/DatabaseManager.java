package javafxmlapplication;

import java.sql.*;
import java.time.LocalDate;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:usuarios.db";
    private static String usuarioLogueadoNick = null;
    private static String usuarioLogueadoPassword = null;

    static {
        crearTablaSiNoExiste();
    }
    
    public static void crearTablaSiNoExiste() {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
                     "nick TEXT PRIMARY KEY, " +
                     "email TEXT NOT NULL, " +
                     "password TEXT NOT NULL," +
                     "fecha_nacimiento TEXT NOT NULL" +
                     ")";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Error creando tabla: " + e.getMessage());
        }
    }

    public static boolean registrarUsuario(String nick, String email, String password, LocalDate birthDate) {
        if (usuarioExiste(nick)) return false;
        
        String sql = "INSERT INTO usuarios(nick, email, password, fecha_nacimiento) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nick);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setString(4, birthDate.toString());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error registrando usuario: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean modificarPerfil(String nick, String email, String password, LocalDate birthDate) {
        if (!usuarioExiste(nick)) return false;
        
        String sql = "UPDATE usuarios SET email = ?, password = ?, fecha_nacimiento = ? WHERE nick = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            pstmt.setString(3, birthDate.toString());
            pstmt.setString(4, nick);
            
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error modificando perfil: " + e.getMessage());
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
            if (rs.next()) {
                usuarioLogueadoNick = nick;
                usuarioLogueadoPassword = password;
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error autenticando: " + e.getMessage());
        }
        return false;
    }
    
    public static Usuario obtenerUsuario(boolean valido, String nick, String password) {
        if (!valido || usuarioLogueadoNick == null || !usuarioLogueadoNick.equals(nick) || !usuarioLogueadoPassword.equals(password)) {
            return null;
        }

        String sql = "SELECT * FROM usuarios WHERE nick = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nick);
            pstmt.setString(2, usuarioLogueadoPassword);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String email = rs.getString("email");
                String pass = rs.getString("password");
                LocalDate fechaNacimiento = LocalDate.parse(rs.getString("fecha_nacimiento"));
                return new Usuario(nick, email, pass, fechaNacimiento);
            }
        } catch (SQLException e) {
            System.err.println("Error obteniendo usuario: " + e.getMessage());
        }
        return null;
    }
}