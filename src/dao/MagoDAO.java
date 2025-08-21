package dao;


import model.Mago;

import java.sql.*;
import java.util.ArrayList;

public class MagoDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/guilda_arcanos";
    private static final String USER = "seu_usuario";
    private static final String PASSWORD = "sua_senha";

    public static void postMago(Mago mago) throws SQLException {
        String comando = "INSERT INTO mago(id_mago, nome_mago, nivel_mago) VALUES(?,?,?);";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = conn.prepareStatement(comando)) {
            ps.setInt(1, mago.getIdMago());
            ps.setString(2, mago.getNome());
            ps.setInt(3, mago.getNivel());
            ps.executeUpdate();
        }
    }

    public static Mago buscarMago(int id) throws  SQLException{
        String comando = "SELECT * FROM mago WHERE id_mago = ?;";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = conn.prepareStatement(comando)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Mago mago = null;
            if (rs.next()){
                mago = new Mago(rs.getInt("id_mago"), rs.getString("nome_mago"), rs.getInt("nivel_mago"));
                mago.setArtefatos(ArtefatoDAOImpl.artefatoMagicosMago(mago.getIdMago()));
            }
            return mago;
        }
    }

    public static boolean existeCodigoMago(int idMago) throws SQLException{
        String comando = "SELECT * FROM mago WHERE id_mago = ?;";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = conn.prepareStatement(comando)) {
            ps.setInt(1, idMago);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

    public static boolean existeMagoCadastrado() throws SQLException{
        String comando = "SELECT * FROM mago;";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = conn.prepareStatement(comando)) {
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

    public static ArrayList<Integer> idsMagos() throws SQLException{
        String comando = "SELECT * FROM mago;";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = conn.prepareStatement(comando)) {
            ArrayList<Integer> ids = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                ids.add(rs.getInt("id_mago"));
            }
            return ids;
        }
    }

}
