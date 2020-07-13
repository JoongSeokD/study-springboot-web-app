package me.ljseokd.studyspringbootwebapp.settings;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.ljseokd.studyspringbootwebapp.domain.Account;

@Data
@NoArgsConstructor
public class Notifications {

    private boolean studyCreatedByEmail;

    private boolean studyCreatedByWeb;

    private boolean studyEnrollmentResultByEmail;

    private boolean studyEnrollmentResultByWeb;

    private boolean studyUpdatedByEmail;

    private boolean studyUpdatedByWeb;

    public Notifications(Account account) {
        studyCreatedByEmail = account.isStudyCreatedByEmail();
        studyCreatedByWeb = account.isStudyCreatedByWeb();
        studyEnrollmentResultByEmail = account.isStudyEnrollmentResultByEmail();
        studyEnrollmentResultByWeb = account.isStudyEnrollmentResultByWeb();
        studyUpdatedByEmail = account.isStudyUpdatedByEmail();
        studyUpdatedByWeb = account.isStudyUpdatedByWeb();
    }
}
