package model;

public class Magazine extends Document{
    private int publishNo;
    private int publishMonth;
    public int getPublishNo() {
        return publishNo;
    }
    public int getPublishMonth() {
        return publishMonth;
    }
    public void setPublishNo(int publishNo) {
        this.publishNo = publishNo;
    }
    public void setPublishMonth(int publishMonth) {
        this.publishMonth = publishMonth;
    }
    public Magazine() {
    }
    public Magazine(int documentId, String publisher, int publishAmount, int publishNo, int publishMonth) {
        super(documentId, publisher, publishAmount);
        this.publishNo = publishNo;
        this.publishMonth = publishMonth;
    }
    @Override
    public String toString() {
        return super.toString() + "Magazine [publishNo=" + publishNo + ", publishMonth=" + publishMonth + "]";
    }
    
    
}
