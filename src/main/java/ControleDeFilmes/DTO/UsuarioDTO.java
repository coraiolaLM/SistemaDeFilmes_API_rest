//Objeto de Transferência de Dados para usuarios
package ControleDeFilmes.DTO;

public class UsuarioDTO {
 private String email;
 private String senha;
 private String nome;
 private String login;
 
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
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
public String getLogin() {
	return login;
}
public void setLogin(String login) {
	this.login = login;
}
 
}