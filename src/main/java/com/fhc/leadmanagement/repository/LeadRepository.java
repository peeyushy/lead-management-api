package com.fhc.leadmanagement.repository;

import com.fhc.leadmanagement.entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeadRepository extends JpaRepository<Lead, Long> {

    // Leads assigned directly to an executive user
    List<Lead> findByAssignedUserId(Long userId);

    List<Lead> findByAssignedUserIdIn(List<Long> userIds);

    // Leads assigned to all executives under a specific team leader
    @Query("SELECT l FROM Lead l WHERE l.assignedUserId IN " +
           "(SELECT u.id FROM User u WHERE u.teamleaderid = :teamleaderid)")
    List<Lead> findLeadsForTeamLeader(@Param("teamleaderid") Long teamLeaderId);
}