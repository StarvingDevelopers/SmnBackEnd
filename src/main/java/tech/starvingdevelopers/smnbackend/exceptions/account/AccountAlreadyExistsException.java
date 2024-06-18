package tech.starvingdevelopers.smnbackend.exceptions.account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import tech.starvingdevelopers.smnbackend.exceptions.GeneralException;

public class AccountAlreadyExistsException extends GeneralException {
    private final String detail;

    public AccountAlreadyExistsException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        problemDetail.setTitle("Account Already Exists");
        problemDetail.setDetail(detail);
        return problemDetail;
    }
}
