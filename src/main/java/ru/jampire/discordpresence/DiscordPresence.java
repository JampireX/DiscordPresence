package ru.jampire.discordpresence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DiscordPresence {

	private static ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

	public static void main(String... args) {
		Gson gson = new GsonBuilder().create();
		try (InputStreamReader isr = new InputStreamReader(DiscordPresence.class.getResourceAsStream("/config.json"))) {
			Config config = gson.fromJson(isr, Config.class);
			DiscordRPC.discordInitialize(config.getClientId(), null, true);
			executorService.scheduleWithFixedDelay(() -> {
				try {
					DiscordRichPresence.Builder rich = new DiscordRichPresence.Builder(config.getState());
					rich.setDetails(config.getDetails());
					rich.setBigImage(config.getBigImage().getKey(), config.getBigImage().getText());
					rich.setSmallImage(config.getSmallImage().getKey(), config.getSmallImage().getText());

					DiscordRPC.discordUpdatePresence(rich.build());

					System.out.println("updated");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, 0, 10, TimeUnit.SECONDS);
			System.out.println("started");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}