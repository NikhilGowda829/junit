package com.junit.junit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.junit.junit.entity.Hotel;

public interface HotelRepo extends JpaRepository<Hotel, Integer> {
}
