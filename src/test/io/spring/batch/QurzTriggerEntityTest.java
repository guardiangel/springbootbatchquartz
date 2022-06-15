package io.spring.batch;

import io.spring.batch.repository.QutzTriggerEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class QurzTriggerEntityTest {

    @Autowired
    private QutzTriggerEntityRepository repository;

    @Test
    public void test() {
        repository.findAll().forEach(qutzTriggerEntity -> {
            System.err.println(qutzTriggerEntity);
        });
    }
}
