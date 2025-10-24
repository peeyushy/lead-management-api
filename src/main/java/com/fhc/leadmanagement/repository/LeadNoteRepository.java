package com.fhc.leadmanagement.repository;

import com.fhc.leadmanagement.entity.LeadNote;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LeadNoteRepository extends JpaRepository<LeadNote, Long> {

	// Find all notes associated with a given lead ID
	List<LeadNote> findByLeadId(Long leadId);
}