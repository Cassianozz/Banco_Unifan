import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("=== Cadastro de Cliente ===");
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Digite o email: ");
        String email = scanner.nextLine();


        Cliente cliente1 = new Cliente(nome, cpf, email);
        cliente1.exibirInfo();

        System.out.println("\n=== Escolha o Tipo de Conta ===");
        System.out.println("1 - Conta Corrente");
        System.out.println("2 - Conta Poupança");
        System.out.print("Digite sua opção: ");

        int tipoConta = scanner.nextInt();
        scanner.nextLine();

        Conta conta = null;
        if (tipoConta == 1) {
            conta = new ContaCorrente(cliente1);
            System.out.println("Conta Corrente criada com sucesso!");
        } else if (tipoConta == 2) {
            conta = new ContaPoupanca(cliente1);
            System.out.println("Conta Poupança criada com sucesso");
        } else {
            System.out.println("Opção inválida!");
        }

        System.out.println(conta);

        scanner.close();
    }
}
