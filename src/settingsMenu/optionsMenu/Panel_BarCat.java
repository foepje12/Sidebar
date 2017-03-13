package settingsMenu.optionsMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import handlers.BarItemHandler;
import handlers.CategoryHandler;
import main.Constants;
import main.SideBar;
import settingsMenu.SettingsPanel;

public class Panel_BarCat extends JPanel
{
	private static final long serialVersionUID = 1L;

	private String catgName;

	public Panel_BarCat(String catgName, String name, SettingsPanel settingsPanel, String type)
	{
		this(name, settingsPanel, type);
		this.catgName = catgName;
	}

	/**
	 * @wbp.parser.constructor
	 */
	public Panel_BarCat(String name, SettingsPanel settingsPanel, String type)
	{
		super();
		setBounds(0, 0, Constants.settingsMainWidth, Constants.settingsMainHeight);
		setPreferredSize(new Dimension(Constants.settingsWidth, Constants.settingsHeight));
		setBackground(Color.LIGHT_GRAY);

		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent event)
			{
				if (SwingUtilities.isMiddleMouseButton(event))
				{
					SideBar.SwitchToArcSelector();
				}
			}
		});

		JTextField textField_ChangeName = new JTextField();
		textField_ChangeName.setColumns(10);
		textField_ChangeName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		textField_ChangeName.setText(name);

		JTextField textField_WebUrl = new JTextField();
		textField_WebUrl.setColumns(10);
		textField_WebUrl.setEditable(false);
		textField_WebUrl.setBackground(Color.WHITE);
		textField_WebUrl.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		switch (type)
		{
		case "CATEGORY":
			new JLabel(CategoryHandler.getCategoryInfo(catgName));
			break;
		case "BAR_ITEM":
			String[] strings = BarItemHandler.getBarItemInfo(catgName, name);
			if (strings != null)
			{
				new JLabel(strings[0]);
				textField_WebUrl.setText(strings[1]);
			}
			break;
		}

		Button_Option_Style btnDelete = new Button_Option_Style("Delete");
		btnDelete.getModel().addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				if (btnDelete.getModel().isPressed())
				{
					CategoryHandler.deleteCategory(name);
					settingsPanel.RefreshScrollPane(type);
				}
			}
		});

		Button_Option_Style btnChangeName = new Button_Option_Style("Change Name");
		btnChangeName.getModel().addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent event)
			{
				if (btnChangeName.getModel().isPressed())
				{
					if (type.equals("CATEGORY"))
					{
						CategoryHandler.renameCategory(name, textField_ChangeName.getText());
					}
					if (type.equals("BAR_ITEM"))
					{
						BarItemHandler.renameBarItem(catgName, name, textField_ChangeName.getText());
					}
					settingsPanel.RefreshScrollPane(type);
				}
			}
		});

		Button_Option_Style btnChangeIcon = new Button_Option_Style("Change Icon");
		btnChangeIcon.getModel().addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent event)
			{
				if (btnChangeIcon.getModel().isPressed())
				{
					FileDialog fileChooser = new FileDialog(SettingsPanel.GetJFrame(), "Choose a file",
							FileDialog.LOAD);
					fileChooser.setDirectory(Constants.baseFilePath);
					fileChooser.setVisible(true);
					fileChooser.setFilenameFilter(new FilenameFilter()
					{
						@Override
						public boolean accept(File dir, String name)
						{
							String[] acceptedFormats =
							{ ".png" };

							for (String string : acceptedFormats)
							{
								if (name.endsWith(string))
								{
									return true;
								}
							}
							return false;
						}
					});

					String fileName = fileChooser.getFile();
					String fileDir = fileChooser.getDirectory();

					if (fileName != null && fileDir != null)
					{
						File source = new File(fileDir + fileName);
						String dest = "assets/icons/";
						try
						{
							Files.copy(source.toPath(), (new File(dest + fileName)).toPath(),
									StandardCopyOption.REPLACE_EXISTING);

							switch (type)
							{
							case "CATEGORY":
								CategoryHandler.changeCategoryIcon(name, dest + fileName);
								break;
							case "BAR_ITEM":
								BarItemHandler.changeBarItemIcon(catgName, name, dest + fileName);
							}

						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
					}
				}
			}
		});

		Button_Option_Style btnWebUrl = new Button_Option_Style("Change weburl");
		btnWebUrl.getModel().addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				if (btnWebUrl.getModel().isPressed())
				{
					BarItemHandler.changeBarItemWebUrl();
				}
			}
		});

	}
}
