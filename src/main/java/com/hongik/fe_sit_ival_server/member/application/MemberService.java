package com.hongik.fe_sit_ival_server.member.application;

import com.hongik.fe_sit_ival_server.global.exception.CustomException;
import com.hongik.fe_sit_ival_server.global.exception.ErrorCode;
import com.hongik.fe_sit_ival_server.member.dao.MemberRepository;
import com.hongik.fe_sit_ival_server.member.domain.Member;
import com.opencsv.CSVReader;
import java.io.InputStreamReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void saveMemberFromCsv(MultipartFile file) {
        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                // 첫 번째 줄이 헤더라면 스킵
                if (line[0].equals("id")) continue;

                Member member = Member.createMember(line[0], line[1], line[2]);
                // 중복 회원 검증 로직 필요
                memberRepository.save(member);
            }
        } catch (Exception e) {
            throw new CustomException(ErrorCode.METHOD_ARGUMENT_NOT_VALID);
        }
    }
}
