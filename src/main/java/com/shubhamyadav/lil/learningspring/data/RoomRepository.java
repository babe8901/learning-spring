package com.shubhamyadav.lil.learningspring.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
