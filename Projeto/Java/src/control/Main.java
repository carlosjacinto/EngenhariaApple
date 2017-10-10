package control;

<<<<<<< HEAD
import view.Principal;
=======
import view.NotaEntradaView;
>>>>>>> a5e85a52fda284cdf3644960567730c5ed2d391a

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
		new NotaEntradaView().setVisible(true);
>>>>>>> a5e85a52fda284cdf3644960567730c5ed2d391a
	}
}
