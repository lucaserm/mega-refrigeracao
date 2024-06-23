package com.omeninotech.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
}
