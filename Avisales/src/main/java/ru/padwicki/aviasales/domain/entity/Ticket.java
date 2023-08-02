package ru.padwicki.aviasales.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name="tickets")
public class Ticket {

    @Id
    @Column(name = "id",nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "start_town",nullable = false)
    String startTown;

    @Column(name = "end_town",nullable = false)
    String endTown;

    @Column(name = "way",nullable = false)
    String way;

    @Column(name = "price",nullable = false)
    Integer price;

    @Column(name = "airport",nullable = false)
    String airport;

    @Column(name = "date_time", nullable = false)
    String dateTime;

    @Column(name = "time",nullable = false)
    double time;

    @Column(name = "remaining",nullable = false)
    Integer remaining;


    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getTime() { return time; }

    public void setTime(double time) { this.time = time; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getStartTown() { return startTown; }

    public void setStartTown(String startTown) { this.startTown = startTown; }

    public String getEndTown() { return endTown; }

    public void setEndTown(String endTown) { this.endTown = endTown; }

    public Integer getPrice() { return price; }

    public void setPrice(Integer price) { this.price = price; }

    public String getAirport() { return airport; }

    public void setAirport(String airport) { this.airport = airport; }

    public Integer getRemaining() { return remaining; }

    public void setRemaining(Integer remaining) { this.remaining = remaining; }
}
