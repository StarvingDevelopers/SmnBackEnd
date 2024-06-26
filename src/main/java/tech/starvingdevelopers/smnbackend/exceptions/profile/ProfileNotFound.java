package tech.starvingdevelopers.smnbackend.exceptions.profile;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import tech.starvingdevelopers.smnbackend.exceptions.GeneralException;

public class ProfileNotFound extends GeneralException {
    private final String detail;

    public ProfileNotFound(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Profile not found");
        problemDetail.setDetail(detail);
        return problemDetail;
    }
}
