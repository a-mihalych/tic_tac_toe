package ru.mihalych.tic_tac_toe.bot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;

@Configuration
@PropertySource("classpath:config.properties")
@Getter
public class BotConfig {

	@Value("${bot.tokenTelegram}")
	String token;
	@Value("${bot.name}")
	String botName;
	@Value("${bot.username}")
	String botUsername;
	@Value("${bot.chatId}")
	String chatId;
	@Value("${bot.chatIdOwner}")
	String generalChatId;
	@Value("${bot.chatIdChat}")
	String chatIdChat;
}
