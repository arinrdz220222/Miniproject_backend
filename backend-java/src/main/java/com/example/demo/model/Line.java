package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "line")
public class Line {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer lineId;
	private String start;
	private String stop;
	private String link;
	
	@ManyToOne
	@JoinColumn(name = "bus_id")
	private Bus bus;
	
	public Line(Integer lineId, String start, String stop, String link) {
		super();
		this.lineId = lineId;
		this.start = start;
		this.stop = stop;
		this.link = link;
	}
	
	public Line() {
		super();
	}
	
	public String getBus() {
		return bus.getBusName();
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getStop() {
		return stop;
	}
	public void setStop(String stop) {
		this.stop = stop;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
}