
package br.com.LocadoraVeiculo.ConexaoBD;

import br.com.LocadoraVeiculo.Login.Tela_Login;
import java.sql.*;


/**
 *
 * @author edunativa
 */
public class ConexaoBD {
    Tela_Login tlogin = new Tela_Login();
    //Metodo responsavel por estabelecer a Conexao com o Banco de dados
    
    public static Connection conector(){
        //variavel de Conexao;
        java.sql.Connection conexao = null;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/LocadoraCar";
        String usuario = "root";
        String senha = "edunativa";
        
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
            
            return conexao;
            
        } catch (ClassNotFoundException | SQLException e) {
                   
            return null;
        }
       
    }
}
