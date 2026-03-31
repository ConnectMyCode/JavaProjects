/**
 * Enum representing the lifecycle state of a ticket.
 *
 * Values:
 * - OPEN: Ticket is newly created
 * - IN_PROGRESS: Work has started on the ticket
 * - CLOSED: Ticket is resolved
 *
 * Stored in DB as STRING (not ordinal) for readability and safety.
 */

package com.supporttriage.entity;

/**
 * Represents the status of a ticket  
 */
public enum TicketStatus {
    OPEN,
    IN_PROGRESS,
    CLOSED
}
