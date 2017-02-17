package limite;

import java.awt.EventQueue;

import javax.swing.JWindow;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class SplashScreenprogressBar {

	private JWindow window;
	private JProgressBar progressBar;
	private static int count;
    private static Timer timer1;
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SplashScreenprogressBar window = new SplashScreenprogressBar();
					window.window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SplashScreenprogressBar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		window = new JWindow();
		window.setBounds(100, 100, 450, 300);
		window.setLocationRelativeTo(window);
		window.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("images\\image.JPG"));
		lblNewLabel.setBounds(0, 0, 450, 280);
		window.getContentPane().add(lblNewLabel);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(0, 280, 450, 20);
		progressBar.setMaximum(49);
		loadProgressBar();
		window.getContentPane().add(progressBar);	
	}
	
	/**
	 * Load the ProgressBar
	 */
	private void loadProgressBar(){
		ActionListener al = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				count++;
                progressBar.setValue(count);
                //System.out.println(count);
                if (count == 50) {
                	EventQueue.invokeLater(new Runnable() {
            			public void run() {
            				try {
            					FenetrePrincipale window = new FenetrePrincipale();
            					window.frame.setVisible(true);
            				} catch (Exception e) {
            					e.printStackTrace();
            				}
            			}
            		});      	
                    window.setVisible(false);//swapped this around with timer1.stop()

                    timer1.stop();
                }				
			}
		};
		timer1 = new Timer(50, al);
        timer1.start();
	}
	
}
