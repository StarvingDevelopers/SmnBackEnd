package tech.starvingdevelopers.smnbackend.exceptions.friends;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import tech.starvingdevelopers.smnbackend.exceptions.GeneralException;

public class FriendRequestAlreadyExistsException extends GeneralException {

    @Override
    public ProblemDetail toProblemDetail() {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        problemDetail.setTitle("Friend request already exists");
        return problemDetail;
    }
}
