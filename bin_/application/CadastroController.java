package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CadastroController {

	String login = new String();
	String senha = new String();
	String nome = new String();

	@FXML TextField dataFieldLogin;
	@FXML TextField dataFieldSenha;
	@FXML TextField dataFieldNome;


	@FXML
	public void acaoBotaoCancelar(ActionEvent e) {
    	System.out.println("Botao Cancelar!");

    	Main.changedScreen("cancelar");
    }

	@FXML
	public void acaoBotaoConcluir(ActionEvent e) {
    	System.out.println("Botao Concluir!");

    	login = dataFieldLogin.getText();
    	senha = dataFieldSenha.getText();
    	nome = dataFieldNome.getText();

    	System.out.println(login);
    	System.out.println(senha);
    	System.out.println(nome);

    	Conecta.adicionaUsuario(this.login,this.senha,this.nome);

}


}









