package uz.pdp.hotelcrud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.hotelcrud.entity.Hotel;
import uz.pdp.hotelcrud.repository.HotelRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelService implements BaseService<Hotel, Hotel> {

    private final HotelRepository hotelRepository;

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getOne(Integer id) {
        return hotelRepository.findById(id).orElse(null);
    }

    @Override
    public Hotel add(Hotel hotel) {
        return hotelRepository.existsByName(hotel.getName()) ? null : hotelRepository.save(hotel);
    }

    @Override
    public String delete(Integer id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);

        if(optionalHotel.isEmpty())
            return "Hotel not found";

        hotelRepository.deleteById(id);
        return "Hotel was deleted";
    }

    @Override
    public String edit(Integer id, Hotel hotel) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);

        if(optionalHotel.isEmpty())
            return "Hotel not found";

        if (hotelRepository.existsByName(hotel.getName()))
            return "Hotel name already exists";

        Hotel editingHotel = optionalHotel.get();
        editingHotel.setName(hotel.getName());
        hotelRepository.save(editingHotel);

        return "Hotel was edited";
    }
}
