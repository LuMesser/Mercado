/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Mercado;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Clovis
 */
public class DaoMercado {
    public static boolean inserir(Mercado objeto) {
        String sql = "INSERT INTO mercado (nomefantasia, razaosocial, fundacao, nrfuncionarios, valnabolsa) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, objeto.getNomefantasia());
            ps.setString(2, objeto.getRazaosocial());
            ps.setDate(3, Date.valueOf(objeto.getFundacao()));
            ps.setInt(4, objeto.getNrfuncionarios());
            ps.setDouble(5, objeto.getValnabolsa());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public static void main(String[] args) {
        Mercado objeto = new Mercado();
        objeto.setNomefantasia("Cotribá");
        objeto.setRazaosocial("Mercado");
        objeto.setFundacao(LocalDate.parse("11/01/1988", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setNrfuncionarios(15);
        objeto.setValnabolsa(1.50);
        
        boolean resultado = inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
    public static boolean alterar(Mercado objeto) {
        String sql = "UPDATE mercado SET nomefantasia = ?, razaosocial = ?, fundacao = ?, nrfuncionarios = ?, valnabolsa = ? WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, objeto.getNomefantasia());
            ps.setString(2, objeto.getRazaosocial());
            ps.setDate(3, Date.valueOf(objeto.getFundacao()));
            ps.setInt(4, objeto.getNrfuncionarios());
            ps.setDouble(5, objeto.getValnabolsa());
            ps.setInt(6, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public static boolean excluir(Mercado objeto) {
        String sql = "DELETE FROM mercado WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public static List<Mercado> consultar() {
        List<Mercado> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, nomefantasia, razaosocial, fundacao, nrfuncionarios, valnabolsa FROM mercado";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mercado objeto = new Mercado();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setNomefantasia(rs.getString("nomefantasia"));
                objeto.setRazaosocial(rs.getString("razaosocial"));
                objeto.setFundacao(rs.getDate("fundacao").toLocalDate());
                objeto.setNrfuncionarios(rs.getInt("nrfuncionarios"));
                objeto.setValnabolsa(rs.getDouble("valnabolsa"));
                
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
}
    public static Mercado consultar(int primaryKey) {
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, nomefantasia, razaosocial, fundacao, nrfuncionarios, valnabolsa FROM mercado WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, primaryKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mercado objeto = new Mercado();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setNomefantasia(rs.getString("nomefantasia"));
                objeto.setRazaosocial(rs.getString("razaosocial"));
                objeto.setFundacao(rs.getDate("fundacao").toLocalDate());
                objeto.setNrfuncionarios(rs.getInt("nrfuncionarios"));
                objeto.setValnabolsa(rs.getDouble("valnabolsa"));
                return objeto;//não mexa nesse, ele adiciona o objeto na lista
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
