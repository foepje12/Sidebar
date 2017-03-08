package settingsMenu;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import handlers.CategoryHandler;
import main.Constants;
import main.Sidebar;

public class Panel_Options extends JPanel
{
	private static final long serialVersionUID = 1L;

	private String categoryName;

	public Panel_Options(String categoryName, String name, SettingsPanel settingsPanel, String type)
	{
		this(name, settingsPanel, type);
		this.categoryName = categoryName;
	}

	public Panel_Options(String name, SettingsPanel settingsPanel, String type)
	{
		super();
		setBounds(0, 0, Constants.settingsMainWidth, Constants.settingsMainHeight);

		JTextField textField_ChangeName = new JTextField();
		JTextField textField_IconPath = new JTextField();
		JTextField textField_WebUrl = new JTextField();

		textField_ChangeName.setColumns(10);
		textField_ChangeName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		textField_IconPath.setColumns(10);
		textField_IconPath.setEditable(false);
		textField_IconPath.setBackground(Color.WHITE);
		textField_IconPath.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		textField_WebUrl.setColumns(10);
		textField_WebUrl.setEditable(false);
		textField_WebUrl.setBackground(Color.WHITE);
		textField_WebUrl.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		Button_Option_Style btnDelete = new Button_Option_Style("Delete");

		Button_Option_Style btnChangeName = new Button_Option_Style("Change Name");
		btnChangeName.getModel().addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent event)
			{
				if (btnChangeName.getModel().isPressed())
				{
					if (type == "CATEGORY")
					{
						CategoryHandler.renameCategory(name, textField_ChangeName.getText());
					}
					if (type == "BAR_ITEM")
					{
						CategoryHandler.renameBarItem(categoryName, name, textField_ChangeName.getText());
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
				}
			}
		});

		Button_Option_Style btnWebUrl = new Button_Option_Style("Change weburl");

		GroupLayout gl_mainPanel = new GroupLayout(this);
		gl_mainPanel.setHorizontalGroup(
				gl_mainPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_mainPanel.createSequentialGroup()
						.addContainerGap().addGroup(gl_mainPanel
								.createParallelGroup(Alignment.LEADING).addGroup(gl_mainPanel
										.createSequentialGroup()
										.addComponent(textField_ChangeName, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnChangeName))
								.addGroup(gl_mainPanel.createSequentialGroup()
										.addComponent(textField_IconPath, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnChangeIcon)))
						.addContainerGap(0, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING,
								gl_mainPanel.createSequentialGroup().addContainerGap(0, Short.MAX_VALUE)
										.addComponent(btnDelete).addContainerGap())
						.addGroup(gl_mainPanel.createSequentialGroup().addContainerGap()
								.addComponent(textField_WebUrl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnWebUrl)
								.addContainerGap(0, Short.MAX_VALUE)));
		gl_mainPanel.setVerticalGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_mainPanel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_mainPanel.createParallelGroup(Alignment.BASELINE)
						.addGroup(gl_mainPanel.createSequentialGroup().addGap(3).addComponent(textField_ChangeName))
						.addComponent(btnChangeName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_mainPanel.createParallelGroup(Alignment.BASELINE)
						.addGroup(gl_mainPanel.createSequentialGroup().addGap(3).addComponent(textField_IconPath))
						.addComponent(btnChangeIcon))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(
						gl_mainPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_mainPanel.createSequentialGroup().addComponent(textField_WebUrl)
										.addGap(154).addComponent(btnDelete))
								.addComponent(btnWebUrl))
				.addContainerGap()));
		setLayout(gl_mainPanel);

		Sidebar.packJFrame();
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
		{
			return;
		}

		if ((getLength() + str.length()) <= limit)
		{
			super.insertString(offset, str, attr);
		}
	}
}