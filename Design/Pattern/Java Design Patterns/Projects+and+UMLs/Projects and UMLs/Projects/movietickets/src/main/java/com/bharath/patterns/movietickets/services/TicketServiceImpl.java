package com.bharath.patterns.movietickets.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bharath.patterns.movietickets.dao.TicketDao;
import com.bharath.patterns.movietickets.bo.Ticket;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketDao dao;

	@Override
	public void purchaseTicket(Ticket ticket) {
		// Process Payment
		com.bharath.patterns.movietickets.entities.Ticket ticketEntity = new com.bharath.patterns.movietickets.entities.Ticket();
		//ticketEntity.setId(ticket.getId());
		ticketEntity.setMovie(ticket.getMovieName());
		ticketEntity.setScreen(ticket.getScreenNo());
		ticketEntity.setSeat(ticket.getSeatNo());
		dao.create(ticketEntity);
		// Email Ticket or Text Message the ticket
	}

}
