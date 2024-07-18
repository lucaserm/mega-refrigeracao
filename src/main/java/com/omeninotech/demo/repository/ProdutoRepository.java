package com.omeninotech.demo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omeninotech.demo.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
	public List<Produto> findAllByOrderByIdAsc();
	public Produto findByCodigo(String codigo);
}
