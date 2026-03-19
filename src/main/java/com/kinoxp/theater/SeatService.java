package com.kinoxp.theater;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<SeatResponse> getSeatsByTheaterId(Long theaterId) {
        return seatRepository.findByTheater_TheaterId(theaterId)
                .stream()
                .map(seat -> new SeatResponse(
                        seat.getSeatId(),
                        seat.getTheater().getTheaterId(),
                        seat.getRowNumber(),
                        seat.getSeatNumber()
                ))
                .toList();
    }
}
