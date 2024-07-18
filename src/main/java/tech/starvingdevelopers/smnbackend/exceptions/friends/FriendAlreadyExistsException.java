package tech.starvingdevelopers.smnbackend.exceptions.friends;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import tech.starvingdevelopers.smnbackend.exceptions.GeneralException;

public class FriendAlreadyExistsException extends GeneralException {
    private final String detail;

    public FriendAlreadyExistsException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        problemDetail.setTitle("Friend already exists");
        problemDetail.setDetail(detail);
        return problemDetail;
    }
}
