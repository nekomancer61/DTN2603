use Testing_System;
-- Question 1: Thêm ít nhất 10 record vào mỗi table

INSERT INTO department (department_id, department_name)
VALUES
(6, N'Marketing'),
(7, N'Sales'),
(8, N'Finance'),
(9, N'IT'),
(10, N'Support'),
(11, N'R&D'),
(12, N'QA'),
(13, N'Legal'),
(14, N'Accounting'),
(15, N'Operations');

INSERT INTO `account`
(account_id,email,username,fullname,department_id,position_id,create_date)
VALUES
(6,'user6@test.com','user6','User Six',6,1,'2002-01-01'),
(7,'user7@test.com','user7','User Seven',7,2,'2002-01-02'),
(8,'user8@test.com','user8','User Eight',8,3,'2002-01-03'),
(9,'user9@test.com','user9','User Nine',9,4,'2002-01-04'),
(10,'user10@test.com','user10','User Ten',10,5,'2002-01-05'),
(11,'user11@test.com','user11','User Eleven',11,1,'2002-01-06'),
(12,'user12@test.com','user12','User Twelve',12,2,'2002-01-07'),
(13,'user13@test.com','user13','User Thirteen',13,3,'2002-01-08'),
(14,'user14@test.com','user14','User Fourteen',14,4,'2002-01-09'),
(15,'user15@test.com','user15','User Fifteen',15,5,'2002-01-10');


INSERT INTO `group`
(group_id,group_name,creator_id,create_date)
VALUES
(6,'gr6',6,'2002-02-01'),
(7,'gr7',7,'2002-02-02'),
(8,'gr8',8,'2002-02-03'),
(9,'gr9',9,'2002-02-04'),
(10,'gr10',10,'2002-02-05'),
(11,'gr11',11,'2002-02-06'),
(12,'gr12',12,'2002-02-07'),
(13,'gr13',13,'2002-02-08'),
(14,'gr14',14,'2002-02-09'),
(15,'gr15',15,'2002-02-10');

INSERT INTO group_account
(group_id,account_id,join_date)
VALUES
(6,6,'2002-02-01'),
(7,7,'2002-02-02'),
(8,8,'2002-02-03'),
(9,9,'2002-02-04'),
(10,10,'2002-02-05'),
(11,11,'2002-02-06'),
(12,12,'2002-02-07'),
(13,13,'2002-02-08'),
(14,14,'2002-02-09'),
(15,15,'2002-02-10');

INSERT INTO category_question
(category_id,category_name)
VALUES
(6,'Java'),
(7,'C#'),
(8,'Python'),
(9,'SQL'),
(10,'Networking'),
(11,'Linux'),
(12,'Docker'),
(13,'Cloud'),
(14,'Security'),
(15,'AI');

INSERT INTO question
(question_id,content,category_id,type_id,creator_id)
VALUES
(6,'What is Java?',6,1,6),
(7,'Explain encapsulation.',7,1,7),
(8,'What is Python?',8,1,8),
(9,'Define SQL JOIN.',9,1,9),
(10,'What is TCP/IP?',10,1,10),
(11,'Explain Linux permissions.',11,1,11),
(12,'What is Docker?',12,1,12),
(13,'What is cloud computing?',13,1,13),
(14,'Explain encryption.',14,1,14),
(15,'What is AI?',15,1,15);

INSERT INTO answer
(answer_id,content,question_id,is_correct)
VALUES
(6,'Java is an OOP language.',6,1),
(7,'Encapsulation hides data.',7,1),
(8,'Python is interpreted.',8,1),
(9,'JOIN combines tables.',9,1),
(10,'TCP/IP is a network protocol suite.',10,1),
(11,'chmod changes permissions.',11,1),
(12,'Docker uses containers.',12,1),
(13,'Cloud provides on-demand resources.',13,1),
(14,'Encryption protects data.',14,1),
(15,'AI simulates intelligence.',15,1);

INSERT INTO exam
(exam_id,`code`,title,category_id,duration,creator_id)
VALUES
(6,'EX006','Java Exam',6,60,6),
(7,'EX007','C# Exam',7,60,7),
(8,'EX008','Python Exam',8,60,8),
(9,'EX009','SQL Exam',9,90,9),
(10,'EX010','Networking Exam',10,90,10),
(11,'EX011','Linux Exam',11,60,11),
(12,'EX012','Docker Exam',12,60,12),
(13,'EX013','Cloud Exam',13,90,13),
(14,'EX014','Security Exam',14,120,14),
(15,'EX015','AI Exam',15,120,15);

INSERT INTO exam_question
(exam_id,question_id)
VALUES
(6,6),
(7,7),
(8,8),
(9,9),
(10,10),
(11,11),
(12,12),
(13,13),
(14,14),
(15,15);

-- Question 2: lấy ra tất cả các phòng ban

select * from department;

-- Question 3: lấy ra id của phòng ban "Sale"

select department_id from department
where department_name = 'Sale' 

-- Question 4: lấy ra thông tin account có full name dài nhất

select * from `account`
order by char_length(fullname) desc
limit 1;

-- Question 5: Lấy ra thông tin account có full name dài nhất và thuộc phòng ban có id= 3

select * from `account`
where department_id = 3
order by char_length(fullname) desc
limit 1;

-- Question 6: Lấy ra tên group đã tham gia trước ngày 20/12/2019

select group_name from `group`
where create_date < '2019-12-20';

-- Question 7: Lấy ra ID của question có >= 4 câu trả lời

select question_id from answer
group by question_id 
having count(1) >= 4;

-- Question 8: Lấy ra các mã đề thi có thời gian thi >= 60 phút và được tạo trước ngày
-- 20/12/2019

select `code` from exam 
where duration >= 60 and create_date < '2019-12-20';

-- Question 9: Lấy ra 5 group được tạo gần đây nhất

select * from `group`
order by create_date desc 
limit 5;

-- Question 10: Đếm số nhân viên thuộc department có id = 2

select count(1) from account 
where department_id = 2;

-- Question 11: Lấy ra nhân viên có tên bắt đầu bằng chữ "D" và kết thúc bằng chữ "o"

select * from `account`
where fullname like 'D%' and fullname like '%o';
