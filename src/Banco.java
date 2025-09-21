import models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Banco {

    static Scanner input = new Scanner(System.in);
    static ArrayList<Conta> contasBancarias;
    public static HashMap<String, String> credenciaisDeAcesso = new HashMap<>();
    public static Conta contaLogada;

    public static void main(String[] args) {
        contasBancarias = new ArrayList<>();
        menuPrincipal();
    }


    public static void menuPrincipal() {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║        MENU PRINCIPAL        ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║ 1 - Entrar na conta          ║");
        System.out.println("║ 2 - Criar Conta              ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.print("➤ Escolha uma opção: ");


        int login = input.nextInt();

        switch (login) {
            case 1:
                if (Login.fazerLogin()) {
                    Banco.operacoes();
                }
                break;
            case 2:
                criarConta();
        }

    }


    public static void operacoes(){
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║        MENU DO BANCO         ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║ 1 - Depositar                ║");
        System.out.println("║ 2 - Sacar                    ║");
        System.out.println("║ 3 - Transferir               ║");
        System.out.println("║ 4 - Informação da conta      ║");
        System.out.println("║ 5 - Extrato                  ║");
        System.out.println("║ 6 - Sair                     ║");
        System.out.println("╚══════════════════════════════╝");

        System.out.print("➤ Escolha uma opção: ");

        int operacao = input.nextInt();

        switch (operacao){
            case 1:
                depositar();
                break;
            case 2:
                sacar();
                break;
            case 3:
                transferir();
                break;
            case 4:
                info();
                break;
            case 5:
                extrato();
                break;
            case 6:
                System.out.println("Até logo!");
                menuPrincipal();
                break;


            default:
                System.out.printf("Opçao invalida!");
                operacoes();
                break;
        }
    }

    public static void criarConta() {
        Cadastro.criarConta();
        operacoes();
    }

    private static Conta encontrarConta(int numeroConta){
        Conta conta = null;
        if(contasBancarias.size() > 0){

            for(Conta c: contasBancarias) {
                if(c.getNumeroConta() == numeroConta){
                    conta = c;
                }
            }
        }
        return conta;
    }

    public static void depositar() {
        System.out.printf("Qual o valor deseja depositar? ");
        Double valorDeposito = input.nextDouble();
        contaLogada.depositar(valorDeposito);
        operacoes();
    }

    public static void sacar() {
        System.out.printf("Qual o valor deseja Sacar? ");
        Double valorSaque = input.nextDouble();
        contaLogada.sacar(valorSaque);
        operacoes();
    }

    public static void transferir() {



        System.out.printf("Informe o numero da Conta do Destinatario: ");
        int numeroContaDestinatario = input.nextInt();
        Conta contaDestinatario = encontrarConta(numeroContaDestinatario);

            if (contaDestinatario != null){
                System.out.printf("Informe o valor da transferencia: ");
                double valor = input.nextDouble();

                contaLogada.transferir(contaDestinatario, valor);
            } else {
            System.out.printf("Conta para transferencia não encontrada");
                  }
        operacoes();
    }

    public static void info() {
        for (Conta conta: contasBancarias){
            System.out.printf(String.valueOf(contaLogada));
        }
        operacoes();
    }
    public static void listarContas(){
        if(contasBancarias.size() > 0){
            for(Conta conta: contasBancarias){
                System.out.printf(String.valueOf(conta));
            }
        } else {
            System.out.printf("Não há contas!");
        }
        operacoes();
    }

    public static void extrato() {
        contaLogada.imprimirExtrato();
        operacoes();
    }
}
