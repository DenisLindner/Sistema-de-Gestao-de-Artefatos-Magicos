package dao;

import model.ArtefatoMagico;

import java.sql.*;
import java.util.ArrayList;

public class ArtefatoDAOImpl {
    public static ArrayList<ArtefatoMagico> artefatoMagicosMago(int idMago) throws SQLException{
        ArrayList<String> codigos = ArtefatoDAO.codigosArtefatosMago(idMago);
        ArrayList<ArtefatoMagico> artefatos = new ArrayList<>();

        for (String codigo : codigos){
            artefatos.add(ArtefatoDAO.buscarArtfato(codigo));
        }
        return artefatos;
    }


}
