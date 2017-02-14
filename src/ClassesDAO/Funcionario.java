
package ClassesDAO;

import java.time.LocalDate;

/**
 *
 * @author Francisco Eduardo
 */
public abstract class Funcionario {
    //Atributos;
    private String nomeCompleto;
    private LocalDate dataNascimento;
    private String rg;
    private String cpf;
    private String celular;
    private String endereco;
    private String cargo;
    private double salario;
    private LocalDate dataAdmissao;
    private LocalDate dataDemissao;
    
    
    
    //Construtores

    public Funcionario(String nomeCompleto, LocalDate dataNascimento, String rg, String cpf, String celular, String endereco, String cargo, double salario, LocalDate dataAdmissao, LocalDate dataDemissao) {
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.rg = rg;
        this.cpf = cpf;
        this.celular = celular;
        this.endereco = endereco;
        this.cargo = cargo;
        this.salario = salario;
        this.dataAdmissao = dataAdmissao;
        this.dataDemissao = dataDemissao;
    }

    public Funcionario(String nomeCompleto, LocalDate dataNascimento, String cpf, String celular) {
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.celular = celular;
    }

    public Funcionario(String nomeCompleto, String cargo, double salario, LocalDate dataAdmissao) {
        this.nomeCompleto = nomeCompleto;
        this.cargo = cargo;
        this.salario = salario;
        this.dataAdmissao = dataAdmissao;
    }

    public Funcionario(String nomeCompleto, String cpf, String celular) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.celular = celular;
    }

    public Funcionario() {
    }

    //Metodos Acessores

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public LocalDate getDataDemissao() {
        return dataDemissao;
    }

    public void setDataDemissao(LocalDate dataDemissao) {
        this.dataDemissao = dataDemissao;
    }
    
    
    
    
    
}
