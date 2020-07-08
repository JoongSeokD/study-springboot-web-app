package me.ljseokd.studyspringbootwebapp.account;

import me.ljseokd.studyspringbootwebapp.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<Account,Long> {
    boolean existsByEmail(String email);

    boolean existsByNickName(String nickname);
}