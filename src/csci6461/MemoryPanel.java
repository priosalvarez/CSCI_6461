package csci6461;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class MemoryPanel extends JPanel{
	
	
	private JScrollPane memoryScrollPanel;
	
	public MemoryPanel(int memorySize, JTextPane[] memory) {
		
		//Init memory
		memory = new JTextPane[memorySize];
		
		JPanel mainPanel = new JPanel();
        
		/*
		 * GridBagLayout for the panel that is contained by the memory
		 * scroll panel
		 */
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWeights = new double[]{};
		
		mainPanel.setLayout(gbl_panel);
		memoryScrollPanel = new JScrollPane();
		
		int y = 0;
		for(int i = 0; i < memorySize; i++){
			
			/*
			 * Properties for the labels of the memory panel
			 * Insets = Margins
			 * X & Y = Position
			 * Weight and Width = Number of rows or columns to contain the component. 
			 */
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(5,0,5,0);
			gbc_label.fill = GridBagConstraints.HORIZONTAL;
			gbc_label.gridx = 0;
			gbc_label.gridy = y;
			gbc_label.gridheight = 1;
			gbc_label.gridwidth = 1;
			
			//Labels (memory position) for the memory panel
			JLabel label = new JLabel("" + i);
			mainPanel.add(label, gbc_label);
			
			/*
			 * Properties for the input of the memory panel
			 * Insets = Margins
			 * X & Y = Position
			 * Weight and Width = Number of rows or columns to contain the component. 
			 */
			GridBagConstraints gbc_text = new GridBagConstraints();
			gbc_text.insets = new Insets(5,0,5,0);
			gbc_text.fill = GridBagConstraints.HORIZONTAL;
			gbc_text.gridx = 1;
			gbc_text.gridy = y;
			gbc_text.gridheight = 1;
			gbc_text.gridwidth = 1;
			
			//Inputs (for the memory position) for the memory panel
			JTextPane text = new JTextPane();
			text.setText("0000000000000000");
			memory[i] = text;
			mainPanel.add(text, gbc_text);
			//Increase to draw next memory position
			y++;
		}
		memoryScrollPanel.setViewportView(mainPanel);
		
	}
	
	/**
	 * Return the memory panel
	 * @return memoryPanel
	 */
	public JScrollPane getSplitPane() {
        return memoryScrollPanel;
    }
	
	/**
     * Create the frame properties
     * for the test method
     */
    private static JFrame createGUI() {
 
        //Create and set up the window.
        JFrame frame = new JFrame();
        frame.setBounds(100, 100, 1200, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }
 
    /**
     * Test method for the memory panel independently
     */
    public static void main(String[] args) {
    	JFrame frame = createGUI();
    	MemoryPanel splitPaneDemo = new MemoryPanel(2048, null);
        frame.getContentPane().add(splitPaneDemo.getSplitPane());
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	//Display the window.
                frame.setVisible(true);
                splitPaneDemo.getSplitPane().getViewport().setViewPosition(new Point(0,0));
            }
        });
        
    }
	
	
	
	
	
}
