package tech.starvingdevelopers.smnbackend.exceptions.account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import tech.starvingdevelopers.smnbackend.exceptions.GeneralException;

public class AccountPasswordIncorrectlyException extends GeneralException {

    @Override
    public ProblemDetail toProblemDetail() {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        problemDetail.setTitle("Account Password Incorrectly");
        return problemDetail;
    }
}
