package com.tasklist;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("tasktracker")
public interface TasklistConfig extends Config
{
	@ConfigItem(keyName = "tasklistData", name = "", description = "", hidden = true)
	default String goalTrackerData()
	{
		return "";
	}

	@ConfigItem(keyName = "tasklistData", name = "", description = "", hidden = true)
	void goalTrackerData(String str);

//	@ConfigItem(keyName = "goalTrackerItemCache", name = "", description = "", hidden = true)
//	default String goalTrackerItemCache()
//	{
//		return "";
//	}
//
//	@ConfigItem(keyName = "goalTrackerItemCache", name = "", description = "", hidden = true)
//	void goalTrackerItemCache(String str);
//
//	@ConfigItem(keyName = "goalTrackerItemNoteMapCache", name = "", description = "", hidden = true)
//	default String goalTrackerItemNoteMapCache()
//	{
//		return "";
//	}
//
//	@ConfigItem(keyName = "goalTrackerItemNoteMapCache", name = "", description = "", hidden = true)
//	void goalTrackerItemNoteMapCache(String str);
}