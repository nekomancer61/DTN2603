package backend.services;

import java.util.List;

import backend.repositories.Impl.DocumentManagementImpl;
import model.Book;
import model.Document;
import model.Magazine;
import model.Newspaper;

public class DocumentManagementService {
    private DocumentManagementImpl dmI; 
    public DocumentManagementService(List<Document> documents, List<Book> books, List<Magazine> mags,
            List<Newspaper> news) {
        dmI  = new DocumentManagementImpl(documents, books, mags, news);
    }

    public DocumentManagementService() {
    }

	public void insertBook(int documentId, String publisher, int publishAmount, String author, int pageNumber) {
		Book b = new Book(documentId, publisher, publishAmount, author, pageNumber);
        dmI.insertDocument(b);
	}

    public void insertMag(int documentId, String publisher, int publishAmount, int publishNo, int publishMonth) {
        Magazine mag = new Magazine(documentId, publisher, publishAmount, publishNo, publishMonth);
        dmI.insertDocument(mag);
    }

    public void insertNews(int documentId, String publisher, int publishAmount, int publishDay) {
        Newspaper news = new Newspaper(documentId, publisher, publishAmount, publishDay);
        dmI.insertDocument(news);    
    }

    public void insertDoc(int documentId, String publisher, int publishAmount) {
        Document doc = new Document(documentId, publisher, publishAmount);
        dmI.insertDocument(doc);
    }

    public void remove(int documentId) {
        dmI.deleteDocument(documentId);
    }

    public void displayInfo() {
        dmI.showDocumentInfo();
    }

    public void showBooks() {
        dmI.searchDocument(1);
    }

    public void showNews() {
        dmI.searchDocument(2);    
    }

    public void showMags() {
        dmI.searchDocument(3);
    }
    
    

    
}
