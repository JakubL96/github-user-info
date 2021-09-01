package jakub.linek.github.users;

import jakub.linek.github.users.dao.StatisticRepo;
import jakub.linek.github.users.entity.Statistic;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StatisticRepositoryTests {
    @Autowired
    private StatisticRepo statisticRepo;

    @Test
    void testCounterIncrementation() throws InterruptedException {
        String login = "testLogin";

        final ExecutorService executor = Executors.newFixedThreadPool(100);

        for (int i = 0; i<100; i++) {
            executor.execute(() -> statisticRepo.incrementCounter(login));
        }

        executor.shutdown();
        executor.awaitTermination(30, TimeUnit.SECONDS);

        Optional<Statistic> statistic = statisticRepo.findById(login);

        assert statistic.get().getRequestCount() == 100;
    }
}
