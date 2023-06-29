package com.jobportal.JobPortal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "jobs")
public class JobPostModel {
    @Id
    private String id;
    private String profile;
    private String desc;
    private int salary;
    private int exp;

    private String companyName;
    private String companyLogo;

    private String techs[];

    private String createdBy;
}
