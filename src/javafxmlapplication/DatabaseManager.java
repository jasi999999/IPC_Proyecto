package javafxmlapplication;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:usuarios.db";

    private static String usuarioLogueadoNick = null;
    private static String usuarioLogueadoPassword = null;

    static {
        crearTablaSiNoExiste();
    }

    public static void crearTablaSiNoExiste() {
        String sqlUsuarios = "CREATE TABLE IF NOT EXISTS usuarios (" +
                     "nick TEXT PRIMARY KEY, " +
                     "email TEXT NOT NULL, " +
                     "password TEXT NOT NULL," +
                     "fecha_nacimiento TEXT NOT NULL," +
                     "imagen BLOB" +
                     ")";
        
        String sqlEstadisticas = "CREATE TABLE IF NOT EXISTS usuarios_estadisticas (" +
                                 "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                 "nick TEXT NOT NULL, " +
                                 "resultado TEXT CHECK(resultado IN ('acierto', 'fallo')) NOT NULL, " +
                                 "fecha TEXT NOT NULL, " +
                                 "FOREIGN KEY(nick) REFERENCES usuarios(nick) ON DELETE CASCADE" +
                                 ")";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlUsuarios);
            stmt.execute(sqlEstadisticas);
        } catch (SQLException e) {
            System.err.println("Error creando tabla: " + e.getMessage());
        }
    }

    public static boolean registrarUsuario(String nick, String email, String password, LocalDate birthDate, byte[] imagen) {
        if (usuarioExiste(nick)) return false;
        
        String sql = "INSERT INTO usuarios(nick, email, password, fecha_nacimiento, imagen) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nick);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setString(4, birthDate.toString());
            if (imagen != null){
                pstmt.setBytes(5, imagen);
            } else {
                pstmt.setNull(5, Types.BLOB);
            }
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error registrando usuario: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean modificarPerfil(String nick, String email, String password, LocalDate birthDate, byte[] imagen) {
        if (!usuarioExiste(nick)) return false;
        
        String sql = "UPDATE usuarios SET email = ?, password = ?, fecha_nacimiento = ?, imagen = ? WHERE nick = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            pstmt.setString(3, birthDate.toString());
            if (imagen != null) {
            pstmt.setBytes(4, imagen);
            } else {
                pstmt.setNull(4, Types.BLOB);
            }
            pstmt.setString(5, nick);
            
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
            pstmt.setString(1, nick.trim());
            pstmt.setString(2, password.trim());
            
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
                byte[] imagen = rs.getBytes("imagen"); 
                return new Usuario(nick, email, pass, fechaNacimiento, imagen);
            }
        } catch (SQLException e) {
            System.err.println("Error obteniendo usuario: " + e.getMessage());
        }
        return null;
    }
    
    public static boolean registrarResultado(String nick, boolean acierto) {
        String sql = "INSERT INTO usuarios_estadisticas(nick, resultado, fecha) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nick);
            pstmt.setString(2, acierto ? "acierto" : "fallo");
            pstmt.setString(3, LocalDate.now().toString());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error registrando resultado: " + e.getMessage());
            return false;
        } 
    }

    public static List<Resultado> obtenerResultados(String nick) {
        List<Resultado> resultados = new ArrayList<>();
        String sql = "SELECT resultado, fecha FROM usuarios_estadisticas WHERE nick = ? ORDER BY fecha ASC";
        try (Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nick);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String resultado = rs.getString("resultado");
                String fecha = rs.getString("fecha");
                resultados.add(new Resultado(fecha, resultado));
            }
        } catch (SQLException e) {
            System.err.println("Error obteniendo resultados: " + e.getMessage());
        }
        return resultados;
    }
}