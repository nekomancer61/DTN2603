package backend.repositories;

import java.util.List;

import model.Book;
import model.Document;
import model.Magazine;
import model.Newspaper;

public interface DocumentManagementRepository {
    boolean insertDocument(Book book);
    boolean insertDocument(Newspaper newspaper);
    boolean insertDocument(Magazine magazine);
    boolean insertDocument(Document document);
    boolean deleteDocument(int documentId);
    void showDocumentInfo();
    List<? extends Document> searchDocument(int type); 
}
