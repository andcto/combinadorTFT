package application;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class CombinarController {
	@FXML
	private ComboBox<String> boxItens1;
	@FXML
	private ComboBox<String> boxItens2;


}
/*
public static void carregaComboBox(ComboBox<String> comboBox){  //Função para carregar a ComboBox

	List<String> strList = new ArrayList<String>();

	ResultSet rs = null;

	String query = "SELECT tb_itens FROM nome";

	try
	{
		Class.forName("org.postgresql.Driver");
		Connection conecta1 = DriverManager.getConnection("jdbc:postgresql://localhost:5433/tft_db","postgres","123456");
		conecta1.prepareStatement(query);
		PreparedStatement pst1 = conecta1.prepareStatement(query);

		rs = pst1.executeQuery();

		while(rs.next()){
		       strList.add(rs.getString("nome"));
		   }

		for(int i=1;i<10;i++)
		{
			comboBox.setId(strList.toString());
			//comboBox.setItems(FXCollections.observableArrayList(rs.getString("nome")));

		}

		rs.close();
		conecta1.close();
	}
	catch(Exception e)
	{
		JOptionPane.showMessageDialog(null,
				"Ocorreu erro ao carregar a Combo Box", "Erro",
				JOptionPane.ERROR_MESSAGE);
	}

}
*/






