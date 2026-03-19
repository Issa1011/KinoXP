package com.kinoxp.theater;

public record SeatResponse(
        Long seatId,
        Long theaterId,
        int rowNumber,
        int seatNumber
) {}
