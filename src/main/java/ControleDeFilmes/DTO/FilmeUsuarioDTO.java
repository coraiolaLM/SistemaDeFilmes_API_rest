package ControleDeFilmes.DTO;

import java.time.LocalDate;

public class FilmeUsuarioDTO {
	 	private Long filmeUsuarioId;
	 	private Long filmeId;
	    private boolean assistido;
	    private LocalDate dataAssistido;
	    
	    public Long getFilmeUsuarioId() {
	        return filmeUsuarioId;
	    }

	    public void setFilmeUsuarioId(Long filmeUsuarioId) {
	        this.filmeUsuarioId = filmeUsuarioId;
	    }
	    public Long getFilmeId() {
	        return filmeId;
	    }
	    public void setFilmeId(Long filmeId) {
	        this.filmeId = filmeId;
	    }
	    public boolean isAssistido() {
	        return assistido;
	    }
	    public void setAssistido(boolean assistido) {
	        this.assistido = assistido;
	    }
    
	    public LocalDate getDataAssistido() {
        return dataAssistido;
    }
	    public void setDataAssistido(LocalDate dataAssistido) {
        this.dataAssistido = dataAssistido;
    }
}