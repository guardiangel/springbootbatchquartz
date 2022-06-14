package io.spring.batch;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Created by Abderrahmen on 08/06/2020.
 */
@SpringBootApplication
@EnableBatchProcessing(modular = false)
@EntityScan("io.spring.batch.domain")
public class SpringQuartzIntegration {
  public static void main(String[] args) {
    SpringApplication.run(SpringQuartzIntegration.class, args);
  }
}
