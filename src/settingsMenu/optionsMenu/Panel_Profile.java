package settingsMenu.optionsMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Set;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListCellRenderer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import handlers.CategoryHandler;
import handlers.ProfileHandler;
import main.Constants;
import settingsMenu.SettingsPanel;

public class Panel_Profile extends JPanel
{
	private static final long serialVersionUID = 1L;

	public Panel_Profile(String name)
	{
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(Constants.settingsMainWidth, Constants.settingsMainHeight));
		setLayout(new BorderLayout(0, 0));

		PaintPanel paintPanel = new PaintPanel();
		paintPanel.setPreferredSize(new Dimension(304, 304));
		add(paintPanel);

		JPanel panel_naming = new JPanel();
		panel_naming.setBackground(Color.GRAY);
		add(panel_naming, BorderLayout.EAST);
		panel_naming.setLayout(new BorderLayout(0, 0));

		JPanel NameChangePanel = new JPanel();
		NameChangePanel.setBackground(Color.GRAY);
		NameChangePanel.setPreferredSize(new Dimension(160, 100));
		panel_naming.add(NameChangePanel, BorderLayout.NORTH);

		JTextField txt_ChangeName = new JTextField();
		txt_ChangeName.setPreferredSize(new Dimension(130, 25));

		Button_Option_Style button = new Button_Option_Style("Change Name");
		button.getModel().addChangeListener(new ChangeListener()
		{

			@Override
			public void stateChanged(ChangeEvent arg0)
			{
				if (button.getModel().isPressed() && txt_ChangeName.getText().length() > 0)
				{
					ProfileHandler.renameProfile(name, txt_ChangeName.getText());
					SettingsPanel.GetSettingsPanel().addScrollPane("PROFILE");
				}

			}
		});

		GroupLayout gl_NameChangePanel = new GroupLayout(NameChangePanel);
		gl_NameChangePanel.setHorizontalGroup(gl_NameChangePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_NameChangePanel.createSequentialGroup().addContainerGap(58, Short.MAX_VALUE)
						.addGroup(gl_NameChangePanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txt_ChangeName, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 126,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(button, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		gl_NameChangePanel.setVerticalGroup(gl_NameChangePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_NameChangePanel.createSequentialGroup().addContainerGap()
						.addComponent(txt_ChangeName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(button, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(80)));
		NameChangePanel.setLayout(gl_NameChangePanel);

		JPanel RestPanel = new JPanel();
		RestPanel.setBackground(Color.GRAY);
		panel_naming.add(RestPanel, BorderLayout.CENTER);

		JComboBox<Object> comboBox = new JComboBox<Object>();
		CategoryComboBoxRenderer renderer = new CategoryComboBoxRenderer();
		renderer.setPreferredSize(new Dimension(200, 130));
		comboBox.setRenderer(renderer);
		comboBox.setMaximumRowCount(3);

		GroupLayout gl_RestPanel = new GroupLayout(RestPanel);
		gl_RestPanel.setHorizontalGroup(gl_RestPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_RestPanel.createSequentialGroup().addContainerGap(20, Short.MAX_VALUE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		gl_RestPanel.setVerticalGroup(gl_RestPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_RestPanel
						.createSequentialGroup().addContainerGap().addComponent(comboBox, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(215, Short.MAX_VALUE)));
		RestPanel.setLayout(gl_RestPanel);
	}
}

class CategoryComboBoxRenderer extends JLabel implements ListCellRenderer<Object>
{
	private static final long serialVersionUID = 1L;
	private ImageIcon[] categoryIcons;
	private Set<String> categoryStrings;

	public CategoryComboBoxRenderer()
	{
		super();
		setOpaque(true);
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);

		if (CategoryHandler.getCategoryIconArray() != null)
		{
			categoryIcons = CategoryHandler.getCategoryIconArray();
		}
		
		if(CategoryHandler.getCategoryNames() != null)
		{
			categoryStrings = CategoryHandler.getCategoryNames();
		}
	}

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus)
	{

		int selectedIndex = ((Integer) value).intValue();

		if (isSelected)
		{
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		}
		else
		{
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}

		// Set the icon and text. If icon was null, say so.
		ImageIcon icon = categoryIcons[selectedIndex];
		String pet = categoryStrings[selectedIndex];
		setIcon(icon);
		if (icon != null)
		{
			setText(pet);
			setFont(list.getFont());
		}

		return this;
	}

}
