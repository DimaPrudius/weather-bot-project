package bot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import service.impl.WeatherServiceImpl;

import java.util.List;

public class Bot extends TelegramLongPollingBot {

    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    @Override
    public String getBotUsername() {
        return "Dimastuyy_weather_bot";
    }

    @Override
    public String getBotToken() {
        return "2087418719:AAFDGnc6MEJ86T7jFoNprebUFZLIxEKTY0E";
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        var cityName = update.getMessage().getText();

        var service = new WeatherServiceImpl();

        var sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText(service.getByCityName(cityName).toString());

        KeyboardRow firstKeyboardRow = new KeyboardRow();
        firstKeyboardRow.add("Hi");

        KeyboardRow secondKeyboardRow = new KeyboardRow();
        secondKeyboardRow.add("Buy");

        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setKeyboard(List.of(firstKeyboardRow,secondKeyboardRow));
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);



        execute(sendMessage);

    }

//    @SneakyThrows
//    public synchronized void sendMsg(String chaId, String msg) {
//
//    }
}
