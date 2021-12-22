package com.yj.kiosk.service;

import com.yj.kiosk.domain.Member;
import com.yj.kiosk.dto.MemberDTO;
import com.yj.kiosk.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public List<MemberDTO> findAll(){
        List<Member> memberlist = memberRepository.findAll();
        List<MemberDTO> members = new ArrayList<>();
        for(Member member : memberlist){
            members.add(new MemberDTO(member.getId(),member.getName(),member.getEmail(),member.getPicture(),member.getRole()));
        }
        return members;
    }
}
