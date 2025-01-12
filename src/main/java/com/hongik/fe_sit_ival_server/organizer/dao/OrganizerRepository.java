package com.hongik.fe_sit_ival_server.organizer.dao;

import com.hongik.fe_sit_ival_server.organizer.domain.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizerRepository extends JpaRepository<Organizer, Long> {}
