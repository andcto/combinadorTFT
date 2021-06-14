package application;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class combinadorInicioController {

	@FXML Button botaoEditarUsuario;
	@FXML private TextField textFieldUsuario;

	public void setTextFieldUsuario(){
		textFieldUsuario.setText(System.getProperty("nome"));
    }

	@FXML
	public void acaoBotaoListar(ActionEvent e) {
    	System.out.println("Botao Listar!");

    	Main.changedScreen("listarItens");
    }

	@FXML
	public void acaoBotaoDetalhe(ActionEvent e) {
    	System.out.println("Botao Listar!");

    	Main.changedScreen("MostrarDetalheItemDialog");
    }

	@FXML
	public void acaoBotaoVoltar(ActionEvent e) {
    	System.out.println("Botao Voltar!");

    	Main.changedScreen("cancelar");
    }
	@FXML
	public void acaoBotaoEditar(ActionEvent e) {
    	System.out.println("Botao Editar Usuarios!");
    	
    	boolean b = Boolean.parseBoolean(System.getProperty("admin"));
    	System.out.println(b);
    	if(b){
    		Main.changedScreen("editar");
    	}
    	else{
    		JOptionPane.showMessageDialog(null, "Usuário não permitido.");
    	}
    		
	}

}




