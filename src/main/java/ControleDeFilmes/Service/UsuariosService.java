//servicos para usuarios
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

    public Usuario cadastrarUsuario(Usuario usr) {
        System.out.println("Dados recebidos para cadastro: " + usr.toString());
        
        if (usr.getSenha() == null || usr.getSenha().length() < 4) {
            throw new RuntimeException("A senha deve ter no mínimo 4 caracteres!");
        }
        
        System.out.println("Verificando email: " + usr.getEmail());
        if (usuarioRepository.findByEmail(usr.getEmail()) != null) {
            throw new RuntimeException("Email já cadastrado!");
        }
        
        System.out.println("Verificando login: " + usr.getLogin());
        if (usr.getLogin() != null && usuarioRepository.findByLogin(usr.getLogin()) != null) {
            throw new RuntimeException("Login já existe!");
        }

        usr.setPerfil("CLIENTE");
        usr.setSenha(passwordEncoder.encode(usr.getSenha()));
        
        Usuario usuarioSalvo = usuarioRepository.save(usr);
        System.out.println("Usuário cadastrado com ID: " + usuarioSalvo.getId());
        
        return usuarioSalvo;
    }
}