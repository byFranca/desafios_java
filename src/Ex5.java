/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Etec
 */
public class Ex5 {

    public static void main(String[] args) {
        try {
            for (int i = 10; i >=0; i--) {
                System.out.println(i);
                if(i==0){
                    continue;
                }
                Thread.sleep(1000);
            }
            System.out.println("Acabou");
        } catch (InterruptedException e) {
            System.out.println("Erro");
        }
    }
}
