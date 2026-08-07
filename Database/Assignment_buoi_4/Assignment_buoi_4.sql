-- Question 1: Tạo view có chứa danh sách nhân viên thuộc phòng ban sale

CREATE OR REPLACE VIEW view_sale_accounts AS
SELECT
    a.*, d.department_name
FROM `account` a
JOIN department d
    ON a.department_id = d.department_id
WHERE d.department_name = 'Sale';

SELECT * FROM view_sale_accounts;

-- Question 2: Tạo view có chứa thông tin các account tham gia vào nhiều group nhất

CREATE OR REPLACE VIEW view_accounts_most_groups AS
SELECT
    a.account_id,
    a.username,
    a.fullname,
    a.email,
    COUNT(ga.group_id) AS group_count
FROM `account` a
JOIN group_account ga
    ON a.account_id = ga.account_id
GROUP BY
    a.account_id,
    a.username,
    a.fullname,
    a.email
HAVING COUNT(ga.group_id) = (
    SELECT MAX(group_count)
    FROM (
        SELECT COUNT(*) AS group_count
        FROM group_account
        GROUP BY account_id
    ) AS group_counts
);

SELECT *
FROM view_accounts_most_groups;

-- Question 3: Tạo view có chứa câu hỏi có những content quá dài (content quá 300 từ
-- được coi là quá dài) và xóa nó đi

CREATE OR REPLACE VIEW view_long_questions AS
SELECT
    q.*
FROM question q
WHERE
    CHAR_LENGTH(TRIM(q.content)) -- count all characters 
    - CHAR_LENGTH(REPLACE(TRIM(q.content), ' ', '')) -- minus the characters without the space, = the number of space
    + 1 > 300; -- +1 = number of word.

DELETE FROM answer
WHERE question_id IN (
    SELECT question_id
    FROM view_long_questions
);

-- Question 4: Tạo view có chứa danh sách các phòng ban có nhiều nhân viên nhất

CREATE OR REPLACE VIEW view_departments_most_accounts AS
SELECT
    d.department_id,
    d.department_name,
    COUNT(a.account_id) AS employee_count
FROM department d
LEFT JOIN `account` a
    ON d.department_id = a.department_id
GROUP BY
    d.department_id,
    d.department_name
HAVING COUNT(a.account_id) = (
    SELECT MAX(employee_count)
    FROM (
        SELECT COUNT(*) AS employee_count
        FROM `account`
        GROUP BY department_id
    ) AS department_counts
);

select * from view_departments_most_accounts;

-- Question 5: Tạo view có chứa tất các các câu hỏi do user họ Nguyễn tạo.

CREATE OR REPLACE VIEW view_questions_created_by_nguyen AS
SELECT
    q.*
FROM question q
JOIN `account` a
    ON q.creator_id = a.account_id
WHERE a.fullname LIKE 'Nguyễn%'; -- ho nguyen nen bat dau bang nguyen