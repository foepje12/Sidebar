package windowTEST;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import handlers.CategoryHandler;
import settingsMenu.Button_Option_Style;

public class TEST extends JPanel
{
	private JTextField textField_ChangeName;
	private JTextField textField_IconPath;
	private JTextField textField_WebUrl;

	/**
	 * Create the panel.
	 */
	public TEST()
	{
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
		btnDelete.getModel().addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent arg0)
			{
				if (btnDelete.getModel().isPressed())
				{
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
		btnWebUrl.getModel().addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent event)
			{
				if (btnWebUrl.getModel().isPressed())
				{
				}
			}
		});

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

	}
}
