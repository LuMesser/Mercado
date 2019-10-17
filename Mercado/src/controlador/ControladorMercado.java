/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import dao.DaoMercado;
import javax.swing.JOptionPane;
import modelo.Mercado;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import tela.manutencao.ManutencaoMercado;

/**
 *
 * @author Clovis
 */
public class ControladorMercado {

    public static void inserir(ManutencaoMercado man){
        Mercado objeto = new Mercado();
        objeto.setNomefantasia(man.jtfNomefantasia.getText());
        objeto.setRazaosocial(man.jtfRazaosocial.getText());
        objeto.setFundacao(LocalDate.parse(man.jtfFundacao.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setNrfuncionarios(Integer.parseInt(man.jtfNrfuncionarios.getText()));
        objeto.setValnabolsa(Double.parseDouble(man.jtfValnabolsa.getText()));
        
        boolean resultado = DaoMercado.inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
}

    public static void alterar(ManutencaoMercado man){
        Mercado objeto = new Mercado();
        //definir todos os atributos
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText()));
        objeto.setNomefantasia(man.jtfNomefantasia.getText());
        objeto.setRazaosocial(man.jtfRazaosocial.getText());
        objeto.setFundacao(LocalDate.parse(man.jtfFundacao.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setNrfuncionarios(Integer.parseInt(man.jtfNrfuncionarios.getText()));
        objeto.setValnabolsa(Double.parseDouble(man.jtfValnabolsa.getText()));
        
        boolean resultado = DaoMercado.alterar(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }

    public static void excluir(ManutencaoMercado man){
        Mercado objeto = new Mercado();
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoMercado.excluir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
    public static void atualizarTabela(JTable tabela) {
        DefaultTableModel modelo = new DefaultTableModel();
        //definindo o cabeçalho da tabela
        modelo.addColumn("Código");
        modelo.addColumn("Nome fantasia");
        modelo.addColumn("Razão social");
        modelo.addColumn("Fundação");
        modelo.addColumn("Número de funcionários");
        modelo.addColumn("Valor na bolsa");
        List<Mercado> resultados = DaoMercado.consultar();
        for (Mercado objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getNomefantasia());
            linha.add(objeto.getRazaosocial());
            linha.add(objeto.getFundacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            linha.add(objeto.getNrfuncionarios());
            linha.add(objeto.getValnabolsa());
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
    public static void atualizaCampos(ManutencaoMercado man, int pk){ 
        Mercado objeto = DaoMercado.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jtfCodigo.setText(objeto.getCodigo().toString());
        man.jtfNomefantasia.setText(objeto.getNomefantasia());
        man.jtfRazaosocial.setText(objeto.getRazaosocial());
        man.jtfFundacao.setText(objeto.getFundacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        man.jtfNrfuncionarios.setText(objeto.getNrfuncionarios().toString());
        man.jtfValnabolsa.setText(objeto.getValnabolsa().toString());
        
        man.jtfCodigo.setEnabled(false); //desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }
}
