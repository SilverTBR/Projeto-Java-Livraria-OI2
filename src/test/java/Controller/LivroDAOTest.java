/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Guilherme Andreotti
 */
public class LivroDAOTest {
    
    LivroDAO controle = new LivroDAO();
    AluguelDAO aluguel = new AluguelDAO();
    
    public LivroDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    public void setCaminhos(){
        controle.setCaminhoTeste();
        controle.conectar();
        aluguel.conectar();
        aluguel.excluir();
        controle.excluir();
    }
    
    public LivroDAO criaLivroGenerico()
    {
                
        controle.getLivro().setTitulo("Teste");
        controle.getLivro().setGenero("Teste");
        controle.getLivro().setEditora("Teste");
        controle.getLivro().setNomeAutor("Teste");
        controle.getLivro().setSobrenomeAutor("Teste");
        controle.getLivro().setQntPgns(150);
        
        return controle;
    }
    
    /**
     * Teste com cadastro dando sucesso.
     */
    
    @Test
    public void registroUnicoTest(){
        
        setCaminhos();
        controle = criaLivroGenerico();
        controle.inserir();
        controle.consultarTodos();
        ResultSet rs = controle.getrsdados();
        try{
            assertEquals(true,rs.next());
            
            controle.desconectar();
          
        } catch (SQLException ex) {
            fail("Erro ao executar o teste, gerou uma falha de conexão!");
        }          
    }
    
        /**
     * Teste com cadastro dando sucesso.
     */
    
    @Test
    public void registroUnico2Test(){
         
        setCaminhos();
        controle = criaLivroGenerico();
        controle.getLivro().setGenero("Teste 2");
        controle.getLivro().setEditora("Teste 2");
        controle.inserir();
        controle.consultarTodos();
        ResultSet rs = controle.getrsdados();
        try{
            assertEquals(true,rs.next());
            
            controle.desconectar();
          
        } catch (SQLException ex) {
            fail("Erro ao executar o teste, gerou uma falha de conexão!");
        }          
    }
    
     /**
     * Teste com cadastro duplo dando sucesso.
     */
    
    @Test
    public void registroDuploTest(){
         
        setCaminhos();
             
        try{    
            controle.getLivro().setTitulo("Teste");
            controle.getLivro().setGenero("Teste");
            controle.getLivro().setEditora("Teste");
            controle.getLivro().setNomeAutor("Teste");
            controle.getLivro().setSobrenomeAutor("Teste");
            controle.getLivro().setQntPgns(150);
            controle.inserir();
            controle.getLivro().setTitulo("Teste2");
            controle.getLivro().setGenero("Teste2");
            controle.getLivro().setEditora("Teste2");
            controle.getLivro().setNomeAutor("Teste2");
            controle.getLivro().setSobrenomeAutor("Teste2");
            controle.getLivro().setQntPgns(250);
            controle.inserir();
            controle.consultarCount();          
            ResultSet rs = controle.getrsdados();
           
            assertEquals(2, rs.getInt(1));
            controle.desconectar();
           
          
        } catch (SQLException ex) {
            fail("Erro ao executar o teste, gerou uma falha de conexão!");
        }          
    }  
    
    /**
     * Teste com quantidades de páginas inválidas.
     */ 
    
     @Test
    public void qntPgnsInvalidoTest(){
        
        setCaminhos();
        controle = criaLivroGenerico();
        controle.getLivro().setQntPgns(0);
        controle.inserir();
        controle.consultarTodos();      
        try{
            ResultSet rs = controle.getrsdados();
            assertEquals(false,rs.next());
            
            controle.desconectar();
          
        } catch (SQLException ex) {
            fail("Erro ao executar o teste, gerou uma falha de conexão!");
        }          
    }
      
    /**
     * Nome com titulo maior que 100.
     */ 
    
    @Test
    public void nomeInvalidoTest(){
   
        setCaminhos();
        controle = criaLivroGenerico();
        controle.getLivro().setTitulo("Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste Teste"
        + "Teste Teste Teste Teste Teste Teste Teste");     
        controle.inserir();
        controle.consultarTodos();      
        try{
            ResultSet rs = controle.getrsdados();
            assertEquals(false,rs.next());
            
            controle.desconectar();
          
        } catch (SQLException ex) {
            fail("Erro ao executar o teste, gerou uma falha de conexão!");
        }          
    }
    
     /**
     * Nome com campo em branco.
     */
    
    @Test
    public void tituloVazioTest(){
        
        setCaminhos();
        controle = criaLivroGenerico();
        controle.getLivro().setTitulo("");
        controle.inserir();
        controle.consultarTodos();      
        try{
            ResultSet rs = controle.getrsdados();
            assertEquals(false,rs.next());
            
            controle.desconectar();
          
        } catch (SQLException ex) {
            fail("Erro ao executar o teste, gerou uma falha de conexão!");
        }          
    }
    
}
