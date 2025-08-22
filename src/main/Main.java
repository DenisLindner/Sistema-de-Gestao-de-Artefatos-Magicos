package main;

import dao.ArtefatoDAO;
import dao.MagoDAO;
import model.ArtefatoDeCombate;
import model.ArtefatoDeCura;
import model.ArtefatoMagico;
import model.Mago;
import entradas.*;

import java.sql.SQLException;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws  SQLException{
        while (true){
            System.out.println("=== Menu Principal ===\n1 - Cadastrar mago\n2 - Cadastrar artefato\n3 – Emprestar artefato para mago\n4 – Listar artefatos emprestados de um mago\n5 – Devolver artefato\n6 - Listar todos os artefatos disponíveis\n0 - Sair:\nEscolher:");
            int opcao = Entradas.entradaInteiro();
            switch (opcao){
                case 1 -> cadastrarMago();
                case 2 -> cadastrarArtefato();
                case 3 -> emprestarArtefatoMago();
                case 4 -> listarArtefatosMago();
                case 5 -> devolverArtefato();
                case 6 -> listarTodosArtefatosDisponiveis();
                case 0 -> {
                    System.out.println("Saindo do Sistema...");
                    System.exit(0);
                }
                default -> System.out.println("Opção Inválida!");
            }
        }
    }

    public static void cadastrarMago() throws SQLException{
        System.out.println("Insira o ID do Mago:");
        int idMago = Entradas.entradaInteiro();
        if (MagoDAO.existeCodigoMago(idMago)){
            System.out.println("Código já cadastrado!");
            return;
        }

        System.out.println("Insira o Nome do Mago:");
        String nome = Entradas.entradaString();

        int nivel;
        do {
            System.out.println("Insira o Nível do Mago:");
            nivel = Entradas.entradaInteiro();
            if (nivel < 1 || nivel > 20){
                System.out.println("Entrada Inválida! (1 - 20)");
            }
        } while (nivel < 1 || nivel > 20);

        MagoDAO.postMago(new Mago(idMago, nome, nivel));
        System.out.println("Mago Cadastrado com Sucesso!");
    }

    public static void cadastrarArtefato() throws SQLException {
        System.out.println("Insira o Nome do Artefato:");
        String nome = Entradas.entradaString();

        int nivelMagia;
        do {
            System.out.println("Insira o Nível do Artefato:");
            nivelMagia = Entradas.entradaInteiro();
        } while (nivelMagia < 1 || nivelMagia > 10);

        System.out.println("Insira a Descrição do Artefato:");
        String descricao = Entradas.entradaString();

        ArtefatoMagico artefatoMagico = null;
        boolean loop = true;
        while (loop){
            System.out.println("Qual o Tipo do Artefato?\n1 - Cura\n2 - Ataque\nEscolha:");
            int opcao = Entradas.entradaInteiro();
            switch (opcao){
                case 1 -> {
                    artefatoMagico = new ArtefatoDeCura(nome, nivelMagia, descricao);
                    loop = false;
                }
                case 2 -> {
                    artefatoMagico = new ArtefatoDeCombate(nome, nivelMagia, descricao);
                    loop = false;
                }
                default -> System.out.println("Opção Inválida!");
            }
        }

        ArtefatoDAO.postArtefato(artefatoMagico);
        System.out.println("Artefato Cadastrado com Sucesso!");
    }

    public static void emprestarArtefatoMago() throws SQLException{
        if (ArtefatoDAO.artefatosMagicosDisponiveis().isEmpty()){
            System.out.println("Nenhum artefato Disponivel!");
            return;
        }

        if (!MagoDAO.existeMagoCadastrado()){
            System.out.println("Nenhum mago Cadastrado!");
            return;
        }

        listarTodosArtefatosDisponiveis();

        System.out.println("Insira o código do Artefato:");
        String codigo = Entradas.entradaString();

        ArtefatoMagico artefatoMagico = ArtefatoDAO.buscarArtfato(codigo);
        if (artefatoMagico == null){
            System.out.println("Artefato não Encontrado!");
            return;
        }

        if (artefatoMagico.getIdMago() != 0){
            System.out.println("Artefato já Emprestado!");
            return;
        }

        ArrayList<Mago> magos = MagoDAO.getTodosMagos();
        System.out.println("Magos Cadastrados:");
        for (Mago mago : magos){
            System.out.println(mago.toString()+"\n");
        }

        System.out.println("Insira o id do Mago:");
        int id = Entradas.entradaInteiro();

        Mago mago = MagoDAO.buscarMago(id);
        if (mago == null){
            System.out.println("Mago não encontrado!");
            return;
        }
        if(mago.verificarQuantidadeEmprestimo()){
            System.out.println("Limite de Emprestimo atingido para o Mago!");
            return;
        }

        artefatoMagico.emprestarArtefato(mago);
        System.out.println("Empréstimo Realizado com Sucesso!");
    }

    public static void listarArtefatosMago() throws SQLException{
        ArrayList<Mago> magos = MagoDAO.getTodosMagos();
        if (magos.isEmpty()){
            System.out.println("Nenhum Mago Cadastrado!");
            return;
        }

        System.out.println("Magos Cadastrados:");
        for (Mago mago : magos){
            System.out.println(mago.toString()+"\n");
        }

        System.out.println("Insira o id do Mago:");
        int id = Entradas.entradaInteiro();

        Mago mago = MagoDAO.buscarMago(id);
        if (mago == null){
            System.out.println("Mago não encontrado!");
            return;
        }

        System.out.println("Mago "+mago.getNome()+" Escolhido!");
        ArrayList<ArtefatoMagico> artefatos = mago.getArtefatos();
        if (artefatos.isEmpty()){
            System.out.println("Nenhum Artefato Emprestado!");
            return;
        }

        System.out.println("Artefatos Emprestados:");
        for (ArtefatoMagico artefatoMagico : artefatos){
            System.out.println(artefatoMagico.toString()+"\n");
        }
    }

    public static void devolverArtefato() throws SQLException{
        ArrayList<Mago> magos = MagoDAO.getTodosMagos();
        if (magos.isEmpty()){
            System.out.println("Nenhum Mago Cadastrado!");
            return;
        }

        System.out.println("Magos Cadastrados:");
        for (Mago mago : magos){
            System.out.println(mago.toString()+"\n");
        }

        System.out.println("Insira o id do Mago:");
        int id = Entradas.entradaInteiro();

        Mago mago = MagoDAO.buscarMago(id);
        if (mago == null){
            System.out.println("Mago não encontrado!");
            return;
        }
        System.out.println("Mago "+mago.getNome()+" Escolhido!");
        ArrayList<ArtefatoMagico> artefatos = mago.getArtefatos();
        if (artefatos.isEmpty()){
            System.out.println("Nenhum Artefato Emprestado!");
            return;
        }
        System.out.println("Artefatos Emprestados:");
        for (ArtefatoMagico artefatoMagico : artefatos){
            System.out.println(artefatoMagico.toString()+"\n");
        }

        System.out.println("Insira o Código do artefato que deseja Devolver:");
        String codigo = Entradas.entradaString();

        ArtefatoMagico artefatoMagico = ArtefatoDAO.buscarArtfato(codigo);
        if (artefatoMagico == null){
            System.out.println("Artefato não Encontrado!");
            return;
        }

        if (artefatoMagico.getIdMago() != mago.getIdMago()){
            System.out.println("Artefato já Não Emprestado para esse mago!");
            return;
        }

        artefatoMagico.devolverArtefato(mago);
        System.out.println("Artefato Devolvido com Sucesso!");
    }

    public static void listarTodosArtefatosDisponiveis() throws SQLException{
        ArrayList<ArtefatoMagico> artefatos = ArtefatoDAO.artefatosMagicosDisponiveis();
        if (artefatos.isEmpty()){
            System.out.println("Nenhum Artefato Disponível Cadastrado!");
            return;
        }
        System.out.println("Artefatos Disponíveis:");
        for (ArtefatoMagico artefatoMagico : artefatos){
            System.out.println(artefatoMagico.toString()+"\n");
        }
    }
}