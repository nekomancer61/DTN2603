
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

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

        Position position1 = new Position(0, PositionName.DEV);
        Position position2 = new Position(1, PositionName.TEST);
        Position position3 = new Position(2, PositionName.TEST);

        Date today = new Date();
        Account account1 = new Account(0, "acc1@gmail.com", "acc1", "acc 1", department1, position1, today);
        Account account2 = new Account(1, "acc2@gmail.com", "acc2", "acc 2", department2, position2, today);
        Account account3 = new Account(2, "acc3@gmail.com", "acc3", "acc 3", department3, position3, today);

        Group group1 = new Group(0, "group 1", account1, today);
        Group group2 = new Group(1, "group 2", account2, today);
        Group group3 = new Group(3, "group 3", account3, today);

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

        // IF
        // Question 1:
        // Kiểm tra account thứ 2
        // Nếu không có phòng ban (tức là department == null) thì sẽ in ra text "Nhân
        // viên này chưa có phòng ban"
        // Nếu không thì sẽ in ra text "Phòng ban của nhân viên này là …"

        if (account2.getDepartment() == null) {
            System.out.println("Nhân viên này chưa có phòng ban");
        } else {
            System.out.println("Phòng ban của nhân viên này là …" + account2.getDepartment().getDepartmentName());
        }

        // Question 2:
        // Kiểm tra account thứ 2
        // Nếu không có group thì sẽ in ra text "Nhân viên này chưa có group"
        // Nếu có mặt trong 1 hoặc 2 group thì sẽ in ra text "Group của nhân viên này là
        // Java Fresher, C# Fresher"
        // Nếu có mặt trong 3 Group thì sẽ in ra text "Nhân viên này là người quan
        // trọng, tham gia nhiều group"
        // Nếu có mặt trong 4 group trở lên thì sẽ in ra text "Nhân viên này là người
        // hóng chuyện, tham gia tất cả các group"

        GroupAccount[] groupAccounts = new GroupAccount[] { groupAccount1, groupAccount2, groupAccount3 };

        int count = 0;
        for (GroupAccount ga : groupAccounts) {
            if (ga.getAccountId() == 1) {
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Nhân viên này chưa có group");
        } else if (count == 1 || count == 2) {
            System.out.println("Group của nhân viên này là Java Fresher, C# Fresher");
        } else if (count == 3) {
            System.out.println("Nhân viên này là người quan trọng, tham gia nhiều group");
        } else if (count >= 4) {
            System.out.println("Nhân viên này là người hóng chuyện, tham gia tất cả các group");
        }

        // Question 3:
        // Sử dụng toán tử ternary để làm Question 1
        System.out.println(account2.getDepartment() == null ? "Nhân viên này chưa có phòng ban"
                : "Phòng ban của nhân viên này là …"
                        + account2.getDepartment().getDepartmentName());

        // Question 4:
        // Sử dụng toán tử ternary để làm yêu cầu sau:
        // Kiểm tra Position của account thứ 1
        // Nếu Position = Dev thì in ra text "Đây là Developer"
        // Nếu không phải thì in ra text "Người này không phải là Developer"

        System.out.println(account1.getPosition().getPositionName() == PositionName.DEV ? "Đây là Developer"
                : "Người này không phải là Developer");

        // SWITCH CASE
        // Question 5:
        // Lấy ra số lượng account trong nhóm thứ 1 và in ra theo format sau:
        // Nếu số lượng account = 1 thì in ra "Nhóm có một thành viên"
        // Nếu số lượng account = 2 thì in ra "Nhóm có hai thành viên"
        // Nếu số lượng account = 3 thì in ra "Nhóm có ba thành viên"
        // Còn lại in ra "Nhóm có nhiều thành viên"

        count = 0;
        for (GroupAccount ga : groupAccounts) {
            if (ga.getGroupId() == 0) {
                count++;
            }
        }
        switch (count) {
            case 1:
                System.out.println("Nhóm có một thành viên");
                break;
            case 2:
                System.out.println("Nhóm có hai thành viên");
                break;
            case 3:
                System.out.println("Nhóm có ba thành viên");
                break;
            default:
                System.out.println("Nhóm có nhiều thành viên");
        }
        // Question 6:
        // Sử dụng switch case để làm lại Question 2

        count = 0;
        for (GroupAccount ga : groupAccounts) {
            if (ga.getAccountId() == 1) {
                count++;
            }
        }
        switch (count) {
            case 0:
                System.out.println("Nhân viên này chưa có group");
                break;
            case 1:
                System.out.println("Group của nhân viên này là Java Fresher, C# Fresher");
                break;
            case 2:
                System.out.println("Group của nhân viên này là Java Fresher, C# Fresher");
                break;
            case 3:
                System.out.println("Nhân viên này là người quan trọng, tham gia nhiều group");
                break;
            default:
                System.out.println("Nhân viên này là người hóng chuyện, tham gia tất cả các group");
                break;
        }

        // Question 7:
        // Sử dụng switch case để làm lại Question 4

        switch (account1.getPosition().getPositionName()) {
            case DEV:
                System.out.println("Đây là Developer");
                break;
            default:
                System.out.println("Người này không phải là Developer");
                break;
        }

        // FOREACH
        // Question 8:
        // In ra thông tin các account bao gồm: Email, FullName và tên phòng ban của họ

        Account[] accounts = new Account[] {
                account1, account2, account3
        };

        for (Account acc : accounts) {
            System.out.printf("Email: %s, Fullname: %s, Department: %s %n", acc.getEmail(), acc.getFullName(),
                    acc.getDepartment().getDepartmentName());
        }
        // Question 9:
        // In ra thông tin các phòng ban bao gồm: id và name

        Department[] departments = new Department[] {
                department1, department2, department3
        };

        for (Department department : departments) {
            System.out.printf("DepartmentId: %d, DepartmentName: %s %n", department.getDepartmentId(),
                    department.getDepartmentName());
        }

        // FOR
        // Question 10:
        // In ra thông tin các account bao gồm: Email, FullName và tên phòng ban của
        // họ theo định dạng như sau:
        // Thông tin account thứ 1 là:
        // Email: NguyenVanA@gmail.com
        // Full name: Nguyễn Văn A
        // Phòng ban: Sale
        // Thông tin account thứ 2 là:
        // Email: NguyenVanB@gmail.com
        // Full name: Nguyễn Văn B
        // Phòng ban: Marketting

        for (int i = 0; i < accounts.length; i++) {
            System.out.printf("Thông tin account thứ %d là:%n", i);
            System.out.printf("Email: %s%n", accounts[i].getEmail());
            System.out.printf("Fullname: %s%n", accounts[i].getEmail());
            System.out.printf("Department: %s%n", accounts[i].getDepartment().getDepartmentName());
        }

        // Question 11:
        // In ra thông tin các phòng ban bao gồm: id và name theo định dạng sau:
        // Thông tin department thứ 1 là:
        // Id: 1
        // Name: Sale
        // Thông tin department thứ 2 là:
        // Id: 2
        // Name: Marketing

        for (int i = 0; i < departments.length; i++) {
            System.out.printf("Thông tin account thứ %d là:%n", i);
            System.out.printf("Id: %s%n", departments[i].getDepartmentId());
            System.out.printf("Name: %s%n", departments[i].getDepartmentName());
        }

        // Question 12:
        // Chỉ in ra thông tin 2 department đầu tiên theo định dạng như Question 10

        for (int i = 0; i < 2; i++) {
            System.out.printf("Thông tin account thứ %d là:%n", i);
            System.out.printf("Id: %s%n", departments[i].getDepartmentId());
            System.out.printf("Name: %s%n", departments[i].getDepartmentName());
        }

        // Question 13:
        // In ra thông tin tất cả các account ngoại trừ account thứ 2

        for (int i = 0; i < accounts.length; i++) {
            if (i == 1)
                continue;
            System.out.printf("Thông tin account thứ %d là:%n", i);
            System.out.printf("Email: %s%n", accounts[i].getEmail());
            System.out.printf("Fullname: %s%n", accounts[i].getEmail());
            System.out.printf("Department: %s%n", accounts[i].getDepartment().getDepartmentName());
        }

        // Question 14:
        // In ra thông tin tất cả các account có id < 4

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getAccountId() >= 4)
                continue;
            System.out.printf("Thông tin account thứ %d là:%n", i);
            System.out.printf("Email: %s%n", accounts[i].getEmail());
            System.out.printf("Fullname: %s%n", accounts[i].getEmail());
            System.out.printf("Department: %s%n", accounts[i].getDepartment().getDepartmentName());
        }

        // Question 15:
        // In ra các số chẵn nhỏ hơn hoặc bằng 20

        for (int i = 0; i <= 20; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }

        // WHILE
        // Question 16:
        // Làm lại các Question ở phần FOR bằng cách sử dụng WHILE kết hợp với
        // lệnh break, continue

        int j = 0;
        while (j < departments.length) {
            System.out.printf("Thông tin account thứ %d là:%n", j);
            System.out.printf("Id: %s%n", departments[j].getDepartmentId());
            System.out.printf("Name: %s%n", departments[j].getDepartmentName());
            j++;
        }

        int k = 0;
        while (k < 2) {
            System.out.printf("Thông tin account thứ %d là:%n", k);
            System.out.printf("Id: %s%n", departments[k].getDepartmentId());
            System.out.printf("Name: %s%n", departments[k].getDepartmentName());
            k++;
        }

        int l = 0;
        while (l < accounts.length) {
            if (l == 1) {
                l++;
                continue;
            }
            System.out.printf("Thông tin account thứ %d là:%n", l);
            System.out.printf("Email: %s%n", accounts[l].getEmail());
            System.out.printf("Fullname: %s%n", accounts[l].getEmail());
            System.out.printf("Department: %s%n", accounts[l].getDepartment().getDepartmentName());
            l++;
        }

        int m = 0;
        while (m < accounts.length) {
            if (accounts[m].getAccountId() >= 4) {
                m++;
                continue;
            }
            System.out.printf("Thông tin account thứ %d là:%n", m);
            System.out.printf("Email: %s%n", accounts[m].getEmail());
            System.out.printf("Fullname: %s%n", accounts[m].getEmail());
            System.out.printf("Department: %s%n", accounts[m].getDepartment().getDepartmentName());
            m++;
        }

        int n = 0;
        while (n <= 20) {
            if (n % 2 == 0) {
                System.out.println(n);
            }
            n++;
        }

        // DO-WHILE
        // Question 17:
        // Làm lại các Question ở phần FOR bằng cách sử dụng DO-WHILE kết hợp với
        // lệnh break, continue
        int i = 0;
        do {
            System.out.printf("Thông tin account thứ %d là:%n", i);
            System.out.printf("Email: %s%n", accounts[i].getEmail());
            System.out.printf("Fullname: %s%n", accounts[i].getEmail());
            System.out.printf("Department: %s%n", accounts[i].getDepartment().getDepartmentName());
            i++;
        } while (i < accounts.length);

        j = 0;
        do {
            System.out.printf("Thông tin account thứ %d là:%n", j);
            System.out.printf("Id: %s%n", departments[j].getDepartmentId());
            System.out.printf("Name: %s%n", departments[j].getDepartmentName());
            j++;
        } while (j < departments.length);

        k = 0;
        do {
            System.out.printf("Thông tin account thứ %d là:%n", k);
            System.out.printf("Id: %s%n", departments[k].getDepartmentId());
            System.out.printf("Name: %s%n", departments[k].getDepartmentName());
            k++;
        } while (k < 2);

        l = 0;
        do {
            if (l == 1) {
                l++;
                continue;
            }
            System.out.printf("Thông tin account thứ %d là:%n", l);
            System.out.printf("Email: %s%n", accounts[l].getEmail());
            System.out.printf("Fullname: %s%n", accounts[l].getEmail());
            System.out.printf("Department: %s%n", accounts[l].getDepartment().getDepartmentName());
            l++;
        } while (l < accounts.length);

        m = 0;
        do {
            if (accounts[m].getAccountId() >= 4) {
                m++;
                continue;
            }
            System.out.printf("Thông tin account thứ %d là:%n", m);
            System.out.printf("Email: %s%n", accounts[m].getEmail());
            System.out.printf("Fullname: %s%n", accounts[m].getEmail());
            System.out.printf("Department: %s%n", accounts[m].getDepartment().getDepartmentName());
            m++;
        } while (m < accounts.length);

        n = 0;
        do {
            if (n % 2 == 0) {
                System.out.println(n);
            }
            n++;
        } while (n <= 20);

        // Exercise 2: System out printf
        // Question 1:
        // Khai báo 1 số nguyên = 5 và sử dụng lệnh System out printf để in ra số
        // nguyên đó

        int something = 5;
        System.out.printf("%d%n", something);

        // Question 2:
        // Khai báo 1 số nguyên = 100 000 000 và sử dụng lệnh System out printf để in
        // ra số nguyên đó thành định dạng như sau: 100,000,000

        int q2 = 1000000000;
        System.out.printf("%,d%n", q2);

        // Question 3:
        // Khai báo 1 số thực = 5,567098 và sử dụng lệnh System out printf để in ra số
        // thực đó chỉ bao gồm 4 số đằng sau

        double q3 = 5.567098;
        System.out.printf("%.4f%n", q3);

        // Question 4:
        // Khai báo Họ và tên của 1 học sinh và in ra họ và tên học sinh đó theo định
        // dạng như sau:
        // Họ và tên: "Nguyễn Văn A" thì sẽ in ra trên console như sau:
        // Tên tôi là "Nguyễn Văn A" và tôi đang độc thân.

        String q4 = "Nguyễn Văn A";
        System.out.printf("Tên tôi là \"%s\" và tôi đang độc thân.%n", q4);
        // Question 5:
        // Lấy thời gian bây giờ và in ra theo định dạng sau:
        // 24/04/2020 11h:16p:20s
        LocalDateTime now = LocalDateTime.now();

        System.out.printf("%02d/%02d/%04d %02dh:%02dp:%02ds%n",
                now.getDayOfMonth(),
                now.getMonthValue(),
                now.getYear(),
                now.getHour(),
                now.getMinute(),
                now.getSecond());
        // Question 6:
        // In ra thông tin account (như Question 8 phần FOREACH) theo định dạng
        // table (giống trong Database)

        System.out.printf("|%20s|%20s|%20s|%n", "Email", "Department", "Fullname");
        for (Account acc : accounts) {
            System.out.printf("|%20s|%20s|%20s|%n", acc.getEmail(), acc.getFullName(),
                    acc.getDepartment().getDepartmentName());
        }

        // Exercise 3: Date Format
        // Question 1:
        // In ra thông tin Exam thứ 1 và property create date sẽ được format theo định
        // dạng vietnamese

        DateTimeFormatter vietnameseFormatter = DateTimeFormatter
                .ofPattern("'Ngày' dd 'tháng' MM 'năm' yyyy", new Locale("vi", "VN"));

        System.out.println("Exam ID: " + exam1.getExamId());
        System.out.println("Exam Name: " + exam1.getTitle());
        System.out.println(
                "Create Date: " + LocalDateTime.parse(exam1.getCreateDate().toString()).format(vietnameseFormatter));

        // Question 2:
        // In ra thông tin: Exam đã tạo ngày nào theo định dạng
        // Năm – tháng – ngày – giờ – phút – giây

        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy - MM - dd - HH - mm - ss");

        System.out.println("Exam: " + exam1.getTitle());
        System.out.println("Created at: " + LocalDateTime.parse(exam1.getCreateDate().toString()).format(formatter));

        // Question 3:
        // Chỉ in ra năm của create date property trong Question 2

        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");

        System.out.println("Exam: " + exam1.getTitle());
        System.out.println("Year: " + LocalDateTime.parse(exam1.getCreateDate().toString()).format(yearFormatter));

        // Question 4:
        // Chỉ in ra tháng và năm của create date property trong Question 2

        DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MM/yyyy");

        System.out.println("Exam: " + exam1.getTitle());
        System.out.println("Year: " + LocalDateTime.parse(exam1.getCreateDate().toString()).format(monthYearFormatter));

        // Question 5:
        // Chỉ in ra "MM-DD" của create date trong Question 2
        DateTimeFormatter monDayFormatter = DateTimeFormatter.ofPattern("MM-DD");

        System.out.println("Exam: " + exam1.getTitle());
        System.out.println("Year: " + LocalDateTime.parse(exam1.getCreateDate().toString()).format(monDayFormatter));

        // Exercise 4: Random Number

        // Question 1:
        // In ngẫu nhiên ra 1 số nguyên

        Random random = new Random();
        System.out.println(random.nextInt());

        // Question 2:
        // In ngẫu nhiên ra 1 số thực

        System.out.println(random.nextDouble());

        // Question 3:
        // Khai báo 1 array bao gồm các tên của các bạn trong lớp, sau đó in ngẫu nhiên
        // ra tên của 1 bạn

        String[] names = new String[] { "A", "B", "C", "D", "E" };
        System.out.println(names[random.nextInt(names.length)]);

        // Question 4:
        // Lấy ngẫu nhiên 1 ngày trong khoảng thời gian 24-07-1995 tới ngày 20-12- 1995

        LocalDate start1 = LocalDate.of(1995, 7, 24);
        LocalDate end1 = LocalDate.of(1995, 12, 20);

        long daysBetween = end1.toEpochDay() - start1.toEpochDay();
        long randomDay1 = start1.toEpochDay() + random.nextInt((int) daysBetween + 1);
        System.out.println("Random date: " + LocalDate.ofEpochDay(randomDay1));
        System.out.println();

        // Question 5:
        // Lấy ngẫu nhiên 1 ngày trong khoảng thời gian 1 năm trở lại đây

        System.out.println("Question 5:");
        LocalDate e4today = LocalDate.now();
        LocalDate oneYearAgo = e4today.minusYears(1);

        long daysBetween2 = e4today.toEpochDay() - oneYearAgo.toEpochDay();
        long randomDay2 = oneYearAgo.toEpochDay() + random.nextInt((int) daysBetween2 + 1);
        System.out.println("Random date: " + LocalDate.ofEpochDay(randomDay2));
        System.out.println();

        // Question 6:
        // Lấy ngẫu nhiên 1 ngày trong quá khứ.

        System.out.println("Question 6:");
        LocalDate past = LocalDate.of(1900, 1, 1);

        long daysBetween3 = e4today.toEpochDay() - past.toEpochDay();
        long randomDay3 = past.toEpochDay() + random.nextInt((int) daysBetween3 + 1);
        System.out.println("Random date: " + LocalDate.ofEpochDay(randomDay3));

        // Question 7:
        // Lấy ngẫu nhiên 1 số có 3 chữ số.

        System.out.println(random.nextInt(100, 999));

        // Exercise 5: Input from console

        Scanner sc = new Scanner(System.in);

        // Question 1:
        // Viết lệnh cho phép người dùng nhập 3 số nguyên vào chương trình.

        int e5count = 0;
        int[] e5array = new int[3];
        while (e5count < 3) {
            System.out.println("Number: ");
            if (sc.hasNextInt()) {
                e5array[e5count] = sc.nextInt();
                sc.nextLine();
                e5count++;
            } else {
                continue;
            }
        }

        // Question 2:
        // Viết lệnh cho phép người dùng nhập 2 số thực vào chương trình.

        int e6count = 0;
        double[] e6array = new double[2];
        while (e6count < 2) {
            System.out.println("Number: ");
            if (sc.hasNextDouble()) {
                e6array[e6count] = sc.nextInt();
                sc.nextLine();
                e6count++;
            } else {
                continue;
            }
        }

        // Question 3:
        // Viết lệnh cho phép người dùng nhập họ và tên.

        System.out.println("Ho va ten: ");
        String fullName = sc.nextLine();
        System.out.println(fullName);

        // Question 4:
        // Viết lệnh cho phép người dùng nhập vào ngày sinh nhật của họ.

        System.out.println("Nhập ngày sinh nhật của bạn (dd/MM/yyyy):");
        String birthdayStr = sc.nextLine();

        // Parse the input
        String[] parts = birthdayStr.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        LocalDate birthday = LocalDate.of(year, month, day);
        System.out.println("\nNgày sinh nhật của bạn là: " + birthday);

        // Question 7:
        // Nhập số chẵn từ console

        int q7 = 1;
        while (q7 % 2 != 0) {
            System.out.println("Input one even num: ");
            if (sc.hasNextInt()) {
                q7 = sc.nextInt();
                if (q7 % 2 != 0)
                    continue;
            }

        }
        sc.close();
    }
    // Question 5:
    // Viết lệnh cho phép người dùng tạo account (viết thành method)
    // Đối với property Position, Người dùng nhập vào 1 2 3 4 5 và vào
    // chương trình sẽ chuyển thành Position.Dev, Position.Test,
    // Position.ScrumMaster, Position.PM.

    public static Account createAccount() {
        Account account = new Account();
        Scanner scanner = new Scanner(System.in);
        System.out.println("======= TẠO ACCOUNT =======");

        // Input Account ID
        System.out.print("Nhập Account ID: ");
        account.setAccountId(scanner.nextInt());
        scanner.nextLine(); // consume newline

        // Input Email
        System.out.print("Nhập Email: ");
        account.setEmail(scanner.nextLine());

        // Input Username
        System.out.print("Nhập Username: ");
        account.setUserName(scanner.nextLine());

        // Input Full Name
        System.out.print("Nhập Full Name: ");
        account.setFullName(scanner.nextLine());

        // Create Department
        System.out.print("Nhập Department ID: ");
        int deptId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nhập Department Name: ");
        String deptName = scanner.nextLine();
        Department dept = new Department(deptId, deptName);
        account.setDepartment(dept);

        // Input Position (1-5)
        System.out.println("Chọn Position (1-5):");
        System.out.println("1. Dev");
        System.out.println("2. Test");
        System.out.println("3. ScrumMaster");
        System.out.println("4. PM");
        System.out.println("5. Other");
        System.out.print("Nhập lựa chọn: ");
        int positionChoice = scanner.nextInt();
        scanner.nextLine();

        Position position = null;
        switch (positionChoice) {
            case 1:
                position = new Position(0, PositionName.DEV);
                break;
            case 2:
                position = new Position(0, PositionName.TEST);
                break;
            case 3:
                position = new Position(0, PositionName.SCRUM_MASTER);
                break;
            case 4:
                position = new Position(0, PositionName.PM);
                break;
            default:
                System.out.println("Position không hợp lệ, mặc định là Dev");
                position = new Position(0, PositionName.DEV);
                break;
        }
        account.setPosition(position);

        // Set Create Date
        Date today = new Date();
        account.setCreateDate(today);

        System.out.println("Tạo account thành công!");
        scanner.close();
        return account;
    }

    // Question 6:
    // Viết lệnh cho phép người dùng tạo department (viết thành method)

    public static Department createDepartment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("======= TẠO DEPARTMENT =======");

        System.out.print("Nhập Department ID: ");
        int deptId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nhập Department Name: ");
        String deptName = scanner.nextLine();

        Department dept = new Department(deptId, deptName);
        System.out.println("Tạo department thành công!");
        scanner.close();
        return dept;
    }

    // Question 8:
    // Viết chương trình thực hiện theo flow sau:
    // Bước 1:
    // Chương trình in ra text "mời bạn nhập vào chức năng muốn sử dụng"
    // Bước 2:
    // Nếu người dùng nhập vào 1 thì sẽ thực hiện tạo account
    // Nếu người dùng nhập vào 2 thì sẽ thực hiện chức năng tạo
    // department
    // Nếu người dùng nhập vào số khác thì in ra text "Mời bạn nhập
    // lại" và quay trở lại bước 1

    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n======= CHƯƠNG TRÌNH QUẢN LÝ =======");
            System.out.println("Mời bạn nhập vào chức năng muốn sử dụng:");
            System.out.println("1. Tạo Account");
            System.out.println("2. Tạo Department");
            System.out.println("3. Thêm Group vào Account");
            System.out.println("4. Thêm Account vào Group ngẫu nhiên");
            System.out.println("5. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    createDepartment();
                    break;
                case 3:
                    //addGroupToAccount();
                    break;
                case 4:
                    // addAccountToRandomGroup();
                    break;
                case 5:
                    System.out.println("Kết thúc chương trình!");
                    return;
                default:
                    System.out.println("Mời bạn nhập lại!");
                    break;
            }
            // Question 10: Ask to continue
            System.out.print("\nBạn có muốn thực hiện chức năng khác không? (Có/Không): ");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("Không")) {
                System.out.println("Kết thúc chương trình!");
                return;
            }
            scanner.close();
        }

    }
    // Question 9:
    // Viết method cho phép người dùng thêm group vào account theo flow sau:
    // Bước 1: In ra tên các usernames của user cho người dùng xem
    // Bước 2: Yêu cầu người dùng nhập vào username của account
    // Bước 3: In ra tên các group cho người dùng xem
    // Bước 4: Yêu cầu người dùng nhập vào tên của group
    // Bước 5: Dựa vào username và tên của group người dùng vừa chọn, hãy thêm
    // account vào group đó .

    public static void addGroupToAccount(List<Account> accounts, List<Group> groups, List<GroupAccount> groupAccounts) {
        System.out.println("======= THÊM GROUP VÀO ACCOUNT =======");
        Scanner scanner = new Scanner(System.in);
        // Step 1: Show usernames
        System.out.println("\nDanh sách Username:");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println((i + 1) + ". " + accounts.get(i).getUserName());
        }

        // Step 2: Ask for username
        System.out.print("\nNhập username: ");
        String username = scanner.nextLine();

        // Find account
        Account selectedAccount = null;
        for (Account acc : accounts) {
            if (acc.getUserName().equals(username)) {
                selectedAccount = acc;
                break;
            }
        }

        if (selectedAccount == null) {
            System.out.println("Không tìm thấy account!");
            scanner.close();
            return;
        }

        // Step 3: Show groups
        System.out.println("\nDanh sách Group:");
        for (int i = 0; i < groups.size(); i++) {
            System.out.println((i + 1) + ". " + groups.get(i).getGroupName());
        }

        // Step 4: Ask for group name
        System.out.print("\nNhập tên group: ");
        String groupName = scanner.nextLine();

        // Find group
        Group selectedGroup = null;
        for (Group g : groups) {
            if (g.getGroupName().equals(groupName)) {
                selectedGroup = g;
                break;
            }
        }

        if (selectedGroup == null) {
            System.out.println("Không tìm thấy group!");
            scanner.close();
            return;
        }

        // Step 5: Add account to group
        GroupAccount ga = new GroupAccount();
        ga.setGroupId(selectedGroup.getGroupId());
        ga.setAccountId(selectedAccount.getAccountId());
        Date today = new Date();
        ga.setCreateDate(today);
        groupAccounts.add(ga);

        System.out.println("\nĐã thêm " + username + " vào group " + groupName + " thành công!");
        scanner.close();
    }

    // Question 10: Tiếp tục Question 8 và Question 9
    // Bổ sung thêm vào bước 2 của Question 8 như sau:
    // Nếu người dùng nhập vào 3 thì sẽ thực hiện chức năng thêm group vào
    // account
    // Bổ sung thêm Bước 3 của Question 8 như sau:
    // Sau khi người dùng thực hiện xong chức năng ở bước 2 thì in ra dòng text để
    // hỏi người dùng "Bạn có muốn thực hiện chức năng khác không?". Nếu người dùng
    // chọn "Có" thì quay lại bước 1, nếu người dùng chọn "Không" thì kết thúc
    // chương trình (sử dụng lệnh return để kết thúc chương trình).

    // Exercise 6: Method
    // Question 1:
    // Tạo method để in ra các số chẵn nguyên dương nhỏ hơn 10

    private void printEvenPositiveDecLessThan10() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }

    // Question 2:
    // Tạo method để in thông tin các account

    private void printAccountsInfo(Account[] accounts) {
        for (Account account : accounts) {
            System.out.println(account.toString());
        }
    }

    // Question 3:
    // Tạo method để in ra các số nguyên dương nhỏ hơn 10

    private void printPositiveDecLessThan10() {
        for (int i = 1; i < 10; i++) {
            System.out.println(i);
        }
    }

}