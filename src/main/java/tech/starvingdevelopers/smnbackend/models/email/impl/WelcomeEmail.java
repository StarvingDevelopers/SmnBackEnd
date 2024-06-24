package tech.starvingdevelopers.smnbackend.models.email.impl;

import tech.starvingdevelopers.smnbackend.models.email.EmailBase;
import tech.starvingdevelopers.smnbackend.models.email.EmailType;

public class WelcomeEmail extends EmailBase {
    private final String nickname;

    public WelcomeEmail(String to, EmailType emailType, String nickname) {
        super(to, emailType);
        this.nickname = nickname;
    }

    @Override
    public String getFormattedEmailContent() {
        try {
            String content = this.getEmailContent();
            content = content.replaceAll("@\\{nickname}", nickname);

            return content;
        } catch (Exception exception) {
            exception.printStackTrace(System.err);
        }
        return null;
    }
}
