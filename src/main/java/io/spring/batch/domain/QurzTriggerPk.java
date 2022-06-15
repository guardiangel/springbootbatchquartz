package io.spring.batch.domain;

import java.io.Serializable;

public class QurzTriggerPk implements Serializable {
    private String jobTrigger;
    private String jobName;

    public String getJobTrigger() {
        return jobTrigger;
    }

    public void setJobTrigger(String jobTrigger) {
        this.jobTrigger = jobTrigger;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public String toString() {
        return "QurzTriggerPk{" +
                "jobTrigger='" + jobTrigger + '\'' +
                ", jobName='" + jobName + '\'' +
                '}';
    }
}
