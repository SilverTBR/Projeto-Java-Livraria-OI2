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
public class RenovarDAOTest {
    
    String nome = "Teste Aluguel";
    AluguelDAO controleAluguel = new AluguelDAO();
    RenovarDAO controle = new RenovarDAO();
    AluguelDAOTest aluguel = new AluguelDAOTest();
    
    public RenovarDAOTest() {
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
    
    @Test
    public void renovacaoTest(){
        
        aluguel.setCaminhosAluguel();
        aluguel.setCaminhosCliente();
        aluguel.setCaminhosLivro();
        
        controleAluguel = aluguel.criaAluguel(aluguel.criaCliente(1), aluguel.criaLivro(1));  
        controleAluguel.getAluguel().setDataDev("01-09-2022");
        controleAluguel.inserir();
        controleAluguel.consultarTodos();
        
        ResultSet rs = controleAluguel.getrsdados();
        
        int id = aluguel.retornaId(rs, 1, "id_aluguel");
        
       assertEquals(true, controle.devolucao("11-09-2022", id));

    }
    
    @Test
    public void renovacao2Test(){
        
        aluguel.setCaminhosAluguel();
        aluguel.setCaminhosCliente();
        aluguel.setCaminhosLivro();
        
        controleAluguel = aluguel.criaAluguel(aluguel.criaCliente(1), aluguel.criaLivro(1));  
        controleAluguel.getAluguel().setDataDev("25-11-2022");
        controleAluguel.inserir();
        controleAluguel.consultarTodos();
        
        ResultSet rs = controleAluguel.getrsdados();
        
        int id = aluguel.retornaId(rs, 1, "id_aluguel");
        
       assertEquals(true, controle.devolucao("11-12-2022", id));

    }
    
}
