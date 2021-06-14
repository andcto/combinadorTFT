package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.scene.control.Alert;

public class Conecta {

	static final String url = "jdbc:postgresql://localhost:5432/postgres";
	static final String user = "postgres";
	static final String pass = "123456";

	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");

			Connection conecta = DriverManager.getConnection(url, user, pass);
			if (conecta !=null) {
				System.out.println("Conexão efetuada com sucesso!");
				return conecta;
			}
		}catch(ClassNotFoundException ex){
			JOptionPane.showMessageDialog(null, "O driver não foi encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
			return null;
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas com a conexão\n"+ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return null;
	}

	public static void adicionaUsuario(String login, String senha, String nome){  //Função para cadastrar o usuário
		try {

			Class.forName("org.postgresql.Driver");
			Connection Conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","123456");

			String query = "INSERT INTO tb_usuario (login , senha , nome) VALUES (? , ? , ?)";
			PreparedStatement ps = Conexao.prepareStatement(query);
			ps.setString(1, login);  //Atribui o valor ao primeiro ? e assim por diante
			ps.setString(2, senha);
			ps.setString(3, nome);

			ps.executeUpdate();

			String mensagem = "Usuário adicionado " + nome;
			JOptionPane.showMessageDialog(null, mensagem);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	public static boolean logaUsuario(String login, String senha){
	ResultSet rs = null;

	String query = "SELECT login, senha, nome, admin FROM tb_usuario WHERE login = ? and senha = ?";

	try {
		Class.forName("org.postgresql.Driver");
		Connection conecta1 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","123456");
		conecta1.prepareStatement(query);
		PreparedStatement pst1 = conecta1.prepareStatement(query);
	    pst1.setString(1, login);  //Atribui o valor ao primeiro ? e assim por diante
	    pst1.setString(2, senha);

	    rs = pst1.executeQuery();

	    if(!rs.next()){
	    	JOptionPane.showMessageDialog(null, "Login/Senha Inválidos!");
	    	return false;
	    }
	    else{
	    	boolean teste = rs.getBoolean("admin");	 //recupera um valor booleano para saber se e admin ou nao
	    	String admin =	Boolean.toString(teste); //converte boolean para uma string

	    	System.setProperty("admin", admin);

	    	String nome1 = rs.getString("nome");
	    	System.setProperty("nome", nome1);

	    	String login1 = rs.getString("login");
	    	System.setProperty("login", login1);

	    	showDialog("Login efetuado!", null, "Sucesso");
	    	return true;
	    }

	} catch (Exception e1) {
		e1.printStackTrace();
	 }
	return false;

 }

static void showDialog(String info, String header, String title){
	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	alert.setContentText(info);
	alert.setHeaderText(header);
	alert.showAndWait();
}
}





