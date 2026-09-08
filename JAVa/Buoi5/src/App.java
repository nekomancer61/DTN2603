import java.util.ArrayList;
import java.util.List;

import backend.services.DocumentManagementService;
import frontend.DocumentManagementMenu;
import model.Book;
import model.Document;
import model.Magazine;
import model.Newspaper;

public class App {
    public static void main(String[] args) throws Exception {
        List<Document> documents = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        List<Newspaper> news = new ArrayList<>();
        List<Magazine> mags = new ArrayList<>();

       
        DocumentManagementService service = new DocumentManagementService(documents, books, mags, news);
        DocumentManagementMenu menu = new DocumentManagementMenu(service);
        menu.documentManagement();
    }
}
