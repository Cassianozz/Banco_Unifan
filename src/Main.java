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

        scanner.close();
    }
}
