package io.spring.batch.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "springbootbatchquartz2.qrtz_triggers")
@IdClass(QurzTriggerPk.class)

public class QurzTriggerEntity implements Serializable {

    @Id//这个注解很重要，是联合主键其中的一个
    @Column(name = "TRIGGER_NAME", nullable = false,unique = false)
    private String jobTrigger;

    @Id//这个注解也很重要，是联合主键的另外一个
    @Column(name = "JOB_NAME", nullable = false)
    private String jobName;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobTrigger() {
        return jobTrigger;
    }

    public void setJobTrigger(String jobTrigger) {
        this.jobTrigger = jobTrigger;
    }

    @Override
    public String toString() {
        return "QutzTriggerEntity{" +
                "jobTrigger='" + jobTrigger + '\'' +
                ", jobName='" + jobName + '\'' +
                '}';
    }
}
