package models;

import java.util.Random;
import java.util.Scanner;

public class ContaInvestimento extends Conta {

    private static Scanner input = new Scanner(System.in);
    private Random random = new Random();

    public ContaInvestimento(Cliente cliente) {
        super(cliente);
    }

    @Override
    public String getTipoConta() {
        return "Investimento";
    }

    public void menuInvestimentos() {
        String opcaoStr;
        int opcao = 0;
        do {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║       MENU INVESTIMENTOS     ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║ 1 - Criptomoedas             ║");
            System.out.println("║ 2 - Ações                    ║");
            System.out.println("║ 3 - Fundos Imobiliários      ║");
            System.out.println("║ 4 - Voltar                   ║");
            System.out.println("╚══════════════════════════════╝");

            System.out.print("➤ Escolha uma opção: ");
            opcaoStr = input.nextLine();

            if (opcaoStr.matches("\\d+")) {
                opcao = Integer.parseInt(opcaoStr);

                if (opcao < 1 || opcao > 4) {
                    System.out.println("➤ Opção inválida! Digite de 1 a 4.");
                    opcao = 0;
                }
            } else {
                System.out.println("➤ Entrada inválida! Digite apenas números.");
            }

        } while (opcao == 0);


        switch (opcao) {
            case 1: investirCriptomoedas(); break;
            case 2: investirAcoes(); break;
            case 3: investirFundosImobiliarios(); break;
            case 4: return;
            default:
                System.out.println("Opção inválida!");
                menuInvestimentos();
                break;
        }
    }

    private void investirCriptomoedas() {
        String opcaoStr;
        int opcao = 0;

        do {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║     INVESTIR EM CRIPTO       ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║ 1 - Bitcoin (BTC)            ║");
            System.out.println("║ 2 - Ethereum (ETH)           ║");
            System.out.println("║ 3 - Solana (SOL)             ║");
            System.out.println("║ 4 - Dogecoin (DOGE)          ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("➤ Escolha uma opção: ");
            opcaoStr = input.nextLine();

            if (opcaoStr.matches("\\d+")) {
                opcao = Integer.parseInt(opcaoStr);
                if (opcao < 1 || opcao > 4) {
                    System.out.println("➤ Opção inválida! Digite de 1 a 4.");
                    opcao = 0;
                }
            } else {
                System.out.println("➤ Entrada inválida! Digite apenas números.");
            }
        } while (opcao == 0);

        String moeda = switch (opcao) {
            case 1 -> "Bitcoin";
            case 2 -> "Ethereum";
            case 3 -> "Solana";
            case 4 -> "Dogecoin";
            default -> ""; // nunca será usado
        };

        String valorStr;
        double valorInvestimento = 0;
        do {
            System.out.print("Digite o valor que deseja investir: ");
            valorStr = input.nextLine();

            if (valorStr.matches("\\d+(\\.\\d{1,2})?")) { // aceita decimais
                valorInvestimento = Double.parseDouble(valorStr);
                if (valorInvestimento <= 0) {
                    System.out.println("➤ Valor inválido! Digite um valor maior que zero.");
                    valorInvestimento = 0;
                }
            } else {
                System.out.println("➤ Entrada inválida! Digite apenas números.");
            }
        } while (valorInvestimento == 0);

        investirRisco(valorInvestimento, "Criptomoeda: " + moeda);
    }



    private void investirAcoes() {
        System.out.print("Informe o valor que deseja investir em Ações: ");
        double valor = input.nextDouble();
        input.nextLine();
        investirRisco(valorPercentual(-0.3, 1.0, valor), "Ações");
    }

    private void investirFundosImobiliarios() {
        System.out.print("Informe o valor que deseja investir em Fundos Imobiliários: ");
        double valor = input.nextDouble();
        input.nextLine();
        investirRisco(valorPercentual(-0.1, 0.5, valor), "Fundos Imobiliários");
    }

    // Método que aplica ganho ou perda baseado na porcentagem
    private void investirRisco(double resultado, String tipo) {
        double valorInvestido = resultado - this.getSaldo();
        if (resultado > this.getSaldo()) {
            System.out.printf("Lucro obtido em %s: R$ %.2f%n", tipo, resultado - this.getSaldo());
        } else {
            System.out.printf("Perda ocorrida em %s: R$ %.2f%n", tipo, this.getSaldo() - resultado);
        }
        this.setSaldo(resultado);
        System.out.printf("Novo saldo: R$ %.2f%n", this.getSaldo());
    }

    // Calcula o valor final baseado em risco percentual
    private double valorPercentual(double min, double max) {
        double valor = input.nextDouble();
        input.nextLine();
        if (valor > this.getSaldo()) {
            System.out.println("Saldo insuficiente!");
            return this.getSaldo(); // mantém saldo sem alteração
        }
        this.setSaldo(this.getSaldo() - valor);
        double porcentagem = min + (max - min) * random.nextDouble();
        return this.getSaldo() + valor + (valor * porcentagem);
    }

    private double valorPercentual(double min, double max, double valor) {
        if (valor > this.getSaldo()) {
            System.out.println("Saldo insuficiente!");
            return this.getSaldo(); // mantém saldo sem alteração
        }
        this.setSaldo(this.getSaldo() - valor);
        double porcentagem = min + (max - min) * random.nextDouble();
        return this.getSaldo() + valor + (valor * porcentagem);
    }
}
