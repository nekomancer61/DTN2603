import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Account;
import models.Answer;
import models.CategoryQuestion;
import models.Department;
import models.Exam;
import models.Group;
import models.GroupAccount;
import models.Position;
import models.Question;
import models.TypeQuestion;
import models.Position.PositionName;
import models.TypeQuestion.TypeName;

public class Program {
    public static void main(String[] args) throws Exception {
        // System.out.println("Hello, World!");

        Department department1 = new Department(0, "Department #1");
        Department department2 = new Department(1, "Department #2");
        Department department3 = new Department(2, "Department #3");
        Department department4 = new Department(3, "Department #4");
        Department department5 = new Department(4, "Department #5");
        Department[] departments = {department1,department2,department3,department4,department5};


        Position position1 = new Position(0, PositionName.DEV);
        Position position2 = new Position(1, PositionName.TEST);
        Position position3 = new Position(2, PositionName.TEST);

        Date today = new Date();
        Account account1 = new Account(0, "acc1@gmail.com", "acc1", "acc 1", department1, position1, today);
        Account account2 = new Account(1, "acc2@gmail.com", "acc2", "acc 2", department2, position2, today);
        Account account3 = new Account(2, "acc3@gmail.com", "acc3", "acc 3", department3, position3, today);
        Account account4 = new Account(1, "acc2@gmail.com", "acc2", "acc 2", department2, position2, today);
        Account account5 = new Account(2, "acc3@gmail.com", "acc3", "acc 3", department3, position3, today);
        Account[] accounts = {account1,account2,account3,account4,account5};

        Group group1 = new Group(0, "Java 1", account1, today);
        Group group2 = new Group(1, "Java", account2, today);
        Group group3 = new Group(3, "group 3", account3, today);
        Group[] groups = {group1,group2,group3};

        TypeQuestion type1 = new TypeQuestion(0, TypeName.MULTIPLE_CHOICE);
        TypeQuestion type2 = new TypeQuestion(1, TypeName.MULTIPLE_CHOICE);
        TypeQuestion type3 = new TypeQuestion(2, TypeName.ESSAY);

        GroupAccount groupAccount1 = new GroupAccount(0, 0, today);
        GroupAccount groupAccount2 = new GroupAccount(1, 1, today);
        GroupAccount groupAccount3 = new GroupAccount(1, 1, today);

        CategoryQuestion categoryQuestion1 = new CategoryQuestion(0, "cate 1");
        CategoryQuestion categoryQuestion2 = new CategoryQuestion(1, "cate 2");
        CategoryQuestion categoryQuestion3 = new CategoryQuestion(2, "cate 3");

        Question question1 = new Question(0, "content 1", categoryQuestion1, type1, account1, today);
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

        Exercise1_DataTypeCasting ex1 = new Exercise1_DataTypeCasting();
        ex1.question1();
        ex1.question2();
        ex1.question3();
        ex1.question4();
        Exercise2_DefaultValue ex2 = new Exercise2_DefaultValue();
        ex2.question1();
        Exercise3_BoxingAndUnboxing ex3 = new Exercise3_BoxingAndUnboxing();
        ex3.question1();
        ex3.question2();
        ex3.question3();
        Exercise4_String ex4 = new Exercise4_String();
        ex4.question1();
        ex4.question2();
        ex4.question3();
        ex4.question4();
        ex4.question5();
        ex4.question6();
        ex4.question7();
        ex4.question8(groups);
        ex4.question9(groups);
        ex4.question10("String a", "String b");
        ex4.question11("acahawkjdhajkdhskjdhaksh");
        ex4.question12("abvc123123");
        ex4.question13("VTI Academy123");
        ex4.question14("VTI Academy");
        ex4.question15("THOU SHALL NOT PASS");
        ex4.question16("THOU SHALL NOT PASS",6);
        Exercise5_ObjectsMethod ex5 = new Exercise5_ObjectsMethod(departments);
        ex5.question1();
        ex5.question2();
        ex5.question3();
        ex5.question4();
        ex5.question5();
        ex5.question6();
        ex5.question7(accounts);
    }

}