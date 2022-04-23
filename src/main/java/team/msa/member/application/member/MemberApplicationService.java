package team.msa.member.application.member;

import team.msa.member.domain.model.member.Member;

import java.util.Map;

public interface MemberApplicationService {

    // 회원가입
    Member SignUp(Map<String, String> param);

    // 로그인
    Member Login(Map<String, String> param);

    // 강사 생성 (관리자만)
    Member SetUpLecturer(Map<String, String> param);

    // 회원정보 수정
    Member EditMemberInfo(String id);

    // 회원정보 제공
    Member GetMemberInfo(String id);

}