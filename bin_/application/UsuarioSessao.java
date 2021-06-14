package application;

public class UsuarioSessao {

	static String usuario;
	static String nome;
	static boolean admin;

	static UsuarioSessao usuarioLogado;


	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean usuarioAdmin) {
		UsuarioSessao.admin = usuarioAdmin;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuarioLogado) {
		UsuarioSessao.usuario = usuarioLogado;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nomeLogado) {
		UsuarioSessao.nome = nomeLogado;
	}

}
