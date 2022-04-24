package team.msa.member.application.response;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberRegistrationResponse {

    private int memberId; // 생성된 회원 고유번호
}
