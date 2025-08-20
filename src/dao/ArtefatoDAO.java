package dao;

import model.ArtefatoMagico;

import java.sql.*;

public class ArtefatoDAO {
    private static final String URL = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:6543/postgres";
    private static final String USER = "postgres.vwpfkxbjxmykqdqklmwf";
    private static final String PASSWORD = "Denisroot1";

    public static void postArtefato(ArtefatoMagico artefatoMagico) throws SQLException{
        String comando = "INSERT INTO artefato(codigo_artefato, nome_artefato, nivel_magia, descricao, tipo_artefato, id_mago) VALUES(?,?,?,?,?,?);";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = conn.prepareStatement(comando)) {
            ps.setString(1, artefatoMagico.getCodigo());
            ps.setString(2, artefatoMagico.getNome());
            ps.setInt(3, artefatoMagico.getNivelMagia());
            ps.setString(4, artefatoMagico.getDescricao());
            ps.setString(5, artefatoMagico.getClass().getSimpleName());
            ps.setInt(6, artefatoMagico.getIdMago());
            ps.executeUpdate();
        }
    }

    public static boolean existeArtefatoCadastrado() throws SQLException{
        String comando = "SELECT * FROM artefato;";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = conn.prepareStatement(comando)) {
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

    public static ArtefatoMagico buscarArtfato(String codigo) throws  SQLException{
        String comando = "SELECT * FROM artefato WHERE codigo = ?;";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = conn.prepareStatement(comando)) {
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            ArtefatoMagico artefatoMagico = null;
            if (rs.next()){

            }
            return artefatoMagico;
        }
    }
}
