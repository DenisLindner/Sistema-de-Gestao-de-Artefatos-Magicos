package model;

public class ArtefatoDeCura extends ArtefatoMagico{
    private int cura = 5;

    public ArtefatoDeCura(String nome, int nivelMagia, String descricao){
        super(nome, nivelMagia, descricao);
    }

    public ArtefatoDeCura(String codigo, String nome, int nivelMagia, String descricao, int idMago){
        super(codigo, nome, nivelMagia, descricao, idMago);
    }

    @Override
    public void atributoExtra(){
        System.out.println("Cura: "+(cura*this.getNivelMagia()));
    }
}
