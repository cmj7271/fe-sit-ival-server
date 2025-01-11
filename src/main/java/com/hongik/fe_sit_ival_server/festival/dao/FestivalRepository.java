package com.hongik.fe_sit_ival_server.festival.dao;

import com.hongik.fe_sit_ival_server.festival.domain.Festival;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FestivalRepository extends JpaRepository<Festival, Long> {}
