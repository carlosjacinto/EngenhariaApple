package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFGenerator {
	
	private PDDocument documento;
	private ArrayList<Produto> produtos;
	private PDPage paginaBranco;
	
	private int pages=0;
	
	public boolean createPDF(ArrayList<Produto> produtos) throws IOException {
		if(produtos.isEmpty() || produtos==null) return false;
		
		init(produtos);
		
		for (int i = 0; i < pages; i++){
			paginaBranco = new PDPage();
			documento.addPage(paginaBranco);
			
			write(documento.getPage(i), i);
		}
		
		savePDF();
		
		return true;
	}
	
	private void init(ArrayList<Produto> produtos) {
		this.produtos = produtos;
		documento = new PDDocument();
		
		if(produtos.size()%5==0) pages=produtos.size()/5;
		else pages=produtos.size()/5 + 1;
	}
	
	private void write(PDPage page, int i) throws IOException {
		
		PDPageContentStream str = new PDPageContentStream(documento, page) ;
		str.beginText();
		
		str.newLineAtOffset(50, 770);
		str.setLeading(14.5f);
		
		str.setFont(PDType1Font.COURIER_BOLD, 16);
		str.showText("                 Relátorio de estoque");
		str.newLine();
		
		for (int j = i*5; j <((i+1)*5) && j<produtos.size(); j++) {
			
			str.newLine();
			str.setFont(PDType1Font.TIMES_BOLD, 10);
			str.showText("Nome:");
			
			str.newLine();
			str.setFont(PDType1Font.TIMES_ROMAN, 10);
			str.showText(produtos.get(j).getNomeProduto());
			
			str.newLine();
			str.setFont(PDType1Font.TIMES_BOLD, 10);
			str.showText("Codigo:");
			
			str.newLine();
			str.setFont(PDType1Font.TIMES_ROMAN, 10);
			str.showText(produtos.get(j).getIdProduto()+"");
			
			str.newLine();
			str.setFont(PDType1Font.TIMES_BOLD, 10);
			str.showText("Quantidade:");
			
			str.newLine();
			str.setFont(PDType1Font.TIMES_ROMAN, 10);
			str.showText(produtos.get(j).getQtdEstoqueProduto()+"");
			
			str.newLine();
			str.setFont(PDType1Font.TIMES_BOLD, 10);
			str.showText("Preço:");
			
			str.newLine();
			str.setFont(PDType1Font.TIMES_ROMAN, 10);
			str.showText("R$ " + produtos.get(j).getPrecoVendaProduto());
			
			str.newLine();
			str.showText("----------------------------------------------------------------------------------------------------------------------------------------------------------");
			
		}
		
		str.endText();
		str.close();
	}
	
	private void savePDF() throws IOException {
		
		File file = new File("Relatorios/teste.pdf");
		if(file.exists()) {
			int index=0;
			while(file.exists()) {
				index++;
				file = new File("Relatorios/teste("+ index +").pdf");
			}
			documento.save("Relatorios/teste("+ index +").pdf");
		}
		else documento.save("Relatorios/teste.pdf");
		
		documento.close();
	}

}