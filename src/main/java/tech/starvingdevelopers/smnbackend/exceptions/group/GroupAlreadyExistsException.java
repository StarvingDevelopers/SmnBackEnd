package tech.starvingdevelopers.smnbackend.exceptions.group;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import tech.starvingdevelopers.smnbackend.exceptions.GeneralException;

public class GroupAlreadyExistsException extends GeneralException {
    private final String detail;

    public GroupAlreadyExistsException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        problemDetail.setTitle("Group Already Exists");
        problemDetail.setDetail(detail);
        return problemDetail;
    }
}
