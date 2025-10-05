package ru.mihalych.tic_tac_toe.bot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.MaybeInaccessibleMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import ru.mihalych.tic_tac_toe.bot.element.Button;
import ru.mihalych.tic_tac_toe.bot.element.Field;
import ru.mihalych.tic_tac_toe.bot.element.Other;
import ru.mihalych.tic_tac_toe.user.model.User;

@Service
public class BotService {

	public List<Object> parseUpdate(Update update) {
		List<Object> sends = new ArrayList<>();
		String response;
		User user;
		if (update.hasMessage()) {
			Message telegramMessage = update.getMessage();
			user = User.builder()
					.chatId(telegramMessage.getChatId())
					.firstName(telegramMessage.getFrom().getFirstName())
					.lastName(telegramMessage.getFrom().getLastName())
					.build();
			if (telegramMessage.hasEntities() && telegramMessage.getText().startsWith("/")) {
				String telegramCommand = telegramMessage.getText().trim().toLowerCase();
				if (("/start".equals(telegramCommand)) || ("/help".equals(telegramCommand))) {
					if ("/start".equals(telegramCommand)) {
						response = Field.start(user);
						if (response != null) {
							sends.add(createSendMessage(user.getChatId(), response, null));
							if (Field.PLAYERS[1] == null) {
								user.setCheck("x");
								user.setOpen(true);
							} else {
								user.setCheck("o");
								user.setOpen(false);
								sends.add(createSendMessage(Field.PLAYERS[0].getChatId(), response, Button.getField()));
								sends.add(createSendMessage(Field.PLAYERS[1].getChatId(), response, Button.getField()));
							}
						} else {
							// response = null
						}
					} else {
						sends.add(createSendMessage(user.getChatId(), Other.TXT_HELP, null));
					}
				} else {
					// не известная команда
				}
			} else {
				// не понятное сообщение 
			}
		} else {
			if (update.hasCallbackQuery()) {
				MaybeInaccessibleMessage telegramCallback = update.getCallbackQuery().getMessage();
				long chatId = telegramCallback.getChatId();
				user = Field.PLAYERS[0].getChatId() == chatId ? Field.PLAYERS[0] : Field.PLAYERS[1];
				if (user.isOpen()) {
					String txtCallback = update.getCallbackQuery().getData();
					String[] points = txtCallback.split("-");
					if(Field.FIELD_3_3[Integer.parseInt(points[0])][Integer.parseInt(points[1])].isEmpty()) {
						Field.FIELD_3_3[Integer.parseInt(points[0])][Integer.parseInt(points[1])] = user.getCheck();
						response = Field.PLAYERS[0].getFullName() + " - " + Field.PLAYERS[1].getFullName();
						sends.add(createSendMessage(Field.PLAYERS[0].getChatId(), response, Button.getField()));
						sends.add(createSendMessage(Field.PLAYERS[1].getChatId(), response, Button.getField()));
						User userWinner = Field.getWinner();
						if (userWinner != null) {
							Field.PLAYERS[0].setOpen(false);
							Field.PLAYERS[1].setOpen(false);
							response = "\n" + userWinner.getFullName() + " одержал победу. Поздравляем!";
							sends.add(createSendMessage(Field.PLAYERS[0].getChatId(), response, null));
							sends.add(createSendMessage(Field.PLAYERS[1].getChatId(), response, null));
						} else {
							Field.PLAYERS[0].setOpen(!Field.PLAYERS[0].isOpen());
							Field.PLAYERS[1].setOpen(!Field.PLAYERS[1].isOpen());
						}
					}
				}
			} else {
				// не понятное сообщение
			}
		}
		return sends;
	}
	
	private SendMessage createSendMessage(long chatId, String txt, InlineKeyboardMarkup inlineKeyboardMarkup) {
		SendMessage sendMessage = new SendMessage();
		sendMessage.setChatId(chatId);
		sendMessage.setText(txt);
		if (inlineKeyboardMarkup != null) {
			sendMessage.setReplyMarkup(inlineKeyboardMarkup);
		}
		return sendMessage;
	}
}
