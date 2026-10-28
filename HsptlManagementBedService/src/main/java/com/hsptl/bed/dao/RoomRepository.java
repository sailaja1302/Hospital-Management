package com.hsptl.bed.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hsptl.bed.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{

	@Query
	Room findRoomByRoomNum(Long roomNum);
}
