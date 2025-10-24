package com.fhc.leadmanagement.service;

import org.springframework.stereotype.Service;
import com.fhc.leadmanagement.entity.Lead;

@Service
public class NotificationService {

	public void notifyLeadAssigned(Long userId, Lead lead) {
		// For MVP, just log notification or store it for frontend retrieval later
		System.out.println(
				"Notify user " + userId + ": Lead assigned - " + lead.getFirstName() + " " + lead.getLastName());

		// TODO: Implement real notification push to frontend (WebSocket or polling)
		// Example: Save notification record to DB, send WebSocket event, etc.
	}
}
