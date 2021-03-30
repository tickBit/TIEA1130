import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Kirjaudu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create the frame.
	 */
	public Kirjaudu(Asiakasrekisteri rekisteri) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JPanel contentPane;
		JTextField txtEmail;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKirjaudu = new JLabel("Kirjaudu sis\u00E4\u00E4n");
		lblKirjaudu.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblKirjaudu.setBounds(10, 11, 173, 20);
		contentPane.add(lblKirjaudu);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(10, 42, 51, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(72, 39, 244, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblKirjautuminen = new JLabel("");
		lblKirjautuminen.setForeground(Color.RED);
		lblKirjautuminen.setBounds(59, 104, 257, 14);
		contentPane.add(lblKirjautuminen);
		
		JButton btnKirja = new JButton("Kirjaudu sis\u00E4\u00E4n");
		btnKirja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String email = txtEmail.getText().trim();
				
				for (int i = 0; i < rekisteri.palautaAsiakkaat().size(); i++) {
					if (rekisteri.palautaAsiakkaat().get(i).getEmail().equals(email)) {
						GUI.setKirjautunut(true, email);
						Asiakas a = rekisteri.getAsiakasEmail(email);

						int tilausnumero = a.getAsiakasnumero();
											
						String tilausinfo = GUI.getTilaukset(tilausnumero);
						GUI.setTilaustiedot(tilausinfo);
						
						setVisible(false);
					} else {
						lblKirjautuminen.setText("Sähköpostiosoitetta ei löytynyt.");
					}
				}
			}
		});
		btnKirja.setBounds(72, 70, 244, 23);
		contentPane.add(btnKirja);
		
		JButton btnPeruLogin = new JButton("Peru");
		btnPeruLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnPeruLogin.setBounds(148, 227, 89, 23);
		contentPane.add(btnPeruLogin);
		

	}
}
