package ch.zhaw.freelancer4u.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Document("job")
public class Job {
    public Job(double d) {
        this.earnings = d;
    }

    public double getEarnings() {
        return this.earnings;
    }
    public Job(int i, JobType implement) {
        
    }
    @Id
    private String id;
    @NonNull
    private String description;
    @NonNull
    private JobType jobType; 
    @NonNull
    private Double earnings;
    private JobState jobState = JobState.NEW;
    @NonNull
    private String companyId;
    private String freelancerId;
}
