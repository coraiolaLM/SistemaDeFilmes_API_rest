package ControleDeFilmes.Service;

import ControleDeFilmes.Models.Filme;
import ControleDeFilmes.Models.FilmeUsuario;
import ControleDeFilmes.Models.Usuario;
import ControleDeFilmes.Repository.FilmesUsuarioJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class FilmesUsuariosService {

    private final FilmesUsuarioJpaRepository filmesUsuarioRepository;
    private final FilmesService filmesService;
    private final UsuariosService usuariosService;

    @Autowired
    public FilmesUsuariosService(FilmesUsuarioJpaRepository filmesUsuarioRepository,
                               FilmesService filmesService,
                               UsuariosService usuariosService) {
        this.filmesUsuarioRepository = filmesUsuarioRepository;
        this.filmesService = filmesService;
        this.usuariosService = usuariosService;
    }

    public FilmeUsuario adicionarFilmeParaAssistir(Long filmeId, UserDetails userDetails) {
        Usuario usuario = usuariosService.findByEmail(userDetails.getUsername());
        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        Filme filme = filmesService.buscarPorId(filmeId);
        if (filme == null) {
            throw new RuntimeException("Filme não encontrado");
        }

        if (filmeJaAdicionado(usuario, filme.getTitulo())) {
            throw new RuntimeException("Você já adicionou este filme à sua lista");
        }

        return adicionarFilmeParaUsuario(usuario, filme);
    }
    
    public List<FilmeUsuario> listarFilmesPorUsuarioEStatus(Usuario usuario, boolean assistido) {
        return filmesUsuarioRepository.findByUsuarioAndAssistido(usuario, assistido);
    }
    
    public void marcarComoAssistido(Long filmeUsuarioId, UserDetails userDetails) {
        Usuario usuario = usuariosService.findByEmail(userDetails.getUsername());
        if (usuario == null) {
            throw new RuntimeException("Usuário não autenticado");
        }
        marcarComoAssistido(filmeUsuarioId);
    }

    public void removerFilmeParaAssistir(Long filmeUsuarioId, UserDetails userDetails) {
        Usuario usuario = usuariosService.findByEmail(userDetails.getUsername());
        if (usuario == null) {
            throw new RuntimeException("Usuário não autenticado");
        }
        removerFilmeParaAssistir(filmeUsuarioId);
    }

    public FilmeUsuario adicionarFilmeParaUsuario(Usuario usuario, Filme filme) {
        FilmeUsuario filmeUsuario = new FilmeUsuario();
        filmeUsuario.setUsuario(usuario);
        filmeUsuario.setFilme(filme);
        filmeUsuario.setAssistido(false);
        return filmesUsuarioRepository.save(filmeUsuario);
    }

    public void marcarComoAssistido(Long filmeUsuarioId) {
        filmesUsuarioRepository.findById(filmeUsuarioId).ifPresent(filmeUsuario -> {
            filmeUsuario.setAssistido(true);
            filmeUsuario.setDataAssistido(LocalDate.now());
            filmesUsuarioRepository.save(filmeUsuario);
        });
    }

    public boolean filmeJaAdicionado(Usuario usuario, String titulo) {
        return filmesUsuarioRepository.existsByUsuarioAndFilme_Titulo(usuario, titulo);
    }

    public void removerFilmeParaAssistir(Long filmeUsuarioId) {
        filmesUsuarioRepository.findById(filmeUsuarioId).ifPresent(filmeUsuario -> {
            if (!filmeUsuario.isAssistido()) {
                filmesUsuarioRepository.delete(filmeUsuario);
            }
        });
    }
}