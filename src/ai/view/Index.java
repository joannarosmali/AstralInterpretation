package ai.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import ai.controller.ComponentController;
import ai.message.AIMessage;
import ai.model.dto.Component;
import ai.model.enums.TypeComponentEnum;

public class Index extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4177101666800272794L;
	private ComponentController componentController;
	private List<Component> energy = null;
	private String[] housesName, signsNames; //se usa como modelo de los combobox
	private JLabel[] jlEnergy;
	private JComboBox<String>[] cbHouses = null, cbSigns = null;

	private static ResourceBundle bundle = AIMessage.getMessage();

	public static int FONT_ZISE = 14;

	private JPanel frmInterpretacinAstral;
	private JTextField tfName;
	private JScrollPane jsc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index window = new Index();
					window/*.frmInterpretacinAstral*/.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, bundle.getString("ai.error"), 
							bundle.getString("ai.title"), JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	@SuppressWarnings("unchecked")
	public Index() {
		componentController= new ComponentController();
		energy = new ArrayList<Component>(componentController.getComponent(TypeComponentEnum.PLANET.getType()));
		jlEnergy = new JLabel[energy.size()];
		cbHouses = new JComboBox[energy.size()];
		cbSigns = new JComboBox[energy.size()];
		housesName = componentController.getHousesName();
		signsNames = componentController.getSignsName();
		initialize();
		/*String ruta =new File (".").getAbsolutePath ();
		ruta = ruta.substring(0, ruta.indexOf(".")) + "resources\\meaning.json";
		JOptionPane.showMessageDialog(null, ruta);*/
	}

	/**
	 * Initialize the contents of the frame.
	 */

	/**
	 * 
	 */
	private void initialize() {
		frmInterpretacinAstral = new JPanel();
		//frmInterpretacinAstral.setAlwaysOnTop(true);
//		frmInterpretacinAstral.setResizable(true);
		/*frmInterpretacinAstral.*/setTitle(bundle.getString("ai.title"));
		/*frmInterpretacinAstral.getContentPane().*/setBackground(SystemColor.control);
		frmInterpretacinAstral./*getContentPane().*/setLayout(null);
		/*frmInterpretacinAstral.setPreferredSize(new Dimension(300, 700));*/
//		
		jsc = new JScrollPane();
		//jsc.createVerticalScrollBar();
		jsc.setBounds(5, 10, 380, 150);
//		frmInterpretacinAstral.getContentPane().add(jsc);
		jsc.setViewportView(frmInterpretacinAstral);
		
		frmInterpretacinAstral.setPreferredSize(new Dimension(350, 250));

		JButton btnGenerarReporte = new JButton(bundle.getString("ai.button.generate"));
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tfName.getText().equals("")){
					JOptionPane.showMessageDialog(null/*frmInterpretacinAstral*/, bundle.getString("ai.error.name"), 
							bundle.getString("ai.title"), JOptionPane.WARNING_MESSAGE);
				}else{
					//frmInterpretacinAstral.setAlwaysOnTop(false);
					int[] houses =  new int[energy.size()];
					int[] signs = new int[energy.size()];
					for (int i = 0; i < energy.size(); i++) {
						if(!((Component)energy.get(i)).getName().equals("Asc")){
							houses[i]= cbHouses[i].getSelectedIndex();
						}
						signs[i]= cbSigns[i].getSelectedIndex();
					}
					//					Interpretation window = new Interpretation(componentController.getAstralChar(tfName.getText(), energy, houses, signs));
					//					window.setVisible();
					//TODO ojo con los errores aqui debería mostrarse un mensaje de error
					new DocGeneration(componentController.getAstralChar(tfName.getText(), energy, houses, signs));
					//frame.setVisible(true);
				}
			}
		});
		btnGenerarReporte.setToolTipText(bundle.getString("ai.tooltip"));
		btnGenerarReporte.setFont(new Font(bundle.getString("ai.font"), Font.PLAIN, 25));
		btnGenerarReporte.setBounds(460, 450, 250, 50);
		frmInterpretacinAstral./*getContentPane().*/add(btnGenerarReporte);

		JLabel lblbienvenidoAlSistema = new JLabel(bundle.getString("ai.welcome"));
		lblbienvenidoAlSistema.setForeground(new Color(0, 153, 204));
		lblbienvenidoAlSistema.setFont(new Font(bundle.getString("ai.font"), Font.BOLD | Font.ITALIC, 22));
		lblbienvenidoAlSistema.setBounds(110, 9, 600, 30);
		frmInterpretacinAstral./*getContentPane().*/add(lblbienvenidoAlSistema);

		JLabel jLabel2 = new JLabel("");
		jLabel2.setBackground(Color.WHITE);
		jLabel2.setIcon(new ImageIcon(getClass().getResource(bundle.getString("ai.image.central"))));
		jLabel2.setBounds(445, 9, 500, 500);
		frmInterpretacinAstral./*getContentPane().*/add(jLabel2);

		JLabel lblNombreDelNativo = new JLabel(bundle.getString("ai.name"));
		lblNombreDelNativo.setFont(new Font(bundle.getString("ai.font"), Font.BOLD, FONT_ZISE));
		lblNombreDelNativo.setBounds(10, 50, 160, 30);
		frmInterpretacinAstral./*getContentPane().*/add(lblNombreDelNativo);

		tfName = new JTextField();
		tfName.setDropMode(DropMode.INSERT);
		tfName.setBackground(new Color(255, 255, 255));
		tfName.setFont(new Font(bundle.getString("ai.font"), Font.PLAIN, FONT_ZISE));
		tfName.setBounds(170, 50, 250, 30);
		frmInterpretacinAstral./*getContentPane().*/add(tfName);
		tfName.setColumns(30);

		int x=40, y=90, w=70, h=30, j=45;

		//TODO OJO hay que considerar la paginación

		for (int i = 0; i < energy.size(); i++) {
			jlEnergy[i]= new JLabel(((Component)energy.get(i)).getName()+ ":");
			jlEnergy[i].setFont(new Font(bundle.getString("ai.font"), Font.BOLD, FONT_ZISE));
			jlEnergy[i].setHorizontalAlignment(JLabel.RIGHT);
			jlEnergy[i].setBounds(x, y, w+40, h);
			frmInterpretacinAstral./*getContentPane().*/add(jlEnergy[i]);

			cbSigns[i] = new JComboBox<String>();
			cbSigns[i].setModel(new DefaultComboBoxModel<String>(signsNames));
			cbSigns[i].setFont(new Font(bundle.getString("ai.font"), Font.BOLD, FONT_ZISE));

			if(!((Component)energy.get(i)).getName().equals("Asc")){
				cbHouses[i] = new JComboBox<String>();
				cbHouses[i].setModel(new DefaultComboBoxModel<String>(housesName));
				cbHouses[i].setFont(new Font(bundle.getString("ai.font"), Font.BOLD, FONT_ZISE));
				cbHouses[i].setBounds(x+130, y, w+30, h);
				frmInterpretacinAstral./*getContentPane().*/add(cbHouses[i]);
				cbSigns[i].setBounds(x+260, y, w+50, h);
			}else{
				cbSigns[i].setBounds(x+130, y, w+60, h);
			}


			frmInterpretacinAstral./*getContentPane().*/add(cbSigns[i]);
			y+=j;
		}

		/*frmInterpretacinAstral.*/setBounds(10, 5, 800, 700);
		/*frmInterpretacinAstral.*/setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(jsc);
	}
}
