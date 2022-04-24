package team.msa.member.presentation.shared.response;

import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import team.msa.member.domain.model.member.Member;

public interface ServerResponseFactory {

    Mono<ServerResponse> successOnly();
    Mono<ServerResponse> successBodyValue(Object response);
}
