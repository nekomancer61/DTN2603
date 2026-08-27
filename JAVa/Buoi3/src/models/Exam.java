package models;
import java.util.Date;
import java.util.List;

public class Exam {
    private int examId;
    private String code;
    private String title;
    private CategoryQuestion categoryId;
    private int duration;
    private Account creator;
    private Date createDate;
    private List<Question> questions;
    public Exam() {
    }
    public Exam(int examId, String code, String title, CategoryQuestion categoryId, int duration, Account creator,
            Date createDate) {
        this.examId = examId;
        this.code = code;
        this.title = title;
        this.categoryId = categoryId;
        this.duration = duration;
        this.creator = creator;
        this.createDate = createDate;
    }
    public Exam(int examId, String code, String title, CategoryQuestion categoryId, int duration, Account creator,
            Date createDate, List<Question> questions) {
        this.examId = examId;
        this.code = code;
        this.title = title;
        this.categoryId = categoryId;
        this.duration = duration;
        this.creator = creator;
        this.createDate = createDate;
        this.questions = questions;
    }
    public int getExamId() {
        return examId;
    }
    public void setExamId(int examId) {
        this.examId = examId;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public CategoryQuestion getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(CategoryQuestion categoryId) {
        this.categoryId = categoryId;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public Account getCreator() {
        return creator;
    }
    public void setCreator(Account creator) {
        this.creator = creator;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public List<Question> getQuestions() {
        return questions;
    }
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    @Override
    public String toString() {
        return "Exam [examId=" + examId + ", code=" + code + ", title=" + title + ", categoryId=" + categoryId
                + ", duration=" + duration + ", creator=" + creator + ", createDate=" + createDate + "]";
    }
    
}
