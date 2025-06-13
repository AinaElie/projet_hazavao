package school.hei.test.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import school.hei.test.model.TraductionResponse;
import school.hei.test.repository.TraductionChat;

import java.util.List;
import java.util.Map;

@Getter
@Service
public class TraductionService {
    private final TraductionChat traductionChat;
    public TraductionService(TraductionChat traductionChat) {
        this.traductionChat = traductionChat;
    }

    public TraductionResponse traduire(String mot) {
        return traductionChat.response(mot);
    }
}
