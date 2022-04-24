package team.msa.member.application.member;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import team.msa.member.application.response.MemberBlahBlahResponse;

@Service
public class MemberApplicationServiceImpl implements MemberApplicationService {

    @Override
    public MemberBlahBlahResponse teacherRegistration(ServerRequest request) {
        return MemberBlahBlahResponse.builder().memberId(1).build();
    }

    @Override
    public MemberBlahBlahResponse studentRegistration(ServerRequest request) {
        return MemberBlahBlahResponse.builder().memberId(1).build();
    }

    @Override
    public MemberBlahBlahResponse login(ServerRequest request) {
        return MemberBlahBlahResponse.builder().memberId(1).build();
    }

    @Override
    public MemberBlahBlahResponse findMemberInfo(ServerRequest request) {
        return MemberBlahBlahResponse.builder().memberId(1).build();
    }
}
