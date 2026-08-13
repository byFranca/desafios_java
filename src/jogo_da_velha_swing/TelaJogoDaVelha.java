package jogo_da_velha_swing;

import javax.swing.*;
import java.awt.*;

public class TelaJogoDaVelha {

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
    Color roxo = new Color(9, 26, 101);

    JFrame frame;
    JLabel lblVez;

    public TelaJogoDaVelha() {
        iniciarTabuleiro();
        iniciarInterface();
    }

    public void iniciarTabuleiro() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                tabuleiro[i][j] = '-';

            }
        }

        jogadas = 0;
        jogadorAtual = 'X';
        jogoAtivo = true;
    }

    public void iniciarInterface() {
        frame = new JFrame();
        frame.setTitle("JOGO DA VELHA");
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel painelSuperior = new JPanel(new BorderLayout());
        painelSuperior.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));

        JLabel titulo = new JLabel("JOGO DA VELHA", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        painelSuperior.add(titulo, BorderLayout.NORTH);

        lblVez = new JLabel("Vez do jogador: " + String.valueOf(jogadorAtual), SwingConstants.CENTER);
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

                btn.addActionListener(e -> realizarJogada(linha, coluna));

                botoes[i][j] = btn;
                grade.add(btn);
            }
        }

        frame.add(grade, BorderLayout.CENTER);
        frame.setVisible(true);

    }

    public void realizarJogada(int linha, int coluna) {
        if (!jogoAtivo) {
            return;
        }

        if (!verificarEspacoVazio(linha, coluna)) {
            JOptionPane.showMessageDialog(null, "Esse espaço ja esta marcado", "espaço indisponivel", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        tabuleiro[linha][coluna] = jogadorAtual;
        botoes[linha][coluna].setText(String.valueOf(jogadorAtual));

        if (jogadorAtual == 'X') {
            botoes[linha][coluna].setBackground(corX);
        } else {
            botoes[linha][coluna].setBackground(corO);
        }
        jogadas++;

        if (verificarGanhador()) {
            lblVez.setText("O jogador '" + String.valueOf(jogadorAtual) + "' ganhou. Parabens!");
            lblVez.setForeground(corVencedor);
            JOptionPane.showMessageDialog(null, "O jogador '" + String.valueOf(jogadorAtual) + "' ganhou\nParabens!", "vitoria", JOptionPane.INFORMATION_MESSAGE);
            jogoAtivo = false;
            return;
        }

        if (jogadas == 9) {
            lblVez.setText("Jogo empatado!!");
            lblVez.setForeground(roxo);
            JOptionPane.showMessageDialog(null, "O jogo empatou, nenhum jogador venceu", "Empate", JOptionPane.INFORMATION_MESSAGE);
            jogoAtivo = false;
            return;
        }

        jogadorAtual = alterarJogador();
        lblVez.setText("Vez do jogador: " + String.valueOf(jogadorAtual));


    }

    public char alterarJogador() {
        if (jogadorAtual == 'X') {
            lblVez.setForeground(corO);
            return 'O';
        }
        lblVez.setForeground(corX);
        return 'X';
    }

    public boolean verificarEspacoVazio(int linha, int coluna) {
        return tabuleiro[linha][coluna] == '-';
    }

    public boolean verificarGanhador() {
        char var = jogadorAtual;

        for (int i = 0; i < linhas; i++) {
            if (tabuleiro[i][0] == var && tabuleiro[i][1] == var && tabuleiro[i][2] == var) {
                return true;
            }

            if (tabuleiro[0][i] == var && tabuleiro[1][i] == var && tabuleiro[2][i] == var) {
                return true;
            }

        }

        if (tabuleiro[0][0] == var && tabuleiro[1][1] == var && tabuleiro[2][2] == var) {
            return true;
        }

        if (tabuleiro[0][2] == var && tabuleiro[1][1] == var && tabuleiro[2][0] == var) {
            return true;
        }

        return false;

    }

}