package tech.starvingdevelopers.smnbackend.exceptions.friends;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import tech.starvingdevelopers.smnbackend.exceptions.GeneralException;

public class FriendRequestNotFoundException extends GeneralException {
    private final String detail;

    public FriendRequestNotFoundException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Friend Request Not Found");
        problemDetail.setDetail(detail);
        return problemDetail;
    }
}
