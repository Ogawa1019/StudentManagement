INSERT INTO students (fullname,furigana,nickname,email,city,age,gender,remark,is_deleted)
VALUES
      ( '山田太郎', 'ヤマダタロウ', 'タロウ', 'yamada@example.com', '東京', 25, '男性', '', 0),
      ( '鈴木花子', 'スズキハナコ', 'ハナ', 'suzuki@example.com', '大阪', 22, '女性', '', 0),
      ( '佐藤健', 'サトウケン', 'ケン', 'sato@example.com', '福岡', 30, '男性', '', 0),
      ( '高橋美咲', 'タカハシミサキ', 'ミサキ', 'takahashi@example.com', '名古屋', 28, '女性', '', 0),
      ( '田中一郎', 'タナカイチロウ', 'イチロー', 'tanaka@example.com', '札幌', 35, '男性', '', 0),
      ( '伊藤二郎', 'イトウジロウ', 'ジロウ', 'ito@example.com', '高知', 24, '男性', '', 0),
      ( '山口次郎', 'ヤマグチジロウ', 'ジロー', 'yama@example.com', '岩手', 45, '男性', '', 0),
      ( '武田太郎', 'タケダタロウ', 'タケダ', 'takeda@example.com', '大分', 34, '女性', '', 0),
      ( '武田真', 'タケダマコト', 'マコト', 'takeda@example.com', '大分', 34, '女性', '', 0),
      ( '中村雅人', 'ナカムラマサト', 'マサト', 'hamada@example.com', '栃木', 18, '女性', '', 0),
      ( '藤原厚志', 'フジワラコウシ', 'フジ', 'fuj@example.com', '埼玉', 26, '男性', '', 0);

INSERT INTO students_courses (student_id,course_name,start_date,end_date)
VALUES
      ( 1, 'Javaコース', NULL, NULL),
      (1, 'Spring Bootコース', NULL, NULL),
      ( 2, 'MySQLコース', NULL, NULL),
      ( 3, 'AWSコース', NULL, NULL),
      ( 4, 'Javaコース', NULL, NULL),
      ( 6, 'Javaコース', NULL, NULL),
      ( 7, 'Javaコース', NULL, NULL),
      ( 8, 'Javaコース', NULL, NULL),
      ( 5, 'Javaコース', NULL, NULL),
      ( 5, 'Spring Bootコース', NULL, NULL),
      ( 9, 'AWSコース', '2026-08-14', '2027-08-14'),
      ( 10, 'AWSコース', '2026-08-14', '2027-08-14'),
      ( 11, 'AWSコース', '2026-08-19', '2027-08-19'),
      ( 11, 'Javaコース', '2026-08-21', '2027-08-21'),
      ( 12, 'Javaコース', '2026-08-23', '2027-08-23'),
      ( 13, 'MySQLコース', '2026-09-16', '2027-09-16');