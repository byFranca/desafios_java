package jogo_da_velha_swing;

import javax.swing.*;
import java.awt.*;

public class TelaJogoDaVelha{

    int linhas = 3;
    int colunas = 3;

    char[][] tabuleiro = new char[linhas][colunas];
    JButton[][] botoes = new JButton[linhas][colunas];

    char jogadorAtual;
    int jogadas;
    boolean jogoAtivo;

    // Cores
    Color corFundo = new Color(0, 134, 215);
    Color corX = new Color(16, 171, 37);
    Color corO = new Color(189, 10, 10);
    Color corVencedor = new Color(255, 215, 0); // dourado

    Color verde = new Color(46, 204, 113);

    JFrame frame;
    JLabel lblVez;

    public TelaJogoDaVelha(){
        iniciarTabuleiro();
        iniciarInterface();
    }

    public void iniciarTabuleiro(){
        for(int i =0; i<linhas; i++){
            for(int j =0; j<colunas; j++){
                tabuleiro[i][j] = '-';

            }
        }

        jogadas = 0;
        jogadorAtual = 'X';
        jogoAtivo = true;
    }

    public void iniciarInterface(){
        frame = new JFrame();
        frame.setTitle("JOGO DA VELHA");
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        JPanel painelSuperior = new JPanel(new BorderLayout());
        painelSuperior.setBorder(BorderFactory.createEmptyBorder(15,10,10,10));

        JLabel titulo = new JLabel("JOGO DA VELHA", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        painelSuperior.add(titulo, BorderLayout.NORTH);

        lblVez = new JLabel("Vez do jogador: X", SwingConstants.CENTER);
        lblVez.setFont(new Font("Arial", Font.BOLD, 18));
        lblVez.setForeground(corX);
        painelSuperior.add(lblVez, BorderLayout.CENTER);

        frame.add(painelSuperior, BorderLayout.NORTH);

        // Grade 3x3
        JPanel grade = new JPanel(new GridLayout(linhas, colunas, 8, 8));
        grade.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        grade.setBackground(Color.DARK_GRAY);

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                JButton btn = new JButton("");
                btn.setFont(new Font("Arial", Font.BOLD, 48));
                btn.setBackground(corFundo);
                btn.setFocusPainted(false);
                btn.setForeground(Color.WHITE);

                final int linha = i;
                final int coluna = j;

                btn.addActionListener(e-> realizarJogada(linha, coluna));

                botoes[i][j] = btn;
                grade.add(btn);
            }
        }

        frame.add(grade, BorderLayout.CENTER);
        frame.setVisible(true);

    }
    
    public void realizarJogada(int linha, int coluna){
        if(tabuleiro[linha][coluna] == '-'){
            botoes[linha][coluna].setBackground(verde);
            tabuleiro[linha][coluna] = 'X';
        }else{
            botoes[linha][coluna].setBackground(corFundo);
            tabuleiro[linha][coluna] = '-';
        }
    }

}