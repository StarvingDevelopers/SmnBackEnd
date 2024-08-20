package tech.starvingdevelopers.smnbackend.exceptions.group;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import tech.starvingdevelopers.smnbackend.exceptions.GeneralException;

public class GroupNotFoundException extends GeneralException {
    private final String detail;

    public GroupNotFoundException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Group Not Found!");
        problemDetail.setDetail(detail);
        return problemDetail;
    }
}
