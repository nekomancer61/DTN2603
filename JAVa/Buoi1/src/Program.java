
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Program {
    public static void main(String[] args) throws Exception {
        //System.out.println("Hello, World!");

        Department department1 = new Department(0, "Department #1");
        Department department2 = new Department(1, "Department #2");
        Department department3 = new Department(2, "Department #3");

        Position position1 = new Position(0, "Position1");
        Position position2 = new Position(1, "Position2");
        Position position3 = new Position(2, "Position3");

        Date today = new Date();
        Account account1 = new Account(0, "acc1@gmail.com", "acc1", "acc 1", department1, position1,today);
        Account account2 = new Account(1, "acc2@gmail.com", "acc2", "acc 2", department2, position2,today);
        Account account3 = new Account(2, "acc3@gmail.com", "acc3", "acc 3", department3, position3,today);
    
        Group group1 = new Group(0, "group 1", account1, today);
        Group group2 = new Group(1, "group 2", account2, today);
        Group group3 = new Group(3, "group 3", account3, today);

        TypeQuestion type1 = new TypeQuestion(0, "type 1");
        TypeQuestion type2 = new TypeQuestion(1, "type 2");
        TypeQuestion type3 = new TypeQuestion(2, "type 3");

        GroupAccount groupAccount1 = new GroupAccount(0, 0, today);
        GroupAccount groupAccount2 = new GroupAccount(1, 1, today);
        GroupAccount groupAccount3 = new GroupAccount(1, 1, today);

        CategoryQuestion categoryQuestion1 = new CategoryQuestion(0, "cate 1");
        CategoryQuestion categoryQuestion2 = new CategoryQuestion(1, "cate 2");
        CategoryQuestion categoryQuestion3 = new CategoryQuestion(2, "cate 3");

        Question question1 = new Question(0,"content 1", categoryQuestion1, type1, account1, today);
        Question question2 = new Question(1, "question 2", categoryQuestion2, type2, account2, today);
        Question question3 = new Question(2, "question 3", categoryQuestion3, type3, account3, today);

        Answer answer1 = new Answer(0, "ans 1", question1, false);
        Answer answer2 = new Answer(1, "ans 2", question1, true);
        Answer answer3 = new Answer(2, "ans 3", question1, false);

        Exam exam1 = new Exam(0, "ex1", "exam1", categoryQuestion3, 60, account3, today);
        Exam exam2 = new Exam(1, "ex2", "exam2", categoryQuestion3, 60, account3, today);
        Exam exam3 = new Exam(2, "ex3", "exam3", categoryQuestion3, 60, account3, today);

        List<Exam> exams = new ArrayList<>();
        exams.add(exam1);
        exams.add(exam2);
        exams.add(exam3);

        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        List<Answer> answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);

        question1.setExams(exams);
        question1.setAnswers(answers);

        exam1.setQuestions(questions);

        System.out.println(department1.toString());
        System.out.println(position1.toString());
        System.out.println(account1.toString());
        System.out.println(group1.toString());
        System.out.println(groupAccount1.toString());
        System.out.println(type1.toString());
        System.out.println(categoryQuestion1.toString());
        System.out.println(question1.toString());
        System.out.println(exam1.toString());
        System.out.println(answer1.toString());
        


    }
}
