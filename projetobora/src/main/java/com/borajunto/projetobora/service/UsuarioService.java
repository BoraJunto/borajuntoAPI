package com.borajunto.projetobora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borajunto.projetobora.dto.UsuarioDTO;
import com.borajunto.projetobora.entidade.Usuario;
import com.borajunto.projetobora.repository.UsuarioRepository;


@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> listarTodos(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(UsuarioDTO::new).toList(); 
    }

    public void inserir(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario(usuarioDTO);
        usuario.setCpf(usuarioDTO.getCpf());
        usuarioRepository.save(usuario);

    }

    public UsuarioDTO alterar(UsuarioDTO usuarioDTO){
        Usuario usuario =  new Usuario(usuarioDTO);
        return new UsuarioDTO(usuarioRepository.save(usuario)); 
    }

    public void excluir(String cpf){
        Usuario usuario = usuarioRepository.findById(cpf).get();
        usuarioRepository.delete(usuario);
    }

    public UsuarioDTO buscarPorId(String cpf){
        return new UsuarioDTO(usuarioRepository.findById(cpf).get());
    }
    
      
}
