package com.borajunto.projetobora.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.borajunto.projetobora.dto.LoginDTO;
import com.borajunto.projetobora.dto.TokenDTO;
import com.borajunto.projetobora.entidade.Usuario;
import com.borajunto.projetobora.repository.UsuarioRepository;
import com.borajunto.projetobora.util.JwtUtil;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            Optional<Usuario> usuarioOpt = usuarioRepository.findByLogin(loginDTO.getLogin());

            if (usuarioOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login ou senha inválidos");
            }

            Usuario usuario = usuarioOpt.get();

            if (!passwordEncoder.matches(loginDTO.getSenha(), usuario.getSenha())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login ou senha inválidos");
            }

            String token = jwtUtil.gerarToken(usuario.getLogin());
            TokenDTO tokenDTO = new TokenDTO(token, 86400000L);

            return ResponseEntity.ok(tokenDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro no login: " + e.getMessage());
        }
    }

    @PostMapping("/validar")
    public ResponseEntity<?> validarToken(@RequestBody TokenDTO tokenDTO) {
        try {
            if (jwtUtil.validarToken(tokenDTO.getToken())) {
                return ResponseEntity.ok("Token válido");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido ou expirado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao validar token: " + e.getMessage());
        }
    }
}
