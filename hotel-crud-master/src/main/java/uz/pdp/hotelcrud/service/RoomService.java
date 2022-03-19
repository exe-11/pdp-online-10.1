package uz.pdp.hotelcrud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.hotelcrud.entity.Hotel;
import uz.pdp.hotelcrud.entity.Room;
import uz.pdp.hotelcrud.payload.RoomDto;
import uz.pdp.hotelcrud.repository.HotelRepository;
import uz.pdp.hotelcrud.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService implements BaseService<RoomDto, Room> {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room getOne(Integer id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public Room add(RoomDto roomDto) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());

        if(optionalHotel.isEmpty())
            return null;

        if(roomRepository.existsByNumberAndFloorAndHotelId(roomDto.getNumber(), roomDto.getFloor(), roomDto.getHotelId()))
            return null;

        Room room = new Room();
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());
        room.setHotel(optionalHotel.get());

        return roomRepository.save(room);
    }

    @Override
    public String delete(Integer id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);

        if(optionalRoom.isEmpty())
            return "Room not found";

        roomRepository.deleteById(id);
        return "Room was deleted";
    }

    @Override
    public String edit(Integer id, RoomDto roomDto) {
        Optional<Room> optionalRoom = roomRepository.findById(id);

        if(optionalRoom.isEmpty())
            return "Room not found";

        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());

        if(optionalHotel.isEmpty())
            return "Hotel not found";

        if(roomRepository.existsByNumberAndFloorAndHotelId(roomDto.getNumber(), roomDto.getFloor(), roomDto.getHotelId()))
            return "This room already exists";

        Room editingRoom = optionalRoom.get();
        editingRoom.setNumber(roomDto.getNumber());
        editingRoom.setFloor(roomDto.getFloor());
        editingRoom.setSize(roomDto.getSize());
        editingRoom.setHotel(optionalHotel.get());
        roomRepository.save(editingRoom);

        return "Room was edited";
    }

    public Page<Room> findAllByHotelId(Integer hotelId, int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        return roomRepository.findAllByHotelId(hotelId, pageable);
    }
}
