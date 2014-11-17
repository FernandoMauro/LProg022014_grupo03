/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import ConectaBanco.ConectaBanco;
import Modelo.ModeloCategorias;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando
 */
public class ControleCategorias {
    ConectaBanco conectaCategorias= new ConectaBanco();
    
    public void ControleCategorias(){
        conectaCategorias.conexao();
    }
    public void InserirCategorias(ModeloCategorias mod){
        //conectaCategorias.conexao();
        try {
            PreparedStatement pst = conectaCategorias.conn.prepareStatement("insert into categorias(nome,id_tipo) values(?,?)");
            pst.setString(1, mod.getNome().toUpperCase());
            pst.setInt(2, mod.getId_tipo());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na inserção dos dados! Erro:"+ ex);
        }
        conectaCategorias.desconectata();
    }
    public void ExcluirCategorias(ModeloCategorias mod){
        //conectaCategorias.conexao();
        try {
            PreparedStatement pst = conectaCategorias.conn.prepareStatement("delete from categorias where id = ?");
            pst.setInt(1, mod.getId());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na exclusão dos dados!\n Erro:"+ ex);
        }
        //conectaCategorias.desconectata();
    }
    public void AlterarCategorias(ModeloCategorias mod){
        //conectaCategorias.conexao();
        try {
            PreparedStatement pst = conectaCategorias.conn.prepareStatement("update categorias set nome = ? , id_tipo = ? where id = ?");
            pst.setString(1, mod.getNome().toUpperCase());
            pst.setInt(2, mod.getId_tipo());
            pst.setInt(3, mod.getId());
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na alteração dos dados!\n Erro:"+ ex);
        }
    }
    
}
