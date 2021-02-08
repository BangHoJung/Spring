package papago;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {
	private static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);

	public static AnnotationConfigApplicationContext getContext() {
		return context;
	}
	
	@Bean
	public Papago getPapago() {
		Papago papago = new Papago();
		return papago;
	}
	

}
