package academic.planner.services;

import academic.planner.entities.Schedule;
import academic.planner.repositories.ScheduleRepository;
import academic.planner.utils.AcademicPlannerException;
import academic.planner.utils.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {
    protected final ScheduleRepository ScheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository ScheduleRepository) {
        this.ScheduleRepository = ScheduleRepository;
    }

    public List<Schedule> getAll() {
        return ScheduleRepository.findAll();
    }

    public Schedule getById(Long id) {
        Optional<Schedule> optionalSchedule = ScheduleRepository.findById(id);
        if(! optionalSchedule.isPresent()) throw new AcademicPlannerException(ErrorCode.schedule_not_found, "Schedule not found with id => " + id);
        return optionalSchedule.get();
    }

    public Schedule save(Schedule schedule) {
        return ScheduleRepository.save(schedule);
    }

    public List<Schedule> save(List<Schedule> schedules) {
        List<Schedule> schedulesList = new ArrayList<Schedule>();
        for (Schedule schedule: schedules) {
            schedulesList.add(ScheduleRepository.save(schedule));
        }
        return schedulesList;
    }

    public void delete(Schedule schedule) {
        ScheduleRepository.delete(schedule);
    }
}
