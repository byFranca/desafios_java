package teatro_swing;

import javax.swing.*;
import java.awt.*;

public class TelaTeatro {

    //para nao quebrar, tem que ter no maximo 26 (letras do alfabeto)
    int linhas = 20;
    int colunas = 20;
    char[][] assentos = new char[linhas][colunas];
    JButton[][] botoes = new JButton[linhas][colunas];


    double precoInteiro;
    double precoReserva;
    double porcentagemDaReserva = 0.40;
    int modo = 0;
    int quantidade = 0;

    int totalAssentos = linhas * colunas;
    int livres = totalAssentos;
    int reservados = 0;
    int comprados = 0;


    Color verde = new Color(46, 204, 113);
    Color amarelo = new Color(241, 196, 15);
    Color vermelho = new Color(231, 76, 60);
    Color azul = new Color(52, 152, 219);
    Color preto = new Color(0, 0, 0);
    Color branco = new Color(236, 240, 241);
    Color cinza = new Color(44, 62, 80);


    JFrame frame = new JFrame();
    JLabel lblModo = new JLabel();

    public TelaTeatro() {
        pedirPreco();

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                assentos[i][j] = 'L';
            }
        }

        frame.setTitle("Sistema de Reserva - Teatro");
        frame.setSize(1400, 850);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(5, 5));
        frame.setResizable(false);

        //Grade dos assentos
        JPanel grade = new JPanel(new GridLayout(linhas + 1, colunas + 1, 2, 2));
        grade.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        grade.setBackground(new Color(44, 62, 80));

        for (int i = 0; i <= linhas; i++) {
            for (int j = 0; j <= colunas; j++) {

                if (i == 0 && j == 0) {
                    JLabel vazio = new JLabel("");
                    grade.add(vazio);
                }else if (j == 0) {
                    JLabel lblColuna = new JLabel(String.valueOf((char) ('A' + i - 1)), SwingConstants.CENTER);
                    lblColuna.setForeground(Color.WHITE);
                    lblColuna.setFont(new Font("Arial", Font.BOLD, 12));
                    grade.add(lblColuna);
                }else if (i == 0) {
                    JLabel lblFileira = new JLabel(String.valueOf(j), SwingConstants.CENTER);
                    lblFileira.setForeground(Color.WHITE);
                    lblFileira.setFont(new Font("Arial", Font.BOLD, 12));
                    grade.add(lblFileira);
                }else {
                    JButton btn = new JButton();
                    btn.setBackground(verde);
                    btn.setFocusable(false);

                    // Importante: o assento real fica em [i-1][j-1]
                    botoes[i - 1][j - 1] = btn;
                    grade.add(btn);

                    int linha = i - 1;
                    int coluna = j - 1;

                    btn.addActionListener(e -> clicarAssento(linha, coluna));
                }
            }
        }
        JScrollPane gradeScroll = new JScrollPane(grade);
        frame.add(gradeScroll, BorderLayout.CENTER);

        //painel superior
        JPanel painelSuperior = new JPanel(new BorderLayout());
        painelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelSuperior.setBackground(branco);

        JLabel titulo = new JLabel("TEATRO - Sistema de Reservas", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        painelSuperior.add(titulo, BorderLayout.NORTH);

        lblModo.setText("Modo atual: Visualização");
        lblModo.setHorizontalAlignment(SwingConstants.CENTER);
        lblModo.setForeground(preto);
        lblModo.setFont(new Font("Arial", Font.BOLD, 15));
        painelSuperior.add(lblModo, BorderLayout.CENTER);

        JLabel lblPreco = new JLabel("Preço do ingresso inteiro: " + precoInteiro + "R$", SwingConstants.CENTER);
        lblPreco.setFont(new Font("Arial", Font.BOLD, 13));
        painelSuperior.add(lblPreco, BorderLayout.SOUTH);

        frame.add(painelSuperior, BorderLayout.NORTH);


        //painel lateral
        JPanel painelLateral = new JPanel();
        painelLateral.setBackground(branco);
        JButton btnReserva = new JButton("Reservar assento");
        JButton btnCompra = new JButton("Comprar assento");
        JButton btnCancelaReserva = new JButton("Cancelar reserva");
        JButton btnRelatorio = new JButton("Mostrar relatório");
        JButton btnSair = new JButton("Sair");
        btnReserva.setFocusable(false);
        btnCompra.setFocusable(false);
        btnCancelaReserva.setFocusable(false);
        btnRelatorio.setFocusable(false);
        btnSair.setFocusable(false);

        btnReserva.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        btnCompra.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        btnCancelaReserva.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        btnRelatorio.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        btnSair.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JPanel espBotoes = new JPanel();
        espBotoes.setBackground(branco);
        espBotoes.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        espBotoes.setLayout(new BoxLayout(espBotoes, BoxLayout.Y_AXIS));

        espBotoes.add(btnReserva);
        espBotoes.add(Box.createVerticalStrut(10));
        espBotoes.add(btnCompra);
        espBotoes.add(Box.createVerticalStrut(10));
        espBotoes.add(btnCancelaReserva);
        espBotoes.add(Box.createVerticalStrut(10));
        espBotoes.add(btnRelatorio);
        espBotoes.add(Box.createVerticalStrut(10));
        espBotoes.add(btnSair);
        painelLateral.add(espBotoes, BorderLayout.CENTER);

        frame.add(painelLateral, BorderLayout.EAST);

        //ação dos botoes
        btnSair.addActionListener(e -> {
            int opc = JOptionPane.showConfirmDialog(null, "Deseja mesmo sair do sistema?", "Sair?", JOptionPane.YES_NO_OPTION);
            if (opc == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        btnReserva.addActionListener(e -> {
            quantidade = pedirQuantidade("reservar", "reserva");
            if (quantidade != -1) {
                modo = 1;
                lblModo.setText("Modo atual: Reserva  -  clique no(s) " + quantidade + " assentos livres que deseja reservar!");
                lblModo.setForeground(azul);

            }
        });

        btnCompra.addActionListener(e -> {
            quantidade = pedirQuantidade("Comprar", "compra");
            if (quantidade != -1) {
                modo = 2;
                lblModo.setText("Modo atual: compra  -  clique no(s) " + quantidade + " assentos livres que deseja comprar!");
                lblModo.setForeground(vermelho);
            }
        });

        btnCancelaReserva.addActionListener(e -> {
            modo = 3;
            lblModo.setText("Modo atual: Cancelamento de reserva  -  clique no assento reservados que deseja cancelar!");
            lblModo.setForeground(amarelo);

        });

        btnRelatorio.addActionListener(e -> {
            mostrarRelatorio();
        });


        frame.setVisible(true);
    }

    public int pedirQuantidade(String acao, String titulo) {
        String entrada = JOptionPane.showInputDialog(null, "Quantos assentos deseja " + acao + "?", titulo, JOptionPane.QUESTION_MESSAGE);

        if (entrada == null) {
            return -1;
        }

        try {
            int qtd = Integer.parseInt(entrada);

            if (qtd <= 0 || qtd >= livres) {
                JOptionPane.showMessageDialog(null, "A quantidade deve estar dentro dos limites do teatro!!");
                return -1;
            }

            return qtd;

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Insira um numero válido");
            return -1;
        }

    }

    public void pedirPreco() {

        while (true) {
            String valorStr = JOptionPane.showInputDialog(null,
                    "Digite o preço da entrada inteira!",
                    "Valor do Ingresso",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (valorStr != null) {
                try {

                    double valor = Double.parseDouble(valorStr.replace(",", ".").trim());
                    if (valor > 0) {
                        precoInteiro = valor;
                        precoReserva = valor * porcentagemDaReserva;
                        JOptionPane.showMessageDialog(null,
                                "Ingresso inteiro: R$ " + String.format("%.2f", precoInteiro) +
                                        "\nValor da reserva (40%): R$ " + String.format("%.2f", precoReserva)
                        );

                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "O valor deve ser maior que R$0");
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Insira um valor numerico");
                }
            } else {
                System.exit(0);
            }
        }

    }

    public void clicarAssento(int linha, int coluna) {
        if (modo == 0) {
            JOptionPane.showMessageDialog(null, "Selecione uma opção do menu", "Nenhum metodo selecionado", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (modo == 1) {
            if (assentos[linha][coluna] == 'L') {
                assentos[linha][coluna] = 'R';
                botoes[linha][coluna].setBackground(amarelo);
                livres--;
                reservados++;
                quantidade--;
                if (quantidade == 0) {
                    lblModo.setText("Modo atual: Visualização");
                    lblModo.setForeground(preto);
                    JOptionPane.showMessageDialog(null, "Acabaram as reservas!");
                    modo = 0;
                } else {
                    lblModo.setText("Modo atual: Reserva  -  clique no(s) " + quantidade + " assentos livres que deseja reservar!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Esse assento ja esta ocupado, por favor, escolha outro", "Assento indisponivel", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (modo == 2) {
            if (assentos[linha][coluna] == 'L' || assentos[linha][coluna] == 'R') {

                if (assentos[linha][coluna] == 'L') {
                    livres--;
                }

                if (assentos[linha][coluna] == 'R') {
                    reservados--;
                }

                assentos[linha][coluna] = 'X';
                botoes[linha][coluna].setBackground(vermelho);

                comprados++;
                quantidade--;
                if (quantidade == 0) {
                    lblModo.setText("Modo atual: Visualização");
                    lblModo.setForeground(preto);
                    JOptionPane.showMessageDialog(null, "Acabaram as Compras!");
                    modo = 0;
                } else {
                    lblModo.setText("Modo atual: Compra  -  clique no(s) " + quantidade + " assentos livres que deseja comprar!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Esse assento ja esta ocupado, por favor, escolha outro", "Assento indisponivel", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (modo == 3) {
            if (assentos[linha][coluna] == 'R') {
                assentos[linha][coluna] = 'L';
                botoes[linha][coluna].setBackground(verde);
                reservados--;
                livres++;
                modo = 0;
                lblModo.setText("Modo atual: Visualização");
                lblModo.setForeground(preto);
                JOptionPane.showMessageDialog(null, "Reserva cancelada");

            } else {
                JOptionPane.showMessageDialog(null, "Esse assento nao esta reservado, por favor, escolha outro", "Assento indisponivel", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void mostrarRelatorio() {
        char letra;


        double totalEmReservas = reservados * precoReserva;
        double lucroPotencial = totalAssentos * precoInteiro;
        double totalEmCompras = comprados * precoInteiro;
        double totalArrecadado = totalEmCompras + totalEmReservas;

        String texto = "\n===== RELATORIO =====\n" +
                "Assentos livres: " + livres +
                "\nAssentos reservados:" + reservados +
                "\nAssentos comprados: " + comprados +
                "\nTotal de assentos na sala: " + totalAssentos +
                "\nTotal em reservas: " + totalEmReservas +
                "\nTotal em compras: " + totalEmCompras +
                "\nLucro arrecadado: " + totalArrecadado +
                "\nLucro potencial maximo: " + lucroPotencial;


        JOptionPane.showMessageDialog(null, texto, "RELATORIO FINANCEIRO", JOptionPane.INFORMATION_MESSAGE);
    }
}