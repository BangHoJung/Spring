package book;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {

	@Bean
	public NaverBookSearch search() {
		NaverBookSearch search = new NaverBookSearch();
		return search;
	}
	
}
