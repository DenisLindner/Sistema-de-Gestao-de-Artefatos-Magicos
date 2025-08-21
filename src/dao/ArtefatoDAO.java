package dao;

import model.ArtefatoDeCombate;
import model.ArtefatoDeCura;
import model.ArtefatoMagico;

import java.sql.*;
import java.util.ArrayList;

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

    public static void updateArtefato(ArtefatoMagico artefatoMagico) throws SQLException{
        String comando = "UPDATE artefato SET id_mago = ? WHERE codigo_artefato = ?;";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = conn.prepareStatement(comando)) {
            ps.setInt(1, artefatoMagico.getIdMago());
            ps.setString(2, artefatoMagico.getCodigo());
            ps.executeUpdate();
        }
    }

    public static ArtefatoMagico buscarArtfato(String codigo) throws  SQLException{
        String comando = "SELECT * FROM artefato WHERE codigo_artefato = ?;";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = conn.prepareStatement(comando)) {
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            ArtefatoMagico artefatoMagico = null;
            if (rs.next()){
                if (rs.getString("tipo_artefato").equals("ArtefatoDeCura")){
                    artefatoMagico = new ArtefatoDeCura(rs.getString("codigo_artefato"), rs.getString("nome_artefato"), rs.getInt("nivel_magia"), rs.getString("descricao"), rs.getInt("id_mago"));
                } else {
                    artefatoMagico = new ArtefatoDeCombate(rs.getString("codigo_artefato"), rs.getString("nome_artefato"), rs.getInt("nivel_magia"), rs.getString("descricao"), rs.getInt("id_mago"));
                }
            }
            return artefatoMagico;
        }
    }

    public static int tamanhoTabela() throws SQLException{
        String comando = "SELECT COUNT(*) FROM artefato;";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = conn.prepareStatement(comando)) {
            ResultSet rs = ps.executeQuery();
            int count = 0;
            if (rs.next()){
                 count = rs.getInt("count");
            }
            return count;
        }
    }

    public static ArrayList<String> codigosArtefatosMago(int idMago) throws SQLException{
        String comando = "SELECT * FROM artefato WHERE id_mago = ?;";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = conn.prepareStatement(comando)) {
            ArrayList<String> codigos = new ArrayList<>();
            ps.setInt(1, idMago);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                codigos.add(rs.getString("codigo_artefato"));
            }
            return codigos;
        }
    }

    public static ArrayList<ArtefatoMagico> artefatosMagicosDisponiveis() throws SQLException{
        String comando = "SELECT * FROM artefato WHERE id_mago = 0;";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = conn.prepareStatement(comando)) {
            ArrayList<ArtefatoMagico> artefatos = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                ArtefatoMagico artefatoMagico = null;
                if (rs.getString("tipo_artefato").equals("ArtefatoDeCura")){
                    artefatoMagico = new ArtefatoDeCura(rs.getString("codigo_artefato"), rs.getString("nome_artefato"), rs.getInt("nivel_magia"), rs.getString("descricao"), rs.getInt("id_mago"));
                } else {
                    artefatoMagico = new ArtefatoDeCombate(rs.getString("codigo_artefato"), rs.getString("nome_artefato"), rs.getInt("nivel_magia"), rs.getString("descricao"), rs.getInt("id_mago"));
                }
                artefatos.add(artefatoMagico);
            }
            return artefatos;
        }
    }
}
