package board.vo;

import org.apache.ibatis.type.Alias;

@Alias("member")
public class MemberVO {
	private String id;
	private String pass;
	private String name;
	private int age;
	private int grade;
	private String grade_name;
	
	public MemberVO(String id, String pass, String name, int age, int grade, String grade_name) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.age = age;
		this.grade = grade;
		this.grade_name = grade_name;
	}
	
	public MemberVO(String id, String pass, String name, int age, int grade) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.age = age;
		this.grade = grade;
		this.grade_name = "default";
	}



	public String getGrade_name() {
		return grade_name;
	}

	public void setGrade_name(String grade_name) {
		this.grade_name = grade_name;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public int getGrade() {
		return grade;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	


	
}
