-- 1. Thực hiện tạo DB Testing System và các bảng dữ liệu có ràng buộc và kiểu dữ liệu tương ứng.

drop database if exists Testing_System;
create database if not exists Testing_System;

use Testing_System;

-- Table 1:Department
-- ∙ DepartmentID: định danh của phòng ban (auto increment)
-- ∙ DepartmentName: tên đầy đủ của phòng ban (VD: sale, marketing, …)

drop table if exists department;
create table if not exists department (
	department_id int primary key auto_increment,
    department_name nvarchar(30) not null
);
	
-- Table 2: Position
-- ∙ PositionID: định danh của chức vụ (auto increment)
-- ∙ PositionName: tên chức vụ (Dev, Test, Scrum Master, PM)

drop table if exists position;
create table if not exists position (
	position_id int primary key auto_increment,
    position_name enum ('Dev','Test','Scrum Master','PM','BA') not null
);
	

-- Table 3: Account
-- ∙ AccountID: định danh của User (auto increment)
-- ∙ Email:
-- ∙ Username:
-- ∙ FullName:
-- ∙ DepartmentID: phòng ban của user trong hệ thống
-- ∙ PositionID: chức vụ của User
-- ∙ CreateDate: ngày tạo tài khoản

drop table if exists `account`;
create table if not exists `account` (
	account_id int primary key auto_increment,
    email nvarchar(30) not null,
    username nvarchar(30) not null,
    fullname nvarchar(30) not null,
    department_id int not null,
    position_id int not null,
	foreign key (department_id) references department(department_id),
	foreign key (position_id) references `position`(position_id),
    create_date date not null default now()
);

-- Table 4: Group
-- ∙ GroupID: định danh của nhóm (auto increment)
-- ∙ GroupName: tên nhóm
-- ∙ CreatorID: id của người tạo group
-- ∙ CreateDate: ngày tạo group

drop table if exists `group`;
create table if not exists `group` (
	group_id int primary key auto_increment,
    group_name nvarchar(30) not null,
    creator_id int not null,
    foreign key (creator_id) references `account` (account_id),
    create_date date not null default now()    
);

-- Table 5: GroupAccount
-- ∙ GroupID: định danh của nhóm
-- ∙ AccountID: định danh của User
-- ∙ JoinDate: Ngày user tham gia vào nhóm

drop table if exists group_account;
create table if not exists group_account (
	group_id int not null,
    account_id int not null,
	foreign key (group_id) references `group` (group_id),
    foreign key (account_id) references `account` (account_id),
    join_date date not null default now()
);

-- Table 6: TypeQuestion
-- ∙ TypeID: định danh của loại câu hỏi (auto increment)
-- ∙ TypeName: tên của loại câu hỏi (Essay, Multiple-Choice)

drop table if exists type_question;
create table if not exists type_question (
	type_id int primary key auto_increment,
    type_name enum ('Essay','Multiple Choice')
);

-- Table 7: CategoryQuestion
-- ∙ CategoryID: định danh của chủ đề câu hỏi (auto increment)
-- ∙ CategoryName: tên của chủ đề câu hỏi (Java, .NET, SQL, Postman, Ruby,…)

drop table if exists category_question;
create table if not exists category_question (
	category_id int primary key auto_increment,
    category_name nvarchar(30) not null
);

-- Table 8: Question
-- ∙ QuestionID: định danh của câu hỏi (auto increment)
-- ∙ Content: nội dung của câu hỏi
-- ∙ CategoryID: định danh của chủ đề câu hỏi
-- ∙ TypeID: định danh của loại câu hỏi
-- ∙ CreatorID: id của người tạo câu hỏi
-- ∙ CreateDate: ngày tạo câu hỏi

drop table if exists question;
create table if not exists question (
	question_id int primary key auto_increment,
    content nvarchar(255) not null,
    category_id int not null,
    type_id int not null,
    creator_id int not null,
    foreign key (category_id) references category_question (category_id),
    foreign key (type_id) references type_question (type_id),
    foreign key (creator_id) references `account` (account_id),
    create_date date not null default now()
);

-- Table 9: Answer
-- ∙ AnswerID: định danh của câu trả lời (auto increment)
-- ∙ Content: nội dung của câu trả lời
-- ∙ QuestionID: định danh của câu hỏi
-- ∙ isCorrect: câu trả lời này đúng hay sai

drop table if exists answer;
create table if not exists answer (
	answer_id int primary key auto_increment,
    content nvarchar(255) not null,
    question_id int not null,
    foreign key (question_id) references question (question_id),
    is_correct boolean not null
);

-- Table 10: Exam
-- ∙ ExamID: định danh của đề thi (auto increment)
-- ∙ Code: mã đề thi
-- ∙ Title: tiêu đề của đề thi
-- ∙ CategoryID: định danh của chủ đề thi
-- ∙ Duration: thời gian thi
-- ∙ CreatorID: id của người tạo đề thi
-- ∙ CreateDate: ngày tạo đề thi

drop table if exists exam;
create table if not exists exam (
	exam_id int primary key auto_increment,
    `code` nvarchar(30) not null,
    title nvarchar(50) not null,
    category_id int not null,
    foreign key (category_id) references category_question (category_id),
    duration int not null,
    creator_id int not null,
    foreign key (creator_id) references `account` (account_id),
    create_date date not null default now()
);

-- Table 11: ExamQuestion
-- ∙ ExamID: định danh của đề thi
-- ∙ QuestionID: định danh của câu hỏi

drop table if exists exam_question;
create table if not exists exam_question (
	exam_id int not null,
	foreign key (exam_id) references exam (exam_id),
	question_id int not null,
    foreign key (question_id) references question (question_id)
);

-- Insert data vào 11 table, mỗi table có ít nhất 5 records.
insert into department (department_id, department_name)
values 	(1, N'Bảo vệ'),
		(2, N'Giám đốc'),
		(3, N'CSKH'),
        (4, N'Nhân sự'),
        (5, N'DEV');
        
insert into position (position_id, position_name)
values 	(1, 'Dev'),
		(2, 'Test'),
		(3, 'Scrum Master'),
        (4, 'BA'),
        (5, 'PM');
        
insert into `account` (account_id, email, username, fullname, department_id, position_id, create_date)
values 	(1, 'dev@dev.abc.com',		'dev1',		'Nguyen Van Dev',	1,	1, '2001-01-01'),
		(2, 'test@test.abc.com',	'test1',	'Tran Thi Test',	2,	2, '2001-01-01'),
		(3, 'sm@sm.abc.com',		'sm1', 		'Le Thi SM',		3,	3, '2001-01-01'),
        (4, 'ba@ba.abc.com',		'ba1',		'Nguyen Luu Ba',	4,	4, '2001-01-01'),
        (5, 'pm@pm.abc.com',		'pm1',		'Hoang Kieu PM',	5,	5, '2001-01-01');        

insert into `group` (group_id, group_name, creator_id,create_date)
values 	(1,	'gr1',	1,	'2001-01-01'),
		(2, 'gr2',	1,	'2001-01-01'),
		(3,	'gr3',	1,	'2001-01-01'),
        (4, 'gr4',	1,	'2001-01-01'),
        (5, 'gr5',	1,	'2001-01-01');
        
insert into group_account (group_id, account_id)
values 	(1,1),
		(2,2),
		(3,3),
        (4,4),
        (5,5);
     
insert into type_question (type_id, type_name)
values 	(1,'Multiple Choice'),
		(2, 'Essay');     
        
insert into category_question (category_id, category_name)
values 	(1, 'Math'),
		(2, 'Advance Math'),
		(3, 'Ultra Math'),
        (4, 'Pro Math'),
        (5, '99.6% pure Math');        
        
insert into question (question_id, content,category_id, type_id,creator_id)
values 	(1, 'Do you like math?',		1,	1,	1),
		(2,	'Do you love math?',		2,	2,	2),
		(3,	'Will you live for math?',	3,	1,	3),
        (4,	'Will you die for math?',	4,	1,	4),
        (5, 'Will you kill for math?',	5,	1,	5);
       
insert into answer (answer_id, content, question_id, is_correct)
values 	(1,	'yes',	1,	1),
		(2,	'no',	1,	1),
		(3,	'Maybe',	2,	1),
        (4,	'Ig', 	3, 1),
        (5, 'Hell nah', 4,1);
        
        
insert into exam (exam_id, `code`, title, category_id, duration, creator_id)
values 	(1,	'1',	'ex1',	1,	90,	1),
		(2,	'2',	'ex2',	2,	90,	2),
		(3,	'3',	'ex3',	3,	90,	3),
        (4,	'4',	'ex4',	4,	70,	4),
        (5,	'5',	'ex5',	5,	80,	5);
        
insert into exam_question (exam_id, question_id)
values 	(1,1),
		(2,2),
		(3,3),
        (4,4),
        (5,5);        