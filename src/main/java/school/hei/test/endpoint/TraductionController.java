package school.hei.test.endpoint;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import school.hei.test.service.TraductionService;

@Getter
@RestController
public class TraductionController {
    private final TraductionService traductionService;
    public TraductionController(TraductionService traductionService) {
        this.traductionService = traductionService;
    }
    @GetMapping("/hazavao")
    public String hazavao(@RequestParam String teny) {
        return traductionService.traduire(teny);
    }
}
