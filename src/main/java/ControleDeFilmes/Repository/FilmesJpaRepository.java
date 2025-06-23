package ControleDeFilmes.Repository;

import ControleDeFilmes.Models.Filme;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmesJpaRepository extends JpaRepository<Filme, Long> {
    Filme findByTitulo(String titulo);
    List<Filme> findByTituloContainingIgnoreCase(String titulo);
}