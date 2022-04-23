package team.msa.member.application.member;

import org.springframework.stereotype.Service;
import team.msa.member.domain.model.member.Member;

import java.util.Map;

@Service
public class MemberApplicationServiceImpl implements MemberApplicationService {

    @Override
    public Member SignUp(Map<String, String> param) {
        return null;
    }

    @Override
    public Member Login(Map<String, String> param) {
        return null;
    }

    @Override
    public Member SetUpLecturer(Map<String, String> param) {
        return null;
    }

    @Override
    public Member EditMemberInfo(String id) {
        return null;
    }

    @Override
    public Member GetMemberInfo(String id) {
        return null;
    }
}
