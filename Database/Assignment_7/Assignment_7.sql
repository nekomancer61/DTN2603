delimiter \(^.^)/

-- Question 1: Tạo trigger không cho phép người dùng nhập vào Group có ngày tạo
-- trước 1 năm trước

create trigger check_group_create
before insert on `group`
for each row
begin
	if new.create_date < date_sub(curdate() - interval 1 year) then
		signal sqlstate '45000'
        set message_text = 'Group < 1 year old';
	end if;
end \(^.^)/

-- Question 2: Tạo trigger Không cho phép người dùng thêm bất kỳ user nào vào
-- department "Sale" nữa, khi thêm thì hiện ra thông báo "Department
-- "Sale" cannot add more user"

create trigger prevent_insert_account_into_sale_department
before insert on `account`
for each row
begin
	if exists (
		select 1 from department
        where department_id = new.department_id
        and department_name = 'Sale'
    ) then
		signal sqlstate '45000'
        set message_text = 'Department "Sale" cannot have more account';
    end if;
end \(^.^)/

create trigger prevent_update_account_into_sale_department
before update on `account`
for each row
begin
	if exists (
		select 1 from department
        where department_id = new.department_id
        and department_name = 'Sale'
    ) then
		signal sqlstate '45000'
        set message_text = 'Department "Sale" cannot have more account';
	end if;
end \(^.^)/

-- Question 3: Cấu hình 1 group có nhiều nhất là 5 user

create trigger check_if_insert_group_has_more_than_5_user
before insert on `group_account`
for each row
begin
	declare acc_count INT;
    select count(1) into acc_count from group_account 
	group by group_id
    having group_id = new.group_id;
	if (
		acc_count >= 5 
	) then
		signal sqlstate '45000'
        set message_text = 'Group already have 5 or more users';
    end if;
end \(^.^)/

create trigger check_if_update_group_has_more_than_5_user
before update on `group_account`
for each row
begin
	declare acc_count INT;
    select count(1) into acc_count from group_account 
	group by group_id
    having group_id = new.group_id;
	if (
		acc_count >= 5 
	) then
		signal sqlstate '45000'
        set message_text = 'Group already have 5 or more users';
    end if;
end \(^.^)/

-- Question 4: Cấu hình 1 bài thi có nhiều nhất là 10 Question

create trigger check_if_insert_exam_has_more_than_5_question
before insert on `exam_question`
for each row
begin
	declare q_count INT;
    select count(1) into q_count from exam_question 
	group by exam_id
    having exam_id = new.exam_id;
	if (
		q_count >= 10 
	) then
		signal sqlstate '45000'
        set message_text = 'Exam already has 10 or more questions';
    end if;
end \(^.^)/

create trigger check_if_update_exam_has_more_than_10_question
before update on `exam_question`
for each row
begin
	declare q_count INT;
    select count(1) into q_count from exam_question 
	group by exam_id
    having exam_id = new.exam_id;
	if (
		q_count >= 10 
	) then
		signal sqlstate '45000'
        set message_text = 'Exam already has 10 or more questions';
    end if;
end \(^.^)/

-- Question 5: Tạo trigger không cho phép người dùng xóa tài khoản có email là
-- admin@gmail.com (đây là tài khoản admin, không cho phép user xóa),
-- còn lại các tài khoản khác thì sẽ cho phép xóa và sẽ xóa tất cả các thông
-- tin liên quan tới user đó

create trigger prevent_delete_admin
before delete on account
for each row
begin

    if old.email = 'admin@gmail.com' then

        signal sqlstate '45000'
        set message_text = 'Cannot delete admin account';

    end if;

end \(^.^)/

create trigger delete_account_related_data
before delete on account
for each row
begin

    if old.email <> 'admin@gmail.com' then

        delete from group_account
        where account_id = old.account_id;

        delete from exam_question
        where exam_id in (
            select exam_id
            from exam
            where creator_id = old.account_id
        );

        delete from exam
        where creator_id = old.account_id;

        delete from answer
        where question_id in (
            select question_id
            from question
            where creator_id = old.account_id
        );

        delete from question
        where creator_id = old.account_id;

        delete from `group`
        where creator_id = old.account_id;

    end if;

end \(^.^)/

-- Question 6: Không sử dụng cấu hình default cho field DepartmentID của table
-- Account, hãy tạo trigger cho phép người dùng khi tạo account không điền
-- vào departmentID thì sẽ được phân vào phòng ban "waiting Department"

create trigger set_waiting_department
before insert on account
for each row
begin
    declare waiting_department_id int;

    select department_id
    into waiting_department_id
    from department
    where department_name = 'waiting Department'
    limit 1;

    if new.department_id is null then
        set new.department_id = waiting_department_id;
    end if;

end \(^.^)/

-- Question 7: Cấu hình 1 bài thi chỉ cho phép user tạo tối đa 4 answers cho mỗi
-- question, trong đó có tối đa 2 đáp án đúng.

create trigger check_max_answers
before insert on answer
for each row
begin
    declare answer_count int;
    declare correct_answer_count int;

    select count(*)
    into answer_count
    from answer
    where question_id = new.question_id;

    if answer_count >= 4 then

        signal sqlstate '45000'
        set message_text = 'A question cannot have more than 4 answers';

    end if;

    if new.is_correct = true then

        select count(*)
        into correct_answer_count
        from answer
        where question_id = new.question_id
        and is_correct = true;

        if correct_answer_count >= 2 then

            signal sqlstate '45000'
            set message_text = 'A question cannot have more than 2 correct answers';

        end if;

    end if;

end \(^.^)/

-- Question 8: Viết trigger sửa lại dữ liệu cho đúng:
-- Nếu người dùng nhập vào gender của account là nam, nữ, chưa xác định
-- Thì sẽ đổi lại thành M, F, U cho giống với cấu hình ở database

alter table account
add column gender varchar(20);

create trigger convert_gender
before insert on account
for each row
begin

    if lower(new.gender) = 'nam' then
        set new.gender = 'M';

    elseif lower(new.gender) = 'nữ' then
        set new.gender = 'F';

    elseif lower(new.gender) = 'chưa xác định' then
        set new.gender = 'U';

    end if;

end \(^.^)/

-- Question 9: Viết trigger không cho phép người dùng xóa bài thi mới tạo được 2 ngày

create trigger prevent_delete_new_exam
before delete on exam
for each row
begin

    if datediff(curdate(), old.create_date) < 2 then

        signal sqlstate '45000'
        set message_text = 'Cannot delete an exam created less than 2 days ago';

    end if;

end \(^.^)/
    
-- Question 10: Viết trigger chỉ cho phép người dùng chỉ được update, delete các
-- question khi question đó chưa nằm trong exam nào

create trigger prevent_update_question_in_exam
before update on question
for each row
begin
    declare exam_count int;

    select count(*)
    into exam_count
    from exam_question
    where question_id = old.question_id;

    if exam_count > 0 then

        signal sqlstate '45000'
        set message_text = 'Cannot update a question that is already in an exam';

    end if;

end \(^.^)/

create trigger prevent_delete_question_in_exam
before delete on question
for each row
begin
    declare exam_count int;

    select count(*)
    into exam_count
    from exam_question
    where question_id = old.question_id;

    if exam_count > 0 then

        signal sqlstate '45000'
        set message_text = 'Cannot delete a question that is already in an exam';

    end if;

end \(^.^)/

-- Question 12: Lấy ra thông tin exam trong đó:
-- Duration <= 30 thì sẽ đổi thành giá trị "Short time"
-- 30 < Duration <= 60 thì sẽ đổi thành giá trị "Medium time"
-- Duration > 60 thì sẽ đổi thành giá trị "Long time"

select
    exam_id,
    `code`,
    title,
    duration,
    case
        when duration <= 30 then 'Short time'
        when duration <= 60 then 'Medium time'
        else 'Long time'
    end as duration_type
from exam;

-- Question 13: Thống kê số account trong mỗi group và in ra thêm 1 column nữa có tên
-- là the_number_user_amount và mang giá trị được quy định như sau:
-- Nếu số lượng user trong group =< 5 thì sẽ có giá trị là few
-- Nếu số lượng user trong group <= 20 và > 5 thì sẽ có giá trị là normal
-- Nếu số lượng user trong group > 20 thì sẽ có giá trị là higher

select
    g.group_id,
    g.group_name,
    count(ga.account_id) as number_user,
    case
        when count(ga.account_id) <= 5 then 'few'
        when count(ga.account_id) <= 20 then 'normal'
        else 'higher'
    end as the_number_user_amount
from `group` g
left join group_account ga
    on g.group_id = ga.group_id
group by
    g.group_id,
    g.group_name;

-- Question 14: Thống kê số mỗi phòng ban có bao nhiêu user, nếu phòng ban nào
-- không có user thì sẽ thay đổi giá trị 0 thành "Không có User"

select
    d.department_id,
    d.department_name,
    case
        when count(a.account_id) = 0 then 'Không có User'
        else cast(count(a.account_id) as char)
    end as number_of_user
from department d
left join account a
    on d.department_id = a.department_id
group by
    d.department_id,
    d.department_name;
