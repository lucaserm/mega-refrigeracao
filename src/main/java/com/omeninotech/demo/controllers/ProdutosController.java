package com.omeninotech.demo.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.omeninotech.demo.models.Produto;
import com.omeninotech.demo.repository.ProdutoRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	ProdutoRepository produtoRepository;

	@GetMapping("/novo")
	public String index(Model model) {
		model.addAttribute("produto", new Produto());
		return "insert-produto";
	}

	@PostMapping("/salvar")
	public String create(@Valid Produto produto) {
		produtoRepository.save(produto);
		return "redirect:/";
	}

	@GetMapping("/editar/{id}")
	public String edit(@PathVariable("id") UUID id, Model model) {
		Produto produto = produtoRepository.findById(id).get();
		if (produto == null) {
			return "redirect:/produtos/novo";
		}
		model.addAttribute("produto", produto);
		return "edit-produto";
	}

	@PostMapping("/editar/{id}")
	public String editar(@PathVariable("id") UUID id, Produto produto) {
		produtoRepository.save(produto);
		return "redirect:/";
	}

}
