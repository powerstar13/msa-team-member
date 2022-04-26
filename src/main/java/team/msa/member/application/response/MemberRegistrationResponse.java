package team.msa.member.application.response;

import lombok.*;
import team.msa.member.presentation.shared.response.CreatedSuccessResponse;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberRegistrationResponse extends CreatedSuccessResponse {

    private int memberId; // 회원 고유번호
}
