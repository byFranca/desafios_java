import javax.swing.*;
import java.awt.*;

public class TelaTeatro {

    int linhas = 10;
    int colunas = 10;
    char[][] assentos = new char[linhas][colunas];
    JButton[][] botoes = new JButton[linhas][colunas];
    double precoInteiro;
    double precoReserva;
    double porcentagemDaReserva = 0.40;
    int modo = 0;
    int quantidade = 0;
    Color verde = new Color(17, 188, 22);
    Color amarelo =new Color(217, 217, 22);
    Color vermelho = new Color(248, 9, 0);

    JLabel lblModo;
    JFrame frame = new JFrame();

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
        frame.setLayout(new BorderLayout(10, 10));

        //Grade dos assentos
        JPanel grade = new JPanel(new GridLayout(linhas, colunas, 5, 5));
        grade.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                JButton btn = new JButton("L");
                btn.setBackground(verde);
                botoes[i][j] = btn;
                grade.add(btn);

            }

        }
        JScrollPane gradeScroll = new JScrollPane(grade);
        frame.add(gradeScroll, BorderLayout.CENTER);


        //painel lateral
        JPanel painelLateral = new JPanel();
        JButton btnReserva = new JButton("Reservar assento");
        JButton btnCompra = new JButton("Comprar assento");
        JButton btnCancelaReserva = new JButton("Cancelar reserva");
        JButton btnRelatorio = new JButton("Mostrar relatório");
        JButton btnSair = new JButton("Sair");

        btnReserva.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        btnCompra.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        btnCancelaReserva.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        btnRelatorio.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        btnSair.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JPanel espBotoes = new JPanel();
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

        //painel superior
        JPanel painelSuperior = new JPanel(new BorderLayout());
        painelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        JLabel titulo = new JLabel("TEATRO - Sistema de Reservas", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        painelSuperior.add(titulo, BorderLayout.NORTH);

        JLabel lblModo = new JLabel("Modo atual: n faço a menor ideia", SwingConstants.CENTER);
        lblModo.setFont(new Font("Arial", Font.BOLD, 14));
        painelSuperior.add(lblModo, BorderLayout.CENTER);

        JLabel lblPreco = new JLabel("Preço do ingresso inteiro: "+precoInteiro+"R$", SwingConstants.CENTER);
        lblPreco.setFont(new Font("Arial", Font.BOLD, 12));
        painelSuperior.add(lblPreco, BorderLayout.SOUTH);

        frame.add(painelSuperior, BorderLayout.NORTH);


        frame.setVisible(true);
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


    public static void main(String[] args) {
        new TelaTeatro();

    }
}
