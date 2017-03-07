package settingsMenu;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
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

	public Panel_Options(String name, SettingsPanel settingsPanel, String type)
	{
		super();
		setBounds(0, 0, Constants.settingsMainWidth, Constants.settingsMainHeight);
		setBackground(Color.PINK);

		JTextField txtFavourites = new JTextField();
		txtFavourites.setText(name);
		txtFavourites.setColumns(10);

		JButton btnChangeName = new JButton("Change Name");
		btnChangeName.getModel().addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent event)
			{
				if (btnChangeName.getModel().isPressed())
				{
					CategoryHandler.changeCategoryName(name, txtFavourites.getText());
					settingsPanel.RefreshScrollPane(type);
				}
			}
		});
		btnChangeName.setBackground(Color.WHITE);

		JButton btnChangeIcon = new JButton("Change Icon");
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
						.addComponent(txtFavourites, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
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
								.addComponent(txtFavourites, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
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
