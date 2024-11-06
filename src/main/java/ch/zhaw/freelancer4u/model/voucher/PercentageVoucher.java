package ch.zhaw.freelancer4u.model.voucher;

import java.util.List;

import ch.zhaw.freelancer4u.model.Job;

public class PercentageVoucher implements Voucher {

    private int discount = 0;


    //Aufgabe 7a
    public PercentageVoucher(int discount) {
        if (discount > 50) {
            throw new RuntimeException("Error: Discount value must less or equal 50.");
        }
        if (discount <= 0) {
            throw new RuntimeException("Error: Discount value must be greater zero.");
        }
        this.discount = discount;
    }

    @Override
    public double getDiscount(List<Job> jobs) {
        var totalPrice = jobs.stream().mapToDouble(p -> p.getEarnings()).sum();
        return totalPrice * ((double) discount / 100);
    }

    public int getDiscountPercentage() {

        return discount;

    }
}
