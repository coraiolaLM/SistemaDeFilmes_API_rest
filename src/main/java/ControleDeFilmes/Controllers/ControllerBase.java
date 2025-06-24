package ControleDeFilmes.Controllers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ControleDeFilmes.DTO.RespostaDTO;
import ControleDeFilmes.Models.Usuario;
import ControleDeFilmes.Service.BaseService;

@RestController
@CrossOrigin(origins = "*")
public class ControllerBase {

    private final BaseService homeService;

    @Autowired
    public ControllerBase(BaseService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("API de Controle de Filmes");
    }
    @GetMapping("/home")
    public ResponseEntity<RespostaDTO> home(@AuthenticationPrincipal UserDetails userDetails,
                                          @RequestParam(required = false) String msg,
                                          @RequestParam(required = false) String search) {
        Usuario usuario = homeService.getUsuario(userDetails);
        
        Map<String, Object> dados = new HashMap<>();
        if (search != null && !search.isEmpty()) {
            dados.put("filmes", homeService.buscarPorTituloContendo(search));
        } else {
            dados.put("filmes", homeService.listarTodosFilmes());
        }

        dados.put("filmesParaAssistir", homeService.listarFilmesPorUsuarioEStatus(usuario, false));
        dados.put("filmesAssistidos", homeService.listarFilmesPorUsuarioEStatus(usuario, true));

        String mensagem = msg != null ? msg : "Dados carregados com sucesso";
        
        return ResponseEntity.ok(new RespostaDTO(mensagem, dados));
    }
}