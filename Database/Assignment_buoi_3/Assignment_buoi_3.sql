-- Question 1: Viết lệnh để lấy ra danh sách nhân viên và thông tin phòng ban của họ

select * from `account` acc 
left join department dep on dep.department_id = acc.department_id;

-- Question 2: Viết lệnh để lấy ra thông tin các account được tạo sau ngày 20/12/2010 

select * from `account` 
where create_date > '2010-12-20';

-- Question 3: Viết lệnh để lấy ra tất cả các developer

select * from `account` acc
left join position po on acc.position_id = po.position_id
where po.position_name = 'DEV';

-- Question 4: Viết lệnh để lấy ra danh sách các phòng ban có >3 nhân viên

select * from department de
left join (
	select department_id, count(1) as sl_nhanvien
    from `account`
    group by department_id
) acc
on acc.department_id = de.department_id
where acc.sl_nhanvien > 3;

-- Question 5: Viết lệnh để lấy ra danh sách câu hỏi được sử dụng trong đề thi nhiều
-- nhất

select * from question quest
inner join (
	select question_id from exam_question 
    group by question_id
    having count(1) = 
		(select count(1)  from exam_question
		group by question_id
        order by count(1) desc
        limit 1)) question_count
on quest.question_id = question_count.question_id;

-- Question 6: Thông kê mỗi category Question được sử dụng trong bao nhiêu Question

select cate.category_id, cate.category_name, question_count.sl_question
from category_question cate
left join (
	select count(1) as sl_question, category_id from question
	group by category_id
	) question_count
on question_count.category_id = cate.category_id;

-- Question 7: Thông kê mỗi Question được sử dụng trong bao nhiêu Exam

select quest.question_id, quest.content, question_count.sl_exam from question quest
left join ( 
	select count(1) as sl_exam, question_id from exam_question
	group by question_id) question_count
on question_count.question_id = quest.question_id;

-- Question 8: Lấy ra Question có nhiều câu trả lời nhất

select quest.*, answer_count.answer_count from question quest
inner join (
	select question_id, count(1) as answer_count from answer 
    group by question_id
    having count(1) = 
		(select count(1)  from answer
		group by question_id
        order by count(1) desc
        limit 1)) answer_count
on quest.question_id = answer_count.question_id;

-- Question 9: Thống kê số lượng account trong mỗi group

select gr.*, sl_acc.sl_account from `group` gr
left join 
	(select count(1) as sl_account, group_id from group_account
	group by account_id) sl_acc
on gr.group_id = sl_acc.group_id;

-- Question 10: Tìm chức vụ có ít người nhất

select po.*,  position_with_accounts.sl_acc from position po
left join ( -- left join to fetch the one with no account
	select position_id, count(1) as sl_acc from `account`
	group by position_id
	having count(1) <= (
		select count(1) from `account`
		group by position_id
		order by count(1) asc
		limit 1
)
) position_with_accounts
on po.position_id = position_with_accounts.position_id;

-- Question 11: Thống kê mỗi phòng ban có bao nhiêu dev, test, scrum master, PM

select
    d.department_name,

    (select COUNT(1)
     from `account` a
     left join position p on a.position_id = p.position_id
     where a.department_id = d.department_id
       and p.position_name = 'Dev') as Dev_Count,
	
    (select COUNT(1)
     from `account` a
     left join position p on a.position_id = p.position_id
     where a.department_id = d.department_id
       and p.position_name = 'Test') as Test_Count,
       
	(select COUNT(1)
     from `account` a
     left join position p on a.position_id = p.position_id
     where a.department_id = d.department_id
       and p.position_name = 'Scrum Master') as SM_Count,
	
    (select COUNT(1)
     from `account` a
     left join position p on a.position_id = p.position_id
     where a.department_id = d.department_id
       and p.position_name = 'PM') as PM_Count
    

from department d;

-- Question 12: Lấy thông tin chi tiết của câu hỏi bao gồm: thông tin cơ bản của
-- question, loại câu hỏi, ai là người tạo ra câu hỏi, câu trả lời là gì, ...

select * from question quest
left join category_question category on category.category_id = quest.category_id
left join `account` acc on acc.account_id = quest.creator_id
left join answer ans on ans.question_id = quest.question_id
left join type_question `type` on `type`.type_id = quest.type_id;

-- Question 13: Lấy ra số lượng câu hỏi của mỗi loại tự luận hay trắc nghiệm

select `type`.type_id, type_name, sl_q.sl_question from type_question `type`
left join (
	select count(1) as sl_question, type_id
    from question
    group by type_id
) sl_q on sl_q.type_id = `type`.type_id;

-- Question 14:Lấy ra group không có account nào

select * from `group` 
where group_id not in 
	(select group_id from group_account
    group by group_id);

-- Question 15: Lấy ra group không có account nà

select * from `group` 
where group_id not in 
	(select group_id from group_account
    group by group_id);

-- Question 16: Lấy ra question không có answer nào

select * from question where question_id not in
(select question_id from answer
group by question_id);