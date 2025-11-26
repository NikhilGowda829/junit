package com.junit.junit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.junit.junit.entity.Hotel;
import com.junit.junit.repository.HotelRepo;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
public class HotelRepoTest {

    @Autowired
    private HotelRepo hotelRepo;

    @Test
    @Order(1)
    @Rollback(false)
    public void saveHotelTest() {
        Hotel hotel = new Hotel("Radisson Blu", "Mysore", "Dosa");
        hotelRepo.save(hotel);
        assertThat(hotel.getHid()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getHotelTest() {
        Hotel hotel = hotelRepo.findById(1).get();
        assertThat(hotel.getHid()).isEqualTo(1);
    }

    @Test
    @Order(3)
    @Rollback(false)
    public void updateHotelTest() {
        Hotel hotel = hotelRepo.findById(1).get();
        hotel.setFitem("Idli");
        Hotel updated = hotelRepo.save(hotel);
        assertThat(updated.getFitem()).isEqualTo("Idli");
    }

    @Test
    @Order(4)
    @Rollback(false)
    public void deleteHotelTest() {
        Hotel hotel = hotelRepo.findById(1).get();
        hotelRepo.delete(hotel);
        boolean exists = hotelRepo.existsById(hotel.getHid());
        assertThat(exists).isFalse();
    }
}
