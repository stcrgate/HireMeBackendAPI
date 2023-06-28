package com.jobportal.JobPortal;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JobPostRepo extends MongoRepository<JobPostModel, String> {
    @Query("{$or: [ {profile: {$regex: ?0, $options: 'i'}}, {desc: {$regex: ?0, $options: 'i'}}, {techs: {$regex: ?0, $options: 'i'}} ]}")
    List<JobPostModel> jobSearch(String author);

    @Query("{$and: [{_id: ?0}, {'fieldToUpdate': ?1}]}")
    void updateFieldById(String id, String updatedValue);
}
