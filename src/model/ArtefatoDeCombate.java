package model;

import java.sql.SQLException;

public class ArtefatoDeCombate extends ArtefatoMagico {

    public ArtefatoDeCombate(String nome, int nivelMagia, String descricao) throws SQLException {
        super(nome, nivelMagia, descricao);
    }

    public ArtefatoDeCombate(String codigo, String nome, int nivelMagia, String descricao, int idMago){
        super(codigo, nome, nivelMagia, descricao, idMago);
    }

    @Override
    public void atributoExtra(){
        int ataque = 5;
        System.out.println("Ataque: "+(ataque*this.getNivelMagia()));
    }
}
