package com.intuit_interview.example.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Created by dhawangayash on 1/21/18.
 */

@Entity
@Table(name = "user")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;

    @Column(nullable = false)
    private String emailId;

    @Column
    private String phone;

    @Column
    private String address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private Set<Task> tasks = new HashSet<>();

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public long getUserId() {
        return id;
    }

    public void setUserId(long userId) {
        this.id = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        String tasks = this.tasks != null? getTasks().stream().map(x -> x.toString())
                .collect(Collectors.joining(", ")) : "NO TASKS";

        return "User{" +
                "userId=" + id +
                ", emailId='" + emailId + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}
