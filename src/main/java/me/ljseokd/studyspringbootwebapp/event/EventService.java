package me.ljseokd.studyspringbootwebapp.event;

import lombok.RequiredArgsConstructor;
import me.ljseokd.studyspringbootwebapp.domain.Account;
import me.ljseokd.studyspringbootwebapp.domain.Event;
import me.ljseokd.studyspringbootwebapp.domain.Study;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public Event createEvent(Event event, Study study, Account account) {
        event.setCreateBy(account);
        event.setCreatedDateTime(LocalDateTime.now());
        event.setStudy(study);
        return eventRepository.save(event);

    }
}
