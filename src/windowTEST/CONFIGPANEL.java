package windowTEST;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

public class CONFIGPANEL extends JPanel
{

	/**
	 * Create the panel.
	 */
	public CONFIGPANEL()
	{
		
		JLabel label = new JLabel("");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addContainerGap(382, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addContainerGap(271, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
