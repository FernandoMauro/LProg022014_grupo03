package Relatorios;

import ConectaBanco.ConectaBanco;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class Relatorios {

    ConectaBanco conecta = new ConectaBanco();

    public Relatorios() {
        conecta.conexao();
    }

    public void imprimeRelTiposVeiculos() {
        try {
            conecta.executaSql("select * from tipos");
            JRResultSetDataSource relatTipos = new JRResultSetDataSource(conecta.rs);
            JasperPrint jpTipos = JasperFillManager.fillReport("src\\Relatorios\\tiposVeiculos.jasper", new HashMap(), relatTipos);
            JasperViewer jv = new JasperViewer(jpTipos, false);
            jv.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
            jv.setTitle("Relatório de Tipos de Veiculos");
            jv.setVisible(true);
        } catch (JRException es) {
            JOptionPane.showMessageDialog(null, "Erro ao execultar o Relatório!\n ERRO :" + es);
        }
    }

    public void imprimeRelCategoriasVeiculos() {
        try {
            conecta.executaSql("SELECT DISTINCT categorias.id, categorias.nome, tipos.nome AS tipo FROM categorias INNER JOIN tipos ON categorias.id_tipo = tipos.id ORDER BY tipo, categorias.id");
            JRResultSetDataSource relatTipos = new JRResultSetDataSource(conecta.rs);
            JasperPrint jpTipos = JasperFillManager.fillReport("src\\Relatorios\\categoriasVeiculos.jasper", new HashMap(), relatTipos);
            JasperViewer jv = new JasperViewer(jpTipos, false);
            jv.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
            jv.setTitle("Relatório de Categorias de Veiculos");
            jv.setVisible(true);
        } catch (JRException es) {
            JOptionPane.showMessageDialog(null, "Erro ao execultar o Relatório!\n ERRO :" + es);
        }
    }

    public void imprimeRelClientes() {
        try {
            conecta.executaSql("SELECT clientes.id, clientes.nome, concat(substring(clientes.placa,1,3),'-',substring(clientes.placa,4,7)) as placa, tipos.nome as tipo, categorias.nome as categoria FROM clientes INNER JOIN tipos ON clientes.tipo = tipos.id INNER JOIN categorias ON clientes.categoria = categorias.id order by clientes.nome");
            JRResultSetDataSource relatTipos = new JRResultSetDataSource(conecta.rs);
            JasperPrint jpTipos = JasperFillManager.fillReport("src\\Relatorios\\clientes.jasper", new HashMap(), relatTipos);
            JasperViewer jv = new JasperViewer(jpTipos, false);
            jv.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
            jv.setTitle("Relatório de Categorias de Veiculos");
            jv.setVisible(true);
        } catch (JRException es) {
            JOptionPane.showMessageDialog(null, "Erro ao execultar o Relatório!\n ERRO :" + es);
        }
    }

    public void imprimeTabelaPrecos() {
        try {
            conecta.executaSql("SELECT * FROM PRECOS");
            JRResultSetDataSource relatTipos = new JRResultSetDataSource(conecta.rs);
            JasperPrint jpTipos = JasperFillManager.fillReport("src\\Relatorios\\tabelaPrecos.jasper", new HashMap(), relatTipos);
            JasperViewer jv = new JasperViewer(jpTipos, false);
            jv.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
            jv.setTitle("Relatório de Categorias de Veiculos");
            jv.setVisible(true);
        } catch (JRException es) {
            JOptionPane.showMessageDialog(null, "Erro ao execultar o Relatório!\n ERRO :" + es);
        }
    }
}
