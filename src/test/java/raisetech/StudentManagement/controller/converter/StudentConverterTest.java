package raisetech.StudentManagement.controller.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;

class StudentConverterTest {

  private StudentConverter converter = new StudentConverter();

  @Test
  void 受講生情報とコース情報が正しく紐付くこと () {
    Student student = new Student();
    student.setId("1");
    student.setFullname("山田太郎");

    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setId(1);
    studentCourse.setStudentId(1);
    studentCourse.setCourseName("Javaコース");

    List<StudentCourse> studentCourses = List.of(studentCourse);
    StudentDetail result = converter.convertStudentDetail(student, studentCourses);

    assertSame(student, result.getStudent());
    assertSame(studentCourses, result.getStudentCourseList());
  }

  @Test
  void 受講生に紐づくコース情報が正しく設定されること() {
    Student student1 = new Student();
    student1.setId("1");
    student1.setFullname("山田太郎");

    Student student2 = new Student();
    student2.setId("2");
    student2.setFullname("鈴木花子");

    StudentCourse course1 = new StudentCourse();
    course1.setId(1);
    course1.setStudentId(1);
    course1.setCourseName("Javaコース");

    StudentCourse course2 = new StudentCourse();
    course2.setId(2);
    course2.setStudentId(1);
    course2.setCourseName("Spring Bootコース");

    StudentCourse course3 = new StudentCourse();
    course3.setId(3);
    course3.setStudentId(2);
    course3.setCourseName("MySQLコース");

    List<Student> students = List.of(student1, student2);
    List<StudentCourse> studentCourses = List.of(course1, course2, course3);

    List<StudentDetail> result =
        converter.convertStudentDetails(students, studentCourses);

    assertEquals(2, result.size());

    assertSame(student1, result.get(0).getStudent());
    assertEquals(2, result.get(0).getStudentCourseList().size());
    assertSame(course1, result.get(0).getStudentCourseList().get(0));
    assertSame(course2, result.get(0).getStudentCourseList().get(1));

    assertSame(student2, result.get(1).getStudent());
    assertEquals(1, result.get(1).getStudentCourseList().size());
    assertSame(course3, result.get(1).getStudentCourseList().get(0));
  }

  @Test
  void コース情報が存在しない場合は空のリストになること () {
    Student student = new Student();
    student.setId("1");
    student.setFullname("山田太郎");

    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setId(1);
    studentCourse.setStudentId(2);
    studentCourse.setCourseName("Javaコース");

    List<Student> students = List.of(student);
    List<StudentCourse> studentCourses = List.of(studentCourse);

    List<StudentDetail> result =
        converter.convertStudentDetails(students, studentCourses);

    assertEquals(1, result.size());
    assertSame(student, result.get(0).getStudent());
    assertTrue(result.get(0).getStudentCourseList().isEmpty());

  }
}