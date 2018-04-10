package com.example.a501_09.myportfolio_chungnam.db;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

/**
 * Entity mapped to table "TRIP".
 */
@Entity
public class Trip {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private java.util.Date start_day;

    @NotNull
    private java.util.Date end_day;

    @NotNull
    private java.util.Date created_at;

    @NotNull
    private java.util.Date update_at;
    private String title;
    private Integer number_of_member;
    private Long total_money;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    @Generated
    public Trip() {
    }

    public Trip(Long id) {
        this.id = id;
    }

    @Generated
    public Trip(Long id, java.util.Date start_day, java.util.Date end_day, java.util.Date created_at, java.util.Date update_at, String title, Integer number_of_member, Long total_money) {
        this.id = id;
        this.start_day = start_day;
        this.end_day = end_day;
        this.created_at = created_at;
        this.update_at = update_at;
        this.title = title;
        this.number_of_member = number_of_member;
        this.total_money = total_money;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public java.util.Date getStart_day() {
        return start_day;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setStart_day(@NotNull java.util.Date start_day) {
        this.start_day = start_day;
    }

    @NotNull
    public java.util.Date getEnd_day() {
        return end_day;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setEnd_day(@NotNull java.util.Date end_day) {
        this.end_day = end_day;
    }

    @NotNull
    public java.util.Date getCreated_at() {
        return created_at;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCreated_at(@NotNull java.util.Date created_at) {
        this.created_at = created_at;
    }

    @NotNull
    public java.util.Date getUpdate_at() {
        return update_at;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setUpdate_at(@NotNull java.util.Date update_at) {
        this.update_at = update_at;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumber_of_member() {
        return number_of_member;
    }

    public void setNumber_of_member(Integer number_of_member) {
        this.number_of_member = number_of_member;
    }

    public Long getTotal_money() {
        return total_money;
    }

    public void setTotal_money(Long total_money) {
        this.total_money = total_money;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}