/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Visao;

import ConectaBanco.ConectaBanco;
import Controle.ControleClientes;
import Modelo.ModeloClientes;
import Modelo.ModeloTabela;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Fernando
 */
public class FrmClientes extends javax.swing.JFrame {
    ConectaBanco conecta = new ConectaBanco();
    ConectaBanco conectaTipos = new ConectaBanco();
    ConectaBanco conectaCategorias = new ConectaBanco();
    Modelo.ModeloClientes mod = new ModeloClientes();
    ControleClientes controle = new ControleClientes();
    /**
     * Creates new form FrmClientes
     */
    public FrmClientes() {
        initComponents();
        conecta.conexao();
        conectaTipos.conexao();
        conectaCategorias.conexao();
        jComboTipo.removeAllItems();
        jComboCategoria.removeAllItems();
        jButtonSalvar.setEnabled(false);
        try {
             conectaTipos.executaSql("select * from tipos order by nome");
             conectaTipos.rs.first();
             do{
                 jComboTipo.addItem(conectaTipos.rs.getString("nome"));
             }while(conectaTipos.rs.next());
             conectaCategorias.executaSql("select * from categorias order by nome");
             conectaCategorias.rs.first();
             do{
                 jComboCategoria.addItem(conectaCategorias.rs.getString("nome"));
             }while(conectaCategorias.rs.next());
             conecta.executaSql("SELECT id, nome, concat(substring(placa,1,3),'-',substring(placa,4,7)) as placa , tipo, categoria FROM clientes order by nome");
             conecta.rs.first();
             jTextId.setText(String.valueOf(conecta.rs.getInt("id")));
             jTextNome.setText(conecta.rs.getString("nome"));
             jTextPlaca.setText(conecta.rs.getString("placa"));
             conectaTipos.executaSql("select * from tipos where id = "+ conecta.rs.getString("tipo"));
             conectaTipos.rs.first();
             jComboTipo.setSelectedItem(conectaTipos.rs.getString("nome"));
             conectaCategorias.executaSql("select * from categorias where id = "+ conecta.rs.getString("categoria"));
             conectaCategorias.rs.first();
             jComboCategoria.setSelectedItem(conectaCategorias.rs.getString("nome"));
             preencherTabelaClientes("SELECT id, nome, concat(substring(placa,1,3),'-',substring(placa,4,7)) as placa , tipo, categoria FROM clientes order by nome");
             
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(rootPane, "Erro ao setar o primeiro registro! \nErro:"+ex);
         }
    }
    public void preencherTabelaClientes(String SQL){
        conecta.conexao();
        conectaTipos.conexao();
        conectaCategorias.conexao();
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"ID","NOME","PLACA","TIPO","CATEGORIA"};
        
        conecta.executaSql(SQL);
        try {
            conecta.rs.first();
            do{
                conectaTipos.executaSql("select * from tipos where id = "+conecta.rs.getInt("tipo"));
                conectaTipos.rs.first();
                conectaCategorias.executaSql("select * from categorias where id = "+conecta.rs.getInt("categoria"));
                conectaCategorias.rs.first();
                dados.add(new Object[]{conecta.rs.getInt("id"),conecta.rs.getString("nome"),conecta.rs.getString("placa"),conectaTipos.rs.getString("nome"),conectaCategorias.rs.getString("nome")});
            }while(conecta.rs.next());
        } catch (SQLException ex) {
            Logger.getLogger(FrmClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        jTableClientes.setModel(modelo);
        jTableClientes.getColumnModel().getColumn(0).setPreferredWidth(80);
        jTableClientes.getColumnModel().getColumn(0).setResizable(false);
        jTableClientes.getColumnModel().getColumn(1).setPreferredWidth(242);
        jTableClientes.getColumnModel().getColumn(1).setResizable(false);
        jTableClientes.getColumnModel().getColumn(2).setPreferredWidth(180);
        jTableClientes.getColumnModel().getColumn(2).setResizable(false);
        jTableClientes.getColumnModel().getColumn(3).setPreferredWidth(180);
        jTableClientes.getColumnModel().getColumn(3).setResizable(false);
        jTableClientes.getColumnModel().getColumn(4).setPreferredWidth(180);
        jTableClientes.getColumnModel().getColumn(4).setResizable(false);
        jTableClientes.getTableHeader().setReorderingAllowed(false);
        jTableClientes.setAutoResizeMode(jTableClientes.AUTO_RESIZE_OFF);
        jTableClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
    }   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextId = new javax.swing.JTextField();
        jTextNome = new javax.swing.JTextField();
        jTextPlaca = new javax.swing.JTextField();
        jComboTipo = new javax.swing.JComboBox();
        jComboCategoria = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();
        jButtonInicio = new javax.swing.JButton();
        jButtonAdicionar = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jButtonAnterior = new javax.swing.JButton();
        jButtonProximo = new javax.swing.JButton();
        jButtonAtualizar = new javax.swing.JButton();
        jButtonDeletar = new javax.swing.JButton();
        jButtonUltimo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Clientes.");
        setResizable(false);

        jLabel1.setText("CODIGO:");

        jLabel2.setText("NOME:");

        jLabel3.setText("PLACA:");

        jLabel4.setText("TIPO:");

        jLabel5.setText("CATEGORIA:");

        jTextId.setEditable(false);
        jTextId.setBackground(new java.awt.Color(255, 255, 0));

        jTextPlaca.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextPlacaFocusLost(evt);
            }
        });
        jTextPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextPlacaActionPerformed(evt);
            }
        });

        jComboTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboTipo.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboTipoPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jComboCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboCategoria.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboCategoriaFocusGained(evt);
            }
        });

        jTableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableClientes);

        jButtonInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inicio.png"))); // NOI18N
        jButtonInicio.setMaximumSize(new java.awt.Dimension(81, 57));
        jButtonInicio.setMinimumSize(new java.awt.Dimension(81, 57));
        jButtonInicio.setPreferredSize(new java.awt.Dimension(81, 57));
        jButtonInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInicioActionPerformed(evt);
            }
        });

        jButtonAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/add.png"))); // NOI18N
        jButtonAdicionar.setToolTipText("Adiciona novo registro");
        jButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarActionPerformed(evt);
            }
        });

        jButtonSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Save_48x48.png"))); // NOI18N
        jButtonSalvar.setToolTipText("Salva");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButtonAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/back.png"))); // NOI18N
        jButtonAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnteriorActionPerformed(evt);
            }
        });

        jButtonProximo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/next.png"))); // NOI18N
        jButtonProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProximoActionPerformed(evt);
            }
        });

        jButtonAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/refresh.png"))); // NOI18N
        jButtonAtualizar.setToolTipText("Atualiza");
        jButtonAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAtualizarActionPerformed(evt);
            }
        });

        jButtonDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/delete.png"))); // NOI18N
        jButtonDeletar.setToolTipText("Exclui");
        jButtonDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeletarActionPerformed(evt);
            }
        });

        jButtonUltimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fim.png"))); // NOI18N
        jButtonUltimo.setPreferredSize(new java.awt.Dimension(81, 57));
        jButtonUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUltimoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jTextPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jComboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonAdicionar)
                                .addGap(2, 2, 2)
                                .addComponent(jButtonSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonAtualizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonDeletar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jButtonAnterior)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonProximo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextId, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextNome, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jComboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jComboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAnterior)
                    .addComponent(jButtonProximo)
                    .addComponent(jButtonUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonAdicionar)
                    .addComponent(jButtonAtualizar)
                    .addComponent(jButtonDeletar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(738, 593));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInicioActionPerformed
        // TODO add your handling code here:
        
        try {
            conecta.executaSql("SELECT id, nome, concat(substring(placa,1,3),'-',substring(placa,4,7)) as placa , tipo, categoria FROM clientes order by nome");
            conecta.rs.first();
            jTextId.setText(String.valueOf(conecta.rs.getInt("id")));
            jTextNome.setText(conecta.rs.getString("nome"));
            jTextPlaca.setText(conecta.rs.getString("placa").substring(0, 3) + conecta.rs.getString("placa").substring(4, 8));
            conectaTipos.executaSql("select * from tipos where id = "+ conecta.rs.getString("tipo"));
            conectaTipos.rs.first();
            jComboTipo.setSelectedItem(conectaTipos.rs.getString("nome"));
            conectaCategorias.executaSql("select * from categorias where id = "+ conecta.rs.getString("categoria"));
            conectaCategorias.rs.first();
            jComboCategoria.setSelectedItem(conectaCategorias.rs.getString("nome"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao setar o primeiro registro! \nErro:"+ex);
        }

    }//GEN-LAST:event_jButtonInicioActionPerformed

    private void jButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarActionPerformed
        // TODO add your handling code here:
        jTextId.setText(null);
        jTextNome.setText(null);
        jTextPlaca.setText(null);
        jTableClientes.setVisible(false);
        jButtonSalvar.setEnabled(true);
        jButtonAtualizar.setEnabled(false);
        jButtonDeletar.setEnabled(false);
        jButtonInicio.setEnabled(false);
        jButtonAnterior.setEnabled(false);
        jButtonUltimo.setEnabled(false);
        jButtonProximo.setEnabled(false);
       
    }//GEN-LAST:event_jButtonAdicionarActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        // TODO add your handling code here:
        if(jTextNome.getText().equals("") || jTextPlaca.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Todos os campos são Obrigatorios");
            return ;
        }
        try {
            mod.setNome(jTextNome.getText());
            mod.setPlaca(jTextPlaca.getText());
            conectaTipos.executaSql("select * from tipos where nome = '"+ jComboTipo.getSelectedItem()+"'");
            conectaTipos.rs.first();
            mod.setTipo(conectaTipos.rs.getInt("id"));
            conectaCategorias.executaSql("select * from categorias where nome = '"+ jComboCategoria.getSelectedItem()+"'");
            conectaCategorias.rs.first();
            mod.setCategoria(conectaCategorias.rs.getInt("id"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao atualizar o registro! \nErro:"+ex);
        }
        
            
        controle.InserirClientes(mod);
        jButtonSalvar.setEnabled(false);
        jButtonAtualizar.setEnabled(true);
        jButtonDeletar.setEnabled(true);
        
        jTextId.setText(null);
        jTextNome.setText(null);
        jTextPlaca.setText(null);
        jTableClientes.setVisible(true);
        jButtonSalvar.setEnabled(false);
        jButtonAtualizar.setEnabled(true);
        jButtonDeletar.setEnabled(true);
        jButtonInicio.setEnabled(true);
        jButtonAnterior.setEnabled(true);
        jButtonUltimo.setEnabled(true);
        jButtonProximo.setEnabled(true);
        preencherTabelaClientes("SELECT id, nome, concat(substring(placa,1,3),'-',substring(placa,4,7)) as placa , tipo, categoria FROM clientes order by nome");
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnteriorActionPerformed
        // TODO add your handling code here:
        try {
            //conecta.executaSql("select * from tipos order by id");
            conecta.rs.previous();
            jTextId.setText(String.valueOf(conecta.rs.getInt("id")));
            jTextNome.setText(conecta.rs.getString("nome"));
            jTextPlaca.setText(conecta.rs.getString("placa").substring(0, 3) + conecta.rs.getString("placa").substring(4, 8));
            conectaTipos.executaSql("select * from tipos where id = "+ conecta.rs.getString("tipo"));
            conectaTipos.rs.first();
            jComboTipo.setSelectedItem(conectaTipos.rs.getString("nome"));
            conectaTipos.executaSql("select * from tipos where id = "+ conecta.rs.getString("tipo"));
            conectaTipos.rs.first();
            jComboTipo.setSelectedItem(conectaTipos.rs.getString("nome"));
            conectaCategorias.executaSql("select * from categorias where id = "+ conecta.rs.getString("categoria"));
            conectaCategorias.rs.first();
            jComboCategoria.setSelectedItem(conectaCategorias.rs.getString("nome"));

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(rootPane, "Erro ao setar o registro! \nErro:"+ex);
        }
    }//GEN-LAST:event_jButtonAnteriorActionPerformed

    private void jButtonProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProximoActionPerformed
        // TODO add your handling code here:
        try {
            conecta.rs.next();
            jTextId.setText(String.valueOf(conecta.rs.getInt("id")));
            jTextNome.setText(conecta.rs.getString("nome"));
            jTextPlaca.setText(conecta.rs.getString("placa").substring(0, 3) + conecta.rs.getString("placa").substring(4, 8));
            conectaTipos.executaSql("select * from tipos where id = "+ conecta.rs.getString("tipo"));
            conectaTipos.rs.first();
            jComboTipo.setSelectedItem(conectaTipos.rs.getString("nome"));
            conectaTipos.executaSql("select * from tipos where id = "+ conecta.rs.getString("tipo"));
            conectaTipos.rs.first();
            jComboTipo.setSelectedItem(conectaTipos.rs.getString("nome"));
            conectaCategorias.executaSql("select * from categorias where id = "+ conecta.rs.getString("categoria"));
            conectaCategorias.rs.first();
            jComboCategoria.setSelectedItem(conectaCategorias.rs.getString("nome"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao setar o primeiro registro! \nErro:"+ex);
        }
    }//GEN-LAST:event_jButtonProximoActionPerformed

    private void jButtonAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtualizarActionPerformed
        // TODO add your handling code here:
        if(jTextNome.getText().equals("") || jTextPlaca.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Todos os campos são Obrigatorios");
            return ;
        }
        try {
            mod.setId(Integer.parseInt(jTextId.getText()));
            mod.setNome(jTextNome.getText());
            mod.setPlaca(jTextPlaca.getText().substring(0, 3)+jTextPlaca.getText().substring(3, 7));
            conectaTipos.executaSql("select * from tipos where nome = '"+ jComboTipo.getSelectedItem()+"'");
            conectaTipos.rs.first();
            mod.setTipo(conectaTipos.rs.getInt("id"));
            conectaCategorias.executaSql("select * from categorias where nome = '"+ jComboCategoria.getSelectedItem()+"'");
            conectaCategorias.rs.first();
            mod.setCategoria(conectaCategorias.rs.getInt("id"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao atualizar o registro! \nErro:"+ex);
        }
        
        controle.AlterarClientes(mod);
        preencherTabelaClientes("SELECT id, nome, concat(substring(placa,1,3),'-',substring(placa,4,7)) as placa , tipo, categoria FROM clientes order by nome");
    }//GEN-LAST:event_jButtonAtualizarActionPerformed

    private void jButtonDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeletarActionPerformed
        // TODO add your handling code here:
        mod.setId(Integer.parseInt(jTextId.getText()));
        mod.setNome(jTextNome.getText());
        controle.ExcluirClientes(mod);
        try {
            conecta.rs.first();
            jTextId.setText(String.valueOf(conecta.rs.getInt("id")));
            jTextNome.setText(conecta.rs.getString("nome"));
        } catch (SQLException ex) {
            Logger.getLogger(FrmTiposVeiculos.class.getName()).log(Level.SEVERE, null, ex);
        }
        preencherTabelaClientes("SELECT id, nome, concat(substring(placa,1,3),'-',substring(placa,4,7)) as placa , tipo, categoria FROM clientes order by nome");
    }//GEN-LAST:event_jButtonDeletarActionPerformed

    private void jButtonUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUltimoActionPerformed
        // TODO add your handling code here:
        try {
            conecta.executaSql("SELECT id, nome, concat(substring(placa,1,3),'-',substring(placa,4,7)) as placa , tipo, categoria FROM clientes order by nome");
            conecta.rs.last();
            jTextId.setText(String.valueOf(conecta.rs.getInt("id")));
            jTextNome.setText(conecta.rs.getString("nome"));
            jTextPlaca.setText(conecta.rs.getString("placa").substring(0, 3) + conecta.rs.getString("placa").substring(4, 8));
            conectaTipos.executaSql("select * from tipos where id = "+ conecta.rs.getString("tipo"));
            conectaTipos.rs.first();
            jComboTipo.setSelectedItem(conectaTipos.rs.getString("nome"));
            conectaTipos.executaSql("select * from tipos where id = "+ conecta.rs.getString("tipo"));
            conectaTipos.rs.first();
            jComboTipo.setSelectedItem(conectaTipos.rs.getString("nome"));
            conectaCategorias.executaSql("select * from categorias where id = "+ conecta.rs.getString("categoria"));
            conectaCategorias.rs.first();
            jComboCategoria.setSelectedItem(conectaCategorias.rs.getString("nome"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao setar o ultimo registro! \nErro:"+ex);
        }
    }//GEN-LAST:event_jButtonUltimoActionPerformed

    private void jTextPlacaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextPlacaFocusLost
        // TODO add your handling code here:
        
        if(!Pattern.matches("[A-Z]{3}[0-9]{4}", jTextPlaca.getText().toUpperCase()))
        {
            JOptionPane.showMessageDialog(rootPane, "Placa incorreta!");
            jTextPlaca.setText(null);
            
        }
    }//GEN-LAST:event_jTextPlacaFocusLost

    private void jTextPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextPlacaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextPlacaActionPerformed

    private void jTableClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableClientesMouseClicked
        int linha = jTableClientes.getSelectedRow();
        jTextId.setText(jTableClientes.getValueAt(linha, 0).toString());
        jTextNome.setText(jTableClientes.getValueAt(linha, 1).toString());
        jTextPlaca.setText(jTableClientes.getValueAt(linha, 2).toString().substring(0, 3)+jTableClientes.getValueAt(linha, 2).toString().substring(4, 8));
        try {
            conectaTipos.executaSql("select * from tipos where nome = '"+ jTableClientes.getValueAt(linha, 3).toString()+"'");
            conectaTipos.rs.first();
            jComboTipo.setSelectedItem(conectaTipos.rs.getString("nome"));
            preencheCombo();
        } catch (SQLException ex) {
            Logger.getLogger(FrmClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jTableClientesMouseClicked

    private void jComboCategoriaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboCategoriaFocusGained
        // TODO add your handling code here:
        if(jComboTipo.getSelectedItem() != null){ 
            preencheCombo();
        }
    }//GEN-LAST:event_jComboCategoriaFocusGained

    private void jComboTipoPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboTipoPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        preencheCombo();
    }//GEN-LAST:event_jComboTipoPopupMenuWillBecomeInvisible
    private void preencheCombo(){
    
            try {
                conectaTipos.executaSql("select * from tipos where nome = '" + jComboTipo.getSelectedItem() + "'");
                conectaTipos.rs.first();
                conectaCategorias.executaSql("select * from categorias where id_tipo = '"+conectaTipos.rs.getInt("id")+"'");
                conectaCategorias.rs.first();
                jComboCategoria.removeAllItems();
                do{
                    jComboCategoria.addItem(conectaCategorias.rs.getString("nome"));
                }while(conectaCategorias.rs.next());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao preencher os combobox! Erro: \n" + ex);
            }
           
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdicionar;
    private javax.swing.JButton jButtonAnterior;
    private javax.swing.JButton jButtonAtualizar;
    private javax.swing.JButton jButtonDeletar;
    private javax.swing.JButton jButtonInicio;
    private javax.swing.JButton jButtonProximo;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonUltimo;
    private javax.swing.JComboBox jComboCategoria;
    private javax.swing.JComboBox jComboTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableClientes;
    private javax.swing.JTextField jTextId;
    private javax.swing.JTextField jTextNome;
    private javax.swing.JTextField jTextPlaca;
    // End of variables declaration//GEN-END:variables
}
