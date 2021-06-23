package br.com.wellington.security.jtw;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import br.com.wellington.models.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

	@Value("${security.jwt.expiracao}")
	private String expiracao;
	@Value("${security.jwt.chave-assinatura}")
	private String chaveAssinatura;

	public String gerarToken(Usuario usuario) {
		long expira = Long.valueOf(expiracao);
		LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expira);
		Date data = Date.from(dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant());
		// HashMap<String, Object> claims = new HashMap<>();
		// claims.put("emaildoUsuario", "usuario@gmail.com");
		// claims.put("rolesUsuario", "USER");

		return Jwts.builder().setSubject(usuario.getLogin()).setExpiration(data)
				// .setClaims(claims)//pode colocar mais informações através do hashmap dentro
				// do token
				.signWith(SignatureAlgorithm.HS512, chaveAssinatura).compact();

	}

	private Claims obterClaims(String token) throws ExpiredJwtException {
		return Jwts.parser().setSigningKey(chaveAssinatura).parseClaimsJws(token).getBody();
	}

	public boolean tokenValido(String token) {
		try {
			Claims claims = obterClaims(token);
			Date expiration = claims.getExpiration();
			LocalDateTime localDateTime = expiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			return !LocalDateTime.now().isAfter(localDateTime);
		} catch (Exception e) {
			return false;
		}
	}
	
	public String obterLoginUsuario(String token) throws ExpiredJwtException {
		return (String) obterClaims(token).getSubject();
	}
	/*
	 * public static void main(String args[]) {
	 * 
	 * ConfigurableApplicationContext ctx =
	 * SpringApplication.run(SellsApplication.class); JwtService service =
	 * ctx.getBean(JwtService.class); Usuario u =
	 * Usuario.builder().login("José").build(); System.out.println("Token: " +
	 * service.gerarToken(u)); }
	 */

}
