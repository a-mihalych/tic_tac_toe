package ru.mihalych.tic_tac_toe.bot.element;

import com.vdurmont.emoji.EmojiParser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Icon {

	ICON_X(":x:"),
	ICON_O(":o:"),
	ICON_START(":clapper:");
	
	private final String kodIconTxt;
	
	public String getkodIconUnicode() {
		return EmojiParser.parseToUnicode(kodIconTxt);
	}
}
