package model;

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

    public ArrayList<ArtefatoMagico> getArtefatos() {
        return artefatos;
    }

    public void emprestarArtefato(ArtefatoMagico artefatoMagico){
        this.artefatos.add(artefatoMagico);
    }

    public void devolverArtefato(ArtefatoMagico artefatoMagico){
        this.artefatos.remove(artefatoMagico);
    }
}
