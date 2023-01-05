package market.ver02.dao;

import java.util.ArrayList;

import market.ver02.dto.Book;

public class BookRepository {
	private ArrayList<Book> listOfBooks = new ArrayList<Book>();
	private static BookRepository instance = new BookRepository(); // 추가 부분
	
	public static BookRepository getInstance() { // 추가 부분
		return instance;
	}
	public BookRepository() {
		Book book1 = new Book("a111", "워런 버핏 머니 마인드", 17100);
		book1.setAuthor("로버트 해그스트롬 저/오은미 역/이상건 감수");
		book1.setDescription("누가 끝까지 미소 지을 것인가?");
		book1.setPublisher("흐름출판");
		book1.setCategory("경제");
		book1.setUnitsInStock(0);
		book1.setTotalPages(0);
		book1.setReleseDate(null);
		book1.setCondition(null);
		book1.setFilename("a111.png");

		
		Book book2 = new Book("B1234", "빅데이터로 전망하는 대한민국 부동산의 미래", 16500);
		book2.setAuthor("경제만랩 리서치팀, 오대열, 황한솔, 안주환, 황유상 저 외 1명");
		book2.setDescription("단순히 감으로 부동산시장을 평가하는 시대는 끝났다!");
		book2.setPublisher("메이트북스");
		book2.setCategory("경제");
		book2.setUnitsInStock(0);
		book2.setTotalPages(0);
		book2.setReleseDate(null);
		book2.setCondition(null);
		book2.setFilename("P1235.png");

		Book book3 = new Book("B12345", "호암자전", 25200);
		book3.setAuthor("이병철 저");
		book3.setDescription("삼성 창업자 호암 이병철의 자전이다.");
		book3.setPublisher("나남");
		book3.setCategory("인물");
		book3.setUnitsInStock(0);
		book3.setTotalPages(0);
		book3.setReleseDate(null);
		book3.setCondition(null);
		book3.setFilename("P1236.png");

		listOfBooks.add(book1);
		listOfBooks.add(book2);
		listOfBooks.add(book3);
//		private String author; // 저자
//		private String description; // 설명
//		private String publisher; // 출판사
//		private String category; // 분류
//		private long unitsInStock; // 재고 수
//		private long totalPages; // 페이지 수
//		private String releseDate; // 출판일(월/년)
//		private String condition; // 신규 도서 or 중고 도서 or E-book
	}
	
	public ArrayList<Book> getAllBooks(){
		return listOfBooks;
	}
	
	public Book getBookBytId(String booktId) {
		Book bookBytId = null;
		for(int i = 0; i < listOfBooks.size(); i++) {
			Book book = listOfBooks.get(i);
			if (book != null && book.getBooktId() != null && book.getBooktId().equals(booktId)) {
				bookBytId = book;
				break;
			}
		}
		return bookBytId;
	}
	public void addBook(Book book) { // 추가 부분
		listOfBooks.add(book);
	}
}
