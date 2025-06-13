package school.hei.test.conf;

import org.springframework.test.context.DynamicPropertyRegistry;
import school.hei.test.PojaGenerated;

@PojaGenerated
public class EmailConf {

  void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("aws.ses.source", () -> "dummy-ses-source");
  }
}
