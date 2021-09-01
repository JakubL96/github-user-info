package jakub.linek.github.users;

import jakub.linek.github.users.service.CalculateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest()
public class CalculateServiceTest {

    @Autowired
    private CalculateService calculateService;

    @Test
    public void checkCalculationWithZeroReturnNaN() {
        double result = calculateService.calculate(0L, 0L);
        assert result != result;
    }

    @Test
    public void checkCalculation() {
        assert calculateService.calculate(3L, 1L) == 6;
    }
}
