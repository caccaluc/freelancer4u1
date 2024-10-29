package ch.zhaw.freelancer4u.model.voucher;

import org.junit.jupiter.api.Test;

import ch.zhaw.freelancer4u.model.Job;


import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FiveBucksVoucherTest {

    @Test
    public void testEmpty() {
        FiveBucksVoucher voucher = new FiveBucksVoucher();
        double discount = voucher.getDiscount(Collections.emptyList());
        assertEquals(0.0, discount, "Discount should be 0.0 for empty job list");
    }

    @Test
    public void testTen() {
        FiveBucksVoucher voucher = new FiveBucksVoucher();
        Job job = new Job(10); // Angenommen, die Job-Klasse hat einen Konstruktor, der Earnings setzt
        double discount = voucher.getDiscount(Collections.singletonList(job));
        assertEquals(5.0, discount, "Discount should be 5.0 when earnings are 10.0");
    }
}
