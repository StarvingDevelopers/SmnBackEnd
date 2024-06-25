package tech.starvingdevelopers.smnbackend.exceptions.friends;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import tech.starvingdevelopers.smnbackend.exceptions.GeneralException;

public class FriendNotFoundException extends GeneralException {
    private final String detail;

    public FriendNotFoundException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Friend not found");
        problemDetail.setDetail(detail);
        return problemDetail;
    }
}
