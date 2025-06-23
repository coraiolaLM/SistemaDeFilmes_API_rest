package ControleDeFilmes.Repository;

import ControleDeFilmes.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
    Usuario findByLogin(String login);
    Usuario findByNome(String nome);
}