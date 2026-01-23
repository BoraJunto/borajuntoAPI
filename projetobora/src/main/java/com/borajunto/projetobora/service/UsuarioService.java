package com.borajunto.projetobora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.borajunto.projetobora.dto.UsuarioDTO;
import com.borajunto.projetobora.entidade.Usuario;
import com.borajunto.projetobora.repository.UsuarioRepository;


@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UsuarioDTO> listarTodos(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(UsuarioDTO::new).toList(); 
    }

    public void inserir(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario(usuarioDTO);
        usuario.setCpf(usuarioDTO.getCpf());
        
        // Criptografa a senha antes de salvar
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        
        usuarioRepository.save(usuario);
    }

    public UsuarioDTO alterar(UsuarioDTO usuarioDTO){
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(usuarioDTO.getCpf());
        
        if (usuarioExistente.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        
        Usuario usuario = usuarioExistente.get();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setLogin(usuarioDTO.getLogin());
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTelefone(usuarioDTO.getTelefone());
        usuario.setUniversidade(usuarioDTO.getUniversidade());
        usuario.setRA(usuarioDTO.getRA());
        usuario.setSexo(usuarioDTO.getSexo());
        
        return new UsuarioDTO(usuarioRepository.save(usuario)); 
    }

    public void excluir(String cpf){
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(cpf);
        if (usuarioOpt.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        usuarioRepository.delete(usuarioOpt.get());
    }

    public UsuarioDTO buscarPorId(String cpf){
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(cpf);
        return usuarioOpt.map(UsuarioDTO::new).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    }
    
}
