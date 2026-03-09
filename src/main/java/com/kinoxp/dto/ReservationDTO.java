package com.kinoxp.dto;

import com.kinoxp.model.user.User;

public class ReservationDTO {
private String movieTitle;
private int numberOfTickets;
private double totalPrice;
private int rowNumber;
private Long showingId;
private Long userId;


public ReservationDTO(String movieTitle, int numberOfTickets, double totalPrice, int rowNumber, Long userId, Long showingId) {
    this.movieTitle = movieTitle;
    this.numberOfTickets = numberOfTickets;
    this.totalPrice = totalPrice;
    this.rowNumber = rowNumber;
    this.showingId = showingId;
    this.userId = userId;

}

public ReservationDTO() {

}
public String getMovieTitle() {
    return movieTitle;
}
public void setMovieTitle(String movieTitle) {
    this.movieTitle = movieTitle;
}
public int getNumberOfTickets() {
    return numberOfTickets;
}
public void setNumberOfTickets(int numberOfTickets) {
    this.numberOfTickets = numberOfTickets;
}
public double getTotalPrice() {
    return totalPrice;
}
public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
}
public int getRowNumber() {
    return rowNumber;
}
public void setRowNumber(int rowNumber) {
    this.rowNumber = rowNumber;
}
public Long getShowingId() {
    return showingId;
}
public void setShowingId(Long showingId) {
    this.showingId = showingId;
}
public Long getUserId() {
    return userId;
}
public void setUserId(Long userId) {
    this.userId = userId;
}

}
