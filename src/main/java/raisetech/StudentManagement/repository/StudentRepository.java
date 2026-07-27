package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> search();

  @Select("""
      SELECT *
      FROM students
      WHERE id = #{id}
      """)
  Student searchStudent(int id);

  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> searchStudentCourse();

  @Select("""
      SELECT *
      FROM students_courses
      WHERE student_id = #{id}
      """)
  List<StudentsCourses> searchStudentCourseByStudentId(int id);

  @Insert("""
      INSERT INTO students
      (fullname, furigana, nickname, email, city, age, gender, remark, is_deleted)
      VALUES
      (#{fullname}, #{furigana}, #{nickname}, #{email}, #{city}, #{age}, #{gender}, #{remark}, #{isDeleted})
      """)
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudent(Student student);


  @Insert("""
      INSERT INTO students_courses
      (student_id, course_name, start_date, end_date)
      VALUES
      (#{studentId}, #{courseName}, #{startDate}, #{endDate})
      """)
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudentsCourses(StudentsCourses studentsCourses);

  @Update("""
      UPDATE students
      SET
        fullname = #{fullname},
        furigana = #{furigana},
        nickname = #{nickname},
        email = #{email},
        city = #{city},
        age = #{age},
        gender = #{gender},
        remark = #{remark},
        is_deleted = #{isDeleted}
      WHERE id = #{id}
      """)
  void updateStudent(Student student);

  @Update("""
      UPDATE students_courses
      SET
        course_name = #{courseName},
        start_date = #{startDate},
        end_date = #{endDate}
      WHERE id = #{id}
      """)
  void updateStudentsCourses(StudentsCourses studentsCourses);
}