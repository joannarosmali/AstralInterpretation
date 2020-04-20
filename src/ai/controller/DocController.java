/**
 * 
 */
package ai.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * @author Joanna
 *
 */
public class DocController {

	@SuppressWarnings("resource")
	public boolean generateDoc(String titulo, List<String> parrafo, String ubicacion) {
        XWPFDocument lol = new XWPFDocument();
        XWPFParagraph paragraph =  lol.createParagraph();
        XWPFRun run = paragraph.createRun();
        
       run.setText(titulo);
        
        run.setFontSize(25);
        run.setFontFamily("Helvetica");
        // es para dibujar y tipo de linea en debajo de titulo
        run.setUnderline(UnderlinePatterns.THICK);
        run.setBold(true);
        run.setItalic(true);
        //se trabaja con el color hexadecimal
        //run.setColor("DF0101");

        paragraph.setAlignment(ParagraphAlignment.CENTER);
    
        
        for (int i = 0; i < parrafo.size(); i++) {
        	// declaramos otra para el parrafo
            XWPFParagraph paragraphText =  lol.createParagraph();
            XWPFRun pText = paragraphText.createRun();
            pText.addBreak();
            pText.setFontSize(12);
            pText.setFontFamily("Arial");
        	pText.addBreak();
        	pText.addCarriageReturn();

    		pText.setText(parrafo.get(i));
        	if(i%2 == 0){
                pText.setBold(true);
                pText.setItalic(true);
                paragraphText.setAlignment(ParagraphAlignment.CENTER);
        	}else{
                pText.setBold(false);
                pText.setItalic(false);
                paragraphText.setAlignment(ParagraphAlignment.BOTH);
        	}
		}
 
        try{
            FileOutputStream output = new FileOutputStream(ubicacion);
            lol.write(output);
            output.close();
                
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
        
    }
	
	public void openDoc(String dir){
		ProcessBuilder p = new ProcessBuilder();
		p.command("cmd.exe", "/c", dir);
		try {
			p.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
