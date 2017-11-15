package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class PDFGenerator {
	
	private PDDocument documento;
	private ArrayList<Produto> produtos = ProdutoDAO.getInstance().retornaProdutoArrayList("");
	private PDPage paginaBranco;
	
	private int pages=0;
	
	//Strings
	private static final String DIVISOR = "-------------------------------------------------------------"
			+ "---------------------------------------------------------------------------------------------";
	private static final String PATH = "Relatorios/Relatorio";
	private static final String LOGO_PATH = "Relatorios/logo_pdf.png";
	
	public boolean createPDF() throws IOException {
		if(produtos.isEmpty() || produtos==null) return false;
		
		init();
		
		paginaBranco = new PDPage();
		documento.addPage(paginaBranco);
		criarCapa(documento.getPage(0));
		
		for (int i = 1; i <= pages; i++){
			paginaBranco = new PDPage();
			documento.addPage(paginaBranco);
			
			write(documento.getPage(i), i);
		}
		
		savePDF();
		
		return true;
	}
	
	private void init() {
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
			str.showText(DIVISOR);
			
		}
		
		for (int j = 0; j < 3; j++) str.newLine();
		str.showText("                                                                                                                                                          "+(i+1));
		
		str.endText();
		str.close();
	}
	
	private void criarCapa(PDPage p) {
		
		try {
			PDImageXObject pdImage = PDImageXObject.createFromFile("Interno/logo_pdf.png", documento);
			
			PDPageContentStream contentStream = new PDPageContentStream(documento, p);
			
			contentStream.drawImage(pdImage, 200, 300);
			
			contentStream.beginText();
			
			contentStream.newLineAtOffset(50, 700);
			contentStream.setLeading(14.5f);
			
			contentStream.setFont(PDType1Font.COURIER_BOLD, 30);
			contentStream.showText("    Relátorio de estoque");
			
			for (int i = 0; i < 32; i++) {
				contentStream.newLine();
			}
			
			contentStream.setFont(PDType1Font.TIMES_BOLD, 22);
			contentStream.showText("                                   Apple Cart");

			contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
			for (int i = 0; i < 10; i++) contentStream.newLine();
			contentStream.showText("                                           " + new Date(System.currentTimeMillis()));
			
			contentStream.endText();

			contentStream.close();

			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível gerar o relatório!", null,
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void savePDF() throws IOException {
		
		File file = new File(PATH+".pdf");
		if(file.exists()) {
			int index=0;
			while(file.exists()) {
				index++;
				file = new File(PATH+"("+ index +").pdf");
			}
			documento.save(PATH+"("+ index +").pdf");
		}
		else documento.save(PATH+".pdf");
		
		documento.close();
	}

}