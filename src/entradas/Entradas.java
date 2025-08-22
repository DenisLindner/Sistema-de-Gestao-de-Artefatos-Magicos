package entradas;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Entradas {
    static Scanner SC = new Scanner(System.in);
    public static int entradaInteiro(){
        int numero;
        while (true){
            try {
                numero = SC.nextInt();
                break;
            } catch (InputMismatchException e){
                System.out.println("Entrada Inválida! Tente Novamente:");
                SC.nextLine();
            }
        }
        return numero;
    }

    public static String entradaString(){
        String string;
        SC.nextLine();
        do {
            string = SC.nextLine().toUpperCase();
            if (string.isEmpty()){
                System.out.println("Entrada Inválida! Tente Novamente:");
            }
        } while (string.isEmpty());
        return string;
    }
}
