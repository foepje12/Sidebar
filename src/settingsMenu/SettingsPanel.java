package settingsMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import handlers.CategoryHandler;
import main.ClickListener;
import main.Constants;
import main.Sidebar;

public class SettingsPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	public JPanel mainPanel;
	public JPanel panelScrollPane;
	private JScrollPane scrollPane;
	public String currentCategoryName;

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

		addMainPanel();

		scrollPane = new JScrollPane(panelScrollPane);
		add(scrollPane, BorderLayout.LINE_START);

		addScrollPane("CATEGORY");

		JLabel lblNewLabel = new JLabel("New label");
		add(lblNewLabel, BorderLayout.NORTH);

		Sidebar.getJframe().pack();
	}

	private void addMainPanel()
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
	}

	private void resetScrollPane()
	{
		if (panelScrollPane != null)
		{
			panelScrollPane.removeAll();
		}
	}

	void addScrollPane(String type)
	{
		resetScrollPane();

		panelScrollPane = new JPanel();

		panelScrollPane.setLayout(new BoxLayout(panelScrollPane, BoxLayout.Y_AXIS));

		switch (type)
		{
		case "CATEGORY":
			addCategoriesToScrollPane(panelScrollPane);
			break;
		case "BAR_ITEM":
			addBarItemToScrollPane(panelScrollPane, currentCategoryName);
			break;
		}

		scrollPane.setViewportView(panelScrollPane);
		Sidebar.packJFrame();
	}

	private void addCategoriesToScrollPane(JPanel panel)
	{
		Set<String> strings = CategoryHandler.getCategoryNames();

		for (String catgNames : strings)
		{
			JLabel label = new Label_Category(catgNames, this);

			panel.add(label);
		}

		BaseScrollLabel addNewCategoryLabel = new BaseScrollLabel("Add Category", new ClickListener()
		{
			@Override
			public void singleClick(MouseEvent e)
			{
				
			}
		});

		panel.add(addNewCategoryLabel);
	}

	private void addBarItemToScrollPane(JPanel panel, String categoryName)
	{
		Set<String> strings = CategoryHandler.getBarItemNames(categoryName);

		for (String set : strings)
		{
			Label_BarItem label = new Label_BarItem(set);
			label.setAlignmentY(JLabel.CENTER_ALIGNMENT);
			panel.add(label);
		}
	}
}

class JTextFieldLimit extends PlainDocument
{
	private static final long serialVersionUID = 1L;
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
