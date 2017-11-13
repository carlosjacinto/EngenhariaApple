package control;

import view.NotaEntradaView;

public class Main {

	public static void main(String[] args) {
		try {
			javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
			com.birosoft.liquid.LiquidLookAndFeel.setLiquidDecorations(true, "mac");
		} catch (Exception e) {
		}


		new NotaEntradaView().setVisible(true);


	}
}
