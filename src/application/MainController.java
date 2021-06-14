package application;

import application.Conecta;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class MainController{

	String login = new String();
	String senha = new String();

	@FXML TextField dataFieldLogin;
	@FXML TextField dataFieldSenha;

    private int count = 0 ;

    @FXML
    private Label countLabel ;

    @FXML
    private void increment() {
        count++;
        countLabel.setText("Count: "+count);
    }

    @FXML public void acaoBotaoCadastrar(ActionEvent e) {
    	System.out.println("Botao Cadastrar!");

    	Main.changedScreen("cadastro");
    }

    @FXML public void acaoBotaoListar(ActionEvent e) {
    	System.out.println("Botao Listar!");

    	Main.changedScreen("listarItens");
    }

    @FXML public void acaoBotaoEntrar(ActionEvent e) {
    	System.out.println("Botao Entrar!");

    	login = dataFieldLogin.getText();
    	senha = dataFieldSenha.getText();

    	System.out.println(login);
    	System.out.println(senha);

    	if(Conecta.logaUsuario(this.login,this.senha)){
    		Main.changedScreen("entrar");
    	}
    }
}