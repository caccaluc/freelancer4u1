package ch.zhaw.freelancer4u.model.voucher;

import java.util.List;

import ch.zhaw.freelancer4u.model.Job;

public class PercentageVoucher implements Voucher {

    private int discount = 0;

    public PercentageVoucher(int discount) {
        this.discount = discount;
    }

    @Override
    public double getDiscount(List<Job> jobs) {
        var totalPrice = jobs.stream().mapToDouble(p -> p.getEarnings()).sum();
        return totalPrice * ((double) discount / 100);
    }

}
