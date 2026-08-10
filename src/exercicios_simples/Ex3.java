package exercicios_simples;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Etec
 */
import java.util.Scanner;
public class Ex3 {
    public static void main(String[] args){
        Scanner t = new Scanner(System.in);
        System.out.println("Insira um numero inteiro");
        try{
            int n = t.nextInt();
            if(n%2==0){
                System.out.println(n+" é par");
            }else{
                System.out.println(n+" é impar");
            }
        }catch (Exception e){
            System.out.println("TA ERRADO");
        }
        
        t.close();
    }
}