package tn.esprit.infini2.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;

import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import tn.esprit.infini2.entities.LoanSimulation;






@Service
public class PdfLoanServiceImpl implements IPdfLoanService {

	@Autowired
	LoanSimulationServiceImpl loanSimulationService;
	
	@Override
	public String toPDF(long idloan) 
	{
		String URL_file = null;
		
		LoanSimulation simulation = loanSimulationService.getSimulationById(idloan);
		

		Date d = new Date();
		SimpleDateFormat formater = null;
		formater = new SimpleDateFormat("yyyyMMdd_HHmmss");
		

		try {
			try {
				try {
					try {
		
		URL_file = "C:\\Loans Simulations\\" + idloan + "_" + formater.format(d)
		+ ".pdf";
		
		OutputStream file = new FileOutputStream(new File(URL_file));
		
		
		Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.GRAY);
		Font catFont15B = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, BaseColor.BLACK);
		Font subFontPara13B = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD, BaseColor.BLACK);
		Font subFontPara13N = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.NORMAL, BaseColor.BLACK);
		Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);

		Document my_pdf_report = new Document();
		PdfWriter.getInstance(my_pdf_report, file);
		my_pdf_report.open();

//////////////////////////////DATE//////////////////////
		DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
		String dt = df.format(new Date());
		Paragraph dateStr = new Paragraph("Date: " + dt, smallBold);
		dateStr.setAlignment(Element.ALIGN_RIGHT);
		
		
		Paragraph bank = new Paragraph(simulation.getBankLoan().getNameBank(), subFont);
		bank.setAlignment(Element.ALIGN_LEFT);
		
		Image imgBank = null;
			imgBank = Image.getInstance("C:\\Garantia\\Garantía__6_-removebg-preview.png");
		
		imgBank.setAbsolutePosition(260, 767);
		imgBank.scalePercent(10);
		
///////////////////////ESPACE/////////////////////////////////
		Paragraph espace = new Paragraph(" ", subFont);
		espace.setAlignment(Element.ALIGN_LEFT);
		
//////////////////////PARAGRAPHE 1/////////////////////////////////////////
		Paragraph para1 = new Paragraph("Mr,Mms " + simulation.getCustomerAccountLoan().getFirstNameCustomer()
				+ " " + simulation.getCustomerAccountLoan().getLastNameCustomer(), catFont15B);
		para1.setAlignment(Element.ALIGN_LEFT);
		Paragraph para2 = new Paragraph(
				"Garantia team is glad to provide a simulation system "
						+ "for your demande of credit .",
				subFontPara13N);
		para2.setAlignment(Element.ALIGN_LEFT);
		
/////////////////////////////////TITRE/////////////////////////////////////////
		Paragraph titre1 = new Paragraph("YOUR INFORMATION", catFont15B);
		titre1.setAlignment(Element.ALIGN_CENTER);
		
/////////////////////////////ESPACE////////////////////////////////////////
				
		espace.setAlignment(Element.ALIGN_LEFT);
				
//////////////////////////TABLE DECLARATION///////////////////////////////////////////
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(90);
		

///////////////////////////////Ligne 1//////////////////////////////////
		PdfPCell Cellnom = new PdfPCell(new Phrase("First Name & Last Name:\r\n " + simulation.getCustomerAccountLoan().getFirstNameCustomer() + " "
				+ simulation.getCustomerAccountLoan().getLastNameCustomer(), subFontPara13N));
		Cellnom.setBorder(Rectangle.NO_BORDER);
		Cellnom.setBackgroundColor(BaseColor.WHITE);
		table.addCell(Cellnom);
		
//////////////////////////////Ligne 2/////////////////////////////////////
		PdfPCell CellEmail = new PdfPCell(new Phrase("Email :\r\n" + simulation.getCustomerAccountLoan().getEmailAccount(), subFontPara13N));
		CellEmail.setBorder(Rectangle.NO_BORDER);
		CellEmail.setBackgroundColor(BaseColor.WHITE);
		table.addCell(CellEmail);
		
///////////////////Ligne 3/////////////////////////////////////////////////////
		PdfPCell Celsal = new PdfPCell(new Phrase("Salary :\r\n" + simulation.getSalaire(), subFontPara13N));
		Celsal.setBorder(Rectangle.NO_BORDER);
		Celsal.setBackgroundColor(BaseColor.WHITE);
		table.addCell(Celsal);
		
////////////////////////BREAK/////////////////////////
		Paragraph separator = new Paragraph("_____________________________________________________________________",
				catFont15B);
		separator.setAlignment(Element.ALIGN_CENTER);
		
//////////////////////TITRE//////////////////////////////////////
		Paragraph titre2 = new Paragraph("BANK", catFont15B);
		titre2.setAlignment(Element.ALIGN_CENTER);
		
/////////////////ESPACE///////////////////////////////////////////
		espace.setAlignment(Element.ALIGN_LEFT);
		
////////////////////////TABLE 2////////////////////////////////
		PdfPTable table1 = new PdfPTable(5);
		table1.setWidthPercentage(90);
		
///////////////////////LIGNE 1//////////////////////////////////////////////
		PdfPCell Celbankname = new PdfPCell(new Phrase("Bank :\r\n" + simulation.getBankLoan().getNameBank(), subFontPara13N));
		Celbankname.setBorder(Rectangle.NO_BORDER);
		Celbankname.setBackgroundColor(BaseColor.WHITE);
		table1.addCell(Celbankname);
		
///////////////////////LIGNE 2//////////////////////////////////////////////
		PdfPCell Celagentname = new PdfPCell(new Phrase("Agent :\r\n" + simulation.getBankLoan().getAgentBank().getFirstName() + "   " +simulation.getBankLoan().getAgentBank().getLastName(),subFontPara13N));
		Celagentname.setBorder(Rectangle.NO_BORDER);
		Celagentname.setBackgroundColor(BaseColor.WHITE);
		table1.addCell(Celagentname);
		
///////////////////////LIGNE 3//////////////////////////////////////////////
				PdfPCell Celbankadd = new PdfPCell(new Phrase("Bank Address :\r\n" + simulation.getBankLoan().getAdresseBank() ,subFontPara13N));
				Celbankadd.setBorder(Rectangle.NO_BORDER);
				Celbankadd.setBackgroundColor(BaseColor.WHITE);
				table1.addCell(Celbankadd);
///////////////////////LIGNE 4//////////////////////////////////////////////
				PdfPCell Celtm = new PdfPCell(new Phrase("Average Margin Rate :\r\n" + simulation.getBankLoan().getTauxMoyenDuMarche() ,subFontPara13N));
				Celtm.setBorder(Rectangle.NO_BORDER);
				Celtm.setBackgroundColor(BaseColor.WHITE);
				table1.addCell(Celtm);
				
///////////////////////LIGNE 5//////////////////////////////////////////////
				PdfPCell Celmarg = new PdfPCell(new Phrase("Interet Bank Margin:\r\n" + simulation.getBankLoan().getMargeInteretBank() , subFontPara13N));
				Celmarg.setBorder(Rectangle.NO_BORDER);
				Celmarg.setBackgroundColor(BaseColor.WHITE);
				table1.addCell(Celmarg);	
				
///////////////////////BREAK//////////////////////////////////////////////
				Paragraph separator2 = new Paragraph("_____________________________________________________________________\r\n",catFont15B);
				
				espace.setAlignment(Element.ALIGN_LEFT);

//////////////////////////////TITRE///////////////////////////////////////////////////
				Paragraph titre21 = new Paragraph("ESTIMATION", catFont15B);
				titre21.setAlignment(Element.ALIGN_CENTER);		
				
				espace.setAlignment(Element.ALIGN_LEFT);
////////////////////////TABLE 3////////////////////////////////////////////////////::	
				PdfPTable table3 = new PdfPTable(4);
				table3.setWidthPercentage(100);
				
///////////////////////LIGNE 6//////////////////////////////////////////////
				PdfPCell Celtaux = new PdfPCell(new Phrase("Rate :\r\n" + simulation.getTaux() , subFontPara13N));
				Celtaux.setBorder(Rectangle.NO_BORDER);
				Celtaux.setBackgroundColor(BaseColor.WHITE);
				table3.addCell(Celtaux);	
				
///////////////////////LIGNE 7//////////////////////////////////////////////
				PdfPCell Celmtaux = new PdfPCell(new Phrase("Monthly rate :\r\n" + simulation.getMensuel() , subFontPara13N));
				Celmtaux.setBorder(Rectangle.NO_BORDER);
				Celmtaux.setBackgroundColor(BaseColor.WHITE);
				table3.addCell(Celmtaux);	
				
/////////////////////////LIGNE 8//////////////////////////////////////////////
				PdfPCell Celcapacity = new PdfPCell(new Phrase("Repayment capacity :\r\n" + simulation.getCapaciteDeRemboursement() , subFontPara13N));
				Celcapacity.setBorder(Rectangle.NO_BORDER);
				Celcapacity.setBackgroundColor(BaseColor.WHITE);
				table3.addCell(Celcapacity);	
				
///////////////////////LIGNE 9//////////////////////////////////////////////
				PdfPCell Celmp= new PdfPCell(new Phrase("Monthly payment :\r\n" + simulation.getMensualite() , subFontPara13N));
				Celmp.setBorder(Rectangle.NO_BORDER);
				Celmp.setBackgroundColor(BaseColor.WHITE);
				table3.addCell(Celmp);		
				
				espace.setAlignment(Element.ALIGN_LEFT);
				
///////////////////////LIGNE 10//////////////////////////////////////////////
				PdfPCell CelBI= new PdfPCell(new Phrase("Bank interest :\r\n" + simulation.getInteret() , subFontPara13N));
				CelBI.setBorder(Rectangle.NO_BORDER);
				CelBI.setBackgroundColor(BaseColor.WHITE);
				table3.addCell(CelBI);	
				
///////////////////////LIGNE 11//////////////////////////////////////////////
				PdfPCell Celp= new PdfPCell(new Phrase("Principal :\r\n" + simulation.getPrincipale() , subFontPara13N));
				Celp.setBorder(Rectangle.NO_BORDER);
				Celp.setBackgroundColor(BaseColor.WHITE);
				table3.addCell(Celp);	
				
///////////////////////LIGNE 12//////////////////////////////////////////////
				PdfPCell CelRA= new PdfPCell(new Phrase("Refund Amount :\r\n" + simulation.getMontantRembourse() , subFontPara13N));
				CelRA.setBorder(Rectangle.NO_BORDER);
				CelRA.setBackgroundColor(BaseColor.WHITE);
				table3.addCell(CelRA);
				
///////////////////////LIGNE 13//////////////////////////////////////////////
				PdfPCell CelBIT= new PdfPCell(new Phrase("Bank Total interest :\r\n" + simulation.getInteretTotale() , subFontPara13N));
				CelBIT.setBorder(Rectangle.NO_BORDER);
				CelBIT.setBackgroundColor(BaseColor.WHITE);
				table3.addCell(CelBIT);
				
				Paragraph titrefin = new Paragraph("THANK YOU FOR USING OUR SERVICE.", subFont);
				titrefin.setAlignment(Element.ALIGN_LEFT);	
				
/////////////////FIN//////////////////////////////
				Paragraph finPage1 = new Paragraph(" Page 1/1", smallBold);
				finPage1.setAlignment(Element.ALIGN_RIGHT);	
				
		
						
				my_pdf_report.add(bank);
				my_pdf_report.add(dateStr);
				my_pdf_report.add(imgBank);
			
				my_pdf_report.add(espace);

				my_pdf_report.add(para1);
				my_pdf_report.add(para2);
				my_pdf_report.add(espace);
				
				
				my_pdf_report.add(separator);
				my_pdf_report.add(espace);
				my_pdf_report.add(titre1);
				
				my_pdf_report.add(espace);
				my_pdf_report.add(table);
				my_pdf_report.add(espace);
				
				
				my_pdf_report.add(separator);
				my_pdf_report.add(espace);
				my_pdf_report.add(titre2);
				

				my_pdf_report.add(espace);
				my_pdf_report.add(table1);
				my_pdf_report.add(espace);
				
				
				my_pdf_report.add(separator);
				my_pdf_report.add(espace);
				my_pdf_report.add(titre21);
			

				my_pdf_report.add(espace);
				my_pdf_report.add(table3);
				
				my_pdf_report.add(espace);
				my_pdf_report.add(espace);
			
				my_pdf_report.add(espace);
				my_pdf_report.add(titrefin);
				my_pdf_report.add(espace);
				my_pdf_report.add(espace);
				my_pdf_report.add(espace);
				my_pdf_report.add(espace);
				my_pdf_report.add(finPage1);

				my_pdf_report.close();
				file.close();
				
				Runtime.getRuntime()
				.exec("rundll32 url.dll,FileProtocolHandlerC:\\Loans Simulations\\"
						+ idloan + "_" + formater.format(d) + ".pdf");
					//p.waitFor(2, TimeUnit.SECONDS);
				
						} catch (FileNotFoundException e) {
						e.printStackTrace();
						}
			} catch (DocumentException e) {
				e.printStackTrace();
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	} catch (IOException e) {
		e.printStackTrace();
	}
		return "" + idloan + "_" + formater.format(d) + ".pdf";
	}
}