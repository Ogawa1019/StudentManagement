package raisetech.StudentManagement;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManagementApplication {

  private Map<String, String> student = new HashMap<>();

  public StudentManagementApplication() {
    student.put("name", "Ogawa Takashi");
    student.put("age", "23");
  }

  public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
  }

  @GetMapping("/studentInfo")
  public Map<String, String> getStudentInfo() {
    return student;
  }

  @PostMapping("/studentInfo")
  public void setStudentInfo(
      @RequestParam String name,
      @RequestParam String age) {

    student.put("name", name);
    student.put("age", age);
  }
}