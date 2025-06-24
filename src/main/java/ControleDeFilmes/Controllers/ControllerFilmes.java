package ControleDeFilmes.Controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ControleDeFilmes.DTO.FilmeDTO;
import ControleDeFilmes.DTO.FilmeUsuarioDTO;
import ControleDeFilmes.DTO.RespostaDTO;
import ControleDeFilmes.Models.Filme;
import ControleDeFilmes.Models.FilmeUsuario;
import ControleDeFilmes.Service.FilmesService;
import ControleDeFilmes.Service.FilmesUsuariosService;

@RestController
@RequestMapping("/api/filmes")
@CrossOrigin(origins = "*")
public class ControllerFilmes {

    private final FilmesService filmesService;
    private final FilmesUsuariosService filmesUsuariosService;

    @Autowired
    public ControllerFilmes(FilmesService filmesService,
                          FilmesUsuariosService filmesUsuariosService) {
        this.filmesService = filmesService;
        this.filmesUsuariosService = filmesUsuariosService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<RespostaDTO> cadastrarFilme(
            @RequestBody FilmeDTO filmeDTO,
            @AuthenticationPrincipal UserDetails userDetails) {
        Filme filme = filmesService.cadastrarFilmeComValidacao(
            filmeDTO.getTitulo(),
            filmeDTO.getGenero(),
            filmeDTO.getAnoLancamento(),
            userDetails);
        
        return ResponseEntity.ok(new RespostaDTO("Filme cadastrado com sucesso", filme));
    }

    @PostMapping("/adicionarParaAssistir")
    public ResponseEntity<RespostaDTO> adicionarFilmeParaAssistir(
            @RequestBody FilmeUsuarioDTO filmeUsuarioDTO,
            @AuthenticationPrincipal UserDetails userDetails) {
        FilmeUsuario result = filmesUsuariosService.adicionarFilmeParaAssistir(
            filmeUsuarioDTO.getFilmeId(),
            userDetails);
        
        return ResponseEntity.ok(new RespostaDTO("Filme adicionado à lista", result));
    }

    @PostMapping("/marcarComoAssistido")
    public ResponseEntity<RespostaDTO> marcarComoAssistido(
            @RequestBody Map<String, Long> request,
            @AuthenticationPrincipal UserDetails userDetails) {
        filmesUsuariosService.marcarComoAssistido(
            request.get("filmeUsuarioId"),
            userDetails);
        return ResponseEntity.ok(new RespostaDTO("Filme marcado como assistido", null));
    }

    @PostMapping("/removerParaAssistir")
    public ResponseEntity<RespostaDTO> removerFilmeParaAssistir(
            @RequestBody Map<String, Long> request,
            @AuthenticationPrincipal UserDetails userDetails) {
        filmesUsuariosService.removerFilmeParaAssistir(
            request.get("filmeUsuarioId"),
            userDetails);
        return ResponseEntity.ok(new RespostaDTO("Filme removido da lista", null));
    }
}