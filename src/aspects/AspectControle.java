package aspects;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectControle {

	@Pointcut("execution(* limite.FenetrePrincipale.*.actionPerformed(*)) && args(actionEvent)")
	public void buttonPointcut(ActionEvent actionEvent) {}

	@Before("buttonPointcut(actionEvent)")
	public void beforeButtonPointcut(ActionEvent actionEvent) {
		if (actionEvent.getSource() instanceof JButton) {
			JButton clickedButton = (JButton) actionEvent.getSource();
			switch (clickedButton.getText()) {
			case "Validate":
				JOptionPane.showMessageDialog(null, "A new specification has been added !");
				break;
			case "OK":
				JOptionPane.showMessageDialog(null, "The constraint has been added !");
				break;
			}
		}
		
	}
}
