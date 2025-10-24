package com.fhc.leadmanagement.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fhc.leadmanagement.entity.Lead;
import com.fhc.leadmanagement.entity.LeadNote;
import com.fhc.leadmanagement.repository.LeadNoteRepository;
import com.fhc.leadmanagement.repository.LeadRepository;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

	@Autowired
	private LeadRepository leadRepository;

	@Autowired
	private LeadNoteRepository leadNoteRepository;

	@GetMapping("/all")
	public List<Lead> getAllLeads() {
		return leadRepository.findAll();
	}
	
	@GetMapping("/userin")
	public List<Lead> getLeadByAssignedUserIdIn(@RequestParam List<Long> userIds) {
	    return leadRepository.findByAssignedUserIdIn(userIds);
	}

	@PostMapping("/create")
	public Lead createLead(@RequestBody Lead lead) {
		lead.setCreatedAt(LocalDateTime.now());
		lead.setUpdatedAt(LocalDateTime.now());
		return leadRepository.save(lead);
	}

	@PutMapping("/{id}")
	public Lead updateLead(@PathVariable Long id, @RequestBody Lead leadDetails) {
		Lead existingLead = leadRepository.findById(id).orElseThrow();
		existingLead.setFirstName(leadDetails.getFirstName());
		existingLead.setLastName(leadDetails.getLastName());
		existingLead.setEmail(leadDetails.getEmail());
		existingLead.setPhone(leadDetails.getPhone());
		existingLead.setStatus(leadDetails.getStatus());
		existingLead.setNotes(leadDetails.getNotes());
		existingLead.setAssignedUserId(leadDetails.getAssignedUserId());
		existingLead.setUpdatedAt(LocalDateTime.now());
		return leadRepository.save(existingLead);
	}

	@DeleteMapping("/{id}")
	public void deleteLead(@PathVariable Long id) {
		leadRepository.deleteById(id);
	}

	// GET /leads/{leadId}/notes - get all notes for a lead
	@GetMapping("/{leadId}/notes")
	public List<LeadNote> getAllNotesForLead(@PathVariable Long leadId) {
		return leadNoteRepository.findByLeadId(leadId);
	}

	// POST /leads/{leadId}/notes - add a note to a lead
	@PostMapping("/{leadId}/notes")
	public LeadNote addNoteToLead(@PathVariable Long leadId, @RequestBody LeadNote note) {
		Lead lead = leadRepository.findById(leadId).orElseThrow(() -> new RuntimeException("Lead not found"));
		note.setLead(lead);
		note.setCreatedAt(LocalDateTime.now());
		note.setUpdatedAt(LocalDateTime.now());
		return leadNoteRepository.save(note);
	}

	// PUT /notes/{noteId} - update a specific note
	@PutMapping("/notes/{noteId}")
	public LeadNote updateNote(@PathVariable Long noteId, @RequestBody LeadNote updatedNote) {
		LeadNote existingNote = leadNoteRepository.findById(noteId)
				.orElseThrow(() -> new RuntimeException("Note not found"));
		existingNote.setNote(updatedNote.getNote());
		existingNote.setUpdatedAt(LocalDateTime.now());
		return leadNoteRepository.save(existingNote);
	}

	// DELETE /notes/{noteId} - delete a specific note
	@DeleteMapping("/notes/{noteId}")
	public void deleteNote(@PathVariable Long noteId) {
		LeadNote note = leadNoteRepository.findById(noteId).orElseThrow(() -> new RuntimeException("Note not found"));
		leadNoteRepository.delete(note);
	}
}
