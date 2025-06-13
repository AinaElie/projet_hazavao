package school.hei.test.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TraductionResponse {
    private String word;
    private String result;
}
