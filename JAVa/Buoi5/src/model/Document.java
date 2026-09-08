package model;

public class Document {
    private int documentId;
    private String publisher;
    private int publishAmount;
    public int getDocumentId() {
        return documentId;
    }
    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public int getPublishAmount() {
        return publishAmount;
    }
    public void setPublishAmount(int publishAmount) {
        this.publishAmount = publishAmount;
    }
    public Document() {
    }
    public Document(int documentId, String publisher, int publishAmount) {
        this.documentId = documentId;
        this.publisher = publisher;
        this.publishAmount = publishAmount;
    }
    @Override
    public String toString() {
        return "Document [documentId=" + documentId + ", publisher=" + publisher + ", publishAmount=" + publishAmount + "]";
    }
    
}
