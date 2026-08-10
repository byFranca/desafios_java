package exercicios_simples;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Etec
 */
import java.util.Scanner;
public class Ex1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner t = new Scanner(System.in);
        
        System.out.println("Insira seu nome: ");
        String nome = t.nextLine();
        System.out.println("Olá, "+nome+"! Seja bem-vindo");
        
        t.close();

    }
    
}