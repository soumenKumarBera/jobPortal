package com.Job_Portal.repositry;

import com.Job_Portal.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Long> {

    public Optional<User> findByEmail(String email);

}
