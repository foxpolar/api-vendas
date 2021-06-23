package br.com.wellington.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wellington.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
