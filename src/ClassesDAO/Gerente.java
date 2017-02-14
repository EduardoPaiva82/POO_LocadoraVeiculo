
package ClassesDAO;

import java.time.LocalDate;

/**
 *
 * @author Francisco Eduardo
 */
public class Gerente extends Funcionario{
    //atributos;
    private String nomeLogin;

    public String getNomeLogin() {
        return nomeLogin;
    }

    public void setNomeLogin(String nomeLogin) {
        this.nomeLogin = nomeLogin;
    }

    public String getSenhaLogin() {
        return senhaLogin;
    }

    public void setSenhaLogin(String senhaLogin) {
        this.senhaLogin = senhaLogin;
    }
    private String senhaLogin;

    //Constutor de Gerente
    public Gerente(String nomeLogin, String senhaLogin, String nomeCompleto, LocalDate dataNascimento, String cpf, String celular) {
        super(nomeCompleto, dataNascimento, cpf, celular);
        this.nomeLogin = nomeLogin;
        this.senhaLogin = senhaLogin;
    }

    public Gerente() {
    }
    
    
    
    
    
    
}
