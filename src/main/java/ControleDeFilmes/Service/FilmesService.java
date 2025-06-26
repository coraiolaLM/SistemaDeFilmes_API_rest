//Servicos filmes
package ControleDeFilmes.Service;

import ControleDeFilmes.Models.Filme;
import ControleDeFilmes.Models.Usuario;
import ControleDeFilmes.Repository.FilmesJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FilmesService {

    private final FilmesJpaRepository filmesRepository;
    private final UsuariosService usuariosService;

    @Autowired
    public FilmesService(FilmesJpaRepository filmesRepository, UsuariosService usuariosService) {
        this.filmesRepository = filmesRepository;
        this.usuariosService = usuariosService;
    }

    public Filme cadastrarFilmeComValidacao(String titulo, String genero, Integer anoLancamento, UserDetails userDetails) {
        Usuario usuario = usuariosService.findByEmail(userDetails.getUsername());
        if (usuario == null) {
            throw new RuntimeException("Usuário não autenticado");
        }

        if (buscarPorTitulo(titulo) != null) {
            throw new RuntimeException("Já existe um filme com este título");
        }

        Filme filme = new Filme();
        filme.setTitulo(titulo);
        filme.setGenero(genero);
        filme.setAnoLancamento(anoLancamento);

        return cadastrarFilme(filme);
    }

    public Filme cadastrarFilme(Filme filme) {
        return filmesRepository.save(filme);
    }

    public List<Filme> listarTodosFilmes() {
        return filmesRepository.findAll();
    }

    public Filme buscarPorTitulo(String titulo) {
        return filmesRepository.findByTitulo(titulo);
    }
    
    public Filme buscarPorId(Long id) {
        return filmesRepository.findById(id).orElse(null);
    }
    
    public List<Filme> buscarPorTituloContendo(String titulo) {
        return filmesRepository.findByTituloContainingIgnoreCase(titulo);
    }
}