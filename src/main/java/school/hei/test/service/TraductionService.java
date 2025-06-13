package school.hei.test.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import school.hei.test.model.TraductionResponse;

import java.awt.*;
import java.util.List;
import java.util.Map;

@Getter
@Service
public class TraductionService {
    private final WebClient webClient;
    public TraductionService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://api.openai.com").build();
    }

    public TraductionResponse traduire(String mot) {
        String prompt = "Traduis ce mot en malgache: \"" + mot + "\"";
        String openApiKey = System.getenv("OPENAI_API_KEY");

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-3.5-turbo",
                "messages", List.of(
                        Map.of("role", "user", "content", prompt)
                )
        );

        String responseJson = webClient.post()
                .uri("/v1/chat/completions")
                .header("Authorization", "Bearer " + openApiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // Parser la réponse JSON
        String traduction = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseJson);
            traduction = root
                    .path("choices")
                    .get(0)
                    .path("message")
                    .path("content")
                    .asText();
        } catch (Exception e) {
            traduction = "Erreur lors du parsing de la réponse : " + e.getMessage();
        }

        return new TraductionResponse(mot, traduction);
    }
}
