package com.gunn.jys.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_teach_research_room")
public class TeachResearchRoom {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String roomName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName == null ? null : roomName.trim();
    }
}