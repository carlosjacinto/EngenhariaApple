package control;

<<<<<<< HEAD
import view.PedidoView;
=======
import view.NotaEntradaView;
>>>>>>> refs/remotes/origin/master

public class Main {

	public static void main(String[] args) {
		try {
			javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
			com.birosoft.liquid.LiquidLookAndFeel.setLiquidDecorations(true, "mac");
		} catch (Exception e) {
		}
<<<<<<< HEAD


		new PedidoView().setVisible(true);
=======

		new NotaEntradaView().setVisible(true);
>>>>>>> refs/remotes/origin/master

	}
}
