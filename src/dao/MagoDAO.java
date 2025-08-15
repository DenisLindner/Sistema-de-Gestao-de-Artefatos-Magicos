package dao;

import model.Mago;

import java.sql.*;

public class MagoDAO {
    private static final String URL = "jdbc:postgresql://localhost:port/database";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public static void postMago(Mago mago) throws SQLException {
        String comando = "INSERT INTO mago(id_mago, nome_mago, nivel_mago) VALUES(?,?,?);";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = conn.prepareStatement(comando)) {
            ps.setInt(1, mago.getIdMago());
            ps.setString(2, mago.getNome());
            ps.setInt(3, mago.getNivel());
            ps.executeUpdate();
        }
    }

    public static boolean existeCodigoMago(int idMago) throws SQLException{
        String comando = "SELECT * FROM mago;";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = conn.prepareStatement(comando)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                if (rs.getInt("id_mago") == idMago){
                    return true;
                }
            }
            return false;
        }
    }
}
