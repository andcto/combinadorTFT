package application;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListarItensController {

	@FXML private TableView<ModelTableItens> tabelaItens;
	@FXML private TableColumn<ModelTableItens, String> clmNome;
	@FXML private TableColumn<ModelTableItens, String> clmTipo;
	@FXML private TableColumn<ModelTableItens, String> clmDescricao;
	@FXML private TableColumn<ModelTableItens, Integer> clmIdImagem;

	private String nome;
	private String tipo;
	private String descricao;
	private int id_imagem;

	ObservableList<ModelTableItens> oblist = FXCollections.observableArrayList();

	@FXML void acaoBotaoAtualizar(ActionEvent event) {
		tabelaItens.getItems().clear();
		preencherTabela();
	}
	@FXML public void acaoBotaoPDF(ActionEvent e) throws Exception {
    	System.out.println("Botao PDF!");
    	gerarPDF();

    }
	@FXML public void acaoBotaoDetalhe(ActionEvent e) {
    	System.out.println("Botao Detalhe!");

    	detalheItem();
    }
	@FXML public void acaoBotaoVoltar(ActionEvent e) {
    	System.out.println("Botao Voltar!");
    	Main.changedScreen("entrar");
    }

public void preencherTabela(){
	try {
		Class.forName("org.postgresql.Driver");
		Connection conecta = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","123456");

		ResultSet rs = conecta.createStatement().executeQuery("SELECT * FROM viewlistaritens");

		while(rs.next()){
			oblist.add(new ModelTableItens(rs.getString("nome"),rs.getString("tipo"), rs.getString("descricao"), rs.getInt("id_imagem")));
		}

	} catch (Exception e1) {
		e1.printStackTrace();
	 }

	clmNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
	clmTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
	clmDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
	clmIdImagem.setCellValueFactory(new PropertyValueFactory<>("id_imagem"));

	tabelaItens.setItems(oblist);
}


public void detalheItem(){

	ItemDetalhe detalhe = new ItemDetalhe(nome,tipo,descricao,id_imagem);

	int index;
	index = tabelaItens.getSelectionModel().getSelectedIndex();
	detalhe.setNome(clmNome.getCellData(index));
	detalhe.setTipo(clmTipo.getCellData(index));
	detalhe.setDescricao(clmDescricao.getCellData(index));
	detalhe.setId_imagem(clmIdImagem.getCellData(index));

	MostrarDetalheItemDialogController.showDetalheItemDialog(detalhe);
}


@FXML
private void gerarPDF() throws Exception{
	try {

		String arquivoPDF = "lista_itens.pdf";
		Class.forName("org.postgresql.Driver");
		Connection conecta = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","123456");
		Statement stmt = conecta.createStatement();
		/* Define the SQL query */
		ResultSet query_set = stmt.executeQuery("SELECT * FROM viewlistaritens");
		/* Step-2: Initialize PDF documents - logical objects */
		Document my_pdf_report = new Document();
		PdfWriter.getInstance(my_pdf_report, new FileOutputStream(arquivoPDF));
		my_pdf_report.open();
		//we have four columns in our table
		PdfPTable my_report_table = new PdfPTable(3);
		//create a cell object
		PdfPCell table_cell;

		Paragraph p = new Paragraph("Lista de Itens");
		PdfPCell celula1 = new PdfPCell(); //adiciona o paragrafo com o titulo na segunda celula.
		PdfPCell celula2 = new PdfPCell(p); //adiciona o paragrafo com o titulo na segunda celula.
		PdfPCell celula3 = new PdfPCell(); //adiciona o paragrafo com o titulo na segunda celula.
		celula1.setBorder(-1);
		celula2.setBorder(-1);
		celula3.setBorder(-1);

		my_report_table.addCell(celula1);
		my_report_table.addCell(celula2);
		my_report_table.addCell(celula3);

		my_report_table.addCell("Nome do Item");
		my_report_table.addCell("Tipo");
		my_report_table.addCell("Descricao");


		while (query_set.next()) {
			String nome = query_set.getString("nome");
			table_cell=new PdfPCell(new Phrase(nome));
			my_report_table.addCell(table_cell);
			String tipo=query_set.getString("tipo");
			table_cell=new PdfPCell(new Phrase(tipo));
			my_report_table.addCell(table_cell);
			String descricao=query_set.getString("descricao");
			table_cell=new PdfPCell(new Phrase(descricao));
			my_report_table.addCell(table_cell);
		}
		/* Attach report table to PDF */
		my_pdf_report.add(my_report_table);
		my_pdf_report.close();

		/* Close all DB related objects */
		query_set.close();
		stmt.close();
		conecta.close();

		Desktop.getDesktop().open(new File(arquivoPDF));

		Conecta.showDialog("PDF criado com sucesso!", null, "Sucesso");

	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
}
}
}
