package windowTEST;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Jelle van Ommen\\Downloads\\Tamriel_Map.png"));
		label.setPreferredSize(new Dimension(140, 140));
		
		JLabel lblNewLabel = new JLabel("CategoryName");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Forte", Font.PLAIN, 28));

		GroupLayout gl_mainPanel = new GroupLayout(this);
		gl_mainPanel.setHorizontalGroup(
			gl_mainPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_mainPanel.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnChangeIcon, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_mainPanel.createSequentialGroup()
							.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(30))
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 140, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_mainPanel.setVerticalGroup(
			gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_mainPanel.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnChangeIcon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(185)
							.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		setLayout(gl_mainPanel);

	}
}
