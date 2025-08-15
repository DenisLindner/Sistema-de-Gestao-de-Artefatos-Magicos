package model;

public abstract class ArtefatoMagico {
    private static int codigoGlobal = 1;
    private String codigo;
    private String nome;
    private int nivelMagia;
    private String descricao;
    private int idMago;

    public ArtefatoMagico(String nome, int nivelMagia, String descricao){
        this.codigo = "A"+codigoGlobal;
        this.nome = nome;
        this.nivelMagia = nivelMagia;
        this.descricao = descricao;
        this.idMago = 0;
        codigoGlobal++;
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

    public void emprestarArtefato(Mago mago){
        this.idMago = mago.getIdMago();
        mago.emprestarArtefato(this);
    }

    public void devolverArtefato(Mago mago){
        this.idMago = 0;
        mago.devolverArtefato(this);
    }

    public abstract void atributoExtra();
}
