package me.ljseokd.studyspringbootwebapp.study;

import lombok.RequiredArgsConstructor;
import me.ljseokd.studyspringbootwebapp.domain.Account;
import me.ljseokd.studyspringbootwebapp.domain.Study;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class StudyService {

    private final StudyRepository studyRepository;

    public Study createNewStudy(Study study, Account account) {
        Study newStudy = studyRepository.save(study);
        newStudy.addManager(account);
        return newStudy;

    }
}
