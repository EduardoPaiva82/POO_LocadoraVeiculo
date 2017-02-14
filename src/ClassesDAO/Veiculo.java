
package ClassesDAO;

/**
 *
 * @author edunativa
 */
public class Veiculo {
    private String marca;
    private String modelo;
    private String ano;
    private String placa;
    private String chassi;
    private boolean situacaoLocacao;
    private int valorBonusCarro;

    //Construtores

    public Veiculo(String marca, String modelo, String ano, String placa, String chassi, boolean situacaoLocacao, int valorBonusCarro) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.chassi = chassi;
        this.situacaoLocacao = situacaoLocacao;
        this.valorBonusCarro = valorBonusCarro;
    }
   

    public Veiculo() {
    }

    //Metodos Acessores;
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public boolean isSituacaoLocacao() {
        return situacaoLocacao;
    }

    public void setSituacaoLocacao(boolean situacaoLocacao) {
        this.situacaoLocacao = situacaoLocacao;
    }
    
    
}
