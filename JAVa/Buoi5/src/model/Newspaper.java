package model;

public class Newspaper extends Document {
    private int publishDay;

    public int getPublishDay() {
        return publishDay;
    }

    public void setPublishDay(int publishDay) {
        this.publishDay = publishDay;
    }

    public Newspaper() {
    }

    public Newspaper(int documentId, String publisher, int publishAmount, int publishDay) {
        super(documentId, publisher, publishAmount);
        this.publishDay = publishDay;
    }

    @Override
    public String toString() {
        return super.toString() + "Newspaper [publishDay=" + publishDay + "]";
    }
    
}
