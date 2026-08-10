package jogo_da_velha_swing;

import javax.swing.*;
import java.awt.*;

public class TelaJogoDaVelha {
    int linhas = 3;
    int colunas = 3;
    char modo = 'X';
    int jogadas = 0;
    char[][] tabuleiro = new char[linhas][colunas];
    JButton[][] botoes = new JButton[linhas][colunas];

    Color azul = new Color(0, 134, 215);
    Color verde = new Color(16, 171, 37);
    Color vermelho = new Color(189, 10, 10);

    JFrame frame = new JFrame();

    public TelaJogoDaVelha() {

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                tabuleiro[i][j] = '-';

            }

        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("JOGO DA VELHA");
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel grade = new JPanel(new GridLayout(linhas, colunas, 5, 5));
        grade.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                JButton btn = new JButton("-");
                btn.setFont(new Font("Arial", Font.BOLD, 40));
                btn.setBackground(azul);
                botoes[i][j] = btn;
                grade.add(btn);

                final int linha = i;
                final int coluna = j;

                btn.addActionListener(e -> marcarBotao(linha, coluna));

            }

        }

        JScrollPane gradeScroll = new JScrollPane(grade);
        frame.add(gradeScroll, BorderLayout.CENTER);

        frame.setVisible(true);

    }

    public void marcarBotao(int linha, int coluna) {
        char lugar = tabuleiro[linha][coluna];
        if (lugar == '-') {

            if (modo == 'X') {
                tabuleiro[linha][coluna] = 'X';
                botoes[linha][coluna].setBackground(verde);
                botoes[linha][coluna].setText("X");
                modo = 'O';
                jogadas++;
                return;
            }

            if (modo == 'O') {
                tabuleiro[linha][coluna] = 'O';
                botoes[linha][coluna].setBackground(vermelho);
                botoes[linha][coluna].setText("O");
                modo = 'X';
                jogadas++;
                return;
            }

        } else {
            System.out.println("erro");
        }
    }

    public static void main(String[] args) {
        new TelaJogoDaVelha();
    }

}
