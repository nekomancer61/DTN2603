package model;

public class Book extends Document {
    private String authorName;
    private int pageNumber;
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public int getPageNumber() {
        return pageNumber;
    }
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    public Book(int documentId, String publisher, int publishAmount, String authorName, int pageNumber) {
        super(documentId, publisher, publishAmount);
        this.authorName = authorName;
        this.pageNumber = pageNumber;
    }
    public Book() {
    }
    @Override
    public String toString() {
        return super.toString() + "Book [authorName=" + authorName + ", pageNumber=" + pageNumber + "]";
    }
    
}
