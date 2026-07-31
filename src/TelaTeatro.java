import javax.swing.*;
import java.awt.*;

public class TelaTeatro {

    int linhas = 5;
    int colunas = 5;
    char[][] assentos = new char[linhas][colunas];
    JButton[][] botoes = new JButton[linhas][colunas];
    double precoInteiro;
    double precoReserva;
    double porcentagemDaReserva = 0.40;
    int modo = 0;
    int quantidade = 0;

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
        frame.setSize(1100, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));
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
