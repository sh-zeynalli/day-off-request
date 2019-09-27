package com.example.socar.persistance.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Zeynalli on 05-Sep-19.
 */

@Entity
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "e_id")
    private Employees employees;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime date;

    private String reason;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "t_id")
    private PType type;

    private Date startDate;

    private Date endDate;

    private Time startTime;

    private Time endTime;

    private Long toRole;

    private Long status;

    private String description;

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Permission(Employees employees, LocalDateTime date, String reason, PType type, Date startDate, Date endDate, Time startTime, Time endTime, Long toRole, Long status, String description) {

        this.employees = employees;
        this.date = date;
        this.reason = reason;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.toRole = toRole;
        this.status = status;
        this.description = description;
    }
    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", employees=" + employees +
                ", date=" + date +
                ", reason='" + reason + '\'' +
                ", type=" + type +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", toRole=" + toRole +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }

    public Long getTo() {
        return toRole;
    }

    public void setTo(Long to) {
        this.toRole = to;
    }

    public Permission() {
    }

    public Long getId() {

        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public PType getType() {
        return type;
    }

    public void setType(PType type) {
        this.type = type;
    }

   }
