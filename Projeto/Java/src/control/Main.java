package control;

<<<<<<< HEAD
import view.Principal;

=======
import view.PedidoView;
>>>>>>> refs/remotes/origin/master

public class Main {

	public static void main(String[] args) {
		try {
			javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
			com.birosoft.liquid.LiquidLookAndFeel.setLiquidDecorations(true, "mac");
		} catch (Exception e) {
		}
<<<<<<< HEAD
		new Principal().setVisible(true);
=======


		new PedidoView().setVisible(true);
>>>>>>> refs/remotes/origin/master

	}
}
