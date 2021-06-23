package br.com.wellington.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import br.com.wellington.models.Cliente;

//@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
//public interface ClientRepository extends CrudRepository<Cliente, Long>{
	Cliente findByNome(String nome);

	//Cliente findByidCliente(Long idCliente);

	List<Cliente> findByNomeLike(String nome);

	List<Cliente> findByNomeOrIdCliente(String nome, Long idCliente);

	List<Cliente> findByNomeOrderByIdCliente(String nome);

	Cliente findOneByNome(String nome);

	boolean existsByNome(String nome);

	// com jpql
	@Query(value = "select c from Cliente c where c.nome like :nome")
	List<Cliente> encontrarPorNome(@Param("nome") String nome);

	// com SQL nativa

	@Query(value = "select * from clientes c where c.nome like %:nome% ", nativeQuery = true)
	List<Cliente> encontrarPorNomes(@Param("nome") String nome);

	// void deleteByNome(String nome);
	@Modifying // Informa que haverá transação
	@Transactional
	@Query(value = "delete from Cliente s where s.nome = :nome")
	void deletarUsingQueryAnnotation(@Param("nome") String nome);
	
	
	@Query(value = "select c from Cliente c left join fetch c.pedidos where c.id = :id")
	Cliente findClientFetchPedido(@Param("id") Long id);
	

}
