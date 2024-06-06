package com.impacta.prova_final.controller;

import com.impacta.prova_final.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity criarProduto(@RequestBody ProdutoDTO produto) {
        var usuario = usuarioRepository.getReferenceById(produto.idUsuario());
        Produto produto1 = new Produto(produto,usuario);
        usuario.incluirProduto(produto1);

        repository.save(produto1);

        return ResponseEntity.ok().body("Criado com sucesso!");
    }

    @GetMapping
    public List<Produto> getProdutos() {
        List<Produto> produtos = repository.findAll();
        return produtos;
    }
    @GetMapping("/{id}")
    public Produto getProduto(@PathVariable Long id) {
        return repository.findById(id).get();
    }
}
