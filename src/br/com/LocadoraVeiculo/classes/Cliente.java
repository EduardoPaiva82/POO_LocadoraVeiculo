
package br.com.LocadoraVeiculo.classes;

import java.time.LocalDate;

/**
 *
 * @author edunativa
 */
public class Cliente {
    //Atributos
    private String nomeCliente;
    private LocalDate dataNascimento;
    private String cpf;
    private String celular;
    private String endereco;
    private String cidade;
    private String nCNH;
    private LocalDate dataVenciCNH;
    private LocalDate dataPrimCNH;
    private int saldoBonus;
   
    
    //Construtores

    public Cliente(String nomeCliente, LocalDate dataNascimento, String cpf, String celular, String endereco, String cidade, String nCNH, LocalDate dataVenciCNH, LocalDate dataPrimCNH, int saldoBonus) {
        this.nomeCliente = nomeCliente;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.celular = celular;
        this.endereco = endereco;
        this.cidade = cidade;
        this.nCNH = nCNH;
        this.dataVenciCNH = dataVenciCNH;
        this.dataPrimCNH = dataPrimCNH;
        this.saldoBonus = saldoBonus;
    }

    public Cliente(String nomeCliente, LocalDate dataNascimento, String cpf, String celular, String nCNH, int saldoBonus) {
        this.nomeCliente = nomeCliente;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.celular = celular;
        this.nCNH = nCNH;
        this.saldoBonus = saldoBonus;
    }

    public Cliente(String nomeCliente, LocalDate dataNascimento, String cpf, String celular) {
        this.nomeCliente = nomeCliente;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.celular = celular;
    }
    
    public Cliente() {
    }
    
    
    //metodos Acessores;

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getnCNH() {
        return nCNH;
    }

    public void setnCNH(String nCNH) {
        this.nCNH = nCNH;
    }

    public LocalDate getDataVenciCNH() {
        return dataVenciCNH;
    }

    public void setDataVenciCNH(LocalDate dataVenciCNH) {
        this.dataVenciCNH = dataVenciCNH;
    }

    public LocalDate getDataPrimCNH() {
        return dataPrimCNH;
    }

    public void setDataPrimCNH(LocalDate dataPrimCNH) {
        this.dataPrimCNH = dataPrimCNH;
    }

    public int getSaldoBonus() {
        return saldoBonus;
    }

    public void setSaldoBonus(int saldoBonus) {
        this.saldoBonus = saldoBonus;
    }
    
   
    
}
