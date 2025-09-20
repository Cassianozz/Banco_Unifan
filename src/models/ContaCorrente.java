package models;

public class ContaCorrente extends Conta{
    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    public void aplicarTarifaMensal() {
        double tarifa = 10.0;
        if (getSaldo() >= tarifa) {
            setSaldo(getSaldo() - tarifa);
            System.out.println("Tarifa mensal de R$ " + tarifa + "debitada .");

        } else {
            System.out.println("Saldo insuficiente para tarifa mensal.");
        }
    }

    @Override
    public String getTipoConta() {
        return "Conta Corrente";
    }
}
