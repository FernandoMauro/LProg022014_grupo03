/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import ConectaBanco.ConectaBanco;
import Modelo.ModeloTipos;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando
 */
public class ControleTipos {
    ConectaBanco conectaTipos = new ConectaBanco();
    
    public void InserirTipos(ModeloTipos mod){
        conectaTipos.conexao();
        try {
            PreparedStatement pst = conectaTipos.conn.prepareStatement("insert into tipos(nome) values(?)");
            pst.setString(1, mod.getNome().toUpperCase());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na inserção dos dados! Erro:"+ ex);
        }
        conectaTipos.desconectata();
    }
    public void ExcluirTipos(ModeloTipos mod){
        conectaTipos.conexao();
        try {
            PreparedStatement pst = conectaTipos.conn.prepareStatement("delete from tipos where id = ?");
            pst.setInt(1, mod.getId());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na exclusão dos dados!\n Erro:"+ ex);
        }
        conectaTipos.desconectata();
    }
    public void AlterarTipos(ModeloTipos mod){
        conectaTipos.conexao();
        try {
            PreparedStatement pst = conectaTipos.conn.prepareStatement("update tipos set nome = ? where id = ?");
            pst.setString(1, mod.getNome().toUpperCase());
            pst.setInt(2, mod.getId());
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na alteração dos dados!\n Erro:"+ ex);
        }
    }
}
