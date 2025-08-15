package dao;

import model.ArtefatoMagico;

import java.sql.*;

public class ArtefatoDAO {
    private static final String URL = "jdbc:postgresql://localhost:port/database";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public static void postArtefato(ArtefatoMagico artefatoMagico) throws SQLException{
        String comando = "INSERT INTO artefato(codigo_artefato, nome_artefato, nivel_magia, descricao, tipo_artefato, id_mago) VALUES(?,?,?,?,?,?);";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = conn.prepareStatement(comando)) {
            ps.setString(1, artefatoMagico.getCodigo());
            ps.setString(2, artefatoMagico.getNome());
            ps.setInt(3, artefatoMagico.getNivelMagia());
            ps.setString(4, artefatoMagico.getDescricao());
            ps.setString(5, artefatoMagico.getClass().getName());
            ps.setInt(6, artefatoMagico.getIdMago());
            ps.executeUpdate();
        }
    }


}
