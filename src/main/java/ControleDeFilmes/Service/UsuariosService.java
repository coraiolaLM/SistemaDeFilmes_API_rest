package ControleDeFilmes.Service;

import ControleDeFilmes.Models.Usuario;
import ControleDeFilmes.Repository.UsuarioJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuariosService implements UserDetailsService {

    @Autowired
    private UsuarioJpaRepository usuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com email: " + email);
        }
        
        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .roles(usuario.getPerfil().toUpperCase())
                .build();
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    /**
     * Cadastra um novo usuário após validar as informações.
     * Lança RuntimeException se a validação falhar.
     * @param usr O objeto Usuario a ser cadastrado.
     * @return O Usuario salvo.
     */
    public Usuario cadastrarUsuario(Usuario usr) {
        if (usr.getSenha() == null || usr.getSenha().length() < 4) {
            throw new RuntimeException("A senha deve ter no mínimo 4 caracteres!");
        }
        if (usuarioRepository.findByEmail(usr.getEmail()) != null) {
            throw new RuntimeException("Email já cadastrado!");
        }
        if (usr.getLogin() != null && usuarioRepository.findByLogin(usr.getLogin()) != null) {
            throw new RuntimeException("Login já existe!");
        }

        usr.setPerfil("CLIENTE");
        usr.setSenha(passwordEncoder.encode(usr.getSenha()));
        
        return usuarioRepository.save(usr);
    }
}