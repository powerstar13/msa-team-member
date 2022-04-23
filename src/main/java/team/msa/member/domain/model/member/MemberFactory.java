package team.msa.member.domain.model.member;

import java.util.List;

public interface MemberFactory {

    Member memberBuilder(String memberName, String memberPassword, MemberType memberType); // 운영자 계정 생성
    List<Member> adminSetUpListBuilder(); // 초기 사이트 운영자 데이터 세팅
}
