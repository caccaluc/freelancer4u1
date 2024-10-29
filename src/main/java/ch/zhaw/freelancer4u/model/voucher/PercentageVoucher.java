package ch.zhaw.freelancer4u.model.voucher;

import java.util.List;

import ch.zhaw.freelancer4u.model.Job;


    public class PercentageVoucher implements Voucher {
    private int discountPercentage;

    public PercentageVoucher(int discount) {
        this.discountPercentage = discount;
    }

    @Override
    public double getDiscount(List<Job> jobs) {
        if (jobs.isEmpty()) return 0.0;
        double totalEarnings = jobs.stream().mapToDouble(Job::getEarnings).sum();
        return (totalEarnings * discountPercentage) / 100.0;
    }
}
    
