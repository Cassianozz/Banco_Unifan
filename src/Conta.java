public abstract class Conta {
    private static int contador = 0;
    private final int id;
    private final Cliente cliente;
    private double saldo;
    private boolean ativa;

    public Conta(Cliente cliente) {
        this.id = ++contador;
        this.cliente = cliente;
        this.saldo = 0.0;
        this.ativa = true;
    }

    public int getId(){ return id; }
    public Cliente getCliente() { return cliente; }
    public double getSaldo() { return saldo; }
    public boolean isAtiva() { return ativa; }

    protected void setSaldo(double saldo) { this.saldo = saldo; }
    public void setAtiva(boolean ativa) { this.ativa = ativa; }

    public abstract boolean sacar(double valor);
    public abstract boolean depositar(double valor);
    public abstract boolean transferir(double valor, Conta destino);

    @Override
    public String toString() {
        return String.format("Conta #%d - %s - Saldo: R$ %.2f",
                                id, cliente.getNome(), saldo);
    }
}

class ContaCorrente extends Conta {
    private double limite;

    public ContaCorrente(Cliente cliente) {
        super(cliente);
        this.limite = 1000.00;
    }

    public ContaCorrente(Cliente cliente, double limite) {
        super(cliente);
        this.limite = limite;
    }

    public double getLimite() { return limite; }
    public void setLimite(double limite) {this.limite = limite; }

    @Override
    public boolean sacar(double valor) {
        return false;
    }

    @Override
    public boolean depositar(double valor) {
        return false;
    }

    @Override
    public boolean transferir(double valor, Conta destino) {
        return false;
    }

    @Override
    public String toString() {
        return String.format("==== Informações da Conta ====\nTipo: Conta Corrente\nid: %d\nNome do proprietario: %s\nLimite da conta: R$ %.2f",
                getId(), getCliente().getNome(), limite);
    }

}

class ContaPoupanca extends Conta {

    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    @Override
    public boolean sacar(double valor) {
        return false;
    }

    @Override
    public boolean depositar(double valor) {
        return false;
    }

    @Override
    public boolean transferir(double valor, Conta destino) {
        return false;
    }

    @Override
    public String toString() {
        return String.format("==== Informações da Conta ====\nTipo: Conta Poupança\nid: %d\nNome do proprietario: %s",
                getId(), getCliente().getNome());
    }
}

