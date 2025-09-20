package models;

public class ContaPoupanca extends Conta {
    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    public void aplicarRendimento() {
        double rendimento = getSaldo() * 0.01;
        setSaldo(getSaldo() + rendimento);
        System.out.println("Rendimento de R$ " + rendimento + " aplicado.");
    }

    @Override
    public String getTipoConta() {
        return "Conta Poupança";
    }
}
