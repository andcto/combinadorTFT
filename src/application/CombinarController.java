package application;

import javafx.scene.control.TextArea;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.imageio.ImageIO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class CombinarController {

	private String nome;
	private String tipo;
	private String descricao;
	private int id_imagem;

	ObservableList<String> itemChoices = FXCollections.observableArrayList(
			"Cota de Malha",
			"Lágrima da Deusa",
			"Espada G.p.C",
			"Bastão Desnecessariamente Grande",
			"Arco Recurvo",
			"Capa Negatron",
			"Cinto do Gigante",
			"Espátula"
	    );

	@FXML private TextArea descricaoItemTextArea;
    @FXML private TextField nomeItemTextField;
    @FXML private TextField tipoItemTextField;
    @FXML private ImageView imageViewItem;
	@FXML private ChoiceBox<String> choiceBox1 = new ChoiceBox<String>(itemChoices);
	@FXML private ChoiceBox<String> choiceBox2 = new ChoiceBox<String>(itemChoices);

	@FXML private void initialize(){
		choiceBox1.setItems(itemChoices);
		choiceBox2.setItems(itemChoices);
	}

	@FXML
	public void acaoBotaoCombinar(ActionEvent e) throws FileNotFoundException, IOException {
		 System.out.println("combinar");
		 String a, b;
		 a = choiceBox1.getValue();
		 b = choiceBox2.getValue();
		 System.out.println(a);
		 System.out.println(b);
		 combinar(a, b);
    }

	private void combinar(String a, String b) throws FileNotFoundException, IOException {

		int idItemA=0, idItemB=0, idCombinado=0;

		String query = ("SELECT * FROM tb_item WHERE nome = ?");
		String queryCombinado = ("SELECT * FROM viewcombinaritens WHERE id_item = ?");

		try {
			Class.forName("org.postgresql.Driver");
			Connection conecta = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","123456");

			conecta.prepareStatement(query);
			PreparedStatement pst = conecta.prepareStatement(query);
		    pst.setString(1, a);  //Atribui o valor ao primeiro ? e assim por diante

		    ResultSet rs = pst.executeQuery();

			while(rs.next()){
				idItemA = rs.getInt("id_item");
			}
				System.out.println(idItemA);

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			Class.forName("org.postgresql.Driver");
			Connection conecta = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","123456");

			conecta.prepareStatement(query);
			PreparedStatement pst = conecta.prepareStatement(query);
		    pst.setString(1, b);  //Atribui o valor ao primeiro ? e assim por diante

		    ResultSet rs = pst.executeQuery();

			while(rs.next()){
				idItemB = rs.getInt("id_item");
			}
				System.out.println(idItemB);

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		idCombinado = Integer.valueOf(String.valueOf(idItemA) + String.valueOf(idItemB));
		System.out.println(idCombinado);

		//busca do item combinado
		try {
			Class.forName("org.postgresql.Driver");
			Connection conecta = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","123456");

			conecta.prepareStatement(queryCombinado);
			PreparedStatement pst = conecta.prepareStatement(queryCombinado);
		    pst.setFloat(1, idCombinado);  //Atribui o valor ao primeiro ? e assim por diante

		    ResultSet rs = pst.executeQuery();

			while(rs.next()){
				nome = rs.getString("nome");
				tipo = rs.getString("tipo");
				descricao = rs.getString("descricao");
				id_imagem = rs.getInt("id_imagem");
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		WritableImage wr = carregaImagem(id_imagem);

    	imageViewItem.setImage(wr);
		descricaoItemTextArea.setText(descricao);
		nomeItemTextField.setText(nome);
		tipoItemTextField.setText(tipo);



	}

	public WritableImage carregaImagem(int num) throws FileNotFoundException, IOException{
		BufferedImage imagem;
		String diretorio = "C:\\Users\\Andrey\\projeto-bd\\tft-combinador\\src\\application\\imagens\\itens-icones\\"+num+".png";
		System.out.print(diretorio);

		imagem = ImageIO.read(new FileInputStream(diretorio));

		WritableImage wr = null;
	    if (imagem != null) {
	        wr = new WritableImage(imagem.getWidth(), imagem.getHeight());
	        PixelWriter pw = wr.getPixelWriter();
	        for (int x = 0; x < imagem.getWidth(); x++) {
	            for (int y = 0; y < imagem.getHeight(); y++) {
	                pw.setArgb(x, y, imagem.getRGB(x, y));
	            }
	        }
	    }
	    return wr;
	}

	@FXML
	public void acaoBotaoLimpar(ActionEvent e) {
		descricaoItemTextArea.clear();
		nomeItemTextField.clear();
		tipoItemTextField.clear();
		imageViewItem.setImage(null);


		System.out.println("limpar");

    }

	@FXML
	public void acaoBotaoVoltar(ActionEvent e) {
		descricaoItemTextArea.clear();
		nomeItemTextField.clear();
		tipoItemTextField.clear();
		imageViewItem.setImage(null);
    	Main.changedScreen("entrar");
    }
}





