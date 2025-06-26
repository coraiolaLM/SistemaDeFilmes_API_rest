//Centralizar o tratamento de erros e exceções em um único lugar
package ControleDeFilmes.DTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TratamentoGlobalDeExecoes {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<RespostaDTO> handleRuntimeException(RuntimeException ex) {
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	            .body(new RespostaDTO(ex.getMessage(), null));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<RespostaDTO> handleException(Exception ex) {
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body(new RespostaDTO("Erro interno no servidor", null));
	}
}