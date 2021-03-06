package br.com.LocadoraVeiculo.Telas.Cadastros;

import br.com.LocadoraVeiculo.ConexaoBD.ConexaoBD;
import br.com.LocadoraVeiculo.classes.Cliente;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Period;
import javax.swing.JOptionPane;

/**
 *
 * @author edunativa
 */
public class Tela_CadastroCliente extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form Tela_CadastroCliente
     */
    public void tornarCamposEditaveis() {
        jFormattedCPF.setEditable(true);
        jFormattedCNH.setEditable(true);
        jFormattedCelular.setEditable(true);;
        jTextEndereco.setEditable(true);
        jTextComplemento.setEditable(true);
        jTextNCasa.setEditable(true);
        jTextBairro.setEditable(true);
        jComboBoxCidade.setEnabled(true);
        jComboBoxCidade.setEditable(true);
        jFormattedDataPrimCNH.setEditable(true);
        jFormattedDataVenciCNH.setEditable(true);
        jTextSaldoInicial.setEditable(true);
        jTextNomeLogin.setEditable(true);
        jTextSenhaLogin.setEditable(true);
        //Mostar os Botões Salvar e Cancelar;
        btnSalvar.setEnabled(true);
        btnCancelar.setEnabled(true);
    }

    public Tela_CadastroCliente() {

        initComponents();
        conexao = ConexaoBD.conector();
    }
//Metodo que serve para Adiconar um Cliente ao Banco de dados
    public void addCliente() {
        String sql = "insert into Clientes(nomeCliente, dataNascimento, cpf, celular, endereco, cidade, nCNH, dataVencimento, dataPrimCNH)Values(?,?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, jTextNomeCliente.getText());
            pst.setString(2, jFormattedDataNascimento.getText());
            pst.setString(3, jFormattedCPF.getText());
            pst.setString(4, jFormattedCelular.getText());
            pst.setString(5, jTextEndereco.getText());
            pst.setString(6, jComboBoxCidade.getSelectedItem().toString());
            pst.setString(7, jFormattedCNH.getText());
            pst.setString(8, jFormattedDataPrimCNH.getText());
            pst.setString(9, jFormattedDataVenciCNH.getText());

            //caso os campos estiverem vazios isEmpty efeiturar o bloco de código abaixo;
            if (jTextNomeCliente.getText().isEmpty() || jFormattedDataNascimento.getText().isEmpty() || jFormattedCPF.getText().isEmpty() || jFormattedCelular.getText().isEmpty() || jFormattedCNH.getText().isEmpty() || jFormattedDataPrimCNH.getText().isEmpty() || jFormattedDataVenciCNH.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!!!");

            } else {
                //a linha abaixo atualiza a tabela Clientes com os dados do Formulário de cadastro;
                //e Mostrar um janela de Dialogo dizendo que foi cadastrado com sucesso;
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente Cadastrado\nCom Sucesso!!");
                    //Limpar o Formulario ao clicar em Salvar
                    jTextNomeCliente.setText(null);
                    jFormattedDataNascimento.setText(null);
                    jFormattedCelular.setText(null);
                    jFormattedCPF.setText(null);
                    jTextEndereco.setText(null);
                    jTextComplemento.setText(null);
                    jTextNCasa.setText(null);
                    jComboBoxCidade.setVisible(false);
                    jTextBairro.setText(null);
                    jFormattedCNH.setText(null);
                    jFormattedDataPrimCNH.setText(null);
                    jFormattedDataVenciCNH.setText(null);
                    status.setEnabled(false);
                  
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops Erro ao Cadastrar Cliente:\n" + e);
        }
    }
    
    public void addLoginCliente(){
        String sql = "insert into Usuario (CPF, nomeLogin, senhaLogin) values (?,?,?)";
        
        try {
            pst = conexao.prepareStatement(sql);
           
            pst.setString(1, jFormattedCPF.getText());
            pst.setString(2, jTextNomeLogin.getText());
            pst.setString(3, jTextSenhaLogin.getText());
            

            
            if (jTextNomeLogin.getText().isEmpty() || jTextSenhaLogin.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha os Campos Obrigatórios!!!");

            } else {
                //a linha abaixo atualiza a tabela Clientes com os dados do Formulário de cadastro;
                //e Mostrar um janela de Dialogo dizendo que foi cadastrado com sucesso;
                int adicionadoLogin = pst.executeUpdate();

                if (adicionadoLogin > 0) {
                    JOptionPane.showMessageDialog(null, "Login e Senha\nCadastrado Com Sucesso!!");
                    //Limpar o Formulario ao clicar em Salvar
                   
                    jTextNomeLogin.setText(null);
                    jTextSenhaLogin.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ops Erro ao Cadastrar Login ou Senha:\n" + e);
        }
    }

    public void calcularAnosCNH() {
        Cliente d = new Cliente();
        //formato da data: 00/00/0000 data da Primeira CNH
        String dia = jFormattedDataPrimCNH.getText().substring(0, 2);
        String mes = jFormattedDataPrimCNH.getText().substring(3, 5);
        String ano = jFormattedDataPrimCNH.getText().substring(6, 10);
        //Pegando a data de Vencimento da CNH
        String diav = jFormattedDataVenciCNH.getText().substring(0, 2);
        String mesv = jFormattedDataVenciCNH.getText().substring(3, 5);
        String anov = jFormattedDataVenciCNH.getText().substring(6, 10);

        d.setDataPrimCNH(LocalDate.of(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia)));
        d.setDataVenciCNH(LocalDate.of(Integer.parseInt(anov), Integer.parseInt(mesv), Integer.parseInt(diav)));

        int anos = Period.between(d.getDataPrimCNH(), d.getDataVenciCNH()).getYears();
        //Fazendo o calculo da idade com a verificação de Periodo do getDatanascimento a data Atual
        //o .getYears mostra o Periodo em Anos;
        jTxtAnosCNH.setText(Integer.toString(anos));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormattedDataNascimento = new javax.swing.JFormattedTextField();
        status = new javax.swing.JLabel();
        jTextNomeCliente = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jFormattedCPF = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jFormattedCNH = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jFormattedCelular = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextEndereco = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextComplemento = new javax.swing.JTextField();
        jTextNCasa = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextBairro = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jComboBoxCidade = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jFormattedDataPrimCNH = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        jFormattedDataVenciCNH = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextSaldoInicial = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        jTxtAnosCNH = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextNomeLogin = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextSenhaLogin = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Cadastro de Cliente");
        setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        setMaximumSize(new java.awt.Dimension(650, 500));
        setMinimumSize(new java.awt.Dimension(500, 300));
        setPreferredSize(new java.awt.Dimension(650, 500));
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        try {
            jFormattedDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedDataNascimento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedDataNascimento.setToolTipText("Após Digitar a Data Aperte Enter");
        jFormattedDataNascimento.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jFormattedDataNascimento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedDataNascimentoKeyPressed(evt);
            }
        });
        getContentPane().add(jFormattedDataNascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 110, -1));

        status.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/LocadoraVeiculo/Icones/iconeSIM.png"))); // NOI18N
        status.setToolTipText("<html> <p>Após cadastrar data de nascimento<br> clique aqui para liberar os demais campos</p></html>");
        status.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/LocadoraVeiculo/Icones/IconeNao.png"))); // NOI18N
        status.setEnabled(false);
        getContentPane().add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 28, 27));

        jTextNomeCliente.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextNomeCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextNomeClienteKeyReleased(evt);
            }
        });
        getContentPane().add(jTextNomeCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 397, -1));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel1.setText("*Nome Completo:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setText("*Data Nascimento");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, -1, -1));

        jFormattedCPF.setEditable(false);
        try {
            jFormattedCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedCPF.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jFormattedCPF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedCPFKeyReleased(evt);
            }
        });
        getContentPane().add(jFormattedCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 128, -1));

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel3.setText("*CPF nº:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel4.setText("*CNH nº:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, -1, -1));

        jFormattedCNH.setEditable(false);
        try {
            jFormattedCNH.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedCNH.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jFormattedCNH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedCNHKeyReleased(evt);
            }
        });
        getContentPane().add(jFormattedCNH, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 136, -1));

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel5.setText("*Celular nº:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, -1, -1));

        jFormattedCelular.setEditable(false);
        try {
            jFormattedCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) 9####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedCelular.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        getContentPane().add(jFormattedCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, 140, -1));

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 1, 20)); // NOI18N
        jLabel6.setText("Cadastro de Cliente");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 246, 33));

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel7.setText("Endereço:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        jTextEndereco.setEditable(false);
        jTextEndereco.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        getContentPane().add(jTextEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 333, -1));

        jLabel8.setText("Complemento:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 200, -1, -1));

        jTextComplemento.setEditable(false);
        jTextComplemento.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        getContentPane().add(jTextComplemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 220, 127, -1));

        jTextNCasa.setEditable(false);
        jTextNCasa.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        getContentPane().add(jTextNCasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 220, 63, -1));

        jLabel9.setText("Nº:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 200, -1, -1));

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel10.setText("Bairro:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        jTextBairro.setEditable(false);
        jTextBairro.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        getContentPane().add(jTextBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 283, -1));

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel11.setText("Cidade:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, -1, -1));

        jComboBoxCidade.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jComboBoxCidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Alto Alegre", "Amajarí", "Boa Vista", "Bonfim", "Cantá", "Caracaraí", "Caraoebe", "Iracema", "Mucajaí", "Normandia", "Pacaraima", "Rorainópolis", "São João da Baliza", "São Luiz", "Santa Maria do Boiaçu", "Uiramutã" }));
        jComboBoxCidade.setSelectedIndex(2);
        jComboBoxCidade.setToolTipText("");
        jComboBoxCidade.setEnabled(false);
        getContentPane().add(jComboBoxCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 290, 226, -1));

        jLabel12.setText("*Data da Primeira CNH:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, -1, -1));

        jFormattedDataPrimCNH.setEditable(false);
        try {
            jFormattedDataPrimCNH.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedDataPrimCNH.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedDataPrimCNH.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        getContentPane().add(jFormattedDataPrimCNH, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 129, -1));

        jLabel13.setText("*Data de Vencimento da CNH:");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 340, 219, -1));

        jFormattedDataVenciCNH.setEditable(false);
        try {
            jFormattedDataVenciCNH.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedDataVenciCNH.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedDataVenciCNH.setToolTipText("Após Digitar a Data Aperte Enter");
        jFormattedDataVenciCNH.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jFormattedDataVenciCNH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedDataVenciCNHKeyPressed(evt);
            }
        });
        getContentPane().add(jFormattedDataVenciCNH, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 360, 129, -1));

        jLabel14.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel14.setText("Saldo Inicial");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 340, -1, -1));

        jTextSaldoInicial.setEditable(false);
        jTextSaldoInicial.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextSaldoInicial.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        getContentPane().add(jTextSaldoInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 360, 68, -1));

        btnSalvar.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setEnabled(false);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 460, 110, 45));

        btnCancelar.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 460, 131, 45));

        btnLimpar.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 460, 110, 45));

        jTxtAnosCNH.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jTxtAnosCNH.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jTxtAnosCNH.setText("0");
        getContentPane().add(jTxtAnosCNH, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 370, 36, -1));

        jLabel16.setText("ano(s)");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 370, -1, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/LocadoraVeiculo/Icones/IconeAddCliente.png"))); // NOI18N
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 60, 50));

        jLabel17.setForeground(new java.awt.Color(255, 51, 0));
        jLabel17.setText("* Campos Obrigatórios");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, -1));

        jLabel18.setText("Nome para Login: ");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, -1, -1));

        jTextNomeLogin.setEditable(false);
        getContentPane().add(jTextNomeLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 190, -1));

        jLabel19.setText("Senha para Login");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 400, -1, 20));

        jTextSenhaLogin.setEditable(false);
        getContentPane().add(jTextSenhaLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 420, 160, -1));

        setBounds(100, 80, 636, 550);
    }// </editor-fold>//GEN-END:initComponents


    private void jTextNomeClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextNomeClienteKeyReleased
        //FAZ com que a jTextField ACEITE apenas Letras Maiusculas e Menusculas numeros não!
        String maiusculo = jTextNomeCliente.getText().toUpperCase(); // tudo Maiusculo;
        jTextNomeCliente.setText(maiusculo);
        jTextNomeCliente.setText(jTextNomeCliente.getText().replaceAll("[^A-Z | ^a-z | ^ã | ^á | ^ó | ^Ç | ^ç]", ""));
    }//GEN-LAST:event_jTextNomeClienteKeyReleased

    private void jFormattedCPFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedCPFKeyReleased
        //FAZ com que o campo CPF receba apenas numeros na hora da digitação;
        jFormattedCPF.setText(jFormattedCPF.getText().replaceAll("[^0-9]", ""));
    }//GEN-LAST:event_jFormattedCPFKeyReleased

    private void jFormattedCNHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedCNHKeyReleased
        jFormattedCNH.setText(jFormattedCNH.getText().replaceAll("[^0-9]", ""));
    }//GEN-LAST:event_jFormattedCNHKeyReleased

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        //Limpar os campos da Tabela cadastro;
        jTextNomeCliente.setText(null);
        jFormattedDataNascimento.setText(null);
        jFormattedCPF.setText(null);
        jFormattedCelular.setText(null);
        jTextEndereco.setText(null);
        jFormattedCNH.setText(null);
        jTextEndereco.setText(null);
        jFormattedDataPrimCNH.setText(null);
        jFormattedDataVenciCNH.setText(null);
        status.setEnabled(false);
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        addCliente();
        addLoginCliente();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void jFormattedDataNascimentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedDataNascimentoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) { //apos preencher o campo data de nascimento e apertar Enter
            Cliente f = new Cliente();
            //formato da data: 00/00/0000
            String dia = jFormattedDataNascimento.getText().substring(0, 2);
            String mes = jFormattedDataNascimento.getText().substring(3, 5);
            String ano = jFormattedDataNascimento.getText().substring(6, 10);

            f.setDataNascimento(LocalDate.of(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia)));

            int idade = Period.between(f.getDataNascimento(), LocalDate.now()).getYears(); //Fazendo o calculo da idade com a verificação de Periodo do getDatanascimento a data Atual
            //o .getYears mostra o Periodo em Anos;
            //int idadeMeses = Period.between(f.getDataNascimento(), LocalDate.now()).getMonths();

            //Convertendo para String novamente para poder ser visto no JLabel
            status.setText(Integer.toString(idade));
            //vIdadeMeses.setText(Integer.toString(idadeMeses));

            //Teste de Condição que testa se é maior de idade caso seja os campos podem ser editados caso contrário permanecem bloqueados;
            if (idade >= 18 && idade <= 90) {

                //habilitando os campos para o cadastro
                tornarCamposEditaveis();
                status.setEnabled(true);

            } else {

                JOptionPane.showMessageDialog(null, "Ops!! Você têm " + idade + " anos\nIdade Inválida!!");
                jFormattedDataNascimento.setText(null);

            }
        }
    }//GEN-LAST:event_jFormattedDataNascimentoKeyPressed

    private void jFormattedDataVenciCNHKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedDataVenciCNHKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            calcularAnosCNH();
        }
    }//GEN-LAST:event_jFormattedDataVenciCNHKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> jComboBoxCidade;
    private javax.swing.JFormattedTextField jFormattedCNH;
    private javax.swing.JFormattedTextField jFormattedCPF;
    private javax.swing.JFormattedTextField jFormattedCelular;
    private javax.swing.JFormattedTextField jFormattedDataNascimento;
    private javax.swing.JFormattedTextField jFormattedDataPrimCNH;
    private javax.swing.JFormattedTextField jFormattedDataVenciCNH;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextBairro;
    private javax.swing.JTextField jTextComplemento;
    private javax.swing.JTextField jTextEndereco;
    private javax.swing.JTextField jTextNCasa;
    private javax.swing.JTextField jTextNomeCliente;
    private javax.swing.JTextField jTextNomeLogin;
    private javax.swing.JTextField jTextSaldoInicial;
    private javax.swing.JTextField jTextSenhaLogin;
    private javax.swing.JLabel jTxtAnosCNH;
    private javax.swing.JLabel status;
    // End of variables declaration//GEN-END:variables
}
