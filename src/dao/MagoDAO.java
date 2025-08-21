package dao;

import conexao.ConectarBanco;
import model.Mago;

import java.sql.*;
import java.util.ArrayList;

public class MagoDAO {
    public static void postMago(Mago mago) throws SQLException {
        String comando = "INSERT INTO mago(id_mago, nome_mago, nivel_mago) VALUES(?,?,?);";
        try (Connection conn = ConectarBanco.conectar(); PreparedStatement ps = conn.prepareStatement(comando)) {
            ps.setInt(1, mago.getIdMago());
            ps.setString(2, mago.getNome());
            ps.setInt(3, mago.getNivel());
            ps.executeUpdate();
        }
    }

    public static Mago buscarMago(int id) throws  SQLException{
        String comando = "SELECT * FROM mago WHERE id_mago = ?;";
        try (Connection conn = ConectarBanco.conectar(); PreparedStatement ps = conn.prepareStatement(comando)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Mago mago = null;
            if (rs.next()){
                mago = new Mago(rs.getInt("id_mago"), rs.getString("nome_mago"), rs.getInt("nivel_mago"));
                mago.setArtefatos(ArtefatoDAO.artefatoMagicosMago(mago.getIdMago()));
            }
            return mago;
        }
    }

    public static boolean existeCodigoMago(int idMago) throws SQLException{
        String comando = "SELECT * FROM mago WHERE id_mago = ?;";
        try (Connection conn = ConectarBanco.conectar(); PreparedStatement ps = conn.prepareStatement(comando)) {
            ps.setInt(1, idMago);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

    public static boolean existeMagoCadastrado() throws SQLException{
        String comando = "SELECT * FROM mago;";
        try (Connection conn = ConectarBanco.conectar(); PreparedStatement ps = conn.prepareStatement(comando)) {
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

    public static ArrayList<Mago> getTodosMagos() throws SQLException{
        String comando = "SELECT * FROM mago;";
        try (Connection conn = ConectarBanco.conectar(); PreparedStatement ps = conn.prepareStatement(comando)) {
            ResultSet rs = ps.executeQuery();
            ArrayList<Mago> magos = new ArrayList<>();
            if (rs.next()){
                Mago mago = new Mago(rs.getInt("id_mago"), rs.getString("nome_mago"), rs.getInt("nivel_mago"));
                mago.setArtefatos(ArtefatoDAO.artefatoMagicosMago(mago.getIdMago()));
                magos.add(mago);
            }
            return magos;
        }
    }

}
