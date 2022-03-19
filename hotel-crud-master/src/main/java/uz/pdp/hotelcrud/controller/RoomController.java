package uz.pdp.hotelcrud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelcrud.entity.Room;
import uz.pdp.hotelcrud.payload.RoomDto;
import uz.pdp.hotelcrud.service.RoomService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public List<Room> getList() {
        return roomService.getAll();
    }

    @GetMapping("/{id}")
    public Room getOneById(@PathVariable Integer id) {
        return roomService.getOne(id);
    }

    @GetMapping("/getByHotelId/{id}/{page}")
    public Page<Room> getListByHotelId(@PathVariable Integer id, @PathVariable Integer page) {
        return roomService.findAllByHotelId(id, page);
    }

    @PostMapping
    public Room add(@RequestBody RoomDto roomDto) {
        return roomService.add(roomDto);
    }

    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id, @RequestBody RoomDto roomDto) {
        return roomService.edit(id, roomDto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        return roomService.delete(id);
    }
    
}
