package com.libraryManagement.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
@Entity
public class Row {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int rowId;

		private String rowName;
		private int capacity;
		
		@ManyToOne
		@JoinColumn(name = "room_roomId", referencedColumnName = "roomId")
		@JsonBackReference
		private Room room;

		@OneToMany(mappedBy = "row", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
		@JsonManagedReference
		private List<Seat> seat;

		
		public int getRowId() {
			return rowId;
		}

		public void setRowId(int rowId) {
			this.rowId = rowId;
		}

		public String getRowName() {
			return rowName;
		}

		public void setRowName(String rowName) {
			this.rowName = rowName;
		}

		public int getCapacity() {
			return capacity;
		}

		public void setCapacity(int capacity) {
			this.capacity = capacity;
		}

		public Room getRoom() {
			return room;
		}

		public void setRoom(Room room) {
			this.room = room;
		}

		public List<Seat> getSeat() {
			return seat;
		}

		public void setSeat(List<Seat> seat) {
			this.seat = seat;
		}

		public Row(int rowId, String rowName, int capacity, Room room, List<Seat> seat) {
			super();
			this.rowId = rowId;
			this.rowName = rowName;
			this.capacity = capacity;
			this.room = room;
			this.seat = seat;
		}

		public Row() {
			super();
			// TODO Auto-generated constructor stub
		}

	}


