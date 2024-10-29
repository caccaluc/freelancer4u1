package ch.zhaw.freelancer4u.model.voucher;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.zhaw.freelancer4u.model.Job;



import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PercentageVoucherTest {

    @ParameterizedTest
    @CsvSource({
        "50, 100, 50.0", // 50% von 100
        "25, 200, 50.0", // 25% von 200
        "100, 75, 75.0", // 100% von 75
        "0, 100, 0.0",   // 0% von 100
        "10, 0, 0.0"     // 10% von 0
    })
    void testPercentageVoucher(int discount, double earnings, double expected) {
        PercentageVoucher voucher = new PercentageVoucher(discount);
        Job job = new Job(earnings);  // Angenommen, Job hat einen Konstruktor, der Earnings setzt
        double actual = voucher.getDiscount(Collections.singletonList(job));
        assertEquals(expected, actual, 0.01);
    }
}
