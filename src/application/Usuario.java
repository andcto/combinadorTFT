package application;

public class Usuario {
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	String login, senha, nome;
	boolean admin;

	public Usuario(String login, String senha, String nome, boolean admin) {
		super();
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.admin = admin;
	}

}
