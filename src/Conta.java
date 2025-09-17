import utilitarios.Utils;
import java.util.ArrayList;
import java.util.List;

public abstract class Conta {

    private static int contadorDeContas = 1;

    private int numeroConta;
    private Cliente cliente;
    private Double saldo = 0.0;
    private List<String> extrato;

    public Conta(Cliente cliente) {
        this.numeroConta = contadorDeContas;
        this.cliente = cliente;
        contadorDeContas += 1;
        this.extrato = new ArrayList<>();
        registrarMovimento("Conta criada com sucesso!");
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setPessoa(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String toString(){
        return "\nNumero da conta: " + this.getNumeroConta() +
         "\nNome: " + this.cliente.getNome() +
         "\nCPf: " + this.cliente.getCpf() +
         "\nEmail: " + this.cliente.getEmail() +
         "\nSaldo: " + Utils.doubleToString(this.getSaldo()) +
                "\n";
    }
    private void registrarMovimento(String descricao) {
        extrato.add(descricao);
    }

    public void imprimirExtrato() {
        System.out.println("\n=== Extrato da Conta " + numeroConta + " ===");
        for (String movimento : extrato) {
            System.out.println(movimento);
        }
        System.out.println("Saldo atual: R$ " + saldo);
    }
}
