package academic.planner.utils;

import academic.planner.msg.Filter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class ObjectUtils {

    public static boolean isTrue( Boolean value ) {
        if ( value == null ) {
            return false;
        }
        return value;
    }
    public static <E> E getParameter( String objectName, E object ) {
        if ( object == null ) {
            throw new IllegalArgumentException("The supplied '" + objectName + "' is null.");
        }
        return object;
    }

    public Pageable constructPageable(Filter filter) {
        if(filter == null) throw new AcademicPlannerException(ErrorCode.filter_null, "Filter can't be null");
        int pageNumber      = filter.getPage() != null      ? filter.getPage() : 0;
        int pageSize        = filter.getPageSize() != null  ? filter.getPageSize() : 10;

        Sort.Direction sortDirection = Sort.Direction.DESC;
        if(Sort.Direction.ASC.name().equals(filter.getSortDirection())) sortDirection = Sort.Direction.ASC;

        Sort sort           = Sort.by(sortDirection, filter.getSortBy() != null ? filter.getSortBy() : "id");
        Pageable pageable   = PageRequest.of(pageNumber, pageSize, sort);

        return pageable;
    }
}
