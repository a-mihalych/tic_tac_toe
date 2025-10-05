package ru.mihalych.tic_tac_toe.user.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Setter
@Getter
@ToString
@EqualsAndHashCode(of = "chatId")
@NoArgsConstructor
@AllArgsConstructor
public class User {

	long chatId;
	String firstName;
	String lastName;
	String check;
	boolean isOpen;
	
	public String getFullName() {
		return firstName + (lastName != null ? (" " + lastName) : "");
	}
}
