package me.ljseokd.studyspringbootwebapp.settings;

import me.ljseokd.studyspringbootwebapp.WithAccount;
import me.ljseokd.studyspringbootwebapp.account.AccountRepository;
import me.ljseokd.studyspringbootwebapp.domain.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SettingsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @AfterEach
    void afterEach(){
        accountRepository.deleteAll();
    }


    @WithAccount("ljseokd")
    @DisplayName("프로필 수정 폼")
    @Test
    void updateProfileForm() throws Exception {
        mockMvc.perform(get(SettingsController.SETTINGS_PROFILE_URL))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("profile"));
    }

    @WithAccount("ljseokd")
    @DisplayName("프로필 수정 하기 - 입력값 정상")
    @Test
    void updateProfile() throws Exception {
        String bio = "짧은 소개를 수정하는 경우.";
        mockMvc.perform(post(SettingsController.SETTINGS_PROFILE_URL)
                .param("bio", bio)
        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(SettingsController.SETTINGS_PROFILE_URL))
                .andExpect(flash().attributeExists("message"));

        Account ljseokd = accountRepository.findByNickname("ljseokd");
        assertEquals(bio, ljseokd.getBio());
    }
    @WithAccount("ljseokd")
    @DisplayName("프로필 수정 하기 - 입력값 에러")
    @Test
    void updateProfile_error() throws Exception {
        String bio = "길게 소개를 수정하는 경우 길게 소개를 수정하는 경우 길게 소개를 수정하는 경우 길게 소개를 수정하는 경우 길게 소개를 수정하는 경우.";
        mockMvc.perform(post(SettingsController.SETTINGS_PROFILE_URL)
                .param("bio", bio)
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name(SettingsController.SETTINGS_PROFILE_VIEW_NAME))
                .andExpect(model().hasErrors())
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("profile"));

        Account ljseokd = accountRepository.findByNickname("ljseokd");
        assertNull(ljseokd.getBio());
    }


    @WithAccount("ljseokd")
    @DisplayName("패스워드 수정 폼")
    @Test
    void updatePasswordForm() throws Exception {
        mockMvc.perform(get(SettingsController.SETTINGS_PASSWORD_URL))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("passwordForm"));
    }

    @WithAccount("ljseokd")
    @DisplayName("패스워드 수정 하기 - 입력값 정상")
    @Test
    void updatePassword() throws Exception {
        mockMvc.perform(post(SettingsController.SETTINGS_PASSWORD_URL)
                .param("newPassword", "12345678")
                .param("newPasswordConfirm", "12345678")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(SettingsController.SETTINGS_PASSWORD_URL))
                .andExpect(flash().attributeExists("message"));

        Account ljseokd = accountRepository.findByNickname("ljseokd");
        assertTrue(passwordEncoder.matches("12345678", ljseokd.getPassword()));
    }

    @WithAccount("ljseokd")
    @DisplayName("패스워드 수정 하기 - 입력값 에러")
    @Test
    void updatePassword_error() throws Exception {
        mockMvc.perform(post(SettingsController.SETTINGS_PASSWORD_URL)
                .param("newPassword", "12345678")
                .param("newPasswordConfirm", "1111111")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name(SettingsController.SETTINGS_PASSWORD_VIEW_NAME))
                .andExpect(model().hasErrors())
                .andExpect(model().attributeExists("passwordForm"))
                .andExpect(model().attributeExists("account"));

        Account ljseokd = accountRepository.findByNickname("ljseokd");
    }

    @WithAccount("ljseokd")
    @DisplayName("알림 수정 폼")
    @Test
    void updateNotificationsForm() throws Exception {
        mockMvc.perform(get(SettingsController.SETTINGS_NOTIFICATIONS_URL))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("notifications"));
    }

    @WithAccount("ljseokd")
    @DisplayName("알림 수정 하기 - 입력값 정상")
    @Test
    void updateNotifications() throws Exception {
        mockMvc.perform(post(SettingsController.SETTINGS_NOTIFICATIONS_URL)
                .param("studyCreatedByEmail", "true")
                .param("studyCreatedByWeb", "true")
                .param("studyEnrollmentResultByEmail", "true")
                .param("studyEnrollmentResultByWeb", "true")
                .param("studyUpdatedByEmail", "true")
                .param("studyUpdatedByWeb", "true")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(SettingsController.SETTINGS_NOTIFICATIONS_URL))
                .andExpect(flash().attributeExists("message"));

        Account ljseokd = accountRepository.findByNickname("ljseokd");
        assertTrue(ljseokd.isStudyCreatedByEmail());
        assertTrue(ljseokd.isStudyCreatedByWeb());
        assertTrue(ljseokd.isStudyUpdatedByEmail());
        assertTrue(ljseokd.isStudyUpdatedByWeb());
        assertTrue(ljseokd.isStudyEnrollmentResultByEmail());
        assertTrue(ljseokd.isStudyEnrollmentResultByWeb());
    }

    @WithAccount("ljseokd")
    @DisplayName("알림 수정 하기 - 입력값 에러")
    @Test
    void updateNotifications_error() throws Exception {
        mockMvc.perform(post(SettingsController.SETTINGS_NOTIFICATIONS_URL)
                .param("studyCreatedByEmail", "true")
                .param("studyCreatedByWeb", "true")
                .param("studyEnrollmentResultByEmail", "true")
                .param("studyEnrollmentResultByWeb", "true")
                .param("studyUpdatedByEmail", "true")
                .param("studyUpdatedByWeb", "true123")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name(SettingsController.SETTINGS_NOTIFICATIONS_VIEW_NAME))
                .andExpect(model().hasErrors())
                .andExpect(model().attributeExists("notifications"))
                .andExpect(model().attributeExists("account"));

    }

    @WithAccount("ljseokd")
    @DisplayName("닉네임 수정 폼")
    @Test
    void updateNicknameForm() throws Exception {
        mockMvc.perform(get(SettingsController.SETTINGS_ACCOUNT_URL))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("nicknameForm"));
    }

    @WithAccount("ljseokd")
    @DisplayName("닉네임 수정 하기 - 입력값 정상")
    @Test
    void updateNickname() throws Exception {
        mockMvc.perform(post(SettingsController.SETTINGS_ACCOUNT_URL)
                .param("nickname", "ljseokd2")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(SettingsController.SETTINGS_ACCOUNT_URL))
                .andExpect(flash().attributeExists("message"));

        Account ljseokd = accountRepository.findByNickname("ljseokd2");
        assertNotNull(ljseokd);
    }

    @WithAccount("ljseokd")
    @DisplayName("닉네임 수정 하기 - 입력값 에러")
    @Test
    void updateNickname_error() throws Exception {
        mockMvc.perform(post(SettingsController.SETTINGS_ACCOUNT_URL)
                .param("nickname", "ljseokd")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name(SettingsController.SETTINGS_ACCOUNT_VIEW_NAME))
                .andExpect(model().hasErrors())
                .andExpect(model().attributeExists("nicknameForm"))
                .andExpect(model().attributeExists("account"));

    }



}