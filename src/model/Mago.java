package model;

import java.sql.SQLException;
import java.util.ArrayList;

public class Mago {
    private int idMago;
    private String nome;
    private int nivel;
    private ArrayList<ArtefatoMagico> artefatos;

    public Mago(int idMago, String nome, int nivel){
        this.idMago = idMago;
        this.nome = nome;
        this.nivel = nivel;
        this.artefatos = new ArrayList<>();
    }

    public int getIdMago() {
        return idMago;
    }

    public String getNome() {
        return nome;
    }

    public int getNivel() {
        return nivel;
    }

    public void setArtefatos(ArrayList<ArtefatoMagico> artefatos) {
        this.artefatos = artefatos;
    }

    public ArrayList<ArtefatoMagico> getArtefatos() {
        return artefatos;
    }

    public void emprestarArtefato(ArtefatoMagico artefatoMagico) throws SQLException {
        this.artefatos.add(artefatoMagico);
    }

    public void devolverArtefato(ArtefatoMagico artefatoMagico) throws SQLException{
        this.artefatos.remove(artefatoMagico);
    }

    public boolean verificarQuantidadeEmprestimo(){
        return this.artefatos.size() >= 3;
    }

    @Override
    public String toString() {
        return "ID: "+this.idMago+
                "\nNOME: "+this.nome+
                "\nNÌVEL: "+this.nivel+
                "\nQTD ARTEFATOS EMPRESTADOS: "+this.artefatos.size();
    }
}
