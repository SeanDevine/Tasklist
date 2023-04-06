package com.tasklist;

import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.ItemID;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ProfileChanged;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;

import java.awt.image.BufferedImage;

@Slf4j
@PluginDescriptor(
		name = "Tasklist",
		description = "Enable the Tasklist panel",
		tags = {"panel"},
		loadWhenOutdated = true
)
public class TasklistPlugin extends Plugin
{
	@Getter
	@Inject
	private Client client;

	@Inject
	private ClientToolbar clientToolbar;

	@Getter
	@Inject
	private TasklistConfig config;

	@Inject
	private ItemManager itemManager;

	@Inject
	private TasklistPanel tasklistPanel;

	private NavigationButton navButton;


//final BufferedImage icon = ImageUtil.loadImageResource(getClass(), "notes_icon.png");

	@Override
	protected void startUp()
	{
		final BufferedImage icon = itemManager.getImage(ItemID.TODO_LIST);
//		final BufferedImage icon = ImageUtil.loadImageResource(getClass(), "notes_icon.png");

		navButton = NavigationButton.builder()
				.tooltip("Tasklist")
				.icon(icon)
				.priority(7)
				.panel(tasklistPanel)
				.build();

		clientToolbar.addNavigation(navButton);

	}

	@Override
	protected void shutDown()
	{
		clientToolbar.removeNavigation(navButton);
	}

	@Subscribe
	public void onProfileChanged(ProfileChanged profileChanged)
	{
		// update notes
	}

	@Provides
	TasklistConfig getConfig(ConfigManager configManager)
	{
		return configManager.getConfig(TasklistConfig.class);
	}
}


