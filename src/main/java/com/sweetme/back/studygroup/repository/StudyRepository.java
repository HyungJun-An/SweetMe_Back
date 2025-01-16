package com.sweetme.back.studygroup.repository;

import com.sweetme.back.studygroup.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study,Long> {

}
