package academic.planner.services;

import academic.planner.entities.AcademicProgram;
import academic.planner.repositories.AcademicProgramRepository;
import academic.planner.utils.AcademicPlannerException;
import academic.planner.utils.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AcademicProgramService {

    protected final AcademicProgramRepository academicProgramRepository;

    @Autowired
    public AcademicProgramService(AcademicProgramRepository academicProgramRepository) {
        this.academicProgramRepository = academicProgramRepository;
    }

    public List<AcademicProgram> getAll() {
        return academicProgramRepository.findAll();
    }

    public List<AcademicProgram> getByDepartmentCode(String departmentCode) {
        return academicProgramRepository.findByDepartmentCode(departmentCode);
    }
    public AcademicProgram getById(Long id) {
        Optional<AcademicProgram> optionalAcademicProgram = academicProgramRepository.findById(id);
        if(! optionalAcademicProgram.isPresent()) throw new AcademicPlannerException(ErrorCode.academic_program_not_found, "AcademicProgram not found with id => " + id);
        return optionalAcademicProgram.get();
    }

    public AcademicProgram getByCode(String code) {
        Optional<AcademicProgram> optionalAcademicProgram = academicProgramRepository.findByCode(code);
        if(! optionalAcademicProgram.isPresent()) throw new AcademicPlannerException(ErrorCode.academic_program_not_found, "AcademicProgram not found with code => " + code);
        return optionalAcademicProgram.get();
    }

    public AcademicProgram save(AcademicProgram academicProgram) {
        return academicProgramRepository.save(academicProgram);
    }

    public List<AcademicProgram> save(List<AcademicProgram> academicPrograms) {
        List<AcademicProgram> academicProgramsList = new ArrayList<AcademicProgram>();
        for (AcademicProgram academicProgram: academicPrograms) {
            academicProgramsList.add(academicProgramRepository.save(academicProgram));
        }
        return academicProgramsList;
    }

    public void delete(Long id) {
        academicProgramRepository.delete(getById(id));
    }

}
