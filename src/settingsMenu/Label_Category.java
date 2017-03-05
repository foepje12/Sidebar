package settingsMenu;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.Sidebar;

public class Label_Category extends BaseScrollLabel
{
	private static final long serialVersionUID = 1L;
	private SettingsPanel settingsPanel;
	private String catgName;
	

	public Label_Category(String catgName, SettingsPanel settingsPanel)
	{
		super(catgName);
		this.catgName = catgName;
		this.settingsPanel = settingsPanel;
	}

	@Override
	public void doSingleClick()
	{
		addCategoryOptions(catgName);
	}
	
	@Override
	public void doDoubleClick()
	{
		settingsPanel.currentCategoryName = catgName;
		settingsPanel.addScrollPane("BAR_ITEM");
	}
	
	void addCategoryOptions(String categoryName)
	{

		JTextField txtFavourites = new JTextField();
		txtFavourites.setText("Favourites");
		txtFavourites.setColumns(10);

		JButton btnChangeName = new JButton("Change Name");
		btnChangeName.getModel().addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent event)
			{
				if(btnChangeName.getModel().isPressed())
				{
					
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
				if(btnChangeName.getModel().isPressed())
				{
					
				}
			}
		});

		JLabel lblIconPath = new JLabel("Icon Path");
		GroupLayout gl_panel = new GroupLayout(settingsPanel.mainPanel);
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
		settingsPanel.mainPanel.setLayout(gl_panel);

		Sidebar.packJFrame();
	}
}
