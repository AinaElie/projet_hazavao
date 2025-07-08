package school.hei.test.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import school.hei.test.repository.TraductionChat;

@Getter
@Service
public class TraductionService {
  private final TraductionChat traductionChat;

  public TraductionService(TraductionChat traductionChat) {
    this.traductionChat = traductionChat;
  }

  public String traduire(String mot) {
    return traductionChat.response(mot);
  }
}
