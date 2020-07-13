package me.ljseokd.studyspringbootwebapp.zone;

import me.ljseokd.studyspringbootwebapp.domain.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
}
