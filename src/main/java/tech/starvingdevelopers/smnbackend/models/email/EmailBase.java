package tech.starvingdevelopers.smnbackend.models.email;

import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Getter
public abstract class EmailBase {
    private final String to;
    private final EmailType emailType;

    public EmailBase(String to, EmailType emailType) {
        this.to = to;
        this.emailType = emailType;
    }

    public String getEmailContent() throws IOException {
        return Files.readString(Path.of(this.emailType.getEmailResourcePath()));
    }

    public abstract String getFormattedEmailContent();
}
