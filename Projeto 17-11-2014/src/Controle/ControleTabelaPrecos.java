/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import ConectaBanco.ConectaBanco;
import Modelo.ModeloTabelaPrecos;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando
 */
public class ControleTabelaPrecos {
    ConectaBanco conectaCategorias= new ConectaBanco();
    
    public void InserirTabelaPrecos(ModeloTabelaPrecos mod){
        conectaCategorias.conexao();
        try {
            PreparedStatement pst = conectaCategorias.conn.prepareStatement("insert into precos (nome,valor) values(?,?)");
            pst.setString(1, mod.getNome().toUpperCase());
            pst.setFloat(2, mod.getValor());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na inserção dos dados! Erro:"+ ex);
        }
        conectaCategorias.desconectata();
    }
    public void ExcluirTabelaPrecos(ModeloTabelaPrecos mod){
        conectaCategorias.conexao();
        try {
            PreparedStatement pst = conectaCategorias.conn.prepareStatement("delete from precos where id = ?");
            pst.setInt(1, mod.getId());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na exclusão dos dados!\n Erro:"+ ex);
        }
        conectaCategorias.desconectata();
    }
    public void AlterarTabelaPrecos(ModeloTabelaPrecos mod){
        conectaCategorias.conexao();
        try {
            PreparedStatement pst = conectaCategorias.conn.prepareStatement("update precos set nome = ? , valor = ? where id = ?");
            pst.setString(1, mod.getNome().toUpperCase());
            pst.setFloat(2, mod.getValor());
            pst.setInt(3, mod.getId());
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na alteração dos dados!\n Erro:"+ ex);
        }
    }
}
