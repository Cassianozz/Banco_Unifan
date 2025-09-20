package models;

import java.text.DecimalFormat;
import java.text.NumberFormat;
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
         "\n Numero da conta: " + this.getNumeroConta() +
         "\n Nome: " + this.cliente.getNome() +
         "\n CPf: " + this.cliente.getCpf() +
         "\n Email: " + this.cliente.getEmail() +
         "\n Saldo: " + Utils.doubleToString(this.getSaldo()) +
         "\n Numero do Cartão: " + this.getNumeroCartao() +
         "\n╚══════════════════════════════════════╝" +
                "\n";
    }

    public void depositar(Double valor) {
        if (valor > 0 ){
            setSaldo(getSaldo() + valor);
            registrarMovimento("Depósito: R$ " + valor);
            System.out.println("Seu depósito foi realizado com sucesso!");
        } else{
            System.out.println("Não foi possível realizar o depósito!");
        }
    }

    public void sacar(Double valor) {
        if (valor > 0 && this.getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);
            registrarMovimento("Saque: R$ " + valor);
            System.out.println("Saque realizado com sucesso!");
        } else {
            registrarMovimento("Tentativa de saque falhou (R$ " + valor + ")");
            System.out.println("Não foi possível realizar o saque");
        }
    }

    public void transferir(Conta contaParaDeposito, Double valor) {
        if (valor > 0 && this.getSaldo() >= valor){
            setSaldo(getSaldo() - valor);
            contaParaDeposito.saldo = contaParaDeposito.getSaldo() + valor;

            registrarMovimento("Transferência enviada: R$ " + valor + " -> models.Conta " + contaParaDeposito.getNumeroConta());
            contaParaDeposito.registrarMovimento("Transferência recebida: R$ " + valor + " <- models.Conta " + this.getNumeroConta());

            System.out.println("Transferência realizada com sucesso!");
        } else {
            registrarMovimento("Falha na transferência de R$ " + valor);
            System.out.println("Não foi possível realizar a transferência");
        }
    }
    private void registrarMovimento(String descricao) {
        extrato.add(descricao);
    }

    public void imprimirExtrato() {
        System.out.println("\n=== Extrato da models.Conta " + numeroConta + " ===");
        for (String movimento : extrato) {
            System.out.println(movimento);
        }
        System.out.println("Saldo atual: R$ " + saldo);
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
    public class Utils {

        static NumberFormat formatandoValores = new DecimalFormat("R$ #,##00.0");

        public static String doubleToString(Double valor){
            return formatandoValores.format(valor);
        }
    }
}
