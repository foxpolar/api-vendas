package br.com.wellington;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;






@SpringBootApplication
//@EnableJpaRepositories("br.com.wellington.repositorio")
public class SellsApplication {

	//@Bean
	//public CommandLineRunner init(@Autowired ClienteRepository  clientRepository, @Autowired PedidoRepository pedidoRepository) {
	//	return args ->{	
			//Cliente c1 = clientRepository.findByidCliente(2L);
			//System.out.println("Nome: "+c1.getPedidos().size());
			//Cliente c1 = new Cliente("Wellington Mateus");
			//Cliente c2 = new Cliente("Maria Helena");
			//Cliente c3 = new Cliente("Paola Márcia");
			
			//clientRepository.save(c1);
			//clientRepository.save(c2);
			//clientRepository.save(c3);
			
			//Cliente c = clientRepository.findByidCliente(1L);			
			//Pedido p = new Pedido();
			//p.setCliente(c);
			//p.setDataPedido(LocalDate.now());
			//p.setTotal(BigDecimal.valueOf(12.86));
			//pedidoRepository.save(p);
			
			//Cliente c1 = clientRepository.findClientFetchPedido(1L);
			//Set<Pedido> pedido = c1.getPedidos();
			//for (Pedido pd : pedido) {
			//	System.out.println("Data: " + pd.getDataPedido() + " Total: "+ pd.getTotal());
			//}
			
			//Cliente c1 = clientRepository.findClientFetchPedido(1L);
			//List<Pedido> lista = pedidoRepository.findByCliente(c1);
			//for (Pedido pd : lista) {
			//	System.out.println("Data: " + pd.getDataPedido() + " Total: "+ pd.getTotal());
			//}
			
			//pedidoRepository.findByCliente(c1).forEach(System.out::println);
			
			
			/*
			Cliente cliente = clientRepository.findByidCliente(1L);
			cliente.setNome("Wellington José Moura Matheus");
			clientRepository.save(cliente);
			System.out.println("Alterou Cliente");
			System.out.println("--------------------");
			System.out.println("Listar todos Cliente");
			//lista todos
			Iterable<Cliente> c = (List<Cliente>) clientRepository.findAll();
			//List<Cliente> c = new ArrayList<>();
		    //clientRepository.findAll().forEach(c::add);//convertendo em lista
			for (Cliente  s : c) {
				System.out.println("Nome: "+s.getNome());
			}
			
			//Cliente cliente = clientRepository.findByidCliente(2L);
			//System.out.println("Nome: "+cliente.getNome());
			
			//Cliente cliente = clientRepository.findByNome("Wellington");
			//System.out.println("Nome: "+cliente.getNome());
			
			//Cliente cliente = new Cliente();
			//cliente.setNome("Maria Helena");
			//clientRepository.save(cliente);
			
			//Cliente c = clientRepository.FindByName(4L);
			//c.setNome("Maria Helena de Moura Mateus");
			//clientRepository.update(c);
			
			//clientRepository.delete(4L);
			
			//List<Cliente> l = clientRepository.findAll();
			//for (Cliente c : l) {
			//	System.out.println("Nome: "+c.getNome());
			//}
			
			//Cliente c = clientRepository.FindByName("Wellington José Moura Matheus");
			//System.out.println("Nome: "+c.getNome());
			//if(c != null) {
			//	c.setNome("Wellington José Moura Mateus");
			//	clientRepository.update(c);
			//}
			/*
			List<Cliente> l = clientRepository.findByNomeLike("%Rodrigues%");
			if(l.isEmpty()) {
				System.out.println("Lista vazia ");
			}
			for (Cliente x : l) {
				System.out.println("Nome: "+x.getNome());
			}
			
			System.out.println("--------------------");
			System.out.println("Listar todos Cliente");
			
			List<Cliente> h = clientRepository.findByNomeOrIdCliente("Wellington José Moura Matheus", 10L);
			for (Cliente p : h) {
				System.out.println("Nome: "+p.getNome());
			}
			
			System.out.println("--------------------");
			System.out.println("Um so  Cliente");
			Cliente g = clientRepository.findOneByNome("Wellington José Moura Matheus");
			System.out.println("Nome: "+g.getNome());
			
			System.out.println("---------Encontrar por Nome-----------");
			List<Cliente> w = clientRepository.encontrarPorNomes("Rodrigues");
			if(w.isEmpty()) {
				System.out.println("Lista vaziassss ");
			}
			for (Cliente p1 : w) {
				System.out.println("Nome: "+p1.getNome());
			}
			System.out.println("--------------------");
			System.out.println("Deletar por nome");
			clientRepository.deletarUsingQueryAnnotation("Wellington José Moura Matheus");
			
			*/
			//System.out.println("Deletar por nome");
			
		//};
	//}
	


	public static void main(String[] args) {
		
		
		SpringApplication.run(SellsApplication.class, args);
	}

}
