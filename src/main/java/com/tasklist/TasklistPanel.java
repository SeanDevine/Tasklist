package com.tasklist;

import lombok.extern.slf4j.Slf4j;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.PluginPanel;
import javax.inject.Inject;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Slf4j
class TasklistPanel extends PluginPanel
{

    private final JPanel mainPanel = new JPanel(new BorderLayout());
    private final TasklistPlugin plugin;
    private final TaskManager taskManager;

    @Inject
    private ItemManager itemManager;

    private final JPanel taskListPanel = new JPanel();


    @Inject
    public TasklistPanel(TasklistPlugin plugin, TaskManager taskManager) {
        super(false);
        this.plugin = plugin;
        this.taskManager = taskManager;
        mainPanel.setBackground(ColorScheme.DARK_GRAY_COLOR);

        setLayout(new BorderLayout());
        setBackground(ColorScheme.DARK_GRAY_COLOR);
        setBorder(new EmptyBorder(8, 8, 8, 8));

        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBackground(ColorScheme.DARK_GRAY_COLOR);

        JLabel title = new JLabel();
        title.setText("Tasklist");
        title.setForeground(Color.WHITE);
        title.setFont(FontManager.getRunescapeBoldFont());
        titlePanel.add(title, BorderLayout.CENTER);

        JButton addButton = new JButton("+ Add task");
        addButton.addActionListener(e -> {
            String taskDescription = JOptionPane.showInputDialog("Enter task description:");
            if (taskDescription != null && !taskDescription.isEmpty()) {
                taskManager.addTask(new Task(taskDescription));
                refreshTaskList();
            }
        });

        titlePanel.add(addButton, BorderLayout.EAST);

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(ColorScheme.DARK_GRAY_COLOR);
        contentPanel.add(taskListPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        refreshTaskList();
    }

    private void refreshTaskList() {
        taskListPanel.removeAll();

        taskListPanel.setLayout(new GridBagLayout());
        taskListPanel.setBackground(ColorScheme.DARK_GRAY_COLOR);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.anchor = GridBagConstraints.NORTHWEST;

        for (Task task : taskManager.getTasks()) {
            JCheckBox checkBox = new JCheckBox();
            checkBox.setSelected(task.isCompleted());
            checkBox.addActionListener(e -> {
                task.setCompleted(checkBox.isSelected());
                taskManager.updateTask(task);
            });

            JLabel taskLabel = new JLabel(task.getDescription());
            taskLabel.setForeground(Color.WHITE);
            taskLabel.setBackground(ColorScheme.DARK_GRAY_COLOR);

//            JButton removeButton = new JButton("X");
//            removeButton.addActionListener(e -> {
//                removeTask(task);
//            });

            JLabel removeLabel = new JLabel();
            removeLabel.setIcon(new ImageIcon(itemManager.getImage(553)));
            removeLabel.setToolTipText("Remove task");
            removeLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    removeTask(task);
                }
            });

            JPanel taskPanel = new JPanel(new BorderLayout());
            taskPanel.add(checkBox, BorderLayout.WEST);
            taskPanel.add(taskLabel, BorderLayout.CENTER);
            taskPanel.add(removeLabel, BorderLayout.EAST);

            taskListPanel.add(taskPanel, constraints);
        }

        taskListPanel.revalidate();
        taskListPanel.repaint();
    }




    private void addTask(String description) {
        Task task = new Task(description);
        taskManager.addTask(task);
        refreshTaskList();
    }

    private void removeTask(Task task) {
        taskManager.removeTask(task);
        refreshTaskList();
    }
}
