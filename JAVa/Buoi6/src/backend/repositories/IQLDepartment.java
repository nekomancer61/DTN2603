package backend.repositories;

import java.util.List;

import models.ConnectionDetail;
import models.Department;

public interface IQLDepartment {
    List<Department> displayDepartment(ConnectionDetail detail);
}
