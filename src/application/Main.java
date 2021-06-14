package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private static Stage stage;

	private static Scene mainScene;
	private static Scene cadastroScene;
	private static Scene inicioScene;
	private static Scene editarUsuarioScene;
	private static Scene listarItensScene;
	private static Scene combinarItensScene;

    @Override
    public void start(Stage primaryStage) throws Exception {

    	stage = primaryStage;

    	primaryStage.setTitle("Combinador de Itens - TFT");

    	Parent fxmlMain = FXMLLoader.load(getClass().getResource("main.fxml"));
    	mainScene = new Scene(fxmlMain, 600, 400);

    	Parent fxmlCadastro = FXMLLoader.load(getClass().getResource("cadastro.fxml"));
    	cadastroScene = new Scene(fxmlCadastro, 600, 400);

    	Parent fxmlInicio = FXMLLoader.load(getClass().getResource("combinadorInicio.fxml"));
    	inicioScene = new Scene(fxmlInicio, 600, 400);

    	Parent fxmlEditarUsuario = FXMLLoader.load(getClass().getResource("EditarUsuario.fxml"));
    	editarUsuarioScene = new Scene(fxmlEditarUsuario, 600, 400);

    	Parent fxmlListarItens = FXMLLoader.load(getClass().getResource("listarItens.fxml"));
    	listarItensScene = new Scene(fxmlListarItens, 600, 400);
    	
    	Parent fxmlCombinarItens = FXMLLoader.load(getClass().getResource("combinar.fxml"));
    	combinarItensScene = new Scene(fxmlCombinarItens, 600, 400);

    	primaryStage.setScene(mainScene);
    	primaryStage.show();

    }

    public static void changedScreen(String screen){
    	switch (screen){
    		case "main":
    			stage.setScene(mainScene);
    			break;
    		case "cadastro":
    			stage.setScene(cadastroScene);
    			break;
    		case "cancelar":
    			stage.setScene(mainScene);
    			break;
    		case "entrar":
    			stage.setScene(inicioScene);
    			break;
    		case "editar":
    			stage.setScene(editarUsuarioScene);
    			break;
    		case "listarItens":
    			stage.setScene(listarItensScene);
    			break;
    		case "combinar":
    			stage.setScene(combinarItensScene);
    			break;

    	}
    }

    public static void main(String[] args) {
        launch(args);
    }


}
