package ch.zhaw.freelancer4u.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import ch.zhaw.freelancer4u.model.Job;
import ch.zhaw.freelancer4u.model.JobType;
import ch.zhaw.freelancer4u.model.JobStateAggregation;

public interface JobRepository extends MongoRepository<Job,String>{
    List<Job> findByEarningsGreaterThan(Double earnings);
    List<Job> findByJobType(JobType jobType);
    List<Job> findByJobTypeAndEarningsGreaterThan(JobType jobType, Double earnings);

    @Aggregation({
        "{'$match': {'companyId': ?0}}",
        "{'$group': {'_id': '$jobState', 'jobIds': {'$push': '$_id'}, 'count': {'$count': {}}}}"
    })
    List<JobStateAggregation> getJobStateAggregation(String companyId);
}


