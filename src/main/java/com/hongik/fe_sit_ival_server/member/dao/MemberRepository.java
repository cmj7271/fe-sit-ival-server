package com.hongik.fe_sit_ival_server.member.dao;

import com.hongik.fe_sit_ival_server.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {}
