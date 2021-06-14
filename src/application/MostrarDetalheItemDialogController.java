package application;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
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

    public void setItemDetalhe(ItemDetalhe detalhe) throws IOException{
    	this.detalhe = detalhe;
    	WritableImage wr = carregaImagem(detalhe.id_imagem);

    	imageViewItem.setImage(wr);
    	textFieldNome.setText(detalhe.getNome());
    	textFieldTipo.setText(detalhe.getTipo());
    	textFieldDescricao.setText(detalhe.getDescricao());

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

}
