/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ConectaBanco.ConectaBanco;
import Controle.ControleClientes;
import Modelo.ModeloClientes;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Fernando
 */
public class Testes {

    ConectaBanco testes = new ConectaBanco();
    ControleClientes controleClientes = new ControleClientes();
    ModeloClientes modClientes = new ModeloClientes();

    public Testes() {
        testes.conexao();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws SQLException {
       testes.executaSql("SELECT * FROM clientes WHERE placa='aaa1234'");
       if (testes.rs!=null && 0 != testes.rs.getFetchSize() ){
            testes.executaSql("DELETE * FROM clientes WHERE placa='aaa1234'");
       }
    }

    @After
    public void tearDown() throws SQLException {
        testes.executaSql("SELECT * FROM clientes WHERE placa='aaa1234'");
        if (testes.rs!=null && 0 != testes.rs.getFetchSize() ){
            testes.executaSql("DELETE * FROM clientes WHERE placa='aaa1234'");
        }
    }

   
    public void TestesAutomatizados() {
        testes.conexao();
    }

   @Test
    public void testaInclusãoDeClientes() throws SQLException {
        
        modClientes.setNome("FERNANDO");
        modClientes.setPlaca("aaa1234");
        modClientes.setTipo(1);
        modClientes.setCategoria(1);
        
        controleClientes.InserirClientes(modClientes);
        
        testes.executaSql("SELECT * FROM clientes WHERE placa='AAA1234'");
        testes.rs.first();
        assertEquals(true, testes.rs.next());
        assertEquals("FERNANDO", testes.rs.getString("nome"));
    }
    
   @Test
    public void testaSeInclusãoDeClientesSempreMaiusculas() throws SQLException {
        
        modClientes.setNome("fernando");
        modClientes.setPlaca("aaa1234");
        modClientes.setTipo(1);
        modClientes.setCategoria(1);
        
        controleClientes.InserirClientes(modClientes);
        
        testes.executaSql("SELECT * FROM clientes WHERE placa='AAA1234'");
        testes.rs.first();
        assertEquals(true, testes.rs.next());
        assertEquals("FERNANDO", testes.rs.getString("nome"));
    }
    
    //@Test
    public void testaExcluirClientes() throws Exception{
         testes.executaSql("SELECT * FROM clientes WHERE placa='AAA1234'");
         testes.rs.first();
        modClientes.setId(Integer.parseInt(testes.rs.getString("id")));
        controleClientes.ExcluirClientes(modClientes);
    }
}
