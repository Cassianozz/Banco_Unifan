package models;
import app.Banco;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public abstract class Conta {
    
    private static int contadorDeContas = 1;

    private int numeroConta;
    private Cliente cliente;
    private Double saldo = 0.0;
    private List<String> extrato;

    private String numeroCartao;

    public Conta(Cliente cliente) {
        this.numeroConta = contadorDeContas;
        this.cliente = cliente;
        contadorDeContas += 1;
        this.extrato = new ArrayList<>();
        this.numeroCartao = this.gerarNumeroCartao();
        registrarMovimento("║ Conta criada com sucesso!");


    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public abstract String getTipoConta();

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getNumeroCartao() { return this.numeroCartao; }

    public String toString(){

        return "\n╔══════════════════════════════════════╗" +
         "\n Número da conta: " + this.getNumeroConta() +
         "\n Nome do Cliente: " + this.cliente.getNome() +
         "\n CPf: " + this.cliente.cpfFormatado() +
         "\n Email: " + this.cliente.getEmail() +
         "\n Tipo da Conta: " + this.getTipoConta() +
         "\n Saldo: " + Utils.doubleToString(this.getSaldo()) +
         "\n Numero do Cartão: " + this.getNumeroCartao() +
         "\n╚══════════════════════════════════════╝" +
                "\n";

    }

    public void depositar(Double valor) {
        if (valor > 0 ){
            setSaldo(getSaldo() + valor);
            registrarMovimento("║ Depósito: R$ " + valor);
            System.out.println("║ Seu depósito de R$" +valor + " foi realizado com sucesso!");
        } else{
            System.out.println("Não foi possível realizar o depósito!");
        }
        System.out.println("╚════════════════════════════════════════╝");
        Banco.intervalo();
    }

    public void sacar(Double valor) {
        if (valor > 0 && this.getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);
            registrarMovimento("║ Saque: R$ " + valor);
            System.out.println("║ Saque de R$" +valor + " realizado com sucesso!");
        } else {
            registrarMovimento("║ Tentativa de saque falhou (R$ " + valor + ")");
            System.out.println("║ ➤ Saldo insuficiente para saque.");
        }
        System.out.println("╚═════════════════════════════════════╝");
        Banco.intervalo();
    }

    public void transferir(Conta contaParaDeposito, Double valor) {
        if (valor > 0 && this.getSaldo() >= valor){
            setSaldo(getSaldo() - valor);
            contaParaDeposito.saldo = contaParaDeposito.getSaldo() + valor;

            registrarMovimento("║ Transferência enviada: R$ " + valor + " -> Conta " + contaParaDeposito.getNumeroConta());
            contaParaDeposito.registrarMovimento("║ Transferência recebida: R$ " + valor + " <- Conta " + this.getNumeroConta());

            System.out.println("║ ➤ Transferência realizada com sucesso!");
        } else {
            registrarMovimento("║ Falha na transferência de R$ " + valor);
            System.out.println("║ ➤ Saldo Insuficiente. Não foi possível realizar a transferência");
        }
        Banco.intervalo();
    }

    private void registrarMovimento(String descricao) {
        extrato.add(descricao);
    }

    public void imprimirExtrato() {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║                EXTRATO DA CONTA " + getNumeroConta()+"                ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        for (String movimento : extrato) {
            System.out.println(movimento);
        }
        System.out.println("║ Saldo atual: " + Utils.doubleToString(this.getSaldo()));
        System.out.println("╚══════════════════════════════════════════════════╝");

        Banco.intervalo();
    }

    public String gerarNumeroCartao() {
        Random random = new Random();
        StringBuilder numeroCartao = new StringBuilder();

        for (int i = 0; i < 16; i++) {
            int digito = random.nextInt(10);
            numeroCartao.append(digito);
            
            if ((i + 1) % 4 == 0 && i < 15){
                numeroCartao.append("-");
            }
            
        }
        return numeroCartao.toString();
    }
    public static class Utils { // precisa ser static se estiver dentro da Conta

        static NumberFormat formatandoValores = new DecimalFormat("R$ #,##0.00"); // duas casas
        static {
            ((DecimalFormat) formatandoValores).setRoundingMode(RoundingMode.HALF_UP); // arredonda corretamente
        }

        public static String doubleToString(Double valor){
            return formatandoValores.format(valor);
        }
    }
}
