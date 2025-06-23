package ControleDeFilmes.Controllers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Use Basic Auth para login");
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Map<String, String>> cadastrarUsuario(@RequestBody Usuario usr) {
        // A lógica de validação agora está no serviço e lança uma RuntimeException em caso de erro.
        // A classe TratamentoGlobalDeExecoes irá capturar a exceção e formatar a resposta de erro.
        usuarioService.cadastrarUsuario(usr);
        
        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "Usuário cadastrado com sucesso! Faça o login.");
        return ResponseEntity.ok(response);
    }
}