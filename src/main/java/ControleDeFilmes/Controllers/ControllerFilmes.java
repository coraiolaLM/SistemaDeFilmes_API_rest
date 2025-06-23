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
    public ResponseEntity<Filme> cadastrarFilme(
            @RequestBody Map<String, String> request,
            @AuthenticationPrincipal UserDetails userDetails) {
        Filme filme = filmesService.cadastrarFilmeComValidacao(
            request.get("titulo"),
            request.get("genero"),
            Integer.parseInt(request.get("anoLancamento")),
            userDetails);
        return ResponseEntity.ok(filme);
    }

    @PostMapping("/adicionarParaAssistir")
    public ResponseEntity<FilmeUsuario> adicionarFilmeParaAssistir(
            @RequestBody Map<String, Long> request,
            @AuthenticationPrincipal UserDetails userDetails) {
        FilmeUsuario result = filmesUsuariosService.adicionarFilmeParaAssistir(
            request.get("filmeId"),
            userDetails);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/marcarComoAssistido")
    public ResponseEntity<String> marcarComoAssistido(
            @RequestBody Map<String, Long> request,
            @AuthenticationPrincipal UserDetails userDetails) {
        filmesUsuariosService.marcarComoAssistido(
            request.get("filmeUsuarioId"),
            userDetails);
        return ResponseEntity.ok("Filme marcado como assistido");
    }

    @PostMapping("/removerParaAssistir")
    public ResponseEntity<String> removerFilmeParaAssistir(
            @RequestBody Map<String, Long> request,
            @AuthenticationPrincipal UserDetails userDetails) {
        filmesUsuariosService.removerFilmeParaAssistir(
            request.get("filmeUsuarioId"),
            userDetails);
        return ResponseEntity.ok("Filme removido da lista");
    }
}