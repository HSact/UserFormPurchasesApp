package com.hsact.domain.model

/**
 * Represents user data used across the application domain.
 *
 * @property participantNumber 16-digit participant number (validated on input).
 * @property code Code associated with the user.
 * @property name User's first name in Latin characters.
 * @property surname User's last name in Latin characters.
 */
data class UserData(
    val participantNumber: String,
    val code: String,
    val name: String,
    val surname: String,
)