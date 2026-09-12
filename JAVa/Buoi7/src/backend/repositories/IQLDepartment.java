package backend.repositories;

import java.util.List;

import models.Department;

public interface IQLDepartment {
    List<Department> displayDepartment();
    void insertDepartment(int departmentId, String departmentName);
    void updateDepartment(int departmentId, String departmentName);
    void deleteDepartment(int departmentId);
}
