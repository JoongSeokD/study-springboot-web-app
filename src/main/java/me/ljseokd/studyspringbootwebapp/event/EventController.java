package me.ljseokd.studyspringbootwebapp.event;

import lombok.RequiredArgsConstructor;
import me.ljseokd.studyspringbootwebapp.account.CurrentUser;
import me.ljseokd.studyspringbootwebapp.domain.Account;
import me.ljseokd.studyspringbootwebapp.domain.Study;
import me.ljseokd.studyspringbootwebapp.event.form.EventForm;
import me.ljseokd.studyspringbootwebapp.study.StudyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/study/{path}")
public class EventController {

    private final StudyService studyService;

    @GetMapping("/new-event")
    public String newEventForm(@CurrentUser Account account, @PathVariable String path, Model model){
        Study study = studyService.getStudyToUpdateStatus(account, path);
        model.addAttribute(study);
        model.addAttribute(account);
        model.addAttribute(new EventForm());
        return "event/form";
    }


}
