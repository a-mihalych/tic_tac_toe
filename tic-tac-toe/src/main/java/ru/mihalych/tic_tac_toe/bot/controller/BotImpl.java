package ru.mihalych.tic_tac_toe.bot.controller;

import java.util.List;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import lombok.extern.slf4j.Slf4j;
import ru.mihalych.tic_tac_toe.bot.config.BotConfig;
import ru.mihalych.tic_tac_toe.bot.element.Other;
import ru.mihalych.tic_tac_toe.bot.service.BotService;

@SuppressWarnings("deprecation")
@Component
@Slf4j
public class BotImpl extends TelegramLongPollingBot {
	
	private final BotConfig botConfig;
	private final BotService botService;
	
	public BotImpl(BotConfig botConfig, BotService botService) {
		this.botConfig = botConfig;
		this.botService = botService;
		try {
			execute(new SetMyCommands(Other.COMMAND_MENU, new BotCommandScopeDefault(), null));
		} catch(TelegramApiException e) {
			log.error("\n!!! class BotImpl. Конструктор. Создание меню: {}", e.getMessage());
		}
		SendMessage sendMessage = new SendMessage();
		sendMessage.setChatId(getChatIdOwner());
		sendMessage.setText("Был запущен бот Крестики-Нолики");
		try {
			execute(sendMessage);
		} catch(TelegramApiException e) {
			log.error("\n!!! class BotImpl. Конструктор. Отправка сообщения о запуске бота: {}", e.getMessage());
		}
	}

	@Override
	public String getBotUsername() {
		return botConfig.getBotUsername();
	}
	
	@Override
	public String getBotToken() {
		return botConfig.getToken();
	}
	
	public String getBotName() {
		return botConfig.getBotName();
	}
	
	public long getChatId() {
		return Long.parseLong(botConfig.getChatId());
	}

	public long getChatIdOwner() {
		return Long.parseLong(botConfig.getGeneralChatId());
	}

	public long getChatIdChat() {
		return Long.parseLong(botConfig.getChatIdChat());
	}

	@Override
	public void onUpdateReceived(Update update) {
		log.info("\n+++ class BotImpl. Метод: onUpdateReceived. Пришёл update: {}", update);
		send(botService.parseUpdate(update));
//		SendMessage sendMessage = new SendMessage();
//		Message message = update.getMessage();
//		sendMessage.setChatId(message.getChatId());
//		sendMessage.setText(message.getText());
//		try {
//			execute(sendMessage);
//		} catch (TelegramApiException e) {
//			log.error("\n!!! class BotImpl. Метод onUpdateReceived, отправка пришёдшего сообщения обраптно: {}", e.getMessage());
//		}
	}
	
	private void send(List<Object> sends) {
		if ((sends != null) && (sends.size() != 0)) {
			int numberMessages = sends.size();
			log.info("\n+++ class BotImpl. Метод: send. Оправка сообщений: {} шт.", numberMessages);
			for (Object o : sends) {
				if (o instanceof SendMessage sendMessage) {
					try {
						execute(sendMessage);
					} catch (TelegramApiException e) {
						log.error("\n!!! class BotImpl. Метод: send. Попытка отправить: {}\n - ошибка: {}", o, e.getMessage());
						e.printStackTrace();
					}
				}
			}
		} else {
			log.info("\n!? class BotImpl. Метод: send. Оправка сообщений, нет сообщений");
		}
	}
}
