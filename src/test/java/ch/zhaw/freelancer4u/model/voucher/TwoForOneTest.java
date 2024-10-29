package ch.zhaw.freelancer4u.model.voucher;

import org.junit.jupiter.api.Test;

import ch.zhaw.freelancer4u.model.Job;
import ch.zhaw.freelancer4u.model.JobType;


import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TwoForOneTest {

    @Test
    void testTwoForOneVoucher() {
        TwoForOneVoucher voucher = new TwoForOneVoucher(JobType.IMPLEMENT);
        Job job1 = new Job(77, JobType.IMPLEMENT);
        Job job2 = new Job(33, JobType.TEST);
        Job job3 = new Job(33, JobType.IMPLEMENT);

        double discount = voucher.getDiscount(Arrays.asList(job1, job2, job3));
        assertEquals(77.0, discount);  // Erwartet ist, dass ein IMPLEMENT Job frei ist
    }
}
