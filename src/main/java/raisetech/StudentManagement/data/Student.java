package raisetech.StudentManagement.data;

import lombok.Getter;
import lombok.Setter;

/**
 * 受講生を扱うオブジェクト。
 */
@Getter
@Setter
public class Student {

  private int id;
  private String fullname;
  private String furigana;
  private String nickname;
  private String email;
  private String city;
  private int age;
  private String gender;
  private String remark;
  private boolean isDeleted;
}