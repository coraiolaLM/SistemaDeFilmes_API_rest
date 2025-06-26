//Objeto de Transferência de Dados para respostas
package ControleDeFilmes.DTO;

public class RespostaDTO {
private String mensagem;
private Object dados;

    public RespostaDTO(String mensagem, Object dados) {
		super();
		this.mensagem = mensagem;
		this.dados = dados;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Object getDados() {
		return dados;
	}
	public void setDados(Object dados) {
		this.dados = dados;
	}
    

}