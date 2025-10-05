package ru.mihalych.tic_tac_toe.bot.element;

import java.util.List;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

public class Other {
	
	public static final String TXT_HELP = "Игра Крестики-Нолики с полем 3х3";
	public static final List<BotCommand> COMMAND_MENU = List.of(
			new BotCommand("/start", "Начать игру"), 
			new BotCommand("/help", "Помощь"));
}
