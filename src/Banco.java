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
        //Ribeiro vai implementar
    }

    private static Conta encontrarConta(int numeroConta){
        //Thiago vai implementar
        return null; 
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
        //Thiago vai implementar
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
