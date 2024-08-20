package tech.starvingdevelopers.smnbackend.exceptions.group;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import tech.starvingdevelopers.smnbackend.exceptions.GeneralException;

public class ParticipantAlreadyExists extends GeneralException {
    private final String detail;

    public ParticipantAlreadyExists(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        problemDetail.setTitle("Participant already exists");
        problemDetail.setDetail(detail);
        return problemDetail;
    }
}
