package br.com.wellington.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.wellington.models.Cliente;
import br.com.wellington.models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	List<Pedido> findByCliente(Cliente cliente);
	
	@Query("Select p from Pedido p left join fetch p.itensPedido where p.idPedido = :id")
	Optional<Pedido> findByIdPedidoFetchItensPedido(@Param("id") Long idPedido);

}
