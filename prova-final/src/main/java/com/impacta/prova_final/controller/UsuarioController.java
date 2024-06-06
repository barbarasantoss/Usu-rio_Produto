package com.impacta.prova_final.controller;

import com.impacta.prova_final.domain.Produto;
import com.impacta.prova_final.domain.Usuario;
import com.impacta.prova_final.domain.UsuarioDTO;
import com.impacta.prova_final.domain.UsuarioRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    public ResponseEntity criarUsuario(@RequestBody UsuarioDTO usuario) {

        repository.save(new Usuario(usuario));
        System.out.println(repository.findAll());
        return ResponseEntity.ok().body("Criado com sucesso!");
    }

    @GetMapping("/{usuarioId}/produtos")
    public ResponseEntity getProdutos(@PathVariable Long usuarioId) {

        var produtos = repository.findById(usuarioId).get();
        return ResponseEntity.ok().body(produtos);

    }


}
