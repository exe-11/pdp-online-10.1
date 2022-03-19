package uz.pdp.hotelcrud.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomDto {
    private Integer number;
    private Integer floor;
    private Integer size;
    private Integer hotelId;
}
