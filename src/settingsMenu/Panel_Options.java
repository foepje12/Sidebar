package settingsMenu;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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

		JTextField txtInputField = new JTextField();
		txtInputField.setText(name);
		txtInputField.setColumns(10);

		JButton btnChangeName = new Button_Option_Style("Change Name");
		btnChangeName.getModel().addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent event)
			{
				if (btnChangeName.getModel().isPressed())
				{
					if (type == "CATEGORY")
					{
						CategoryHandler.renameCategory(name, txtInputField.getText());
					}
					if (type == "BAR_ITEM")
					{
						CategoryHandler.renameBarItem(categoryName, name, txtInputField.getText());
					}

					settingsPanel.RefreshScrollPane(type);
				}
			}
		});

		JButton btnChangeIcon = new Button_Option_Style("Change Icon");
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

		JLabel lblIconPath = new JLabel("Icon Path");

		GroupLayout gl_panel = new GroupLayout(this);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblIconPath, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtInputField, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnChangeIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(btnChangeName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
				.addContainerGap(42, Short.MAX_VALUE)));

		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtInputField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnChangeName))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(btnChangeIcon)
								.addComponent(lblIconPath))
						.addContainerGap(237, Short.MAX_VALUE)));
		setLayout(gl_panel);

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
