CREATE TABLE IF NOT EXISTS students
(
    id int NOT NULL AUTO_INCREMENT,
    fullname varchar(100) NOT NULL,
    furigana varchar(100) NOT NULL,
    nickname varchar(100) DEFAULT NULL,
    email varchar(100) NOT NULL,
    city varchar(100) DEFAULT NULL,
    age int DEFAULT NULL,
    gender varchar(100) DEFAULT NULL,
    remark varchar(255) DEFAULT NULL,
    is_deleted boolean
);

CREATE TABLE IF NOT EXISTS students_courses
(
  id int NOT NULL AUTO_INCREMENT,
  student_id int NOT NULL,
  course_name varchar(100) NOT NULL,
  start_date  date DEFAULT NULL,
  end_date  date DEFAULT NULL
);