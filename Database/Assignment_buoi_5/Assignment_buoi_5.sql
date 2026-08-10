
DELIMITER //


-- Question 1: Tạo store để người dùng nhập vào tên phòng ban và in ra tất cả các
-- account thuộc phòng ban đó.

DROP PROCEDURE IF EXISTS get_accounts_by_department //

CREATE PROCEDURE get_accounts_by_department(
    IN p_department_name VARCHAR(30)
)
BEGIN
    SELECT
        a.account_id,
        a.email,
        a.username,
        a.fullname,
        p.position_name,
        d.department_name,
        a.create_date
    FROM `account` a
    JOIN department d
        ON a.department_id = d.department_id
    JOIN position p
        ON a.position_id = p.position_id
    WHERE d.department_name = p_department_name;
END //


CALL get_accounts_by_department('Sale');

-- Question 2: Tạo store để in ra số lượng account trong mỗi group.

DROP PROCEDURE IF EXISTS count_accounts_in_each_group //

CREATE PROCEDURE count_accounts_in_each_group()
BEGIN
    SELECT
        g.group_id,
        g.group_name,
        COUNT(ga.account_id) AS account_count
    FROM `group` g
    LEFT JOIN group_account ga
        ON g.group_id = ga.group_id
    GROUP BY
        g.group_id,
        g.group_name
    ORDER BY g.group_id;
END //


CALL count_accounts_in_each_group();

-- Question 3: Tạo store để thống kê mỗi type question có bao nhiêu question được tạo
-- trong tháng hiện tại.

DROP PROCEDURE IF EXISTS count_questions_by_type_current_month //

CREATE PROCEDURE count_questions_by_type_current_month()
BEGIN
    SELECT
        tq.type_id,
        tq.type_name,
        COUNT(q.question_id) AS question_count
    FROM type_question tq
    LEFT JOIN question q
        ON tq.type_id = q.type_id
        AND MONTH(q.create_date) = MONTH(CURDATE())
        AND YEAR(q.create_date) = YEAR(CURDATE())
    GROUP BY
        tq.type_id,
        tq.type_name
    ORDER BY tq.type_id;
END //

CALL count_questions_by_type_current_month();

-- Question 4: Tạo store để trả ra id của type question có nhiều câu hỏi nhất. 

DROP PROCEDURE IF EXISTS get_type_question_with_most_questions //

CREATE PROCEDURE get_type_question_with_most_questions(
    OUT p_type_id INT
)
BEGIN
    SELECT
        tq.type_id
    INTO p_type_id
    FROM type_question tq
    LEFT JOIN question q
        ON tq.type_id = q.type_id
    GROUP BY tq.type_id
    ORDER BY COUNT(q.question_id) DESC
    LIMIT 1;

    SELECT p_type_id AS type_id;
END //

CALL get_type_question_with_most_questions(@type_id);
SELECT @type_id;

-- Question 5: Sử dụng store ở question 4 để tìm ra tên của type question. 

DROP PROCEDURE IF EXISTS get_name_of_type_with_most_questions //

CREATE PROCEDURE get_name_of_type_with_most_questions()
BEGIN
    DECLARE v_type_id INT;

    CALL get_type_question_with_most_questions(v_type_id);

    SELECT
        type_id,
        type_name
    FROM type_question
    WHERE type_id = v_type_id;
END //


CALL get_name_of_type_with_most_questions();

-- Question 6: Viết 1 store cho phép người dùng nhập vào 1 chuỗi và trả về group có tên
-- chứa chuỗi của người dùng nhập vào hoặc trả về user có username chứa chuỗi của người dùng nhập vào. 

DROP PROCEDURE IF EXISTS search_group_or_user //

CREATE PROCEDURE search_group_or_user(
    IN p_search VARCHAR(30)
)
BEGIN

    -- Search groups
    SELECT
        'GROUP' AS result_type,
        g.group_id AS id,
        g.group_name AS name
    FROM `group` g
    WHERE g.group_name LIKE CONCAT('%', p_search, '%');

    -- Search users
    SELECT
        'USER' AS result_type,
        a.account_id AS id,
        a.username AS name
    FROM `account` a
    WHERE a.username LIKE CONCAT('%', p_search, '%');

END //

-- Question 7: Viết 1 store cho phép người dùng nhập vào thông tin fullName, email và trong store sẽ tự động gán:
-- username sẽ giống email nhưng bỏ phần @..mail đi
-- positionID: sẽ có default là developer
-- departmentID: sẽ được cho vào 1 phòng chờ
-- Sau đó in ra kết quả tạo thành công


DROP PROCEDURE IF EXISTS create_account //

CREATE PROCEDURE create_account(
    IN p_fullname VARCHAR(30),
    IN p_email VARCHAR(30)
)
BEGIN
    DECLARE v_username VARCHAR(30);
    DECLARE v_position_id INT;
    DECLARE v_department_id INT;

    -- Username = everything before @
    SET v_username = SUBSTRING_INDEX(p_email, '@', 1);

    -- Find Developer position
    SELECT position_id
    INTO v_position_id
    FROM position
    WHERE position_name = 'Dev'
    LIMIT 1;

    -- Find waiting department
    SELECT department_id
    INTO v_department_id
    FROM department
    WHERE department_name = 'Phòng chờ việc'
    LIMIT 1;

    -- Insert account
    INSERT INTO `account`
    (
        email,
        username,
        fullname,
        department_id,
        position_id
    )
    VALUES
    (
        p_email,
        v_username,
        p_fullname,
        v_department_id,
        v_position_id
    );

    -- Display result
    SELECT
        'Account created successfully' AS message,
        LAST_INSERT_ID() AS account_id,
        p_fullname AS fullname,
        p_email AS email,
        v_username AS username,
        v_department_id AS department_id,
        v_position_id AS position_id;
END //


CALL create_account(
    'Nguyen Van A',
    'nguyenvana@gmail.com'
);

-- Question 8: Viết 1 store cho phép người dùng nhập vào Essay hoặc Multiple-Choice
-- để thống kê câu hỏi essay hoặc multiple-choice nào có content dài nhất

DROP PROCEDURE IF EXISTS get_longest_question_by_type //

CREATE PROCEDURE get_longest_question_by_type(
    IN p_type_name VARCHAR(30)
)
BEGIN
    SELECT
        q.question_id,
        q.content,
        tq.type_name,
        CHAR_LENGTH(q.content) AS character_count,
        q.creator_id,
        q.create_date
    FROM question q
    JOIN type_question tq
        ON q.type_id = tq.type_id
    WHERE tq.type_name = p_type_name
    ORDER BY CHAR_LENGTH(q.content) DESC
    LIMIT 1;
END //

-- Question 9: Viết 1 store cho phép người dùng xóa exam dựa vào ID

DROP PROCEDURE IF EXISTS delete_exam //

CREATE PROCEDURE delete_exam(
    IN p_exam_id INT
)
BEGIN
    DECLARE v_exam_question_count INT DEFAULT 0;
    DECLARE v_exam_count INT DEFAULT 0;

    -- Count related exam_question records
    SELECT COUNT(*)
    INTO v_exam_question_count
    FROM exam_question
    WHERE exam_id = p_exam_id;

    -- Delete exam_question first because of FK
    DELETE FROM exam_question
    WHERE exam_id = p_exam_id;

    -- Delete exam
    DELETE FROM exam
    WHERE exam_id = p_exam_id;

    SET v_exam_count = ROW_COUNT();

    -- Display deletion result
    SELECT
        p_exam_id AS exam_id,
        v_exam_count AS exam_removed,
        v_exam_question_count AS exam_question_removed;
END //

-- Question 10: Tìm ra các exam được tạo từ 3 năm trước và xóa các exam đó đi (sử
-- dụng store ở câu 9 để xóa)
-- Sau đó in số lượng record đã remove từ các table liên quan trong khi
-- removing

DROP PROCEDURE IF EXISTS delete_exams_older_than_3_years //

CREATE PROCEDURE delete_exams_older_than_3_years()
BEGIN
    DECLARE done INT DEFAULT 0;
    DECLARE v_exam_id INT;

    DECLARE v_exam_count INT DEFAULT 0;
    DECLARE v_exam_question_count INT DEFAULT 0;
    DECLARE v_current_exam_question_count INT DEFAULT 0;

    DECLARE cur CURSOR FOR
        SELECT exam_id
        FROM exam
        WHERE create_date < DATE_SUB(CURDATE(), INTERVAL 3 YEAR);

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    OPEN cur;

    read_loop: LOOP

        FETCH cur INTO v_exam_id;

        IF done = 1 THEN
            LEAVE read_loop;
        END IF;

        -- Count related records before deleting
        SELECT COUNT(*)
        INTO v_current_exam_question_count
        FROM exam_question
        WHERE exam_id = v_exam_id;

        SET v_exam_question_count =
            v_exam_question_count + v_current_exam_question_count;

        -- Use Question 9 procedure
        CALL delete_exam(v_exam_id);

        SET v_exam_count = v_exam_count + 1;

    END LOOP;

    CLOSE cur;

    -- Final result
    SELECT
        v_exam_count AS exams_removed,
        v_exam_question_count AS exam_question_records_removed;
END //

CALL delete_exams_older_than_3_years();

-- Question 11: Viết store cho phép người dùng xóa phòng ban bằng cách người dùng
-- nhập vào tên phòng ban và các account thuộc phòng ban đó sẽ được
-- chuyển về phòng ban default là phòng ban chờ việc

-- Alter table department, add 'disabled' column

ALTER TABLE department
ADD COLUMN disabled BOOLEAN NOT NULL DEFAULT FALSE;

DROP PROCEDURE IF EXISTS delete_department //

CREATE PROCEDURE delete_department(
    IN p_department_name VARCHAR(30)
)
BEGIN
    DECLARE v_department_id INT;
    DECLARE v_waiting_department_id INT;
    DECLARE v_account_count INT DEFAULT 0;

    -- Find department to delete
    SELECT department_id
    INTO v_department_id
    FROM department
    WHERE department_name = p_department_name
    LIMIT 1;

    -- Find waiting department
    SELECT department_id
    INTO v_waiting_department_id
    FROM department
    WHERE department_name = 'Phòng chờ việc'
    LIMIT 1;


        -- Move accounts to waiting department
        UPDATE `account`
        SET department_id = v_waiting_department_id
        WHERE department_id = v_department_id;

        -- Delete department
        UPDATE department
        SET disabled = TRUE
        WHERE department_id = v_department_id
        ;

        -- Display result
        SELECT
            'Department deleted successfully' AS message,
            p_department_name AS deleted_department,
            v_account_count AS accounts_moved;

END //

CALL delete_department('Sale');

-- Question 12: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong năm nay

DROP PROCEDURE IF EXISTS count_questions_each_month_this_year;

DELIMITER //

CREATE PROCEDURE count_questions_each_month_this_year()
BEGIN

    SELECT
        m.month_number,
        COUNT(q.question_id) AS question_count
    FROM
    (
        SELECT 1 AS month_number
        UNION ALL SELECT 2
        UNION ALL SELECT 3
        UNION ALL SELECT 4
        UNION ALL SELECT 5
        UNION ALL SELECT 6
        UNION ALL SELECT 7
        UNION ALL SELECT 8
        UNION ALL SELECT 9
        UNION ALL SELECT 10
        UNION ALL SELECT 11
        UNION ALL SELECT 12
    ) AS m
    LEFT JOIN question q
        ON MONTH(q.create_date) = m.month_number
        AND YEAR(q.create_date) = YEAR(CURDATE())
    GROUP BY m.month_number
    ORDER BY m.month_number;

END //

CALL count_questions_each_month_this_year();

-- Question 13: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong 6 tháng gần đây nhất
-- (Nếu tháng nào không có thì sẽ in ra là "không có câu hỏi nào trong tháng") 

DROP PROCEDURE IF EXISTS count_questions_last_6_months;

CREATE PROCEDURE count_questions_last_6_months()
BEGIN

    SELECT
        YEAR(m.month_start) AS year,
        MONTH(m.month_start) AS month,
        COUNT(q.question_id) AS question_count
    FROM
    (
        SELECT
            DATE_FORMAT(
                DATE_SUB(CURDATE(), INTERVAL 5 MONTH),
                '%Y-%m-01'
            ) AS month_start

        UNION ALL

        SELECT
            DATE_FORMAT(
                DATE_SUB(CURDATE(), INTERVAL 4 MONTH),
                '%Y-%m-01'
            )

        UNION ALL

        SELECT
            DATE_FORMAT(
                DATE_SUB(CURDATE(), INTERVAL 3 MONTH),
                '%Y-%m-01'
            )

        UNION ALL

        SELECT
            DATE_FORMAT(
                DATE_SUB(CURDATE(), INTERVAL 2 MONTH),
                '%Y-%m-01'
            )

        UNION ALL

        SELECT
            DATE_FORMAT(
                DATE_SUB(CURDATE(), INTERVAL 1 MONTH),
                '%Y-%m-01'
            )

        UNION ALL

        SELECT
            DATE_FORMAT(CURDATE(), '%Y-%m-01')
    ) AS m

    LEFT JOIN question q
        ON q.create_date >= m.month_start
        AND q.create_date < DATE_ADD(m.month_start, INTERVAL 1 MONTH)

    GROUP BY
        YEAR(m.month_start),
        MONTH(m.month_start)

    ORDER BY
        YEAR(m.month_start),
        MONTH(m.month_start);

END //

CALL count_questions_last_6_months();


DELIMITER ;