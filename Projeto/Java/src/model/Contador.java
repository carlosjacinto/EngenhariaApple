package model;

import javax.swing.ImageIcon;

import view.Principal;

public class Contador implements Runnable{
	private int i = 1;
	private Principal principal;
	
	public Contador(Principal principal) {
		// TODO Auto-generated constructor stub
		this.principal = principal;
	}
	
	@Override
	public void run() {
		try {
			for(;;) {
				Thread.sleep(3500);
				principal.getLblFundo().setIcon(new ImageIcon("Interno/"+i+".jpg"));
				if(i == 5) i = 1;
				else i++;
			}
		} catch (InterruptedException e) {
		}
		
	}
	

}
