package academic.planner.services;

import academic.planner.entities.Department;
import academic.planner.repositories.DepartmentRepository;
import academic.planner.utils.AcademicPlannerException;
import academic.planner.utils.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    protected final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public List<Department> getByEstablishmentCode(String establishmentCode) {
        return departmentRepository.findByEstablishmentCode(establishmentCode);
    }

    public Department getById(Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if(! optionalDepartment.isPresent()) throw new AcademicPlannerException(ErrorCode.department_not_found, "department not found with id => " + id);
        return optionalDepartment.get();
    }

    public Department getByCode(String code) {
        Optional<Department> optionalDepartment = departmentRepository.findByCode(code);
        if(! optionalDepartment.isPresent()) throw new AcademicPlannerException(ErrorCode.department_not_found, "department not found with code => " + code);
        return optionalDepartment.get();
    }

    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> save(List<Department> departments) {
        List<Department> departmentsList = new ArrayList<Department>();
        for (Department department: departments) {
            departmentsList.add(departmentRepository.save(department));
        }
        return departmentsList;
    }

    public void delete(Long id) {
        departmentRepository.delete(getById(id));
    }

}
