package application;

public class ItemDetalhe {
	String nome;
	String tipo;
	String descricao;
	int id_imagem;

	public ItemDetalhe(String nome, String tipo, String descricao, int id_imagem) {
		super();
		this.nome = nome;
		this.tipo = tipo;
		this.descricao = descricao;
		this.id_imagem = id_imagem;
	}
	public int getId_imagem() {
		return id_imagem;
	}
	public void setId_imagem(int id_imagem) {
		this.id_imagem = id_imagem;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
