package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EditarUsuarioController {

    @FXML private Button botaoEditar;
    @FXML private Button botaoExcluir;
    @FXML private TableView<ModelTable> tableview;
    @FXML private TableColumn<ModelTable, String> clmNome;
    @FXML private TableColumn<ModelTable, String> clmUsuario;
    @FXML private Button botaoAtualizar;

    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
	private String login;
	private String senha;
	private String nome;
	private boolean admin;

    @FXML void acaoBotaoAtualizar(ActionEvent event) {
    	tableview.getItems().clear();
      	preencherTabela();
    }

    @FXML void acaoBotaoVoltar(ActionEvent event) {
    	Main.changedScreen("entrar");
    }

    @FXML void acaoBotaoEditar(ActionEvent event) {
    	System.out.println("Botao editar usuario");
    	alterarUsuario();

    }

    @FXML void acaoBotaoExcluir(ActionEvent event) {
    	excluirUsuario();
    }






public void preencherTabela(){
	try {
		Class.forName("org.postgresql.Driver");
		Connection conecta = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","123456");

		ResultSet rs = conecta.createStatement().executeQuery("select * from tb_usuario");

		while(rs.next()){
			oblist.add(new ModelTable(rs.getString("nome"),rs.getString("login")));
		}

	} catch (Exception e1) {
		e1.printStackTrace();
	 }

	clmNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
	clmUsuario.setCellValueFactory(new PropertyValueFactory<>("login"));

	tableview.setItems(oblist);
}
public void excluirUsuario(){

	int index;
	String teste;
	String query = "DELETE from tb_usuario WHERE login = ?";
	index = tableview.getSelectionModel().getSelectedIndex();
	teste = clmUsuario.getCellData(index);
	System.out.println(teste);

	if(index>=0){
		try {
			Class.forName("org.postgresql.Driver");
			Connection conecta1 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","123456");

			conecta1.prepareStatement(query);
			PreparedStatement pst1 = conecta1.prepareStatement(query);
		    pst1.setString(1, teste);  //Atribui o valor ao primeiro ? e assim por diante

		    pst1.executeUpdate();
		    
		} catch (Exception e1) {
			e1.printStackTrace();
			Conecta.showDialog("Usuário excluido", null, "Sucesso");
		 }

	}


}

public void alterarUsuario(){
	/* Primeiro le os dados do campo que o usuario selecionou
	 * A copia tem que vir de uma tableview para e só depois tem que transformar em um objeto do tipo Usuario
	 * Copia os dados para um objetivo do tipo Usuario
	 * Abre o dialog para editar esses dados e salvar nesse tipo usuario
	 * Depois que editar salvar os dados modificados no banco de dados
	 * */

	int index, i=0;
	Usuario usuario = new Usuario(login,senha,nome, admin);
	String teste = null;
	index = tableview.getSelectionModel().getSelectedIndex();
	teste = clmUsuario.getCellData(index);
	System.out.println(teste);

	try {
		ResultSet rs;

		String query = "SELECT login, senha, nome, admin FROM tb_usuario WHERE login = ? ";
		Class.forName("org.postgresql.Driver");
		Connection conecta = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","123456");

		conecta.prepareStatement(query);
		PreparedStatement pst = conecta.prepareStatement(query);
	    pst.setString(1, teste);  //Atribui o valor ao primeiro ? e assim por diante

	    rs = pst.executeQuery();

	    while(rs.next()){
	    usuario.setLogin(rs.getString("login"));
	    usuario.setSenha(rs.getString("senha"));
	    usuario.setNome(rs.getString("nome"));
	    boolean b = rs.getBoolean("admin");
	    usuario.setAdmin(b);
	    i++;
	    }

	    pst.close();

	} catch (Exception e1) {
		e1.printStackTrace();

	 }

	UsuarioEditDialogController.showUsuarioEditDialog(usuario);

	if(i>0){
		try {
		boolean b1 = usuario.isAdmin();
		
		String query = "UPDATE tb_usuario SET login=?, senha=?, nome=?, admin=? WHERE login = ? ";
		Class.forName("org.postgresql.Driver");
		Connection conecta = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","123456");

		conecta.prepareStatement(query);
		PreparedStatement pst1 = conecta.prepareStatement(query);

		pst1.setString(1, usuario.getLogin());  //Atribui o valor ao primeiro ? e assim por diante
		pst1.setString(2, usuario.getSenha());
		pst1.setString(3, usuario.getNome());
		pst1.setBoolean(4, b1);
		pst1.setString(5, teste);

		pst1.executeUpdate();

	    pst1.close();

	} catch (Exception e1) {
		e1.printStackTrace();
	}
	}
}
}




