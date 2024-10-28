package com.libraryManagement.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
public class Room {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int roomId;
		
		private String roomName;
		
		@OneToMany(mappedBy = "room" , cascade = CascadeType.ALL,orphanRemoval = true)
		@JsonManagedReference
		private List<Row> rows;
		
		
		public int getRoomId() {
			return roomId;
		}
		public void setRoomId(int roomId) {
			this.roomId = roomId;
		}
		public String getRoomName() {
			return roomName;
		}
		public void setRoomName(String roomName) {
			this.roomName = roomName;
		}
		public List<Row> getRows() {
			return rows;
		}
		public void setRows(List<Row> rows) {
			this.rows = rows;
		}
		public Room(int roomId, String roomName, List<Row> rows) {
			super();
			this.roomId = roomId;
			this.roomName = roomName;
			this.rows = rows;
		}
		public Room() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
	}


=======

@Entity
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private int roomNo;
private String roomName;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getRoomNo() {
	return roomNo;
}
public void setRoomNo(int roomNo) {
	this.roomNo = roomNo;
}
public String getRoomName() {
	return roomName;
}
public void setRoomName(String roomName) {
	this.roomName = roomName;
}
}
>>>>>>> c7d36ed5bb54a86c9e294c11535c98d56c52fac7
