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
        //Kaique vai implementar
    }

    public static void criarConta() {
        
       public static void criarConta() {
        System.out.print("\nNome: ");
        String nome = input.next();

        System.out.print("\nCPF: ");
        String cpf = input.next();

        System.out.print("\nEmail: ");
        String email = input.next();

        Pessoa pessoa = new Pessoa(nome, cpf, email);

        System.out.println("\nEscolha o tipo da conta: ");
        System.out.println("1 - Conta Corrente");
        System.out.println("2 - Conta Poupança");
        int tipo = input.nextInt();

        Conta conta;
        if (tipo == 1) {
            conta = new ContaCorrente(pessoa);
        } else {
            conta = new ContaPoupanca(pessoa);
        }

        contasBancarias.add(conta);
        System.out.println("Sua conta foi criada com sucesso!");

        operacoes();
    }

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
        //Thiago vai implementar
    }

    public static void sacar() {
         //Thiago vai implementar
    }

    public static void transferir() {
         //Thiago vai implementar
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
