package ru.mihalych.tic_tac_toe.bot.element;

import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Button {
	
	BUTTON_ZERO(" "),
	BUTTON_X(Icon.ICON_X.getkodIconUnicode()),
	BUTTON_O(Icon.ICON_O.getkodIconUnicode()),
	CALLBACK_0_0("0-0"),
	CALLBACK_0_1("0-1"),
	CALLBACK_0_2("0-2"),
	CALLBACK_1_0("1-0"),
	CALLBACK_1_1("1-1"),
	CALLBACK_1_2("1-2"),
	CALLBACK_2_0("2-0"),
	CALLBACK_2_1("2-1"),
	CALLBACK_2_2("2-2");

	private final String txt;
	
	public static InlineKeyboardMarkup getField() {
		InlineKeyboardButton button_0_0 = new InlineKeyboardButton(getTxtButton(Field.FIELD_3_3[0][0]));
		button_0_0.setCallbackData(CALLBACK_0_0.txt);
		InlineKeyboardButton button_0_1 = new InlineKeyboardButton(getTxtButton(Field.FIELD_3_3[0][1]));
		button_0_1.setCallbackData(CALLBACK_0_1.txt);
		InlineKeyboardButton button_0_2 = new InlineKeyboardButton(getTxtButton(Field.FIELD_3_3[0][2]));
		button_0_2.setCallbackData(CALLBACK_0_2.txt);
		InlineKeyboardButton button_1_0 = new InlineKeyboardButton(getTxtButton(Field.FIELD_3_3[1][0]));
		button_1_0.setCallbackData(CALLBACK_1_0.txt);
		InlineKeyboardButton button_1_1 = new InlineKeyboardButton(getTxtButton(Field.FIELD_3_3[1][1]));
		button_1_1.setCallbackData(CALLBACK_1_1.txt);
		InlineKeyboardButton button_1_2 = new InlineKeyboardButton(getTxtButton(Field.FIELD_3_3[1][2]));
		button_1_2.setCallbackData(CALLBACK_1_2.txt);
		InlineKeyboardButton button_2_0 = new InlineKeyboardButton(getTxtButton(Field.FIELD_3_3[2][0]));
		button_2_0.setCallbackData(CALLBACK_2_0.txt);
		InlineKeyboardButton button_2_1 = new InlineKeyboardButton(getTxtButton(Field.FIELD_3_3[2][1]));
		button_2_1.setCallbackData(CALLBACK_2_1.txt);
		InlineKeyboardButton button_2_2 = new InlineKeyboardButton(getTxtButton(Field.FIELD_3_3[2][2]));
		button_2_2.setCallbackData(CALLBACK_2_2.txt);
		List<InlineKeyboardButton> rowButton0 = List.of(button_0_0, button_0_1, button_0_2);
		List<InlineKeyboardButton> rowButton1 = List.of(button_1_0, button_1_1, button_1_2);
		List<InlineKeyboardButton> rowButton2 = List.of(button_2_0, button_2_1, button_2_2);
		List<List<InlineKeyboardButton>> rowsButton = List.of(rowButton0, rowButton1, rowButton2);
		return new InlineKeyboardMarkup(rowsButton);
	}
	
	private static String getTxtButton(String txtButton) {
		if (("x".equals(txtButton)) || ("o".equals(txtButton))) {
			if ("x".equals(txtButton)) {
				return BUTTON_X.txt;
			} else {
				return BUTTON_O.txt;
			}
		}
		return BUTTON_ZERO.txt;
	}
}
