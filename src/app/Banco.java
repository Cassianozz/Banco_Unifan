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
        contasDeTeste();
        menuPrincipal();
    }


    public static void menuPrincipal() {
        int login;
        do {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║        MENU PRINCIPAL        ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║ 1 - Entrar na conta          ║");
            System.out.println("║ 2 - Criar Conta              ║");
            System.out.println("║ 3 - Sair                     ║");
            System.out.println("╚══════════════════════════════╝");

            login = lerInteiro("➤ Escolha uma opção: ");

            switch (login) {
                case 1:
                    if (Login.fazerLogin()) {
                        operacoes();
                    }
                    break;
                case 2:
                    criarConta();
                    break;
                case 3:
                    System.out.println("Até logo!");
                    break;
                default:
                    System.out.println("\n╔════════════════════════════════════════════════╗");
                    System.out.println("➤ Opção inválida! Escolha uma opção entre 1 e 3.");
                    System.out.println("╚════════════════════════════════════════════════╝");

            }
        } while (login != 3);
    }


    public static void operacoes() {
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
            operacao = lerInteiro("➤ Escolha uma opção: ");

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
                    return;
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

        } while (true);
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
        // System.out.println("Conta com número " + numeroConta + " não encontrada.");
        return null;
    }


    public static void depositar() {
        double valorDeposito;
        System.out.println("\n╔═════════════════════════════════════════╗");
        System.out.println("║                DEPÓSITO                 ║");
        System.out.println("╠═════════════════════════════════════════╣");

        do {
            valorDeposito = lerInteiro("║ ➤ Digite um valor para depósito: R$");

            if (valorDeposito <= 0) {
                System.out.println("║ ➤ Valor inválido! Digite um valor maior que zero.");
                valorDeposito = 0;
            }
        } while (valorDeposito <= 0);

        contaLogada.depositar(valorDeposito);
    }

    public static void sacar() {
        double valorSaque;
        System.out.println("\n╔═════════════════════════════════════╗");
        System.out.println("║                SAQUE                ║");
        System.out.println("╠═════════════════════════════════════╣");
        do {
            valorSaque = lerInteiro("║ ➤ Digite um valor para saque: R$");

            if (valorSaque <= 0) {
                System.out.println("║ ➤ Valor inválido! Digite um valor maior que zero.");
                valorSaque = 0;
            }

        } while (valorSaque <= 0);

        contaLogada.sacar(valorSaque);
    }

    public static void transferir() {
        int numeroContaDestinatario;
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║                  TRANSFERÊNCIA                ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        numeroContaDestinatario = lerInteiro("║ ➤ Informe o número da Conta do Destinatário: ");
            

        Conta contaDestinatario = encontrarConta(numeroContaDestinatario);

        if (numeroContaDestinatario == contaLogada.getNumeroConta()) {
            System.out.println("║ ➤ Você não pode fazer transferência para sua própria conta.");
        } else {
            if (contaDestinatario != null ) {
            double valor = 0;

            do {
                valor = lerInteiro("║ ➤ Informe o valor da transferência: ");
                
                if (valor <= 0) {
                    System.out.println("║ ➤ Valor inválido! Digite um valor maior que zero.");
                    valor = 0;
                }
            } while (valor <= 0);

            contaLogada.transferir(contaDestinatario, valor);
            } else {
            System.out.println("║ ➤ Conta para transferência não encontrada.");
            }
        }
        System.out.println("╚═══════════════════════════════════════════════╝");
    }

    public static void info() {
        System.out.printf(String.valueOf(contaLogada));
        intervalo();   
    }

    public static void extrato() {
        
        contaLogada.imprimirExtrato();
    }

    public static void intervalo() {
        System.out.print(" ➤ Pressione ENTER para continuar...");
        Banco.input.nextLine();
    }

    public static int lerInteiro(String promt) {

        while (true) {
            System.out.print(promt);
            
            if (input.hasNextInt()) {
                int numero = input.nextInt();
                input.nextLine();
                return numero;
            } else {
                System.out.println("║");
                System.out.println("║ ➤ Entrada inválida! Digite apenas números.");
                System.out.println("║");
                input.next();
            }
        }
    }

    public static void contasDeTeste() {
        // --- Cliente 1: Conta Corrente ---
        Cliente cliente1 = new Cliente("danilo Teste", "11111111111", "joao@teste.com");
        Conta contaCorrente = new ContaCorrente(cliente1);
        
        // Define um saldo inicial para facilitar
        contaCorrente.setSaldo(500.00); 

        // Adiciona à lista de contas e credenciais
        contasBancarias.add(contaCorrente);
        credenciaisDeAcesso.put("11111111111", "1234"); // CPF e Senha
    }
}