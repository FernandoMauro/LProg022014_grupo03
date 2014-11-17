/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import ConectaBanco.ConectaBanco;
import Modelo.ModeloClientes;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando
 */
public class ControleClientes {
    ConectaBanco conectaClientes = new ConectaBanco();
    
    public void InserirClientes(ModeloClientes mod){
        conectaClientes.conexao();
        try {
            PreparedStatement pst = conectaClientes.conn.prepareStatement("insert into clientes(nome, placa, tipo, categoria) values(?, ?, ?, ?)");
            pst.setString(1, mod.getNome().toUpperCase());
            pst.setString(2, mod.getPlaca().toUpperCase());
            pst.setInt(3, mod.getTipo());
            pst.setInt(4, mod.getCategoria());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na inserção dos dados! Erro:"+ ex);
        }
        conectaClientes.desconectata();
    }
    public void ExcluirClientes(ModeloClientes mod){
        conectaClientes.conexao();
        try {
            PreparedStatement pst = conectaClientes.conn.prepareStatement("delete from clientes where id = ?");
            pst.setInt(1, mod.getId());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na exclusão dos dados!\n Erro:"+ ex);
        }
        conectaClientes.desconectata();
    }
    public void AlterarClientes(ModeloClientes mod){
        conectaClientes.conexao();
        try {
            PreparedStatement pst = conectaClientes.conn.prepareStatement("update clientes set nome = ? , placa = ?, tipo = ?, categoria = ? where id = ?");
            pst.setString(1, mod.getNome().toUpperCase());
            pst.setString(2, mod.getPlaca().toUpperCase());
            pst.setInt(3, mod.getTipo());
            pst.setInt(4, mod.getCategoria());
            pst.setInt(5, mod.getId());
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na alteração dos dados!\n Erro:"+ ex);
        }
    }
    
}
