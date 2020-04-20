package ai.view;

import static ai.view.Index.FONT_ZISE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import ai.controller.DocController;
import ai.message.AIMessage;
import ai.model.dto.AstralChar;

public class DocGeneration {

	private JButton jButton4;
	private JButton jButton5;
	private JDialog jDialog;
	private JLabel jLabel16;
	private JTextField ubic;
	private JLabel icon;
	private AstralChar astral;
	
	private static ResourceBundle bundle = AIMessage.getMessage();
	
	private static final String DOC_EXT = ".docx";


	/**
	 * Create the frame.
	 */
	public DocGeneration(AstralChar astral) {
		this.astral = astral;
		initComponents();
	}

	private void initComponents() {

		jDialog = new JDialog();
		jDialog.setType(Type.POPUP);
		jDialog.setResizable(false);
		jDialog.getContentPane().setBackground(new Color(204, 255, 255));
		
		ubic = new JTextField();
		ubic.setFont(new Font(bundle.getString("ai.font"), 0, FONT_ZISE));
		jButton4 = new JButton();
		jLabel16 = new JLabel();
		jLabel16.setFont((new Font(bundle.getString("ai.font"), 0, FONT_ZISE)));
		jButton5 = new JButton();
		icon = new JLabel();

		jButton4.setText(bundle.getString("ai.save"));
		jButton4.setFont(new Font(bundle.getString("ai.font"), 0, FONT_ZISE));
		jButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});

		jButton5.setFont(new Font(bundle.getString("ai.font"), 0, FONT_ZISE)); // NOI18N
		jButton5.setForeground(new Color(0, 0, 0));
		jButton5.setText(bundle.getString("ai.acept"));
		jButton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton5ActionPerformed(evt);
			}
		});

		icon.setIcon(new ImageIcon(getClass().getResource("Documentos Word.png")));
		GroupLayout jDialogLayout = new GroupLayout(jDialog.getContentPane());
		jDialogLayout.setHorizontalGroup(
			jDialogLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(jDialogLayout.createSequentialGroup()
					.addGroup(jDialogLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(jDialogLayout.createSequentialGroup()
							.addGap(230)
							.addComponent(jLabel16))
						.addGroup(jDialogLayout.createSequentialGroup()
							.addGroup(jDialogLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(jDialogLayout.createSequentialGroup()
									.addContainerGap()
									.addComponent(jButton5)
									.addGap(18)
									.addComponent(icon))
								.addGroup(Alignment.LEADING, jDialogLayout.createSequentialGroup()
									.addContainerGap()
									.addComponent(ubic, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addComponent(jButton4)))
					.addContainerGap(69, Short.MAX_VALUE))
		);
		jDialogLayout.setVerticalGroup(
			jDialogLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(jDialogLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(jDialogLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(ubic, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(jButton4))
					.addGroup(jDialogLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(jDialogLayout.createSequentialGroup()
							.addGap(7)
							.addComponent(jLabel16)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(icon))
						.addGroup(jDialogLayout.createSequentialGroup()
							.addGap(43)
							.addComponent(jButton5)))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		jDialog.getContentPane().setLayout(jDialogLayout);

		llamarJDialogWord();
	}

	private void llamarJDialogWord() {

		//Centramos nuestro jDialog
		jDialog.setLocationRelativeTo(null);
		//La hacemos modal
		jDialog.setModal(true);
		//Establecemos dimensiones.
		jDialog.setMinimumSize(new Dimension(500, 200));
		//Establecemos un título para el jDialog
		jDialog.setTitle(bundle.getString("ai.save.title") + astral.getNativeName());
		//La hacemos visible.
		jDialog.setVisible(true);
	}


	private void jButton4ActionPerformed(ActionEvent evt) {
		// TODO add your handling code here:

		JFileChooser dialog = new JFileChooser();
		int opcion = dialog.showSaveDialog(jDialog);

		if(opcion == JFileChooser.APPROVE_OPTION){

			File dir = dialog.getSelectedFile();
			String fl = dir.toString();
			ubic.setText(fl + DOC_EXT);
		}
	}

	private void jButton5ActionPerformed(ActionEvent evt) {
		String title = bundle.getString("ai.title.doc") + astral.getNativeName();

		List<String> pagraph = new ArrayList<String>();
		
		Map<String, Map<String,String>> interpretation = astral.getInterpretation();
		int i = 0;
		for (Entry<String, Map<String, String>> first : interpretation.entrySet()) {
		    for (Entry<String, String> second : first.getValue().entrySet()) {
			    pagraph.add(i++, first.getKey() + " en " + second.getKey() + ":");
			    pagraph.add(i++, second.getValue()+"\n");
			}
		}

		String dir = ubic.getText();
		
		if(!dir.contains(DOC_EXT)){
			//System.out.println("no tiene extension...");
			dir +=DOC_EXT;
			ubic.setText(dir);
		}

		//System.out.println("dir = " +dir);

		DocController w = new DocController();  
		// enviando valor a la otra clase que genere word
		if(w.generateDoc(title,pagraph,dir)){
			JOptionPane.showMessageDialog(null, bundle.getString("ai.file.ready"));
			jDialog.dispose();
			w.openDoc(dir);
		}
		else{
			JOptionPane.showMessageDialog(null, bundle.getString("ai.error"));
		}


	}

}
