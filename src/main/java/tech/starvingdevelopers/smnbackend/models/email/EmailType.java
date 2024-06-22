package tech.starvingdevelopers.smnbackend.models.email;

import lombok.Getter;

@Getter
public enum EmailType {
    WELCOME_EMAIL("Seja Bem-Vindo(a) ao SMN!", "src/main/resources/templates/welcomeEmail.html"),
    RECOVERY_EMAIL("Recuperação de Senha!", "../resources/templates/recoveryEmail.html"),
    PASSWORD_CHANGED_EMAIL("Senha Alterada!", "../resources/templates/passwordChangedEmail.html"),
    AD_EMAIL("Convide Seus Amigos!", "../resources/templates/adEmail.html");

    private final String title;
    private final String emailResourcePath;

    EmailType(String title, String emailResourcePath) {
        this.title = title;
        this.emailResourcePath = emailResourcePath;
    }
}
