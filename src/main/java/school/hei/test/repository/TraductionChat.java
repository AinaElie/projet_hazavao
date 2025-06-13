package school.hei.test.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import school.hei.test.model.TraductionResponse;

import java.util.List;
import java.util.Map;

@Getter
@Repository
public class TraductionChat {
    private final WebClient webClient;
    public TraductionChat(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://api.openai.com").build();
    }

    public TraductionResponse response(String mot) {
        String prompt = "Traduis ce mot en malgache seulement: \"" + mot + "\"";
        String openApiKey = System.getenv("OPENAI_API_KEY");
        String traduction;
        String responseChat;

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-3.5-turbo",
                "messages", List.of(
                        Map.of("role", "user", "content", prompt)
                )
        );

        try{
            responseChat = webClient.post()
                    .uri("/v1/chat/completions")
                    .header("Authorization", "Bearer " + openApiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseChat);
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
