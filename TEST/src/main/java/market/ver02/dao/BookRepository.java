package market.ver02.dao;

import java.util.ArrayList;

import market.ver02.dto.Book;

public class BookRepository {
	private ArrayList<Book> listOfBooks = new ArrayList<Book>();
	private static BookRepository instance = new BookRepository(); // �߰� �κ�
	
	public static BookRepository getInstance() { // �߰� �κ�
		return instance;
	}
	public BookRepository() {
		Book book1 = new Book("a111", "���� ���� �Ӵ� ���ε�", 17100);
		book1.setAuthor("�ι�Ʈ �ر׽�Ʈ�� ��/������ ��/�̻�� ����");
		book1.setDescription("���� ������ �̼� ���� ���ΰ�?");
		book1.setPublisher("�帧����");
		book1.setCategory("����");
		book1.setUnitsInStock(0);
		book1.setTotalPages(0);
		book1.setReleseDate(null);
		book1.setCondition(null);
		book1.setFilename("a111.png");

		
		Book book2 = new Book("B1234", "�����ͷ� �����ϴ� ���ѹα� �ε����� �̷�", 16500);
		book2.setAuthor("�������� ����ġ��, ���뿭, Ȳ�Ѽ�, ����ȯ, Ȳ���� �� �� 1��");
		book2.setDescription("�ܼ��� ������ �ε�������� ���ϴ� �ô�� ������!");
		book2.setPublisher("����Ʈ�Ͻ�");
		book2.setCategory("����");
		book2.setUnitsInStock(0);
		book2.setTotalPages(0);
		book2.setReleseDate(null);
		book2.setCondition(null);
		book2.setFilename("P1235.png");

		Book book3 = new Book("B12345", "ȣ������", 25200);
		book3.setAuthor("�̺�ö ��");
		book3.setDescription("�Ｚ â���� ȣ�� �̺�ö�� �����̴�.");
		book3.setPublisher("����");
		book3.setCategory("�ι�");
		book3.setUnitsInStock(0);
		book3.setTotalPages(0);
		book3.setReleseDate(null);
		book3.setCondition(null);
		book3.setFilename("P1236.png");

		listOfBooks.add(book1);
		listOfBooks.add(book2);
		listOfBooks.add(book3);
//		private String author; // ����
//		private String description; // ����
//		private String publisher; // ���ǻ�
//		private String category; // �з�
//		private long unitsInStock; // ��� ��
//		private long totalPages; // ������ ��
//		private String releseDate; // ������(��/��)
//		private String condition; // �ű� ���� or �߰� ���� or E-book
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
	public void addBook(Book book) { // �߰� �κ�
		listOfBooks.add(book);
	}
}
