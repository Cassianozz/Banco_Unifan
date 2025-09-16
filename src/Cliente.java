public class Cliente {

    private String nome;
    private String cpf;
    private String email;


    public Cliente() {
    }


    public Cliente(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Método para exibir informações
    public void exibirInfo() {
        System.out.println("=== Dados do Cliente ===");
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Email: " + email);
        System.out.println("========================");
    }


    @Override
    public String toString() {
        return "Cliente [Nome=" + nome + ", CPF=" + cpf + ", Email=" + email + "]";
    }
}
