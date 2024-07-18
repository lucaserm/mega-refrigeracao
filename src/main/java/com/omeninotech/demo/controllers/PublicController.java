package com.omeninotech.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import com.omeninotech.demo.models.Produto;
import com.omeninotech.demo.repository.ProdutoRepository;

@Controller
public class PublicController {

	@Autowired
	public ProdutoRepository produtoRepository;

	@RequestMapping("/")
	public String index(Model model) {
		List<Produto> produtos = produtoRepository.findAllByOrderByIdAsc();
		model.addAttribute("produtos", produtos);
		return "public-index.html";
	}

	@RequestMapping("/venda")
	public String venda(Model model) {
		List<Produto> produtos = produtoRepository.findAllByOrderByIdAsc();
		model.addAttribute("produtos", produtos);
		return "venda.html";
	}
	
	@PostMapping("/venda/efetuar/{codigo}/{quantidade}")
	public ResponseEntity efetuarVenda(@PathVariable("codigo") String codigo, @PathVariable("quantidade") int quantidade) {
		Produto produto = produtoRepository.findByCodigo(codigo);
		produto.setQuantidade(produto.getQuantidade() - quantidade);
		produtoRepository.save(produto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
