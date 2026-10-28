package com.hsptl.bed.entity;

import java.util.ArrayList;
import java.util.List;

import com.hsptl.bed.constants.RoomType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Room {

	@Id
	@Column(name="room_num")
	private Long roomNum;
	
	@Enumerated(EnumType.STRING)
	private RoomType roomType;
	
	private Integer roomCapacity;
	
	@OneToMany(mappedBy= "roomNum" ,cascade=CascadeType.ALL)
	private List<Bed> beds = new ArrayList<>();
	
	
}
