package papago;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {
	
	@Bean
	public Papago getPapago() {
		Papago papago = new Papago();
		return papago;
	}
	

}
