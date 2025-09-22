package models;
import app.Banco;

public class Login {

    public static boolean fazerLogin() {
        boolean loginValido;
        String cpf;
        String senha;
        do {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║            LOGIN             ║");
            System.out.println("╠══════════════════════════════╣");

            System.out.print("║ ➤ CPF: ");
            Banco.input.nextLine();

            cpf = Banco.input.nextLine();

            System.out.print("║ ➤ Senha: ");
            senha = Banco.input.nextLine();

            System.out.println("╚══════════════════════════════╝");

            loginValido = Banco.credenciaisDeAcesso.containsKey(cpf) && Banco.credenciaisDeAcesso.get(cpf).equals(senha);

            if (!loginValido) {
                System.out.println("\nUsuário ou senha inválidos. Tente novamente.");
            }

        } while (!loginValido);

        for (Conta conta: Banco.contasBancarias){
            if (conta.getCliente().getCpf().equals(cpf)) {
                Banco.contaLogada = conta;
                break;
            }
        }
        System.out.println("\nmodels.Login bem-sucedido!");
        return true;
    }
}
