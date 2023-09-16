package com.shubhamyadav.lil.learningspring.web;

import com.shubhamyadav.lil.learningspring.data.Guest;
import com.shubhamyadav.lil.learningspring.data.GuestRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/guests")
public class GuestController {
    private final GuestRepository guestRepository;

    public GuestController(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getGuests(Model model) {
        List<Guest> guests = this.guestRepository.findAll();
        model.addAttribute("guests", guests);
        return "guests";
    }
}
