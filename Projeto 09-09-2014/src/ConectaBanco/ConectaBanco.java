
package ConectaBanco;

import java.sql.*;
import javax.swing.JOptionPane;

public class ConectaBanco
{
    public Statement stm;
    public ResultSet rs;
    private String  drive = "com.mysql.jdbc.Driver";
    private String caminho = "jdbc:mysql://localhost:3306/banco";
    private String usuario = "root";
    private String senha = "";
    public Connection conn;
    
    public void conexao()
    {
        try {
            System.setProperty("jdbc.Drivers", drive);
            conn = DriverManager.getConnection(caminho, usuario, senha);
            //JOptionPane.showMessageDialog(null, "Conectado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de conexão!\n Erro: "+ex.getMessage());
        }
    }
    public void desconectata()
    {
        try {
            conn.close();
            //JOptionPane.showMessageDialog(null, "Desconectado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro dao fechar a conexão!\n Erro: "+ex.getMessage());
        }
    }
    public void executaSql(String sql){
    
        try {
            stm = conn.createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de ExecutaSQL!\n Erro: "+ex.getMessage());
        }
    }
}

