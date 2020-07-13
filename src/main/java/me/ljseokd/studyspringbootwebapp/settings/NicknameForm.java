package me.ljseokd.studyspringbootwebapp.settings;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class NicknameForm {
    @NotNull
    @Length(min = 2, max = 20)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣-a-zA-Z0-9_-]{3,20}$")
    private String nickname;
}
