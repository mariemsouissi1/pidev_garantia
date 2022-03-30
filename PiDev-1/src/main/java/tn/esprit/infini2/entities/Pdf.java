package tn.esprit.infini2.entities;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;
 
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class Pdf {
	
	
	 private List<Indemnity_pay> listIndemnity_pays;
     
	    public Pdf(List<Indemnity_pay> listIndemnity_pays) {
	        this.listIndemnity_pays = listIndemnity_pays;
	    }
	    
	    
	    
	    private void writeTableHeader(PdfPTable table) {
	        PdfPCell cell = new PdfPCell();
	        cell.setBackgroundColor(Color.BLUE);
	        cell.setPadding(5);
	         
	        Font font = FontFactory.getFont(FontFactory.HELVETICA);
	        font.setColor(Color.WHITE);
	         
	        cell.setPhrase(new Phrase("Indemnity_pay_ID", font));
	         
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Indemnity_payCode", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Indemnity_payDate", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("amountPayed", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Indemnity_payMethod", font));
	        table.addCell(cell);       
	    }
	    
	    
	    private void writeTableData(PdfPTable table) {
	        for (Indemnity_pay Indemnity_pay : listIndemnity_pays) {
	            table.addCell(String.valueOf(Indemnity_pay.getId()));
	            table.addCell(Indemnity_pay.getIndemnity_payCode());
	            table.addCell(Indemnity_pay.getIndemnity_payDate().toString());
	            table.addCell(String.valueOf(Indemnity_pay.getAmountPayed()));
	            table.addCell(Indemnity_pay.getIndemnity_payMethod());
	        }
	    }
	    
	    public void export(HttpServletResponse response) throws DocumentException, IOException {
	     
	         
	    	  Document document = new Document(PageSize.A4);
	          PdfWriter.getInstance(document, response.getOutputStream());
	           
	          document.open();
	          Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	          font.setSize(18);
	          font.setColor(Color.BLUE);
	           
	          Paragraph p = new Paragraph("List of Users", font);
	          p.setAlignment(Paragraph.ALIGN_CENTER);
	           
	          document.add(p);
	           
	          PdfPTable table = new PdfPTable(5);
	          table.setWidthPercentage(100f);
	          table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
	          table.setSpacingBefore(10);
	           
	          writeTableHeader(table);
	          writeTableData(table);
	           
	          document.add(table);
	           
	          document.close();
	           
	      }

}