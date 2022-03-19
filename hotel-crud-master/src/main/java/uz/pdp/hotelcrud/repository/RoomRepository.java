package uz.pdp.hotelcrud.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hotelcrud.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    Page<Room> findAllByHotelId(Integer hotelId, Pageable pageable);
    boolean existsByNumberAndFloorAndHotelId(Integer number, Integer floor, Integer hotelId);
}
