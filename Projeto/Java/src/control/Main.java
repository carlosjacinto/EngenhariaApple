package control;

import view.FuncionarioView;

public class Main {

	public static void main(String[] args) {
		try {
			javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
			com.birosoft.liquid.LiquidLookAndFeel.setLiquidDecorations(true, "mac");
		} catch (Exception e) {
		}
		new FuncionarioView().setVisible(true);
	}

}
