package model;

import java.sql.SQLException;

public class ArtefatoDeCura extends ArtefatoMagico{
    public ArtefatoDeCura(String nome, int nivelMagia, String descricao) throws SQLException {
        super(nome, nivelMagia, descricao);
    }

    public ArtefatoDeCura(String codigo, String nome, int nivelMagia, String descricao, int idMago){
        super(codigo, nome, nivelMagia, descricao, idMago);
    }

    @Override
    public void atributoExtra(){
        int CURA = 5;
        System.out.println("Cura: "+(CURA *this.getNivelMagia()));
    }
}
