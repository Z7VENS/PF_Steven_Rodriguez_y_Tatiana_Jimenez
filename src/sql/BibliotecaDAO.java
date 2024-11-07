package sql;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

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

            stmt.registerOutParameter(3, Types.BOOLEAN);
            stmt.execute();

            return stmt.getBoolean(3);
        }
    }
}

