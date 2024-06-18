package tech.starvingdevelopers.smnbackend.exceptions.account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import tech.starvingdevelopers.smnbackend.exceptions.GeneralException;

public class AccountNotFoundByUsernameException extends GeneralException {
    private final String detail;

    public AccountNotFoundByUsernameException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Account not found");
        problemDetail.setDetail(detail);
        return problemDetail;
    }
}
