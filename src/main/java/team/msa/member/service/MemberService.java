package team.msa.member.service;

import team.msa.member.domain.Member;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface MemberService {

    // 회원가입
    public Member SignUp(Map<String, String> param);

    // 로그인
    public Member Login(Map<String, String> param);

    // 강사 생성 (관리자만)
    public Member SetUpLecturer(Map<String, String> param);

    // 회원정보 수정
    public Member EditMemberInfo(String id);

    // 회원정보 제공
    public Member GetMemberInfo(String id);

}