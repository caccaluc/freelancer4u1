package ch.zhaw.freelancer4u.model.voucher;


import java.util.List;

import ch.zhaw.freelancer4u.model.Job;
import ch.zhaw.freelancer4u.model.JobType;


public class TwoForOneVoucher implements Voucher {
    private JobType jobType;

    public TwoForOneVoucher(JobType jobType) {
        this.jobType = jobType;
    }

    @Override
    public double getDiscount(List<Job> jobs) {
        long count = jobs.stream().filter(job -> job.getJobType() == jobType).count();
        double discount = jobs.stream()
            .filter(job -> job.getJobType() == jobType)
            .mapToDouble(Job::getEarnings)
            .sorted()
            .limit(count / 2)
            .sum();
        return discount;
        
    }
}