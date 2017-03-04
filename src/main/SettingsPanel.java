package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import handlers.CategoryHandler;

public class SettingsPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JPanel mainPanel;

	public SettingsPanel()
	{
		super();

		setBounds(0, 0, Constants.settingsWidth, Constants.settingsHeight);
		setPreferredSize(new Dimension(Constants.settingsWidth, Constants.settingsHeight));
		setBackground(Color.LIGHT_GRAY);
		setLayout(new BorderLayout());

		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent event)
			{
				if (SwingUtilities.isMiddleMouseButton(event))
				{
					Sidebar.SwitchToArcSelector();
				}
			}
		});

		addScrollPane();
		Sidebar.getJframe().pack();
	}

	private void addScrollPane()
	{
		JPanel mainPane = new JPanel();
		mainPane.setPreferredSize(new Dimension((int) (Constants.leftScrollPaneWidth), Constants.LeftScrollPaneHeight));
		mainPane.setBackground(Color.WHITE);
		add(mainPane, BorderLayout.LINE_START);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		Set<String> strings = CategoryHandler.getCategoryNames();

		for (String set : strings)
		{
			JLabel label = new JLabel(set);
			label.setBackground(Color.CYAN);
			label.setAlignmentY(JLabel.CENTER_ALIGNMENT);

			label.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent event)
				{
					addCategoryOptions(set);
				}

				@Override
				public void mouseEntered(MouseEvent event)
				{
					label.setBackground(new Color(203, 217, 235));
				}

				@Override
				public void mouseExited(MouseEvent event)
				{
					label.setBackground(Color.WHITE);
				}
			});

			Dimension size = new Dimension((int) (Constants.leftScrollPaneWidth), 25);
			label.setMinimumSize(size);
			label.setPreferredSize(size);
			label.setMaximumSize(size);

			label.setBackground(Color.WHITE);
			label.setOpaque(true);
			panel.add(label);
		}
		JScrollPane scrollPane = new JScrollPane(panel);
		add(scrollPane, BorderLayout.LINE_START);
	}

	void addCategoryOptions(String categoryName)
	{
		if (mainPanel != null)
		{
			mainPanel.removeAll();
		}
		else
		{
			mainPanel = new JPanel();
			mainPanel.setLayout(null);
		}

		mainPanel.setBounds(0, 0, Constants.settingsMainWidth, Constants.settingsMainHeight);
		add(mainPanel);

		JTextField field = new JTextField(15);
		field.setDocument(new JTextFieldLimit(20));
		field.setText(categoryName);
		field.setBounds(15, 30, 100, 25);
		mainPanel.add(field);

		JButton changeNameButton = new JButton();
		changeNameButton.setBounds(120, 30, 120, 25);
		changeNameButton.setText("Change Name");
		changeNameButton.getModel().addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent event)
			{
				if(changeNameButton.getModel().isPressed())
				{
					
				}
			}
		});
		mainPanel.add(changeNameButton);
		Sidebar.getJframe().pack();
	}
}

class JTextFieldLimit extends PlainDocument
{
	private int limit;

	JTextFieldLimit(int limit)
	{
		super();
		this.limit = limit;
	}

	@Override
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException
	{
		if (str == null)
			return;

		if ((getLength() + str.length()) <= limit)
		{
			super.insertString(offset, str, attr);
		}
	}
}
