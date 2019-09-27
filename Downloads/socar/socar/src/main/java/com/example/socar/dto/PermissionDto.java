package com.example.socar.dto;

import com.example.socar.persistance.entity.Permission;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Zeynalli on 05-Sep-19.
 */
public class PermissionDto {

    private Long p_Id;

    private Long status = Long.valueOf(0); //default/accepted 0,  rejected -1

    private String description; //accepted null, rejected reason

    private EmployeesDto employees;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime date;

    @NotNull
    @Size(min = 1, message = "Empty Field")
    private String reason;

    @NotNull
    private PTypeDto type;

    @NotEmpty
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startDate;
    @NotEmpty
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date endDate;
    @NotEmpty
    private Time startTime;
    @NotEmpty
    private Time endTime;

    private Long toRole; // icazenin getdiyi shexs- her defe deyishilir digerine oturuldukce

    public PermissionDto() {
    }

    public PermissionDto(Long p_Id, Long status, String description, EmployeesDto employees, LocalDateTime date, @NotNull @Size(min = 1, message = "Empty Field") String reason, @NotNull PTypeDto type, @NotEmpty Date startDate, @NotEmpty Date endDate, @NotEmpty Time startTime, @NotEmpty Time endTime, Long toRole) {
        this.p_Id = p_Id;
        this.status = status;
        this.description = description;
        this.employees = employees;
        this.date = date;
        this.reason = reason;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.toRole = toRole;
    }

    public PermissionDto adapter(Permission p) {

        PTypeDto typeDto = new PTypeDto();
        EmployeesDto edto = new EmployeesDto();

        PermissionDto dto = new PermissionDto(p.getId(),
                p.getStatus(),
                p.getDescription(),
                edto.adapter(p.getEmployees()),
                p.getDate(),
                p.getReason(),
                typeDto.adapter(p.getType()),
                p.getStartDate(),
                p.getEndDate(),
                p.getStartTime(),
                p.getEndTime(),
                p.getTo());
        return dto;
    }

    @Override
    public String toString() {

        return "PermissionDto{" +
                "p_Id=" + p_Id +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", employees=" + employees +
                ", date=" + date +
                ", reason='" + reason + '\'' +
                ", type=" + type +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", toRole=" + toRole +
                '}';
    }


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

    public Long getTo() {
        return toRole;
    }

    public void setTo(Long toRole) { this.toRole = toRole; }

    public Long getP_Id() {
        return p_Id;
    }

    public void setP_Id(Long p_Id) {
        this.p_Id = p_Id;
    }

    public EmployeesDto getEmployees() {
        return employees;
    }

    public void setEmployees(EmployeesDto employees) {
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

    public PTypeDto getType() {
        return type;
    }

    public void setType(PTypeDto type) {
        this.type = type;
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


}
