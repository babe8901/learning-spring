package com.shubhamyadav.lil.learningspring.webservice;

import com.shubhamyadav.lil.learningspring.business.ReservationService;
import com.shubhamyadav.lil.learningspring.business.RoomReservation;
import com.shubhamyadav.lil.learningspring.data.Guest;
import com.shubhamyadav.lil.learningspring.data.Room;
import com.shubhamyadav.lil.learningspring.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebserviceController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    public WebserviceController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    @RequestMapping(path = "/reservations", method = RequestMethod.GET)
    public List<RoomReservation> getReservations(@RequestParam(value = "date", required = false) String dateString) {
        Date date = this.dateUtils.createDateFromDateString(dateString);
        return this.reservationService.getRoomReservationsForDate(date);
    }

    @RequestMapping(path = "/guests", method = RequestMethod.GET)
    public List<Guest> getGuests() {
        return this.reservationService.getHotelGuests();
    }

    @PostMapping(path = "/guests")
    @ResponseStatus(HttpStatus.CREATED)
    public void addGuest(@RequestBody Guest guest) {
        this.reservationService.addGuest(guest);
    }

    @RequestMapping(path = "/rooms", method = RequestMethod.GET)
    public List<Room> getRooms() {
        return this.reservationService.getHotelRooms();
    }
}
