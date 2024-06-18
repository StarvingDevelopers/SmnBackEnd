package tech.starvingdevelopers.smnbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class GeneralException extends RuntimeException {
    public ProblemDetail toProblemDetail() {
        ProblemDetail problemaDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemaDetail.setTitle("Smn Internal Server Error");
        return problemaDetail;
    }
}
