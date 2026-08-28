package com.Job_Portal.repositry;

import com.Job_Portal.entity.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JobRepository extends MongoRepository<Job, Long> {

      public List<Job> findByPostedBy(Long id);

}
