import models.Account;
import models.Department;

public class Exercise5_ObjectsMethod {
    public Department[] departments;

    public Exercise5_ObjectsMethod(Department[] departments) {
        this.departments = departments;
    }

    public void question1(){
        System.out.println(departments[0].toString());
    }
    public void question2(){
        for (Department department : departments) {
            System.out.println(department.toString());
        }
    }

    public void question3(){
        System.out.println(departments[0].getDepartmentId());
    }
    public boolean question4(){
        return departments[0].getDepartmentName().equals("Phòng A");
    }

    public boolean question5(){
        return departments[0].getDepartmentName().equals(departments[1].getDepartmentName());
    }
    public void question6(){
        for (int i = 0; i < departments.length-1; i++) {
            for (int j = i+1;j<departments.length;j++){
                if (departments[i].getDepartmentName().charAt(0) > departments[j].getDepartmentName().charAt(0)){
                    Department tempD = departments[i];
                    departments[i] = departments[j];
                    departments[j] = tempD;
                }
            }
        }
        for (Department department : departments) {
            System.out.println(department.getDepartmentName());
        }
    }
    public void question7(Account[] students){
        Department[] newDepartments = new Department[5];
        for (int i = 0; i < students.length; i++) {
            for (Department department : newDepartments){
                if (department.getDepartmentId() == students[i].getDepartment().getDepartmentId()){
                    continue;
                }
                newDepartments[i] = students[i].getDepartment();
            }
            
        }

        for (int i = 0; i < newDepartments.length-1; i++) {
            for (int j = i+1;j<newDepartments.length;j++){
                if (newDepartments[i].getDepartmentName().charAt(0) > newDepartments[j].getDepartmentName().charAt(0)){
                    Department tempD = newDepartments[i];
                    newDepartments[i] = newDepartments[j];
                    newDepartments[j] = tempD;
                }
            }
        }
        for (Department department : newDepartments) {
            System.out.println(department.getDepartmentName());
        }
    }
}
