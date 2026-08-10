package exercicios_simples;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Etec
 */
import java.util.Scanner;

public class Ex4 {

    public static void main(String[] args) {
        Scanner t = new Scanner(System.in);
        System.out.println("Informe um numero");
        int n = t.nextInt();
        for (int i = 0; i <= 10; i++){
            System.out.println(n+" X "+i+" = "+(n*i));
            
        }
        
        t.close();
    }
}
