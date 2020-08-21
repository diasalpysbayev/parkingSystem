package pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.NumberFormat;

public class CreatePDF {
    static int FONT_SIZE_SMALL = 16;
    static int FONT_SIZE_BIG = 32;
    static int OFFSET = 40;

    public static void createTemplate() throws Exception {
        Document document = new Document();

        Font font1 = new Font(Font.FontFamily.HELVETICA,
                FONT_SIZE_BIG, Font.BOLD);
        Font font2 = new Font(Font.FontFamily.HELVETICA,
                FONT_SIZE_SMALL, Font.ITALIC | Font.UNDERLINE);

        PdfWriter.getInstance(document, new FileOutputStream("template.pdf"));

        document.open();

        Paragraph title = new Paragraph("Receipt", font1);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(FONT_SIZE_BIG);
        document.add(title);

        Paragraph purpose = new Paragraph("PARKING", font2);
        purpose.setSpacingAfter(FONT_SIZE_BIG);
        document.add(purpose);

        Paragraph amount = new Paragraph();
        amount.setFont(font2);
        amount.setSpacingAfter(8);
        amount.add(new Chunk("Price"));
        document.add(amount);

        Paragraph date = new Paragraph();
        date.setFont(font2);
        Phrase phrase = new Phrase();
        phrase.add(new Chunk("Date"));
        date.add(phrase);
        document.add(date);

        document.add(new Paragraph("Name", font2));
        document.close();
    }

    public static void fillInReceipt(Receipt receipt) throws Exception {

        PdfReader reader = new PdfReader(new FileInputStream("template.pdf"));
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("receipt.pdf"));

        PdfContentByte stream = stamper.getOverContent(1);
        stream.beginText();
        stream.setColorFill(BaseColor.BLACK);

        BaseFont font = BaseFont.createFont();

        float pageWidth = reader.getPageSize(1).getWidth();
        stream.setFontAndSize(font, FONT_SIZE_SMALL);
        float v = stream.getEffectiveStringWidth(receipt.getPurpose(), false);

        float fitSize = (pageWidth-OFFSET*2) * FONT_SIZE_SMALL/v;
        stream.setFontAndSize(font, fitSize);
        stream.setTextMatrix(OFFSET, 680);
        stream.showText(receipt.getPurpose());

        stream.setFontAndSize(font, FONT_SIZE_SMALL);

        String amount = NumberFormat.getCurrencyInstance().format(receipt.getPrice());
        v = stream.getEffectiveStringWidth(Integer.toString(receipt.getPrice()), false);
        stream.setTextMatrix(pageWidth - v - OFFSET - 40, 655);
        stream.showText(Integer.toString(receipt.getPrice()) + " tenge");

        v = stream.getEffectiveStringWidth(receipt.getDate() + "", false);
        stream.setTextMatrix(pageWidth - v - OFFSET, 630);
        stream.showText(receipt.getDate() + "");

        v = stream.getEffectiveStringWidth(receipt.getName(), false);
        stream.setTextMatrix(pageWidth - v - OFFSET, 605);
        stream.showText(receipt.getName());

        stream.endText();
        stamper.setFullCompression();
        stamper.close();
    }
}
