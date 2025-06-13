package school.hei.test.conf;

import org.springframework.test.context.DynamicPropertyRegistry;
import school.hei.test.PojaGenerated;

@PojaGenerated
public class BucketConf {

  void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("aws.s3.bucket", () -> "dummy-bucket");
  }
}
