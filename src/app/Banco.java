package app;
import models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Banco {

    public static Scanner input = new Scanner(System.in);
    public static ArrayList<Conta> contasBancarias;
    public static HashMap<String, String> credenciaisDeAcesso = new HashMap<>();
    public static Conta contaLogada;

    public static void main(String[] args) {
        contasBancarias = new ArrayList<>();
        menuPrincipal();
    }


    public static void menuPrincipal() {
        String loginStr;
        int login = 0;

        do {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║        MENU PRINCIPAL        ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║ 1 - Entrar na conta          ║");
            System.out.println("║ 2 - Criar Conta              ║");
            System.out.println("║ 3 - Sair                     ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("➤ Escolha uma opção: ");

            while (!input.hasNextInt()) {
                System.out.println("➤ Entrada inválida! Digite apenas números.");
                System.out.print("➤ Escolha uma opção: ");
                input.next();
            }

            login = input.nextInt();
            input.nextLine(); // Limpa a quebra de linha

            switch (login) {
                case 1:
                    if (Login.fazerLogin()) {
                        Banco.operacoes();
                    } else {
                        menuPrincipal();
                    }
                    break;
                case 2:
                    criarConta();
                    break;
                case 3:
                    System.out.println("Até logo!");
                    break;
                default:
                    System.out.println("➤ Opção inválida! Digite de 1 a 3.");
            }

            /*
            loginStr = input.nextLine();
            if (loginStr.matches("\\d+")) { // só aceita números
                login = Integer.parseInt(loginStr);

                if (login < 1 || login > 2) {
                    System.out.println("➤ Opção inválida! Digite 1 ou 2.");
                    login = 0; // força repetir
                }
            } else {
                System.out.println("➤ Entrada inválida! Digite apenas números.");
            }
             */

        } while (login != 3);
    }


    public static void operacoes() {
        String operacaoStr;
        int operacao = 0;

        do {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║        MENU DO BANCO         ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║ 1 - Depositar                ║");
            System.out.println("║ 2 - Sacar                    ║");
            System.out.println("║ 3 - Transferir               ║");
            System.out.println("║ 4 - Informação da conta      ║");
            System.out.println("║ 5 - Extrato                  ║");

            if (contaLogada instanceof ContaCorrente) {
                System.out.println("║ 6 - Investimentos            ║");
                System.out.println("║ 7 - Sair                     ║");
            } else {
                System.out.println("║ 6 - Sair                     ║");
            }

            System.out.println("╚══════════════════════════════╝");
            System.out.print("➤ Escolha uma opção: ");
            operacaoStr = input.nextLine();

            if (operacaoStr.matches("\\d+")) { // só aceita números
                operacao = Integer.parseInt(operacaoStr);
            } else {
                System.out.println("➤ Entrada inválida! Digite apenas números.");
            }
        } while (operacao == 0);

        if (contaLogada instanceof ContaCorrente) {
            switch (operacao) {
                case 1: depositar(); break;
                case 2: sacar(); break;
                case 3: transferir(); break;
                case 4: info(); break;
                case 5: extrato(); break;
                case 6:
                    ContaInvestimento contaInvest = new ContaInvestimento(contaLogada.getCliente());
                    contaInvest.setSaldo(contaLogada.getSaldo());
                    contaInvest.menuInvestimentos();
                    contaLogada.setSaldo(contaInvest.getSaldo()); // atualiza saldo
                    operacoes();
                    break;
                case 7:
                    System.out.println("Até logo!");
                    menuPrincipal();
                    break;
                default:
                    System.out.println("Opção inválida!");
                    operacoes();
                    break;
            }
        } else {
            switch (operacao) {
                case 1: depositar(); break;
                case 2: sacar(); break;
                case 3: transferir(); break;
                case 4: info(); break;
                case 5: extrato(); break;
                case 6:
                    System.out.println("Até logo!");
                    menuPrincipal();
                    break;
                default:
                    System.out.println("Opção inválida!");
                    operacoes();
                    break;
            }
        }
    }



    public static void criarConta() {
        Cadastro.criarConta();
        operacoes();
    }

    private static Conta encontrarConta(int numeroConta) {
        for (Conta c : contasBancarias) {
            if (c.getNumeroConta() == numeroConta) {
                return c; // achou e retorna direto
            }
        }
        System.out.println("Conta com número " + numeroConta + " não encontrada.");
        return null;
    }


    public static void depositar() {
        System.out.println("\n╔═════════════════════════════════════════╗");
        System.out.println("║                DEPÓSITO                 ║");
        System.out.println("╠═════════════════════════════════════════╣");

        String valorStr;
        double valorDeposito = 0;

        do {
            System.out.print("║ Qual o valor deseja depositar? ");
            valorStr = input.nextLine();

            if (valorStr.matches("\\d+(\\.\\d{1,2})?")) {
                valorDeposito = Double.parseDouble(valorStr);
                if (valorDeposito <= 0) {
                    System.out.println("║ ➤ Valor inválido! Digite um valor maior que zero.");
                    valorDeposito = 0;
                }
            } else {
                System.out.println("║ ➤ Entrada inválida! Digite apenas números.");
            }
        } while (valorDeposito <= 0);

        contaLogada.depositar(valorDeposito);
        operacoes();
    }

    public static void sacar() {
        String valorStr;
        double valorSaque = 0;

        do {
            System.out.print("Qual o valor deseja sacar? ");
            valorStr = input.nextLine();

            if (valorStr.matches("\\d+(\\.\\d{1,2})?")) {
                valorSaque = Double.parseDouble(valorStr);
                if (valorSaque <= 0) {
                    System.out.println("➤ Valor inválido! Digite um valor maior que zero.");
                    valorSaque = 0;
                }
            } else {
                System.out.println("➤ Entrada inválida! Digite apenas números.");
            }
        } while (valorSaque <= 0);

        contaLogada.sacar(valorSaque);
        operacoes();
    }

    public static void transferir() {
        String contaStr;
        int numeroContaDestinatario = 0;

        do {
            System.out.print("Informe o número da Conta do Destinatário: ");
            contaStr = input.nextLine();

            if (contaStr.matches("\\d+")) {
                numeroContaDestinatario = Integer.parseInt(contaStr);
            } else {
                System.out.println("➤ Entrada inválida! Digite apenas números.");
            }
        } while (numeroContaDestinatario == 0);

        Conta contaDestinatario = encontrarConta(numeroContaDestinatario);

        if (contaDestinatario != null) {
            String valorStr;
            double valor = 0;

            do {
                System.out.print("Informe o valor da transferência: ");
                valorStr = input.nextLine();

                if (valorStr.matches("\\d+(\\.\\d{1,2})?")) {
                    valor = Double.parseDouble(valorStr);
                    if (valor <= 0) {
                        System.out.println("➤ Valor inválido! Digite um valor maior que zero.");
                        valor = 0;
                    }
                } else {
                    System.out.println("➤ Entrada inválida! Digite apenas números.");
                }
            } while (valor <= 0);

            contaLogada.transferir(contaDestinatario, valor);
        } else {
            System.out.println("Conta para transferência não encontrada.");
        }
        operacoes();
    }


    public static void info() {
        for (Conta conta: contasBancarias){
            System.out.printf(String.valueOf(contaLogada));
        }
        intervalo();   
        operacoes();
    }

    public static void extrato() {
        contaLogada.imprimirExtrato();
        operacoes();
    }

    public static void intervalo() {
        System.out.print(" ➤ Pressione ENTER para continuar...");
        Banco.input.nextLine();
    }
}