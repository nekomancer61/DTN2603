package backend.repositories.Impl;

import java.util.List;

import backend.repositories.DocumentManagementRepository;
import model.Book;
import model.Document;
import model.Magazine;
import model.Newspaper;

public class DocumentManagementImpl implements DocumentManagementRepository {

    private List<Document> documents;
    private List<Book> books;
    private List<Magazine> mags;
    private List<Newspaper> newspapers;

    public DocumentManagementImpl() {
    }

    public DocumentManagementImpl(List<Document> documents, List<Book> books, List<Magazine> mags,
            List<Newspaper> newspapers) {
        this.documents = documents;
        this.books = books;
        this.mags = mags;
        this.newspapers = newspapers;
    }

    @Override
    public boolean insertDocument(Book book) {
        try {
            documents.add(book);
            books.add(book);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean insertDocument(Newspaper newspaper) {
        try {
            documents.add(newspaper);
            newspapers.add(newspaper);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean insertDocument(Magazine magazine) {
        try {
            documents.add(magazine);
            mags.add(magazine);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean insertDocument(Document document) {
        try {
            documents.add(document);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteDocument(int documentId) {
        for (Document d : documents) {
            if (d.getDocumentId() == documentId) {
                documents.remove(d);
                return true;
            }
        }
        return false;
    }

    @Override
    public void showDocumentInfo() {
       for (Book book : books) {
            System.out.println(book.toString());
       }
       for (Magazine magazine : mags){
        System.out.println(magazine.toString());
       }
       for (Newspaper newspaper : newspapers){
        System.out.println(newspaper.toString());
       }
    }

    @Override
    public List<? extends Document> searchDocument(int type) {
        switch (type) {
            case 1:
                return books;
            case 2:
                return mags;
            case 3:
                return newspapers;
            default:
                break;
        }
        return null;
    }

}
