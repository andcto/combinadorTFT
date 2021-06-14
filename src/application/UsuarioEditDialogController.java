package application;

import application.Usuario;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class UsuarioEditDialogController {

    @FXML private TextField loginField;
    @FXML private TextField nomeField;
    @FXML private PasswordField senhaField;
    @FXML private CheckBox adminCheckBox;
    @FXML private Button confirmaBotao;
    @FXML private Button cancelaBotao;

    private Stage dialogStage;
    private Usuario usuario;
    private static boolean okClicked = false;

    /**
     * Inicializa a classe controlle. Este método é chamado automaticamente
     * após o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Define a pessoa a ser editada no dialog.
     *
     * @param usuario
     */
    public void setusuario(Usuario usuario) {
    	boolean b1 = usuario.isAdmin();
        this.usuario = usuario;

        loginField.setText(usuario.getLogin());
        nomeField.setText(usuario.getNome());
        senhaField.setText(usuario.getSenha());
        adminCheckBox.setSelected(b1);
    }

    /**
     * Retorna true se o usuário clicar OK,caso contrário false.
     *
     * @return
     */
    public static boolean isOkClicked() {
    	
        return okClicked;
    }

    /**
     * Chamado quando o usuário clica OK.
     */
    @FXML
    private void handleOk() {
    		boolean b2 = adminCheckBox.isSelected();
            usuario.setLogin(loginField.getText());
            usuario.setNome(nomeField.getText());
            usuario.setSenha(senhaField.getText());
            usuario.setAdmin(b2);

            okClicked = true;
            Conecta.showDialog("Usuário alterado", null, "Sucesso");
            dialogStage.close();
    }

    /**
     * Chamado quando o usuário clica Cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

public static boolean showUsuarioEditDialog(Usuario usuario) {
    try {
        // Carrega o arquivo fxml e cria um novo stage para a janela popup.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("UsuarioEditDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Cria o palco dialogStage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Editar Usuario");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Window primaryStage = null;
		dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Define a pessoa no controller.
        UsuarioEditDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setusuario(usuario);

        // Mostra a janela e espera até o usuário fechar.
        dialogStage.showAndWait();

        return UsuarioEditDialogController.isOkClicked();
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}
}


