package com.hongik.fe_sit_ival_server.member.api;

import com.hongik.fe_sit_ival_server.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> uploadCsv(@RequestParam("file") MultipartFile file) {
        memberService.saveMemberFromCsv(file);
        return ResponseEntity.ok().build();
    }
}
