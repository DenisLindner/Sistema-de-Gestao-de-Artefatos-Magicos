package model;

import dao.ArtefatoDAO;

import java.sql.SQLException;

public abstract class ArtefatoMagico {
    private String codigo;
    private String nome;
    private int nivelMagia;
    private String descricao;
    private int idMago;

    public ArtefatoMagico(String nome, int nivelMagia, String descricao) throws SQLException {
        this.codigo = "A"+ (ArtefatoDAO.tamanhoTabela()+1);
        this.nome = nome;
        this.nivelMagia = nivelMagia;
        this.descricao = descricao;
        this.idMago = 0;
    }

    public ArtefatoMagico(String codigo, String nome, int nivelMagia, String descricao, int idMago){
        this.codigo = codigo;
        this.nome = nome;
        this.nivelMagia = nivelMagia;
        this.descricao = descricao;
        this.idMago = idMago;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getNivelMagia() {
        return nivelMagia;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getIdMago() {
        return idMago;
    }

    public void emprestarArtefato(Mago mago) throws SQLException{
        this.idMago = mago.getIdMago();
        mago.emprestarArtefato(this);
        ArtefatoDAO.updateArtefato(this);
    }

    public void devolverArtefato(Mago mago) throws SQLException{
        this.idMago = 0;
        mago.devolverArtefato(this);
        ArtefatoDAO.updateArtefato(this);
    }

    public abstract void atributoExtra();

    @Override
    public String toString() {
        return "CÓDIGO: "+this.codigo+
                "\nNOME: "+this.nome+
                "\nNÍVEL MAGIA: "+this.nivelMagia+
                "\nDESCRIÇÃO: "+this.descricao+
                "\nTIPO: "+this.getClass().getSimpleName()+
                "\nSTATUS: "+((this.idMago != 0)? "Emprestado" : "Disponível");
    }
}
