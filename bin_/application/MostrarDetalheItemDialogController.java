package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MostrarDetalheItemDialogController {


    private Stage dialogStage;
    ItemDetalhe detalhe;

    @FXML private TextField textFieldNome;
    @FXML private TextField textFieldTipo;
    @FXML private TextArea textFieldDescricao;
    @FXML private ImageView imageViewItem;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setItemDetalhe(ItemDetalhe detalhe){
    	Image i = carregaImagem(detalhe.getId_imagem());

    	this.detalhe = detalhe;


    	textFieldNome.setText(detalhe.getNome());
    	textFieldTipo.setText(detalhe.getTipo());
    	textFieldDescricao.setText(detalhe.getDescricao());
    	imageViewItem.setImage(i);
    }
    /**
     * Chamado quando o usuário clica Cancel.
     */
    @FXML
    private void handleVoltar() {
        dialogStage.close();
    }

public static void showDetalheItemDialog(ItemDetalhe detalhe){
    try {
        // Carrega o arquivo fxml e cria um novo stage para a janela popup.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("MostrarDetalheItemDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Cria o palco dialogStage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Detalhe Item");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Window primaryStage = null;
		dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        MostrarDetalheItemDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setItemDetalhe(detalhe);

        // Mostra a janela e espera até o usuário fechar.
        dialogStage.showAndWait();

    } catch (IOException e) {
        e.printStackTrace();
    }
}

public Image carregaImagem(int id_imagem){

	String diretorio = "..src/application/imagens/itens-icones/";

	java.net.URL resource = getClass().getResource(diretorio + detalhe.id_imagem + ".png");
	File file = new File(resource.toURI());

	BufferedImage imagem = ImageIO.read(file);
	imagem = (BufferedImage) imagem;
	return imagem;
}

}
