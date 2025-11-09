package com.fhc.leadmanagement.controller;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fhc.leadmanagement.entity.Lead;
import com.fhc.leadmanagement.entity.LeadNote;
import com.fhc.leadmanagement.entity.User;
import com.fhc.leadmanagement.entity.enums.Role;
import com.fhc.leadmanagement.repository.LeadNoteRepository;
import com.fhc.leadmanagement.repository.LeadRepository;
import com.fhc.leadmanagement.repository.UserRepository;
import com.fhc.leadmanagement.service.UserService;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

	@Autowired
	private LeadRepository leadRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LeadNoteRepository leadNoteRepository;

	@Autowired
	private UserService userService;

	@GetMapping("/all")
	public List<Lead> getAllLeads() {
		return leadRepository.findAll();
	}

	@GetMapping("/userid/{userId}")
	public List<Lead> getLeadsForUser(@PathVariable Long userId) throws NotFoundException, AccessDeniedException {
		User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException());

		Role role = user.getRole(); // e.g., "ADMIN", "TEAM_LEADER", "EXECUTIVE"

		if ("ADMIN".equals(role.toString())) {
			return leadRepository.findAll();
		} else if ("TEAM_LEADER".equals(role.toString())) {
			List<Long> executiveIds = userService.getExecutivesByTeamLeader(userId);
			List<Long> userIds = new ArrayList<>(executiveIds);
			userIds.add(userId); // Include TL's own leads
			return leadRepository.findByAssignedUserIdIn(userIds);
		} else if ("EXECUTIVE".equals(role.toString())) {
			return leadRepository.findByAssignedUserId(userId);
		} else {
			throw new AccessDeniedException("Role not recognized");
		}
	}

	@PostMapping("/create")
	public Lead createLead(@RequestBody Lead lead) {
		lead.setCreatedAt(LocalDateTime.now());
		lead.setUpdatedAt(LocalDateTime.now());
		return leadRepository.save(lead);
	}

	// GET lead
	@GetMapping("/{id}")
	public Optional<Lead> getLeadById(@PathVariable Long id) {
		return leadRepository.findById(id);
	}	

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Lead> updateLead(@PathVariable Long id, @RequestBody Lead lead) {
		Lead existingLead = leadRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lead not found"));

		//can't update name & email
		//existingLead.setFirstName(lead.getFirstName());
		//existingLead.setLastName(lead.getLastName());
		//existingLead.setEmail(lead.getEmail());
		existingLead.setContactno(lead.getContactno());
		existingLead.setStatus(lead.getStatus());
		existingLead.setAssignedUserId(lead.getAssignedUserId());
		existingLead.setProject(lead.getProject());
		existingLead.setRequirement(lead.getRequirement());
		existingLead.setBudget(lead.getBudget());
		existingLead.setSource(lead.getSource());
		existingLead.setAssignedUserId(lead.getAssignedUserId());
		existingLead.setStatus(lead.getStatus());
		existingLead.setUpdatedAt(LocalDateTime.now());
		existingLead.setUpdatedByUserId(lead.getUpdatedByUserId());
		//Add new note
		existingLead.getNotes().addAll(lead.getNotes());
		
		Lead savedLead = leadRepository.save(existingLead);

		return ResponseEntity.ok(savedLead);
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
