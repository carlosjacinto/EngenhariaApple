package control;

import view.PrincipalAdm;

public class Main {

	public static void main(String[] args) {
		try {
			javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
			com.birosoft.liquid.LiquidLookAndFeel.setLiquidDecorations(true, "mac");
		} catch (Exception e) {
		}
		new PrincipalAdm().setVisible(true);
	}

}
