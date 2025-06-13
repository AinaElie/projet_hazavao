package school.hei.test.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TraductionController {

    @GetMapping("/hazavao")
    public String hazavao(@RequestParam String teny) {
        return null;
    }
}
