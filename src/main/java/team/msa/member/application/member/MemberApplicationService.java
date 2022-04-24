package team.msa.member.application.member;

import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;
import team.msa.member.application.response.MemberBlahBlahResponse;
import team.msa.member.domain.model.member.Member;

public interface MemberApplicationService {

    MemberBlahBlahResponse teacherRegistration(ServerRequest request); // 강사 등록

    MemberBlahBlahResponse studentRegistration(ServerRequest request); // 학생 회원 가입

    MemberBlahBlahResponse login(ServerRequest request); // 로그인

    Mono<Member> findMemberInfo(ServerRequest request); // 회원 정보 조회

}