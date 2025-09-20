import models.Cliente;
import models.Conta;
import models.ContaCorrente;
import models.ContaPoupanca;

import java.util.ArrayList;
import java.util.Scanner;

public class Banco {

    static Scanner input = new Scanner(System.in);
    static ArrayList<Conta> contasBancarias;

    public static void main(String[] args) {
        contasBancarias = new ArrayList<>();
        operacoes();
    }

    public static void operacoes(){
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║        MENU DO BANCO         ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║ 1 - Criar Conta              ║");
        System.out.println("║ 2 - Depositar                ║");
        System.out.println("║ 3 - Sacar                    ║");
        System.out.println("║ 4 - Transferir               ║");
        System.out.println("║ 5 - Listar Contas            ║");
        System.out.println("║ 6 - Extrato                  ║");
        System.out.println("║ 7 - Sair                     ║");
        System.out.println("╚══════════════════════════════╝");

        System.out.print("➤ Escolha uma opção: ");

        int operacao = input.nextInt();

        switch (operacao){
            case 1:
                criarConta();
                break;
            case 2:
                depositar();
                break;
            case 3:
                sacar();
                break;
            case 4:
                transferir();
                break;
            case 5:
                listarContas();
                break;
            case 6:
                extrato();
                break;
            case 7:
                System.out.println("Até logo!");
                System.exit(0);

            default:
                System.out.printf("Opçao invalida!");
                operacoes();
                break;
        }
    }

    public static void criarConta() {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║        NOVO CADASTRO         ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.print("║ ➤ Nome: ");
        input.nextLine();
        String nome = input.nextLine();
        String cpf ;
        do {
            System.out.println("╠══════════════════════════════╣");
            System.out.print("║ ➤ CPF: ");
            cpf = input.next();

            if (cpf != null && cpf.length() != 11) {
                System.out.println("║ ➤ CPF inválido, digite novamente.");
                }
            } while (cpf.length() != 11);
        String email;

        do {
            System.out.println("╠══════════════════════════════╣");
            System.out.print("║ ➤ Email: ");
            email = input.next();

            if (!email.contains("@") || !email.contains(".")) {
                System.out.println("║ ➤ Email inválido, digite novamente. ");
            }
            } while (!email.contains("@") || !email.contains("."));
        System.out.println("╠══════════════════════════════╣");

        Cliente pessoa = new Cliente(nome, cpf, email);

        System.out.println("║   ESCOLHA O TIPO DA CONTA    ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║ 1 - Conta Corrente           ║");
        System.out.println("║ 2 - Conta Poupança           ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.print("║ ➤ Opção: ");
        int tipo = input.nextInt();
        System.out.println("╚══════════════════════════════╝");

        Conta conta;
        if (tipo == 1) {
            conta = new ContaCorrente(pessoa);
        } else {
            conta = new ContaPoupanca(pessoa);
        }

        contasBancarias.add(conta);
        System.out.println("\n╔═════════════════════════════════╗");
        System.out.println(" Sua conta foi criada com sucesso!");
        System.out.println("╚═════════════════════════════════╝");

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
        System.out.printf("Numero da conta: ");
        int numeroConta = input.nextInt();

        Conta conta = encontrarConta(numeroConta);

        if (conta != null){
            System.out.printf("Qual o valor deseja depositar? ");
            Double valorDeposito = input.nextDouble();
            conta.depositar(valorDeposito);
        } else {
            System.out.printf("A conta não foi encontrada!");
        }
        operacoes();
    }

    public static void sacar() {
        System.out.printf("Numero da conta: ");
        int numeroConta = input.nextInt();

        Conta conta = encontrarConta(numeroConta);

        if (conta != null){
            System.out.printf("Qual o valor deseja Sacar? ");
            Double valorSaque = input.nextDouble();
            conta.sacar(valorSaque);
        } else {
            System.out.printf("A conta não foi encontrada!");
        }
        operacoes();
    }

    public static void transferir() {
        System.out.printf("Numero da Conta do Remetente: ");
        int numeroContaRemetente = input.nextInt();

        Conta contaRemetente = encontrarConta(numeroContaRemetente);

        if (contaRemetente != null){
            System.out.printf("Informe o numero da Conta do Destinatario: ");
            int numeroContaDestinatario = input.nextInt();

            Conta contaDestinatario = encontrarConta(numeroContaDestinatario);

            if (contaDestinatario != null){
                System.out.printf("Informe o valor da transferencia: ");
                double valor = input.nextDouble();

                contaRemetente.transferir(contaDestinatario, valor);
            }
        } else {
            System.out.printf("Conta para transferencia não encontrada");
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
        System.out.print("Número da conta: ");
        int numeroConta = input.nextInt();

        Conta conta = encontrarConta(numeroConta);

        if (conta != null) {
            conta.imprimirExtrato();
        } else {
            System.out.println("Conta não encontrada!");
        }
        operacoes();
    }
}
