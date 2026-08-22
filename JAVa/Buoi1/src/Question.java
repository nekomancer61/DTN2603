import java.util.Date;
import java.util.List;

public class Question {
    private int questionId;
    private String content;
    private CategoryQuestion category;
    private TypeQuestion type;
    private Account creator;
    private Date createDate;
    private List<Answer> answers;
    private List<Exam> exams;
    public Question() {
    }
    public Question(int questionId, String content, CategoryQuestion category, TypeQuestion type, Account creator,
            Date createDate) {
        this.questionId = questionId;
        this.content = content;
        this.category = category;
        this.type = type;
        this.creator = creator;
        this.createDate = createDate;
    }
    public Question(int questionId, String content, CategoryQuestion category, TypeQuestion type, Account creator,
            Date createDate, List<Answer> answers, List<Exam> exams) {
        this.questionId = questionId;
        this.content = content;
        this.category = category;
        this.type = type;
        this.creator = creator;
        this.createDate = createDate;
        this.answers = answers;
        this.exams = exams;
    }
    public int getQuestionId() {
        return questionId;
    }
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public CategoryQuestion getCategory() {
        return category;
    }
    public void setCategory(CategoryQuestion category) {
        this.category = category;
    }
    public TypeQuestion getType() {
        return type;
    }
    public void setType(TypeQuestion type) {
        this.type = type;
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
    public List<Answer> getAnswers() {
        return answers;
    }
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
    public List<Exam> getExams() {
        return exams;
    }
    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
    @Override
    public String toString() {
        return "Question [questionId=" + questionId + ", content=" + content + ", category=" + category.toString() + ", type="
                + type.toString() + ", creator=" + creator.toString() + ", createDate=" + createDate  + "]";
    }
    
    
}
