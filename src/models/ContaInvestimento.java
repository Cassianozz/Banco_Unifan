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
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║       MENU INVESTIMENTOS     ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║ 1 - Criptomoedas             ║");
        System.out.println("║ 2 - Ações                    ║");
        System.out.println("║ 3 - Fundos Imobiliários      ║");
        System.out.println("║ 4 - Voltar                   ║");
        System.out.println("╚══════════════════════════════╝");

        System.out.print("➤ Escolha uma opção: ");
        int opcao = input.nextInt();
        input.nextLine();

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
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║     INVESTIR EM CRIPTO       ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║ 1 - Bitcoin (BTC)            ║");
        System.out.println("║ 2 - Ethereum (ETH)           ║");
        System.out.println("║ 3 - Solana (SOL)             ║");
        System.out.println("║ 4 - Dogecoin (DOGE)          ║");
        System.out.println("╚══════════════════════════════╝");

        System.out.print("➤ Escolha a moeda: ");
        int opcao = input.nextInt();
        input.nextLine();

        String moeda;
        switch (opcao) {
            case 1: moeda = "Bitcoin"; break;
            case 2: moeda = "Ethereum"; break;
            case 3: moeda = "Solana"; break;
            case 4: moeda = "Dogecoin"; break;
            default:
                System.out.println("Opção inválida!");
                investirCriptomoedas();
                return;
        }

        investirRisco(valorPercentual(-0.5, 2.0), "Criptomoeda: " + moeda);
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
