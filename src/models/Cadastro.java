package models;
import app.Banco;

public class Cadastro {
    private Banco banco;

    public static Conta criarConta() {
        String nome;
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║        NOVO CADASTRO         ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.print("║ ➤ Nome: ");
        nome = Banco.input.nextLine();


        String cpf;
        do {
            System.out.println("╠══════════════════════════════╣");
            System.out.print("║ ➤ CPF (11 dígitos): ");
            cpf = Banco.input.next();
            if (cpf.length() != 11) {
                System.out.println("║ ➤ CPF inválido, digite novamente.");
            }
        } while (cpf.length() != 11);


        Banco.input.nextLine();


        String email;
        do {
            System.out.println("╠══════════════════════════════╣");
            System.out.print("║ ➤ Email: ");
            email = Banco.input.next();
            if (!email.contains("@") || !email.contains(".")) {
                System.out.println("║ ➤ Email inválido, digite novamente. ");
            }
        } while (!email.contains("@") || !email.contains("."));

        String senha;
        System.out.println("╠══════════════════════════════╣");
        System.out.print("║ ➤ Senha: ");
        senha = Banco.input.next();

        System.out.println("╠══════════════════════════════╣");

        Banco.credenciaisDeAcesso.put(cpf, senha);
        Cliente pessoa = new Cliente(nome, cpf, email);

        System.out.println("║   ESCOLHA O TIPO DA CONTA    ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║ 1 - Conta Corrente           ║");
        System.out.println("║ 2 - Conta Poupança           ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.print("║ ➤ Opção: ");
        int tipo = Banco.input.nextInt();
        System.out.println("╚══════════════════════════════╝");


        Banco.input.nextLine();

        Conta conta;
        if (tipo == 1) {
            conta = new ContaCorrente(pessoa);
        } else {
            conta = new ContaPoupanca(pessoa);
        }
        Banco.contasBancarias.add(conta);
        System.out.println("\n╔═════════════════════════════════╗");
        System.out.println(" Sua conta foi criada com sucesso!");
        System.out.println("╚═════════════════════════════════╝");
        Banco.intervalo();


        for (Conta c: Banco.contasBancarias){
            if (c.getCliente().getCpf().equals(cpf)) {
                Banco.contaLogada = c;
                break;
            }
        }
        return conta;
    }
}