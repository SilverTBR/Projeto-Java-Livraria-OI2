
package Controller;

import View.JFAluguel;
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
public class AluguelDAOTest{
    
    
    AluguelDAO controle = new AluguelDAO();
    ClienteDAO controleCliente = new ClienteDAO();
    LivroDAO controleLivro = new LivroDAO();
    String nome = "Teste Aluguel";
    
    public AluguelDAOTest() {
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
    

    void setCaminhosCliente(){
        controleCliente.setCaminhoTeste();
        controle.conectar();
        controleCliente.conectar();
        controle.excluir();
        controleCliente.excluir();  
    }
    
    void setCaminhosLivro(){
        controleLivro.setCaminhoTeste();
        controle.conectar();
        controle.excluir();
        controleLivro.conectar();
        controleLivro.excluir();
    }
    
    void setCaminhosAluguel(){
        controle.setCaminhoTeste();
        controle.conectar();
        controle.excluir();
    }
    
    public int criaLivro(int opt){
        controleLivro.getLivro().setTitulo("Teste Aluguel");
        controleLivro.getLivro().setGenero("Teste");
        controleLivro.getLivro().setEditora("Teste");
        controleLivro.getLivro().setNomeAutor("Teste");
        controleLivro.getLivro().setSobrenomeAutor("Teste");
        controleLivro.getLivro().setQntPgns(50);
        controleLivro.inserir();
        controleLivro.consultarTodos();
        
        return retornaId(controleLivro.getrsdados(), opt, "id_livro");
    }
    
    public int criaCliente(int opt){
        controleCliente.getCliente().setNome("Teste Aluguel");
        controleCliente.getCliente().setSobrenome("Teste");
        controleCliente.getCliente().setEstado("SP");
        controleCliente.getCliente().setEndereco("End. Teste");
        controleCliente.getCliente().setCidade("Testalopis");
        if(opt == 2){controleCliente.getCliente().setCPF("121.858.818-82");}
        controleCliente.getCliente().setCPF("121.818.818-81");
        controleCliente.getCliente().setBairro("Bairro Teste"); 
    
        controleCliente.inserir();
        controleCliente.consultarTodos();
        
        return retornaId(controleCliente.getrsdados(), opt, "id_cliente");
    }
    
    int retornaId(ResultSet rs, int opt, String nome){
        int id = 0;
        
        if(opt == 1){
            try{
                if(rs.first()) {
                   id = rs.getInt(nome);
                }
            } catch (SQLException ex) {
            }  
        }else{
            try{
                if(rs.last()) {
                   id = rs.getInt(nome);
                }
            } catch (SQLException ex) {
            }  
        }
        return id;   
    }
    
  
    public AluguelDAO criaAluguel(int idCli, int idLiv){
        
   
        controle.getAluguel().setDataAluguel("02-09-2021");
        controle.getAluguel().setDataDev("02-12-2022");
        controle.getAluguel().setIdCliente(idCli);
        controle.getAluguel().setIdLivro(idLiv);
        
        
        return controle;
        
    }
    
     /**
     * Testando o registro normal de um aluguel, com cliente e livro HAPPY PATH.
     */
    
    @Test
    public void registroSimplesTest(){    
        
        setCaminhosAluguel();
        setCaminhosCliente();
        setCaminhosLivro();
        
        int livro = criaLivro(1);
        int cliente = criaCliente(1);
        
        controle = criaAluguel(cliente, livro);
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
     * Testando o registro normal de um aluguel, com cliente e livro alguns dados diferentes HAPPY PATH
     */
    
    @Test
    public void registroSimples2Test(){    

        setCaminhosAluguel();
        setCaminhosCliente();
        setCaminhosLivro();
        
        int livro = criaLivro(1);
        int cliente = criaCliente(1);
        controleCliente.getCliente().setNome("Teste Aluguel 2");
        controleCliente.getCliente().setBairro("Bairro Teste 2"); 
        
        controle = criaAluguel(cliente, livro);
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
     * Testando o registro normal de um aluguel, com cliente e livro alguns dados diferentes HAPPY PATH
     */
    
    @Test
    public void registroSimples3Test(){    
        
        setCaminhosAluguel();
        setCaminhosCliente();
        setCaminhosLivro();
        
        int livro = criaLivro(1);
        controleLivro.getLivro().setSobrenomeAutor("Teste 2");
        controleLivro.getLivro().setQntPgns(30);
        int cliente = criaCliente(1);
        
        controle = criaAluguel(cliente, livro);
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
     * Testando o registro normal de um aluguel, com cliente e livro alguns dados diferentes HAPPY PATH
     */
    
    @Test
    public void registroSimples4Test(){    
        
        setCaminhosAluguel();
        setCaminhosCliente();
        setCaminhosLivro();
        
        int livro = criaLivro(1);
        int cliente = criaCliente(1);
        
        controle = criaAluguel(cliente, livro);
        controle.getAluguel().setDataDev("18-12-2023");
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
     * Testando o registro normal duplo de um aluguel, com cliente e livro HAPPY PATH
     */
    
    @Test
    public void registroDuploTest(){     
        
        setCaminhosAluguel();
        setCaminhosCliente();
        setCaminhosLivro();
        
        int cliente = criaCliente(1);
        int livro = criaLivro(1);
        int livro2 = criaLivro(2);
        
        try{
            controle = criaAluguel(cliente, livro);
            controle.inserir();

            controle = criaAluguel(cliente, livro2);
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
     * Testando o registro normal duplo de um aluguel, com cliente e livro HAPPY PATH
     */
    
    @Test
    public void registroDuplo2Test(){     
        
        setCaminhosAluguel();
        setCaminhosCliente();
        setCaminhosLivro();
        
        int cliente = criaCliente(1);
        int livro = criaLivro(1);
        int livro2 = criaLivro(2);
        
        try{
            controle = criaAluguel(cliente, livro);
            controle.inserir();

            controle = criaAluguel(cliente, livro2);
            controle.getAluguel().setDataAluguel("03-10-2021");
            controle.getAluguel().setDataDev("05-12-2022");            
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
     * Teste com data de devolução inválida.
     */
    
    @Test
    public void invalidoDataDevTest(){ 

        setCaminhosAluguel();
        setCaminhosCliente();
        setCaminhosLivro();
        
        controle = criaAluguel(criaCliente(1), criaLivro(1));
        controle.getAluguel().setDataDev("02-09-202");
        controle.inserir();
        controle.consultarTodos();
        ResultSet rs = controle.getrsdados();
        try{
          assertEquals(false,rs.next());     
          controle.desconectar();
          
        } catch (SQLException ex) {
            fail("Erro ao executar o teste, gerou uma falha de conexão!");
        }  
    }  
    
     /**
     * Teste inserindo outra data na data de devolução HAPPY PATH.
     */  
    
    @Test
    public void alterandoDataDevTest(){      
        
        setCaminhosAluguel();
        setCaminhosCliente();
        setCaminhosLivro();
        
        controle = criaAluguel(criaCliente(1), criaLivro(1));
        controle.getAluguel().setDataDev("02-09-2022");
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
     *  Teste alterando a data de aluguel, que já vem definida HAPPY PATH.
     */
    
    @Test
    public void alterandoDataAluguelTest(){     
        
        setCaminhosAluguel();
        setCaminhosCliente();
        setCaminhosLivro();
        
        controle = criaAluguel(criaCliente(1), criaLivro(1));
        controle.getAluguel().setDataDev("02-12-2021");
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
     * Pesquisando na tabela de aluguel HAPPY PATH.
     */
    
    @Test
    public void pesquisaTabelaAluguelTest(){
        
        setCaminhosAluguel();
        setCaminhosCliente();
        setCaminhosLivro();
        
        controle = criaAluguel(criaCliente(1), criaLivro(1));
        controle.inserir();
       
        controle.pesquisarAluguel(nome);        
        try{
          ResultSet rs = controle.getrsdados();
          if(rs.first()) {
            assertEquals(nome,rs.getString("nome"));
          }
          controle.desconectar();
          
        } catch (SQLException ex) {
            fail("Erro ao executar o teste, gerou uma falha de conexão!");
        }        
    } 
     
      /**
     * Pesquisando na tabela de clientes HAPPY PATH.
     */   
    @Test
    public void pesquisaTabelaClienteTest(){
        
        setCaminhosAluguel();
        setCaminhosCliente();
        setCaminhosLivro();
        
        controle = criaAluguel(criaCliente(1), criaLivro(1));
        controle.inserir();
        controle.consultarTodos();
        
        controle.getClienteModel(nome);        
        try{
          ResultSet rs = controle.getrsdados();
          if(rs.first()) {
            assertEquals(nome,rs.getString("nome"));
          }
          controle.desconectar();
          
        } catch (SQLException ex) {
            fail("Erro ao executar o teste, gerou uma falha de conexão!");
        }        
    }  
    
      /**
     * Pesquisando na tabela de livros HAPPY PATH. 
     */   
    @Test
    public void pesquisaTabelaLivroTest(){
        
        setCaminhosAluguel();
        setCaminhosCliente();
        setCaminhosLivro();
        
        controle = criaAluguel(criaCliente(1), criaLivro(1));
        controle.inserir();
        controle.consultarTodos();
        
        controle.getLivroModel(nome);        
        try{
          ResultSet rs = controle.getrsdados();
          if(rs.first()) {
            assertEquals(nome,rs.getString("nome"));
          }
          controle.desconectar();
          
        } catch (SQLException ex) {
            fail("Erro ao executar o teste, gerou uma falha de conexão!");
        }        
    }  
    /**
     * Registrando algo com ID inexistente. 
     */
    @Test
    public void idInexistenteTest(){
        
        setCaminhosAluguel();
        setCaminhosCliente();
        setCaminhosLivro();
        
        controle = criaAluguel(2, criaLivro(1));
        controle.inserir();
  
        ResultSet rs = controle.getrsdados();
       
        assertEquals(null,rs);     
        controle.desconectar();        
    }
    
     /**
     * Devolução ainda não realizada HAPPY PATH.
     */
    @Test
    public void DevolucaoNaoRealizadaTest(){
        
        setCaminhosAluguel();
        setCaminhosCliente();
        setCaminhosLivro();
        
        controle = criaAluguel(criaCliente(1), criaLivro(1));
        controle.inserir();
        
        try{
          controle.consultarTodos();
          ResultSet rs = controle.getrsdados();
          if(rs.first()) {
            assertEquals(false,rs.getBoolean("devolucao"));
          }
          controle.desconectar();
          
        } catch (SQLException ex) {
            fail("Erro ao executar o teste, gerou uma falha de conexão!" + ex);
        }    
    
    }
    
     /**
     * Devolução realizada HAPPY PATH.
     */
    @Test
    public void DevolucaoRealizadaTest(){
        
        setCaminhosAluguel();
        setCaminhosCliente();
        setCaminhosLivro();
        
        controle = criaAluguel(criaCliente(1), criaLivro(1));
        controle.inserir();
        controle.consultarTodos();
        ResultSet rs = controle.getrsdados();
        controle.devolucao(retornaId(rs, 1, "id_aluguel"));
        
        try{
          controle.consultarTodos();
          rs = controle.getrsdados();
          if(rs.first()) {
            assertEquals(true,rs.getBoolean("devolucao"));
          }
          controle.desconectar();
          
        } catch (SQLException ex) {
            fail("Erro ao executar o teste, gerou uma falha de conexão!" + ex);
        }    
    }
    
     /**
     * Teste para verificar se determinado livro que foi devolvido, retorna para a lista de livros. HAPPY PATH
     */
    @Test
    public void DevolucaoRetornaParaLista(){
        
        setCaminhosAluguel();
        setCaminhosCliente();
        setCaminhosLivro();
        
        controle = criaAluguel(criaCliente(1), criaLivro(1));
        controle.inserir();
        controle.consultarTodos();
        ResultSet rs = controle.getrsdados();
        controle.devolucao(retornaId(rs, 1, "id_aluguel"));
        
        controle.getLivroModel(nome);        
        try{
          rs = controle.getrsdados();
          if(rs.first()) {
            assertEquals(nome,rs.getString("nome"));
          }
          controle.desconectar();
          
        } catch (SQLException ex) {
            fail("Erro ao executar o teste, gerou uma falha de conexão!");
        }    
        
    } 
}
