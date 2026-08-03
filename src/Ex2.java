/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Etec
 */
import java.util.Scanner;
public class Ex2 {
    public static void main(String[] args){
        Scanner t = new Scanner(System.in);
        int media;
        int total=0;
        int nota;
        
        for(int i=0; i<3;i++){
            System.out.println("Insira a "+(i+1)+"° nota");
            nota = t.nextInt();
            total = total+nota;
        }
        
        media = total/3;
        System.out.println("A media das notas é: "+media);
        
        if(media>= 7){
            System.out.println("Aluno aprovado");
        } else{
            System.out.println("Aluno reprovado");
        }

              
        
        t.close();
    }
}