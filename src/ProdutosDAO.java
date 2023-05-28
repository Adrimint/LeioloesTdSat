/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    int num;
    boolean val;
    public int cadastrarProduto (ProdutosDTO produto){
         conn = new conectaDAO().connectDB();
         int status;
          try{
            prep = conn.prepareStatement("INSERT INTO produtos(nome, valor, status) VALUES (?,?,?)");
            prep.setString(1,produto.getNome());
            // isso pra colocar coisa no telas 
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            status = prep.executeUpdate();
             num = 1;
             val = valia(num);
            return status;
            
        }catch(SQLException ex){
             System.out.println("Erro ao conectar: " + ex.getMessage());
             num = 0;
             val = valia(num);
             return ex.getErrorCode();
        }
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
            conn = new conectaDAO().connectDB();
            String Sql = "Select * from produtos" ;
        try {
                    prep = conn.prepareStatement(Sql);
                    
                    resultset = prep.executeQuery();            
                    
                    
                    while (resultset.next()) { //.next retorna verdadeiro caso exista uma próxima posição dentro do array
                        ProdutosDTO produtos = new ProdutosDTO();
                        
                        produtos.setId(resultset.getInt("id"));
                        produtos.setNome(resultset.getString("nome"));
                        produtos.setValor(resultset.getInt("valor"));
                        produtos.setStatus(resultset.getString("status"));
                        
                        listagem.add(produtos);    
                    }
                    return listagem;
                } catch (Exception e) {
                    return null;
                }
        }
    
    public boolean valia(int teste){
        if(teste == 1){
            return true;
            
        }else{
            return false;
        }
            
    }
    
    public int atualizar(int Id){
        int status;
        conn = new conectaDAO().connectDB();
        try{
            prep = conn.prepareStatement("update produtos set status = ? where id = ?");
            // adicionar variavel ou validar se variavel está vazio ou não, para assim fazer com que ocorra a atualização
            prep.setString(1, "vendido");
            prep.setInt(2, Id); 
            status = prep.executeUpdate();
            return status;
                    
        }catch(SQLException ex){
             System.out.println( "Erro no acesso ao Bando de Dados : "+ ex.getMessage());
             return ex.getErrorCode();
        }
    }
    
     public ArrayList<ProdutosDTO> listarProdutosVendidos(){
            conn = new conectaDAO().connectDB();
            String Sql = "Select * from produtos where status = vendido" ;
        try {
                    prep = conn.prepareStatement(Sql);
                    
                    resultset = prep.executeQuery();            
                    
                    
                    while (resultset.next()) { //.next retorna verdadeiro caso exista uma próxima posição dentro do array
                        ProdutosDTO produtos = new ProdutosDTO();
                        
                        produtos.setId(resultset.getInt("id"));
                        produtos.setNome(resultset.getString("nome"));
                        produtos.setValor(resultset.getInt("valor"));
                        produtos.setStatus(resultset.getString("status"));
                        
                        listagem.add(produtos);    
                    }
                    return listagem;
                } catch (Exception e) {
                    return null;
                }
        }
        
    
    public void desconectar(){
        try {
            conn.close();
        } catch (SQLException ex) {
            // deixando vazio para evitar erros 
        }
    }
    
    
    
        
}

