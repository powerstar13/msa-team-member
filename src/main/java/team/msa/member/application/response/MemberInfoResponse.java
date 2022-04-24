package team.msa.member.application.response;

import lombok.*;
import team.msa.member.domain.model.member.MemberType;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoResponse {

    private int memberId; // 생성된 회원 고유번호
    private MemberType memberType;
}
