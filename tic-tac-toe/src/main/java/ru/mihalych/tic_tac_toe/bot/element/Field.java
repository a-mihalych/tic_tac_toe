package ru.mihalych.tic_tac_toe.bot.element;

import ru.mihalych.tic_tac_toe.user.model.User;

public class Field {

	public static final String[][] FIELD_3_3 = {{"x", "", ""}, {"o", "", "o"}, {"", "x", ""}};
	public static final User[] PLAYERS = new User[2];
	public static boolean IS_START = false;
	private static final String NAME_GAME = " -= Крестики - Нолики =- \n\n";
	private static final String ADD_GAME = " присоединился к игре! Ожидаем начала игры";
	
	public static String start(User user) {
		String result = null;
		if (!IS_START) {
			IS_START = true;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					FIELD_3_3[i][j] = "";
				}
			}
			PLAYERS[0] = user;
			PLAYERS[1] = null;
			result = NAME_GAME + user.getFullName() + ADD_GAME;
		} else {
			if ((!PLAYERS[0].equals(user)) && (PLAYERS[1] == null)) {
				PLAYERS[1] = user;
				result = NAME_GAME + user.getFullName() + ADD_GAME;
			}
		}
		return result;
	}
	
	public static User getWinner() {
		for (int i = 0; i < 3; i++) {
			if (FIELD_3_3[i][0].equals(FIELD_3_3[i][1]) && FIELD_3_3[i][0].equals(FIELD_3_3[i][2]) ||
					FIELD_3_3[0][i].equals(FIELD_3_3[1][i]) && FIELD_3_3[0][i].equals(FIELD_3_3[2][i])) {
				IS_START = false;
				if (FIELD_3_3[i][i].equals(PLAYERS[0].getCheck()) || FIELD_3_3[i][i].equals(PLAYERS[1].getCheck())) {
					if (FIELD_3_3[i][i].equals(PLAYERS[0].getCheck())) {
						return PLAYERS[0];
					} else {
						return PLAYERS[1];
					}
				}
			}
		}
		if (FIELD_3_3[0][0].equals(FIELD_3_3[1][1]) && FIELD_3_3[1][1].equals(FIELD_3_3[2][2]) ||
				FIELD_3_3[0][2].equals(FIELD_3_3[1][1]) && FIELD_3_3[1][1].equals(FIELD_3_3[2][0])) {
			IS_START = false;
			if (FIELD_3_3[1][1].equals(PLAYERS[0].getCheck()) || FIELD_3_3[1][1].equals(PLAYERS[1].getCheck())) {
				if (FIELD_3_3[1][1].equals(PLAYERS[0].getCheck())) {
					return PLAYERS[0];
				} else {
					return PLAYERS[1];
				}
			}
		}
		return null;
	}
}
