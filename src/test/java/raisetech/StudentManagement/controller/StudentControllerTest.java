package raisetech.StudentManagement.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private StudentService service;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  void 受講生詳細の一覧検索が実行できてか空のリストが帰ってくること() throws Exception {
    mockMvc.perform(get("/studentList"))
        .andExpect(status().isOk());

    verify(service, times(1)).searchStudentList();
  }

  @Test
  void 受講生詳細の受講生で適切な値を入力したときに入力チェックに異常が発生しないこと() {
    Student student = new Student();
    student.setId("1");
    student.setFullname("山田太郎");
    student.setFurigana("ヤマダタロウ");
    student.setNickname("タロウ");
    student.setEmail("yamada@example.com");
    student.setCity("東京");
    student.setGender("男性");

    Set<ConstraintViolation<Student>> violations = validator.validate(student);

    assertThat(violations.size()).isEqualTo(0);
  }

  @Test
  void 受講生詳細の受講生でIDに数字以外を用いた時に入力チェックに掛かること() {
    Student student = new Student();
    student.setId("テストです。");
    student.setFullname("山田太郎");
    student.setFurigana("ヤマダタロウ");
    student.setNickname("タロウ");
    student.setEmail("yamada@example.com");
    student.setCity("東京");
    student.setGender("男性");

    Set<ConstraintViolation<Student>> violations = validator.validate(student);

    assertThat(violations.size()).isEqualTo(1);
    assertThat(violations).extracting("message")
        .containsOnly("数字のみ入力するようにしてください。");
  }

  @Test
  void 受講生IDを指定して受講生詳を取得できること() throws Exception {
    String studentId = "1";
    Student student = new Student();
    student.setId(studentId);
    student.setFullname("山田太郎");
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);

    when(service.searchStudent(studentId)).thenReturn(studentDetail);
    mockMvc.perform(get("/student/{id}", studentId))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.student.id").value(1))
        .andExpect(jsonPath("$.student.fullname").value("山田太郎"));

    verify(service, times(1)).searchStudent(studentId);
  }

  @Test
  void 受講生詳細の受講生で名前が空の場合に入力チェックが掛かること() {
    Student student = new Student();
    student.setId("1");
    student.setFullname("");
    student.setFurigana("ヤマダタロウ");
    student.setNickname("タロウ");
    student.setEmail("yamada@example.com");
    student.setCity("東京");
    student.setGender("男性");

    Set<ConstraintViolation<Student>> violations = validator.validate(student);

    assertThat(violations).extracting("propertyPath")
        .extracting(Object::toString)
        .contains("fullname");
  }

  @Test
  void 受講生詳細の受講生でメールアドレスが空の場合に入力チェックが掛かること() {
    Student student = new Student();
    student.setId("1");
    student.setFullname("山田太郎");
    student.setFurigana("ヤマダタロウ");
    student.setNickname("タロウ");
    student.setEmail("");
    student.setCity("東京");
    student.setGender("男性");

    Set<ConstraintViolation<Student>> violations = validator.validate(student);

    assertThat(violations).extracting("propertyPath")
        .extracting(Object::toString)
        .contains("email");
  }

  @Test
  void 受講生詳細の受講生コースで適切な値を入力したときに入力チェックに異常が発生しないこと () {
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setCourseName("Javaコース");

    Set<ConstraintViolation<StudentCourse>> violations = validator.validate(studentCourse);

    assertThat(violations.size()).isEqualTo(0);
  }

  @Test
  void 受講生詳細の受講生コースでコース名が空の場合に入力チェックが掛かること() {
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setCourseName("");

    Set<ConstraintViolation<StudentCourse>> violations = validator.validate(studentCourse);

    assertThat(violations.size()).isEqualTo(1);
  }
}