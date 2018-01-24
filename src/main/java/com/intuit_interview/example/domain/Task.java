package com.intuit_interview.example.domain;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by dhawangayash on 1/21/18.
 */


@Entity
@Table(name = "task")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id")
    private long id;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    @JsonBackReference
//    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    @Column(name = "user_id")
    private long user_id;

    @Column( nullable = false)
    private String taskObjectStoreURL;

    @Column
    private String dueTime;

    @Column
    private String reminderTime;

    public long getTaskId() {
        return id;
    }

    public void setTaskId(long taskId) {
        this.id = taskId;
    }

//    @ManyToOne
//    @JoinColumn(name = "user")
//    public User getUserId() {
//        return user;
//    }
//
//    public void setUserId(User user) {
//        this.user = user;
//    }

    public Task() {}

    public Task(User userId, String taskObjectStoreURL, String creationTime, String lastUpdateTime) {
//        this.user = userId;
        this.taskObjectStoreURL = taskObjectStoreURL;
        this.dueTime = creationTime;
        this.reminderTime = lastUpdateTime;
    }

    public String getTaskObjectStoreURL() {
        return taskObjectStoreURL;
    }

    public void setTaskObjectStoreURL(String taskObjectStoreURL) {
        this.taskObjectStoreURL = taskObjectStoreURL;
    }

    public String getDueTime() {
        return dueTime;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + id +
                ", userId=" + user_id +
                ", taskObjectStoreURL='" + taskObjectStoreURL + '\'' +
                ", dueTime='" + dueTime + '\'' +
                ", reminderTime='" + reminderTime + '\'' +
                '}';
    }

}
