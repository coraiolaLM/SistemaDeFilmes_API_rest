package ControleDeFilmes.Service;

import ControleDeFilmes.Models.Filme;
import ControleDeFilmes.Models.FilmeUsuario;
import ControleDeFilmes.Models.Usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

    private final UsuariosService usuariosService;
    private final FilmesService filmesService;
    private final FilmesUsuariosService filmesUsuariosService;

    @Autowired
    public BaseService(UsuariosService usuariosService,
                       FilmesService filmesService,
                       FilmesUsuariosService filmesUsuariosService) {
        this.usuariosService = usuariosService;
        this.filmesService = filmesService;
        this.filmesUsuariosService = filmesUsuariosService;
    }
    
    public Usuario getUsuario(UserDetails userDetails) {
        return usuariosService.findByEmail(userDetails.getUsername());
    }

    public List<Filme> buscarPorTituloContendo(String search) {
        return filmesService.buscarPorTituloContendo(search);
    }

    public List<Filme> listarTodosFilmes() {
        return filmesService.listarTodosFilmes();
    }

    public List<FilmeUsuario> listarFilmesPorUsuarioEStatus(Usuario usuario, boolean assistido) {
        return filmesUsuariosService.listarFilmesPorUsuarioEStatus(usuario, assistido);
    }
}