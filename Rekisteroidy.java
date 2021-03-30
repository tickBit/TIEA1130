import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Rekisteroidy extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public Rekisteroidy(Asiakasrekisteri rekisteri) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 375, 375);
		
		JPanel contentPane;
		JTextField txtNimi;
		JTextField txtKatuosoite;
		JTextField txtPostinumero;
		JTextField txtEmail;
		JTextField txtPuhnro;
		JTextField txtKaupunki;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtKaupunki = new JTextField();
		txtKaupunki.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtKaupunki.setColumns(10);
		txtKaupunki.setBounds(140, 146, 163, 20);
		contentPane.add(txtKaupunki);
		
		JLabel lblRek = new JLabel("Rekister\u00F6ityminen");
		lblRek.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRek.setBounds(10, 11, 201, 14);
		contentPane.add(lblRek);
		
		JLabel lblNewLabel = new JLabel("Uusi asiakas");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(20, 36, 102, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNimi = new JLabel("Nimi");
		lblNimi.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNimi.setBounds(30, 62, 53, 14);
		contentPane.add(lblNimi);
		
		JLabel lblNewLabel_1 = new JLabel("Katuosoite");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(20, 86, 102, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblPostnr = new JLabel("Postinumero");
		lblPostnr.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPostnr.setBounds(20, 118, 95, 14);
		contentPane.add(lblPostnr);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setBounds(20, 177, 63, 14);
		contentPane.add(lblEmail);
		
		JLabel lblNewLabel_2 = new JLabel("Puhelinnumero");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(20, 205, 116, 14);
		contentPane.add(lblNewLabel_2);
		
		txtNimi = new JTextField();
		txtNimi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNimi.setBounds(140, 59, 163, 20);
		contentPane.add(txtNimi);
		txtNimi.setColumns(10);
		
		txtKatuosoite = new JTextField();
		txtKatuosoite.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtKatuosoite.setBounds(140, 84, 163, 20);
		contentPane.add(txtKatuosoite);
		txtKatuosoite.setColumns(10);
		
		txtPostinumero = new JTextField();
		txtPostinumero.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPostinumero.setColumns(10);
		txtPostinumero.setBounds(140, 115, 163, 20);
		contentPane.add(txtPostinumero);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtEmail.setColumns(10);
		txtEmail.setBounds(140, 174, 163, 20);
		contentPane.add(txtEmail);
		
		txtPuhnro = new JTextField();
		txtPuhnro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPuhnro.setColumns(10);
		txtPuhnro.setBounds(140, 202, 163, 20);
		contentPane.add(txtPuhnro);
		
		JLabel lblStatus = new JLabel("");
		lblStatus.setForeground(Color.RED);
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStatus.setBounds(53, 273, 266, 14);
		contentPane.add(lblStatus);
		
		JButton btnRekisteroidy = new JButton("Rekister\u00F6idy");
		btnRekisteroidy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Asiakas asiakas;
				
				String nimi = "";
				String katuosoite = "";
				String postinumero = "";
				String puhelinnumero = "";
				String email = "";
				String kaupunki = "";
				
				nimi = txtNimi.getText().trim();
				katuosoite = txtKatuosoite.getText().trim();
				postinumero = txtPostinumero.getText().trim();
				puhelinnumero = txtPuhnro.getText().trim();
				email = txtEmail.getText().trim();
				kaupunki = txtKaupunki.getText().trim();
				int asiakasnumero = GUI.getAsiakasnumero() + 1;
				
				if (rekisteri.getAsiakasEmail(email) != null) {
					JOptionPane.showMessageDialog(null, "Tämä sähköpostiosoite on jo käytössä.\nOle hyvä ja valitse toinen.");
					return;	
				}
				
				try {
					
					if (!nimi.equals("") && !katuosoite.equals("") && !postinumero.equals("") && !puhelinnumero.equals("") && !email.equals("") && !kaupunki.equals("")) {
						asiakas = new Asiakas(nimi, email, katuosoite, kaupunki, Integer.parseInt(postinumero), puhelinnumero, asiakasnumero);
						kirjoitaAsiakasrekisteri(asiakas);
						rekisteri.lisaaAsiakas(asiakas);
						dispose();
					} else {
						lblStatus.setText("Täytä kaikki kentät rekisteröityäksesi.");
						return;
					}
					
					asiakasnumero++;
					GUI.setAsiakasnumero(asiakasnumero);
					
				} catch (NumberFormatException ex) {}
				
			}
		});
		btnRekisteroidy.setBounds(115, 230, 136, 37);
		contentPane.add(btnRekisteroidy);
		
		JLabel lblKaupunki = new JLabel("Kaupunki");
		lblKaupunki.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblKaupunki.setBounds(20, 149, 102, 14);
		contentPane.add(lblKaupunki);
		

		
		JButton btnPeruRek = new JButton("Peru");
		btnPeruRek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnPeruRek.setBounds(143, 302, 89, 23);
		contentPane.add(btnPeruRek);
		
	}
	
	private void kirjoitaAsiakasrekisteri(Asiakas asiakas) {
		
	     try{
	          OutputStream os = new FileOutputStream("data//asiakasrekisteri.dat", true);
	          PrintWriter pw = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
	    	  // Tässä näkyy asiakasrekisteri.dat'in tietorakenne samalla
	          pw.write(asiakas.getEmail()+";"+asiakas.getNimi()+";"+asiakas.getKatuosoite()+";"+asiakas.getPostinumero()+";"+asiakas.getKaupunki()+";"+asiakas.getPuhelinnumero() + ";" +asiakas.getAsiakasnumero() +"\n");
	    	  pw.close();

	       }catch(IOException ioe){
	    	   System.out.println("I/O-virhe tiedostonkirjoituksessa (asiakasrekisteri.dat)");
	    	   ioe.printStackTrace();
	      }
	     
	}
}
