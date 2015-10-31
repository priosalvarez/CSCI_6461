package csci6461;
	
	
	import java.awt.*;
	import java.awt.event.ActionListener;
	import javax.swing.*;
	import java.awt.event.ActionEvent;

	public class InputTest {
	   public static void main(String[] args) {
	      ButtonFrame frame = new ButtonFrame();
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setVisible(true);
	   }
	}

	@SuppressWarnings("serial")
	class ButtonFrame extends JFrame {

	   private JTextField inputField; // !! Make field private

	   public ButtonFrame() {

	      setTitle("SunStream Loan Calculator v2.0");
	      setSize(900, 900);
	      ButtonPanel panel = new ButtonPanel();
	      panel.add(new JLabel("Enter your loan amount:"));
	      inputField = new JTextField(40);
	      panel.add(inputField);

	      add(panel, BorderLayout.CENTER);
	   }

	   // !! create a public method to get JTextField's text
	   // !! without exposing the JTextField itself - probably not applicable 
	   public String getInputText() {
	      return inputField.getText();
	   }

	   class ButtonPanel extends JPanel implements ActionListener {
	      /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Component frame;

	      public ButtonPanel() {

	         final JButton b2 = new JButton("Get Input");
	         add(b2, BorderLayout.SOUTH);
	         b2.setActionCommand("processInput");
	         b2.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	               // !! ButtonFrame bf = new ButtonFrame();
	               if ("calculate".equals(e.getActionCommand())) {

	                  //!! call public method on ButtonFrame object
	                  JOptionPane.showMessageDialog(frame,
	                        ButtonFrame.this.getInputText());
	               }
	            }

	         });

	      }

	      @Override
	      public void actionPerformed(ActionEvent ae) {
	         throw new UnsupportedOperationException("Not supported yet.");
	      }

	   }
	}


