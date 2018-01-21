package com.khoubyari.example.domain;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "task")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Task {

    @Id
    @GeneratedValue
    private long taskId;

    @Column
    private long userId;

    @Column( nullable = false)
    private String taskObjectStoreURL;

    @Column( nullable = false)
    private String creationTime;

    @Column()
    private String lastUpdateTime;

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

//    @ManyToOne
//    @JoinColumn(name = "user")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Task() {}

    public Task(long userId, String taskObjectStoreURL, String creationTime, String lastUpdateTime) {
        this.userId = userId;
        this.taskObjectStoreURL = taskObjectStoreURL;
        this.creationTime = creationTime;
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getTaskObjectStoreURL() {
        return taskObjectStoreURL;
    }

    public void setTaskObjectStoreURL(String taskObjectStoreURL) {
        this.taskObjectStoreURL = taskObjectStoreURL;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", userId=" + userId +
                ", taskObjectStoreURL='" + taskObjectStoreURL + '\'' +
                ", creationTime='" + creationTime + '\'' +
                ", lastUpdateTime='" + lastUpdateTime + '\'' +
                '}';
    }

}
