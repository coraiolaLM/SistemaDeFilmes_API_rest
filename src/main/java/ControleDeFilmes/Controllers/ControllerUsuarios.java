package ControleDeFilmes.Controllers;

import java.util.HashMap;
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
import ControleDeFilmes.Models.Usuario;
import ControleDeFilmes.Service.UsuariosService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class ControllerUsuarios {

    private final UsuariosService usuarioService;

    @Autowired
    public ControllerUsuarios(UsuariosService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioService.findByEmail(userDetails.getUsername());
        
        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "Login bem-sucedido");
        response.put("email", usuario.getEmail());
        response.put("nome", usuario.getNome());
        response.put("perfil", usuario.getPerfil());
        
        return ResponseEntity.ok(response);
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<Map<String, String>> cadastrarUsuario(@RequestBody Usuario usr) {
        usuarioService.cadastrarUsuario(usr);
        
        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "Usuário cadastrado com sucesso! Faça o login.");
        return ResponseEntity.ok(response);
    }
}