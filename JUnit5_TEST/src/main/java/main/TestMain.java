package main;
import dao.EmployeeDAO;
import dto.EmployeeDTO;

public class TestMain {
	public static void main(String[] args) {
		String result = EmployeeDAO.getInstance().searchEmployee_position(4);
		//System.out.println(result);
		
		result = EmployeeDAO.getInstance().searchEmployee_lowSalary(5);
		System.out.println(result);
		
		EmployeeDTO dto = EmployeeDAO.getInstance().searchEmployee_eno("DA43");
		System.out.println(dto);
	}
}
