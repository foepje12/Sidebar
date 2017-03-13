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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import handlers.BarItemHandler;
import handlers.CategoryHandler;
import main.Constants;
import main.SideBar;
import settingsMenu.SettingsPanel;
import javax.swing.SwingConstants;

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
		setPreferredSize(new Dimension(Constants.settingsMainWidth, Constants.settingsMainHeight));
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

		JLabel label_icon = new JLabel();
		label_icon.setHorizontalAlignment(SwingConstants.CENTER);
		label_icon.setPreferredSize(new Dimension(75, 75));
		label_icon.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		switch (type)
		{
		case "CATEGORY":
			label_icon.setIcon(new ImageIcon(CategoryHandler.getCategoryInfo(name)));
			break;
		case "BAR_ITEM":
			String[] strings = BarItemHandler.getBarItemInfo(catgName, name);
			if (strings != null)
			{
				label_icon.setIcon(new ImageIcon(strings[0]));
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
					settingsPanel.ResetOptionsPanel();
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
					BarItemHandler.changeBarItemWebUrl(catgName, name, textField_WebUrl.getText());
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addGap(29)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout
												.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
														.createSequentialGroup().addComponent(
																textField_WebUrl, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(
																btnWebUrl, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(textField_ChangeName, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(btnChangeName, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addGap(73)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(label_icon, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
												.addComponent(btnChangeIcon, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGap(196)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(8).addGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(label_icon, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnChangeIcon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(163).addComponent(btnDelete, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(textField_ChangeName, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnChangeName, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(8)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(textField_WebUrl, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnWebUrl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))))
						.addContainerGap()));
		setLayout(groupLayout);

	}
}
