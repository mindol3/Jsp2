package market.ver02.dto;

public class Book {
	private String booktId; // ���� ���̵�
	private String name; // ������
	private Integer unitPrice; // ����
	private String author; // ����
	private String description; // ����
	private String publisher; // ���ǻ�
	private String category; // �з�
	private long unitsInStock; // ��� ��
	private long totalPages; // ������ ��
	private String releseDate; // ������(��/��)
	private String condition; // �ű� ���� or �߰� ���� or E-book
	private String filename; // �̹��� ���ϸ�
	private int quantity; // ��ٱ��Ͽ� ���� ����


	
	public Book() {
		super();
	}

	public Book(String booktId, String name, Integer unitPrice) {
		this.booktId = booktId;
		this.name = name;
		this.unitPrice = unitPrice;
	}

	public String getBooktId() {
		return booktId;
	}

	public void setBooktId(String booktId) {
		this.booktId = booktId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(long unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

	public String getReleseDate() {
		return releseDate;
	}

	public void setReleseDate(String releseDate) {
		this.releseDate = releseDate;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
