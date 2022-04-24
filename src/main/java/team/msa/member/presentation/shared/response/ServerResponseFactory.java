package team.msa.member.presentation.shared.response;

import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface ServerResponseFactory {

    Mono<ServerResponse> successOnly();
    Mono<ServerResponse> successBodyValue(Object response);
}
