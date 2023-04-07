package com.tasklist;

import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.ItemID;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
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

	@Override
	protected void startUp()
	{
		final BufferedImage icon = itemManager.getImage(6648);

		navButton = NavigationButton.builder()
				.tooltip("Tasklist")
				.icon(icon)
				.priority(6)
				.panel(tasklistPanel)
				.build();

		clientToolbar.addNavigation(navButton);
	}

	@Override
	protected void shutDown()
	{
		clientToolbar.removeNavigation(navButton);
	}

	@Provides
	TasklistConfig getConfig(ConfigManager configManager)
	{
		return configManager.getConfig(TasklistConfig.class);
	}
}


