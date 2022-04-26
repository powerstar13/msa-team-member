package team.msa.member.application.response;

import lombok.*;
import team.msa.member.domain.model.member.MemberType;
import team.msa.member.presentation.shared.response.SuccessResponse;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoResponse extends SuccessResponse {

    private int memberId; // 회원 고유번호

    private MemberType memberType;
}
