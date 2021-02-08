package book;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class BookWebProjectApplicationTests {
	
	private static Connection conn = null;
	@BeforeAll
	static void start() {
		
	}
	
	@AfterAll
	static void end() {
		
	}
	
	@Autowired
	BookMapper mapper;
	
	@DisplayName("등록 테스트")
	@Test
	void testA() {
		String bno = "891245671234";
		String title = "자바 프로그래밍";
		String writer = "홍길동";
		String publisher = "J테스트";
		String wdate = "2020-02-19";
		
		int count = mapper.insertBook(new BookDTO(bno, title, writer, publisher, wdate));
		if(count == 0) fail("등록 테스트 에러");
	}
	
	@DisplayName("검색 테스트")
	@Test
	void testB() {
		String search = "자바";
		
		List<BookDTO> list =  mapper.selectBook(search);
		if(list.size()==0) fail("검색 테스트 에러");
	}
	
	@DisplayName("삭제 테스트")
	@Test
	void testC() {
		String bno = "891245671234";
		
		int count = mapper.deleteBook(bno);
		if(count == 0) fail("삭제 테스트 에러");
	}
	
	
	
}
