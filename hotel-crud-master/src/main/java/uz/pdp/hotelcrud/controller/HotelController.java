package uz.pdp.hotelcrud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelcrud.entity.Hotel;
import uz.pdp.hotelcrud.service.HotelService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotel")
public class HotelController {

    private final HotelService hotelService;

    @GetMapping
    public List<Hotel> getList() {
        return hotelService.getAll();
    }

    @GetMapping("/{id}")
    public Hotel getOneById(@PathVariable Integer id) {
        return hotelService.getOne(id);
    }

    @PostMapping
    public Hotel add(@RequestBody Hotel hotel) {
        return hotelService.add(hotel);
    }

    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id, @RequestBody Hotel hotel) {
        return hotelService.edit(id, hotel);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        return hotelService.delete(id);
    }

}
