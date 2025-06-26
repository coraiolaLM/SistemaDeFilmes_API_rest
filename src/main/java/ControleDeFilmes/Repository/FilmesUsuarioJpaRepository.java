//Repositorio de filmes de usuarios
package ControleDeFilmes.Repository;

import ControleDeFilmes.Models.FilmeUsuario;
import ControleDeFilmes.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmesUsuarioJpaRepository extends JpaRepository<FilmeUsuario, Long> {
    List<FilmeUsuario> findByUsuario(Usuario usuario);
    List<FilmeUsuario> findByUsuarioAndAssistido(Usuario usuario, boolean assistido);
    
    boolean existsByUsuarioAndFilme_Titulo(Usuario usuario, String titulo);
}