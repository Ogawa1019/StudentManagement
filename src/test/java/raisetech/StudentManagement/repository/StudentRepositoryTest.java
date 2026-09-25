package raisetech.StudentManagement.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;

@MybatisTest
class StudentRepositoryTest {

  @Autowired
  private StudentRepository sut;

  @Test
  void 受講生の全件検索が行えること() {
    List<Student> actual = sut.search();
    assertThat(actual.size()).isEqualTo(11);
  }

  @Test
  void 受講生IDを指定し検索が行えること() {
    Student actual = sut.searchStudent("1");
    assertThat(actual).isNotNull();
    assertThat(actual.getFullname()).isEqualTo("山田太郎");
  }

  @Test
  void 受講生コース情報の全件検索が行えること() {
    List<StudentCourse> actual = sut.searchStudentCourseList();
    assertThat(actual.size()).isEqualTo(16);
  }

  @Test
  void 受講生IDに紐付くコース情報を検索できること() {
    List<StudentCourse> actual = sut.searchStudentCourse(1);
    assertThat(actual.size()).isEqualTo(2);
  }

  @Test
  void 受講生の登録が行えること() {
    Student student = new Student();
    student.setFullname("山田太郎");
    student.setFurigana("ヤマダタロウ");
    student.setNickname("タロウ");
    student.setEmail("yamada@example.com");
    student.setCity("東京");
    student.setAge(25);
    student.setGender("男性");
    student.setRemark("");
    student.setDeleted(false);

    sut.registerStudent(student);

    List<Student> actual = sut.search();

    assertThat(actual.size()).isEqualTo(12);
  }

  @Test
  void 受講生コース情報の登録が行えること() {
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setStudentId(1);
    studentCourse.setCourseName("Javaコース");
    studentCourse.setStartDate(LocalDateTime.now());
    studentCourse.setEndDate(LocalDateTime.now().plusMonths(3));

    sut.registerStudentCourse(studentCourse);

    List<StudentCourse> actual = sut.searchStudentCourseList();

    assertThat(actual.size()).isEqualTo(17);
  }

  @Test
  void 受講生の更新が行えること() {
    Student student = sut.searchStudent("1");
    student.setFullname("更新太郎");

    sut.updateStudent(student);

    Student actual = sut.searchStudent("1");
    assertThat(actual.getFullname()).isEqualTo("更新太郎");
  }

  @Test
  void 受講生コース情報の更新が行えること() {
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setId(1);
    studentCourse.setCourseName("Spring Boot更新コース");

    sut.updateStudentCourse(studentCourse);

    List<StudentCourse> actual = sut.searchStudentCourse(1);

    assertThat(actual.get(0).getCourseName()).isEqualTo("Spring Boot更新コース");
  }
}