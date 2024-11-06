package ch.zhaw.freelancer4u.model.voucher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import ch.zhaw.freelancer4u.model.Job;

public class PercentageVoucherMockitoTest {
 

    // Aufgabe 7c)
    @Test
    public void testGetDiscount() {
        Job job1 = mock(Job.class);
        Job job2 = mock(Job.class);

        when(job1.getEarnings()).thenReturn(100.0);
        when(job2.getEarnings()).thenReturn(200.0);

        List<Job> jobs = Arrays.asList(job1, job2);
        PercentageVoucher voucher = new PercentageVoucher(10);

        assertEquals(30.0, voucher.getDiscount(jobs), 0.01);
    }
}
