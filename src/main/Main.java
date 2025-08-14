package main;

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

    }

    public static void cadastrarArtefato() throws SQLException {

    }

    public static void emprestarArtefatoMago(){

    }

    public static void listarArtefatosMago(){

    }

    public static void devolverArtefato(){

    }

    public static void listarTodosArtefatosDisponiveis(){

    }
}