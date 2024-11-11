package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaDAO {
    public void prestarLibro(String carnet, int idLibro, String fechaPrestamo, String fechaDevolucion) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement stmt = connection.prepareCall("{call sp_prestar_libro(?, ?, ?, ?)}")) {
            stmt.setString(1, carnet);
            stmt.setInt(2, idLibro);
            stmt.setString(3, fechaPrestamo);
            stmt.setString(4, fechaDevolucion);
            stmt.execute();
        }
    }

    public void devolverLibro(int idPrestamo) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement stmt = connection.prepareCall("{call sp_devolver_libro(?)}")) {
            stmt.setInt(1, idPrestamo);
            stmt.execute();
        }
    }

    public void prestarEquipo(String carnet, int idEquipo, String fechaPrestamo, String fechaDevolucion) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement stmt = connection.prepareCall("{call sp_prestar_equipo(?, ?, ?, ?)}")) {
            stmt.setString(1, carnet);
            stmt.setInt(2, idEquipo);
            stmt.setString(3, fechaPrestamo);
            stmt.setString(4, fechaDevolucion);
            stmt.execute();
        }
    }

    public void devolverEquipo(int idPrestamo) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement stmt = connection.prepareCall("{call sp_devolver_equipo(?)}")) {
            stmt.setInt(1, idPrestamo);
            stmt.execute();
        }
    }

    public void agregarEstudiante(String carnet, String nombre, String email, String telefono) throws SQLException {
        String query = "{CALL sp_agregar_estudiante(?, ?, ?, ?)}";
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement stmt = connection.prepareCall(query)) {
            stmt.setString(1, carnet);
            stmt.setString(2, nombre);
            stmt.setString(3, email);
            stmt.setString(4, telefono);
            stmt.executeUpdate();
        }
    }

    public boolean validarLoginColaborador(String email, String contraseña) throws SQLException {
        String query = "{CALL sp_validar_login_colaborador(?, ?, ?)}";
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement stmt = connection.prepareCall(query)) {
            stmt.setString(1, email);
            stmt.setString(2, contraseña);
    
            stmt.registerOutParameter(3, Types.TINYINT); // Cambiar a Types.TINYINT
            stmt.execute();
    
                return stmt.getInt(3) == 1; // Cambiar a getInt y verificar si es igual a 1
            }
        }

        // Método para obtener los préstamos activos de un usuario
public List<String> obtenerPrestamosActivos(String carnet) {
    List<String> prestamosActivos = new ArrayList<>();
    CallableStatement stmt = null;
    ResultSet rs = null;

    try {
        
        Connection connection = DatabaseConnection.getConnection();

        // Llamar al SP 'obtener_prestamos_activos' pasando el carnet
        stmt = connection.prepareCall("{call obtener_prestamos_activos(?)}");
        stmt.setString(1, carnet); // Asignar el carnet al parámetro
        rs = stmt.executeQuery();

        // Procesar los resultados del SP
        while (rs.next()) {
           
            String equipo = rs.getString("id_equipo");
            prestamosActivos.add( " ID Equipo: " + equipo); 
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Manejo de errores
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return prestamosActivos;
}
}

