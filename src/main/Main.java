package main;

import dao.ArtefatoDAO;
import dao.MagoDAO;
import model.ArtefatoDeCombate;
import model.ArtefatoDeCura;
import model.ArtefatoMagico;
import model.Mago;

import java.sql.SQLException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner SC = new Scanner(System.in);
    public static void main(String[] args) throws  SQLException{
        while (true){
            System.out.println("=== Menu Principal ===\n1 - Cadastrar mago\n2 - Cadastrar artefato\n3 – Emprestar artefato para mago\n4 – Listar artefatos emprestados de um mago\n5 – Devolver artefato\n6 - Listar todos os artefatos disponíveis\n0 - Sair:\nEscolher:");
            int opcao = SC.nextInt();
            switch (opcao){
                case 1 -> cadastrarMago();
                case 2 -> cadastrarArtefato();
                case 3 -> emprestarArtefatoMago();
                case 4 -> listarArtefatosMago();
                case 5 -> devolverArtefato();
                case 6 -> listarTodosArtefatosDisponiveis();
                case 0 -> {
                    System.out.println("Saindo do Sistema...");
                    SC.close();
                    System.exit(0);
                }
                default -> System.out.println("Opção Inválida!");
            }
        }
    }

    public static void cadastrarMago() throws SQLException{
        System.out.println("Insira o ID do Mago:");
        int idMago = SC.nextInt();
        if (MagoDAO.existeCodigoMago(idMago)){
            System.out.println("Código já cadastrado!");
            return;
        }

        System.out.println("Insira o Nome do Mago:");
        String nome = SC.next().trim().toUpperCase();

        int nivel;
        do {
            System.out.println("Insira o Nível do Mago:");
            nivel = SC.nextInt();
        } while (nivel < 1 || nivel > 20);

        MagoDAO.postMago(new Mago(idMago, nome, nivel));
        System.out.println("Mago Cadastrado com Sucesso!");
    }

    public static void cadastrarArtefato() throws SQLException {
        System.out.println("Insira o Nome do Artefato:");
        String nome = SC.nextLine().toUpperCase();
        SC.nextLine();

        int nivelMagia;
        do {
            System.out.println("Insira o Nível do Artefato:");
            nivelMagia = SC.nextInt();
        } while (nivelMagia < 1 || nivelMagia > 10);

        System.out.println("Insira a Descrição do Artefato:");
        String descricao = SC.nextLine().toUpperCase();
        SC.nextLine();

        ArtefatoMagico artefatoMagico = null;
        boolean loop = true;
        while (loop){
            System.out.println("Qual o Tipo do Artefato?\n1 - Cura\n2 - Ataque\nEscolha:");
            int opcao = SC.nextInt();
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
        if (!ArtefatoDAO.existeArtefatoCadastrado()){
            System.out.println("Nenhum artefato Cadastrado!");
            return;
        }

        if (!MagoDAO.existeMagoCadastrado()){
            System.out.println("Nenhum mago Cadastrado!");
            return;
        }


    }

    public static void listarArtefatosMago(){

    }

    public static void devolverArtefato(){

    }

    public static void listarTodosArtefatosDisponiveis(){

    }
}