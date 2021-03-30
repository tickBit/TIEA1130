import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;

import java.awt.Color;
import java.awt.List;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import java.time.temporal.ChronoField;

public class GUI extends JFrame {

	/**
	 * Tekijä: Random Student
	 * 
	 *  En ole kerinnyt muun opiskelukiireen vuoksi kommentoimaan soveullusta paremmin
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private static List listTilaukset;
	private JTabbedPane tabbedPane;
	private JTextField txtPages;
	private static JLabel lblKirjautunut;
	private Kirjat kirjat;
	private Kirjakategoriat kategoriat;
	private Asiakasrekisteri rekisteri;
	private static boolean kirjautunut;
	private static String kirjautunutEmail;
	
	private int tilausnumero;
	private static Tilaukset tilaukset;
	private boolean asiakaslistamoodiKaikkiAsiakkaat;
	private static int asiakasnumero;

	
	public static void setKirjautunut(boolean kirj, String email) {
		kirjautunut = kirj;
		asetaKirjautumistieto(email);
		kirjautunutEmail = email;
	}

	public static int getAsiakasnumero() {
		return asiakasnumero;
	}
	
	public static void setAsiakasnumero(int asnro) {
		asiakasnumero = asnro;
	}
	
	public static void setTilaustiedot(String tilausinfo) {
		String nyktilaukset[];
		nyktilaukset = tilausinfo.split("\n");
		
		for (int i = 0; i < nyktilaukset.length; i++) {
			listTilaukset.add(nyktilaukset[i]);
		}
	}
	
	public static String getTilaukset(int asnro) {
		return tilaukset.getTilausTiedotByAsiakasnumero(asnro);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1132, 779);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		rekisteri = new Asiakasrekisteri();
		asiakasnumero = 0;
		
		tilausnumero = 100;
		tilaukset = new Tilaukset();

		kategoriat = new Kirjakategoriat();
		kategoriat.setKategorioita(0);
		
		kirjat = new Kirjat();
		kirjat.setKirjatLKM(0);
		

		
		lueKirjat();
		lueAsiakasrekisteri();
		
		JPanel contentPane;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JTextField txtPrice;
		
		JTextField txtTekija2;
		JTextField txtKirjaNimi2;
		JTextField txtJulkaisija2;
		JTextField txtHinta;
	
		JTextField txtTekija;
		JTextField txtKirjaNimi;
		JTextField txtJulkaisija;
		JComboBox<String> cmbKunto;
		

		tabbedPane.setBounds(0, 0, 903+192, 740);
		
		JPanel panelAsiakas = new JPanel();
		JPanel panelKauppias = new JPanel();
		
		tabbedPane.insertTab("Asiakas", null, panelAsiakas, "Asiakkaan näkymä", 0);
		panelAsiakas.setLayout(null);
		
		txtHinta = new JTextField();
		txtHinta.setBounds(526, 586, 81, 20);
		panelKauppias.add(txtHinta);
		txtHinta.setColumns(10);
		
		lblKirjautunut = new JLabel("Et ole kirjautunut");
		lblKirjautunut.setBounds(668, 47, 320, 26);
		panelAsiakas.add(lblKirjautunut);
		
		JButton btnRek = new JButton("Rekister\u00F6idy");
		btnRek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (kirjautunut == false) {
					Rekisteroidy rek = new Rekisteroidy(rekisteri);
					rek.setVisible(true);
					
				} else {
					JOptionPane.showMessageDialog(null, "Nykyisen käyttäjän on ensin kirjauduttava ulos.");
					return;
				}
				
			}
			
			
		});
		btnRek.setBounds(943, 10, 137, 26);
		panelAsiakas.add(btnRek);
		
		JLabel lblNewLabel = new JLabel("Antikvariaattikauppa");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(12, 12, 196, 18);
		panelAsiakas.add(lblNewLabel);
		
		JButton btnKirjaudu = new JButton("Kirjaudu sis\u00E4\u00E4n");
		btnKirjaudu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (kirjautunut == true) {
					JOptionPane.showMessageDialog(null, "Nykyisen käyttäjän on ensin kirjauduttava ulos.");
					return;
				}
				
				Kirjaudu kirjaudu = new Kirjaudu(rekisteri);
				kirjaudu.setVisible(true);
			}
		});
		btnKirjaudu.setBounds(668, 10, 173, 26);
		panelAsiakas.add(btnKirjaudu);
		
		JLabel lblHakuKirjoista = new JLabel("Haku kirjoista");
		lblHakuKirjoista.setFont(new Font("Dialog", Font.BOLD, 14));
		lblHakuKirjoista.setBounds(12, 63, 104, 16);
		panelAsiakas.add(lblHakuKirjoista);
		
		JLabel lblNewLabel_1 = new JLabel("Tekij\u00E4");
		lblNewLabel_1.setBounds(12, 89, 55, 16);
		panelAsiakas.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Kirjan nimi");
		lblNewLabel_2.setBounds(12, 117, 81, 16);
		panelAsiakas.add(lblNewLabel_2);
		
		JLabel lblJulkaisija = new JLabel("Julkaisija");
		lblJulkaisija.setBounds(12, 145, 81, 16);
		panelAsiakas.add(lblJulkaisija);
		
		JLabel lblKategoria = new JLabel("Kategoria");
		lblKategoria.setBounds(12, 174, 68, 16);
		panelAsiakas.add(lblKategoria);
		
		txtTekija = new JTextField();
		txtTekija.setBounds(119, 87, 148, 20);
		panelAsiakas.add(txtTekija);
		txtTekija.setColumns(10);
		
		txtKirjaNimi = new JTextField();
		txtKirjaNimi.setBounds(119, 115, 148, 20);
		panelAsiakas.add(txtKirjaNimi);
		txtKirjaNimi.setColumns(10);
		
		txtJulkaisija = new JTextField();
		txtJulkaisija.setBounds(119, 143, 148, 20);
		panelAsiakas.add(txtJulkaisija);
		txtJulkaisija.setColumns(10);
		
		cmbKunto = new JComboBox<String>();
		cmbKunto.setBounds(212, 196, 55, 25);
		cmbKunto.addItem("5");
		cmbKunto.addItem("4");
		cmbKunto.addItem("3");
		cmbKunto.addItem("2");
		cmbKunto.addItem("1");		
		panelAsiakas.add(cmbKunto);
		
		JLabel lblYhteensa = new JLabel("");
		lblYhteensa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblYhteensa.setBounds(670, 11, 46, 14);
		panelKauppias.add(lblYhteensa);
		
		JLabel lblHaunTulos = new JLabel("Haun tulos");
		lblHaunTulos.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblHaunTulos.setBounds(12, 288, 104, 16);
		panelAsiakas.add(lblHaunTulos);
		
		List listTilaa = new List();
		listTilaa.setBounds(12, 310, 265, 362);
		panelAsiakas.add(listTilaa);
		
		JCheckBox checkKunto = new JCheckBox("Rajaa kuntoluokan mukaan");
		checkKunto.setBounds(12, 197, 196, 24);
		panelAsiakas.add(checkKunto);

		JComboBox<String> cmbCat = new JComboBox<String>();
		cmbCat.setBounds(119, 172, 148, 20);
		panelAsiakas.add(cmbCat);
		
		cmbCat.addItem(" ");  // välttämättä ei haeta kategorian perusteella, joten yksi "tyhjä" item mukaan
							  // tämä trimmataan trim()-metodilla myähemmin
		
		for (int i = 0; i < kategoriat.getKirjakategoriat().size(); i++) {
			cmbCat.addItem(kategoriat.getKirjakategoriat().get(i).getKategoriaNimi());
		}
		
		ArrayList<Kirja> loydetytKirjat1 = new ArrayList<Kirja>();
		ArrayList<Kirja> loydetytKirjat2 = new ArrayList<Kirja>();
		ArrayList<Kirja> loydetytKirjat3 = new ArrayList<Kirja>();
		ArrayList<Kirja> loydetytKirjat4 = new ArrayList<Kirja>();
		
		JButton btnHae = new JButton("Hae");
		btnHae.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nimi = "";
				String tekija = "";
				String julkaisija = "";
				String kateg;
				int kuntoluokka = -1;
				boolean hakuKunnolla;
				nimi = txtKirjaNimi.getText().trim();
				tekija = txtTekija.getText().trim();
				julkaisija = txtJulkaisija.getText().trim();
				kateg = cmbCat.getSelectedItem().toString().trim();

				hakuKunnolla = checkKunto.isSelected();
				
				// Jos haku kuntoluokan mukaan on checkattu, niin poimitaan kuntoluokka
				if (hakuKunnolla) {
					try {
						kuntoluokka = Integer.parseInt(cmbKunto.getSelectedItem().toString());
					} catch (NumberFormatException ex) {
						System.out.println("Kuntoluokkaa ei saatu parsittua integeriksi");
					}
				}

				if (hakuKunnolla == true && tekija.equals("") && julkaisija.equals("") && kateg.equals("") && nimi.equals("")) {
					JOptionPane.showMessageDialog(null, "Et voi hakea pelkän kuntoluokan perusteella.\nHaku on liian laaja.");
					return;
				}
				
				loydetytKirjat1.clear();
				loydetytKirjat2.clear();
				loydetytKirjat3.clear();
				loydetytKirjat4.clear();
				
				
				if (tekija.equals("") == false) loydetytKirjat1.addAll(kirjat.annaKirjatKirjoittaja(tekija));
				if (julkaisija.equals("") == false) loydetytKirjat2.addAll(kirjat.annaKirjatJulkaisija(julkaisija));
				if (kateg.equals("") == false) loydetytKirjat3.addAll(kirjat.annaKirjatKategoriaNimi(kateg));

				
				if (!tekija.equals("") && !julkaisija.equals("") && !kateg.equals("")) {
					
					
				
					for (Kirja k3 : loydetytKirjat3) {
						if (k3.getKirjoittaja().equals(tekija) && k3.getJulkaisija().equals(julkaisija)) {
							if (nimi.equals("") == false) {
							// Jos kaikki hakuehdot täsmäävät yhtäaikaa, lisätään kirja lopullisiin läydettyihin kirjoihin
								if (k3.getNimi().equals(nimi)) {
									if (kuntoluokka != -1) {
										if (k3.getKuntoluokka() == kuntoluokka && loydetytKirjat4.contains(k3) == false) loydetytKirjat4.add(k3);
										} else {
											if (loydetytKirjat4.contains(k3) == false) loydetytKirjat4.add(k3);
										}
									}
								} else {
									if (kuntoluokka != -1) {
										if (k3.getKuntoluokka() == kuntoluokka && loydetytKirjat4.contains(k3) == false) loydetytKirjat4.add(k3);
									} else {
										if (loydetytKirjat4.contains(k3) == false) loydetytKirjat4.add(k3);
									}
							}
						}
					}
							
				
			} else if (tekija.equals("") && !julkaisija.equals("") && !kateg.equals("")) {
										
				for (Kirja k3 : loydetytKirjat3) {
					if (k3.getJulkaisija().equals(julkaisija)) {
							if (nimi.equals("") == false) {
								// Jos kaikki hakuehdot täsmäävät yhtäaikaa, lisätään kirja lopullisiin läydettyihin kirjoihin
								if (k3.getNimi().equals(nimi)) {
									if (kuntoluokka != -1) {
										if (k3.getKuntoluokka() == kuntoluokka && loydetytKirjat4.contains(k3) == false) loydetytKirjat4.add(k3);
										} else {
											if (loydetytKirjat4.contains(k3) == false) loydetytKirjat4.add(k3);
										}
									}
								} else {
									if (kuntoluokka != -1) {
										if (k3.getKuntoluokka() == kuntoluokka && loydetytKirjat4.contains(k3) == false) loydetytKirjat4.add(k3);
									} else {
										if (loydetytKirjat4.contains(k3) == false) loydetytKirjat4.add(k3);
									}
							}
						
					}
				}
					
					
				} else if (julkaisija.equals("") && !tekija.equals("") && !kateg.equals("")) {
									
					for (Kirja k3 : loydetytKirjat3) {
						if (k3.getKirjoittaja().equals(tekija)) {
							if (nimi.equals("") == false) {
										
								if (kuntoluokka != -1) {
									// Jos kaikki hakuehdot täsmäävät yhtäaikaa, lisätään kirja lopullisiin läydettyihin kirjoihin
									if (k3.getKuntoluokka() == kuntoluokka && k3.getNimi().equals(nimi)) {
										if (loydetytKirjat4.contains(k3) == false) loydetytKirjat4.add(k3);
									}
								} else {
									if (k3.getNimi().equals(nimi)) {
										if (loydetytKirjat4.contains(k3) == false) loydetytKirjat4.add(k3);
									}
								}
							} else {
								if (kuntoluokka != -1) {
									if (k3.getKuntoluokka() == kuntoluokka && loydetytKirjat4.contains(k3) == false) loydetytKirjat4.add(k3);
								} else {
									if (loydetytKirjat4.contains(k3) == false) loydetytKirjat4.add(k3);
								}
							}
						}
					}
						
					
				} else if (kateg.equals("") && !tekija.equals("") && !julkaisija.equals("")) {

								
					// Tekijä
					for (Kirja k1 : loydetytKirjat1) {
																		
							if (k1.getJulkaisija().equals(julkaisija)) {
									
								// Onko myäs nimen mukaan haku?
								if (nimi.equals("") == false) {
											
									// Onko kuntoluokka annettu?
									if (kuntoluokka != -1) {

										// Jos kaikki hakuehdot täsmäävät yhtäaikaa, lisätään kirja lopullisiin läydettyihin kirjoihin
										if (k1.getNimi().equals(nimi) && k1.getKuntoluokka() == kuntoluokka) {
												if (loydetytKirjat4.contains(k1) == false) loydetytKirjat4.add(k1);
										}
									} else {
										if (k1.getNimi().equals(nimi) && loydetytKirjat4.contains(k1) == false) loydetytKirjat4.add(k1);
									}
											
								} else {
									if (kuntoluokka != -1) {
										// ei lisätä samaa kirjaa useammin kuin kerran
										if (k1.getKuntoluokka() == kuntoluokka && loydetytKirjat4.contains(k1) == false) loydetytKirjat4.add(k1);
									} else {
										if (loydetytKirjat4.contains(k1) == false) loydetytKirjat4.add(k1);
									}
								}
							}
									
						
					}
					
				} else if (kateg.equals("") && julkaisija.equals("") && !tekija.equals("")) {
					// Tekijä
					for (Kirja k1 : loydetytKirjat1) {
							if (nimi.equals("") == false) {
								// Jos kaikki hakuehdot täsmäävät yhtäaikaa, lisätään kirja lopullisiin läydettyihin kirjoihin
								if (k1.getNimi().equals(nimi)) {
									
									if (kuntoluokka != -1) {								
										if (k1.getKuntoluokka() == kuntoluokka && loydetytKirjat4.contains(k1) == false) loydetytKirjat4.add(k1);
									} else {
										if (loydetytKirjat4.contains(k1) == false) loydetytKirjat4.add(k1);
									}
								}

							} else {
								if (kuntoluokka != -1) {
									if (k1.getKuntoluokka() == kuntoluokka && loydetytKirjat4.contains(k1) == false) loydetytKirjat4.add(k1);
									
								} else {
									if (loydetytKirjat4.contains(k1) == false) loydetytKirjat4.add(k1);
								}
							}	
						
					}
				} else if (kateg.equals("") && !julkaisija.equals("") && tekija.equals("")) {
					// Julkaisija 
					for (Kirja k2 : loydetytKirjat2) {
						if (nimi.equals("") == false) {
							// Jos kaikki hakuehdot täsmäävät yhtäaikaa, lisätään kirja lopullisiin läydettyihin kirjoihin
							if (k2.getNimi().equals(nimi)) {
								
								if (kuntoluokka != -1) {
									if (k2.getKuntoluokka() == kuntoluokka && loydetytKirjat4.contains(k2) == false) loydetytKirjat4.add(k2);
							}	} else {
								if (loydetytKirjat4.contains(k2) == false) loydetytKirjat4.add(k2);
								}
						} else {
							if (kuntoluokka != -1) {
								if (k2.getKuntoluokka() == kuntoluokka) {
							
									if (loydetytKirjat4.contains(k2) == false) loydetytKirjat4.add(k2);
						}		} else {
									if (loydetytKirjat4.contains(k2) == false) loydetytKirjat4.add(k2);
								}
						}
					}
					// haetaan kategorian mukaa sekä mahdollisesti kirjan nimen ja kuntoluokan mukaan
				} else if (!kateg.equals("") && julkaisija.equals("") && tekija.equals("")) {
							
					// Kategoria
					for (Kirja k3 : loydetytKirjat3) {
						if (k3.getKategoriaNimi().equals(kateg)) {
							
							// Onko haettu kirjan nimen perusteella?
							if (nimi.equals("") == false) {
								// Jos nimi vastaa kirjaan nimeä, jatketaan
								if (k3.getNimi().equals(nimi)) {
									
									if (kuntoluokka != -1) {
										if (k3.getKuntoluokka() == kuntoluokka && loydetytKirjat4.contains(k3) == false) loydetytKirjat4.add(k3);
									} else {
										if (loydetytKirjat4.contains(k3) == false) loydetytKirjat4.add(k3);
									}
								}
							} else {
								if (kuntoluokka != -1) {
									if (k3.getKuntoluokka() == kuntoluokka && loydetytKirjat4.contains(k3) == false) loydetytKirjat4.add(k3);
								} else {
									if (loydetytKirjat4.contains(k3) == false) loydetytKirjat4.add(k3);
								
								}
							}
						}
					}
					// Haettu vain kirjan nimellä (nyt samannimisiä kirjoja voi olla vain 1)
				} else if (tekija.equals("") && julkaisija.equals("") && kateg.equals("")) {
					loydetytKirjat4.add(kirjat.annaKirjaNimi(nimi));
					
				}
				
				
				listTilaa.removeAll();
				try {
					for (Kirja k : loydetytKirjat4) {
						listTilaa.add(k.getKirjoittaja() +": "+k.getNimi());
					}
				} catch (Exception ex) {}
			}
			
		});
		btnHae.setBounds(169, 233, 98, 26);
		panelAsiakas.add(btnHae);
		
		JButton btnTyhjennHaku = new JButton("Tyhjenn\u00E4 haku");
		btnTyhjennHaku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listTilaa.removeAll();
			}
		});
		btnTyhjennHaku.setBounds(12, 233, 125, 26);
		panelAsiakas.add(btnTyhjennHaku);
		
		listTilaukset = new List();
		listTilaukset.setBounds(485, 89, 444, 203);
		panelAsiakas.add(listTilaukset);
		
		/**
		 * Asiakkaan kirjan tilaaminen (edellyttää kirjautumista)
		 */
		JButton btnTilaa = new JButton("Tilaa valittu kirja");
		btnTilaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (kirjautunut == false) {
					JOptionPane.showMessageDialog(null, "Kirjaudu ensin sisään.");
					return;
				}
				int indeksi = listTilaa.getSelectedIndex();
				String kirjanNimi = listTilaa.getItem(indeksi);
				kirjanNimi = kirjanNimi.substring(kirjanNimi.lastIndexOf(": ") + 2, kirjanNimi.length());
				Kirja kirja = kirjat.annaKirjaNimi(kirjanNimi);
				
				if (kirja.getLukumaara() > kirja.getVarauksia() && kirja.getLukumaara() > 0) {
					kirja.lisaaVarattu();
					Tilaus tilaus = new Tilaus(kirja);
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
					LocalDateTime now = LocalDateTime.now();
					tilaus.asetaTilausPVM(dtf.format(now));
				
					Asiakas a = rekisteri.getAsiakasEmail(kirjautunutEmail);
					tilaus.setNimi(a.getNimi());
					tilaus.setKatuosoite(a.getKatuosoite());
					tilaus.setPostinumero(a.getPostinumero());
					tilaus.setKaupunki(a.getKaupunki());
					tilaus.setAsiakasnumero(a.getAsiakasnumero());
					tilaus.setTilausnumero(tilausnumero);
				
					tilaukset.lisaaTilaus(tilaus);
				
					tilausnumero++;
				
					listTilaukset.add(tilaus.tulostaTilaus());
				} else {
					JOptionPane.showMessageDialog(null, "Kirjan kaikki kappaleet ovat varattuja\ntai se on loppunut varastosta.");
					return;
				}
				
			}
		});
		btnTilaa.setBounds(12, 678, 265, 23);
		panelAsiakas.add(btnTilaa);
		
		JLabel lblTilaukset = new JLabel("Tilauksesi");
		lblTilaukset.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTilaukset.setBounds(485, 58, 278, 26);
		panelAsiakas.add(lblTilaukset);
		
		JButton btnLogout = new JButton("Kirjaudu ulos");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (kirjautunut == false) {
					JOptionPane.showMessageDialog(null, "Kirjaudu ensin sisään.");
					return;
				}
				
				kirjautunut = false;
				lblKirjautunut.setText("Et ole kirjautunut");
				listTilaukset.removeAll();
			}
		});
		btnLogout.setBounds(485, 10, 173, 26);
		panelAsiakas.add(btnLogout);
		
		JButton btnMaksaTilaus = new JButton("Maksa valittu tilaus");
		btnMaksaTilaus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				try {
					
					String tilnro = listTilaukset.getSelectedItem();
					String asnro = listTilaukset.getSelectedItem();
					String aika = listTilaukset.getSelectedItem();
					
					
					// poimitaan tilausnumero
					tilnro = tilnro.substring(14, tilnro.indexOf(" Maksettu:")).trim();
					
					// poimitaan asiakasnumero		
					asnro = asnro.substring(asnro.indexOf("##") + 2, asnro.length()).trim();					
					aika = aika.substring(aika.indexOf("Tilattu: ") + 9, aika.indexOf("Tilattu: ") + 9 + 19);
					
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
					
					LocalDateTime now = LocalDateTime.now();
					LocalDateTime tilausaika = LocalDateTime.parse(aika, dtf);
										
					int numero = -1;
				
					Tilaus tilaus;

					try {
						numero = Integer.parseInt(tilnro);
						tilaus = tilaukset.getTilausByTilausnumero(numero);
					
					 
						// Jos tilauksen tekemisestä on kulunut 4 minuuttia ("14pv") tilaus ei enää ole voimassa
						if (now.getLong(ChronoField.MICRO_OF_DAY) - tilausaika.getLong(ChronoField.MICRO_OF_DAY) > 60000000 * 4) {
							JOptionPane.showMessageDialog(null, "Tilauksesi ei enää ole voimassa.");
							
							// poistetaan tilaus, joka ei ole voimassa
							tilaukset.poistaTilaus(tilaus);
							
						} else {
							
							// muutoin hoidetaan tilauksen maksaminen
							tilaus.maksaTilaus();
							

							
						}
					} catch (NumberFormatException ex) {  }
					
					try {
						numero = Integer.parseInt(asnro);
				
						listTilaukset.removeAll();
						
						String nykTilaukset = tilaukset.getTilausTiedotByAsiakasnumero(numero);
						
						String nyktilaukset[];
						nyktilaukset = nykTilaukset.split("\n");
						
						for (int i = 0; i < nyktilaukset.length; i++) {
							listTilaukset.add(nyktilaukset[i]);
						}
						
					} catch (NumberFormatException ex) {  }
					
				} catch (Exception ex) {  }
				

			}
		});
		btnMaksaTilaus.setBounds(485, 302, 224, 23);
		panelAsiakas.add(btnMaksaTilaus);
		
		//
		// Asiakas peruu tilauksensa
		//
		
		JButton btnPeru = new JButton("Peru valittu tilaus");
		btnPeru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// poimitaan tilausnumero
				try {
					String tilnro = listTilaukset.getSelectedItem();
					String asnro = listTilaukset.getSelectedItem();
					
					tilnro = tilnro.substring(14, tilnro.indexOf(" Maksettu:")).trim();
					asnro = asnro.substring(asnro.indexOf("##") + 2, asnro.length()).trim();

					String aika = listTilaukset.getSelectedItem();					
					aika = aika.substring(aika.indexOf("Tilattu: ") + 9, aika.indexOf("Tilattu: ") + 9 + 19);					
					
					try {
						int numero = Integer.parseInt(tilnro);
						Tilaus t = tilaukset.getTilausByTilausnumero(numero);
						
						if (t.getMaksettu()) {
							JOptionPane.showMessageDialog(null, "Et voi enää perua tilaustasi,\nkoska olet maksanut sen.");
							return;
						}

						// kirja ei enää ole varattu
						Kirja k = kirjat.annaKirjaNimi(t.getTilatunKirjanNimi());
						k.vahennaVarattu();
						// muutoin hoidetaan tilauksen peruminen
						tilaukset.poistaTilaus(t);
						
						listTilaukset.removeAll();
						
					} catch (NumberFormatException ex) {}
					
					try {
						int numero = Integer.parseInt(asnro);
						
						listTilaukset.removeAll();
						
						String nykTilaukset = tilaukset.getTilausTiedotByAsiakasnumero(numero);
						
						String nyktilaukset[];
						nyktilaukset = nykTilaukset.split("\n");
						
						for (int i = 0; i < nyktilaukset.length; i++) {
							listTilaukset.add(nyktilaukset[i]);
						}
						
					} catch (Exception ex) {}
					
				} catch (Exception ex) {}
				
			}
		});
		btnPeru.setBounds(719, 302, 210, 23);
		panelAsiakas.add(btnPeru);
		

		
		tabbedPane.insertTab("Kauppias", null, panelKauppias, "Kauppiaan näkymä", 1);
		panelKauppias.setLayout(null);

		JComboBox<String> cmbCateg = new JComboBox<String>();
		cmbCateg.setBounds(568, 111, 148, 20);
		panelKauppias.add(cmbCateg);
		
		cmbCateg.addItem(" ");
		// lisätään kauppiaan kirjahakuun comboboxiin kirjakategorioiden nimet
		for (int i = 0; i < kategoriat.getKirjakategoriat().size(); i++) {
			cmbCateg.addItem(kategoriat.getKirjakategoriat().get(i).getKategoriaNimi());
		}
		
		JLabel lblAsiakkaat = new JLabel("Asiakkaat");
		lblAsiakkaat.setBounds(27, 24, 144, 14);
		lblAsiakkaat.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelKauppias.add(lblAsiakkaat);
		
		TextArea txtAsiakastiedot = new TextArea();
		txtAsiakastiedot.setBounds(27, 394, 239, 234);
		txtAsiakastiedot.setEditable(false);
		panelKauppias.add(txtAsiakastiedot);

		DefaultListModel<String> listModel = new DefaultListModel<String>();
		final JList<String> listAsiakkaat = new JList<String>(listModel);
        listAsiakkaat.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScroller = new JScrollPane();
        listScroller.setBounds(27, 112, 413, 251);
        listScroller.setViewportView(listAsiakkaat);
        listAsiakkaat.setLayoutOrientation(JList.VERTICAL);
		listAsiakkaat.setBounds(27, 112, 413, 251);
        panelKauppias.add(listScroller);
        
		listAsiakkaat.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
			
				txtAsiakastiedot.setText("");
				
				if (asiakaslistamoodiKaikkiAsiakkaat == true) {
					try {
						int indeksi = listAsiakkaat.getSelectedIndex();
						Asiakas a = rekisteri.palautaAsiakkaat().get(indeksi);
					
						txtAsiakastiedot.append(a.getNimi() + "\n" + a.getKatuosoite() + "\n" + a.getPostinumero() + "\n" + a.getKaupunki() + "\n" + a.getPuhelinnumero() + "\n" + a.getEmail() + "\n");

					} catch (Exception ex) {}
				} else {
					
					try {
						String stringi = listAsiakkaat.getSelectedValue();
						stringi = stringi.substring(stringi.lastIndexOf("##") + 2, stringi.length()).trim();
						
						try {
							int numero = Integer.parseInt(stringi);
							Asiakas a = rekisteri.getAsiakasByNumber(numero);
							txtAsiakastiedot.append(a.getNimi() + "\n" + a.getKatuosoite() + "\n" + a.getPostinumero() + "\n" + a.getKaupunki() + "\n" + a.getPuhelinnumero() + "\n" + a.getEmail() + "\n");
						} catch (NumberFormatException ex) {}

					} catch (Exception ex) {}
				}
			}
		});
		
		
		JButton btnListaaAsiakkaat = new JButton("Katso kaikki asiakkaat");
		btnListaaAsiakkaat.setBounds(26, 59, 173, 23);
		btnListaaAsiakkaat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				asiakaslistamoodiKaikkiAsiakkaat = true;
				
				rekisteri.tyhjennaRekisteri();
				lueAsiakasrekisteri();
				
				ArrayList<Asiakas> asiakkaat = new ArrayList<Asiakas>();
				asiakkaat = rekisteri.palautaAsiakkaat();
				listModel.clear();
				
				for (int i = 0; i < asiakkaat.size(); i++) {
					
					listModel.addElement(asiakkaat.get(i).getNimi() + " : " + asiakkaat.get(i).getEmail());
				}
				
			}
		});
		panelKauppias.add(btnListaaAsiakkaat);
		
		JLabel lblNewLabel_3 = new JLabel("Klikkaa asiakasta n\u00E4hd\u00E4ksei asiakkaan kaikki tiedot");
		lblNewLabel_3.setBounds(27, 87, 452, 14);
		panelKauppias.add(lblNewLabel_3);
		
		JButton btnTyhjennaAsiakaslista = new JButton("Tyhjenn\u00E4");
		btnTyhjennaAsiakaslista.setBounds(279, 374, 161, 23);
		btnTyhjennaAsiakaslista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// tyhjennetään asiakaslista
				listModel.clear();
			}
		});
		panelKauppias.add(btnTyhjennaAsiakaslista);
		

		
		JLabel lblAsiakastiedot = new JLabel("Valitun asiakkaan kaikki tiedot");
		lblAsiakastiedot.setBounds(27, 374, 263, 14);
		panelKauppias.add(lblAsiakastiedot);
		
		JButton btnLahetaKirja = new JButton("L\u00E4het\u00E4 viesti ja kirja asiakkaalle");
		btnLahetaKirja.setBounds(27, 634, 239, 23);
		btnLahetaKirja.setToolTipText("Tarkoittaa nyt valittua tilausta");
		btnLahetaKirja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
								
				if (asiakaslistamoodiKaikkiAsiakkaat == true) {
					JOptionPane.showMessageDialog(null, "Väärä asiakasnäkymä.\nValitse Näytä nykyiset tilaukset");
					return;
				}
				
				if (listAsiakkaat.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Valitse ensin asiakas.");
					return;
				}
				
				// poimitaan asiakkaan tilaukset tilausnumero
				String tilausnumero = listAsiakkaat.getSelectedValue();
				tilausnumero = tilausnumero.substring(14, tilausnumero.indexOf(" Maksettu:")).trim();
				
				int numero = -1;
				
				// poistetaan lähetetty kirja kirjavalikoimasta
				try {
					numero = Integer.parseInt(tilausnumero);
					Tilaus t = tilaukset.getTilausByTilausnumero(numero);
					
					if (t.getLahetetty()) {
						JOptionPane.showMessageDialog(null, "Kirja on jo lähetetty asiakkalle.");
						return;						
					}
					if (t.getMaksettu() == false) {
						JOptionPane.showMessageDialog(null, "Kirjaa ei voi vielä lähettää asiakkalle,\nsillä asiakas ei ole vielä maksanut kirjaa.");
						return;
					}
					
					t.setLahetetty(true);
					
					String kirjanimi = t.getTilatunKirjanNimi();
					
					// poimitaan kirja kirjann nimen perusteella
					Kirja k = kirjat.annaKirjaNimi(kirjanimi);
					k.vahennaVarattu();	// kirja ei enää ole varattu, sillä se on maksettu ja lähetetty asiakkaalle
					
					// poistetaan kirja kirjakokoelmasta
					kirjat.poistaKirja(k);
					
					// poistetaan kirja kirjakategoriasta
					Kirjakategoria kategoria = k.getKirjakategoria();
					kategoriat.poistaKirjaKategoriasta(kategoria);
					
					// asetetaan kauppiaan näkymään kirjojen kokonaislukumäärää
					lblYhteensa.setText(Integer.toString(kirjat.getKirjatLKM()));
					
				} catch (NumberFormatException ex) {}
				
				
				JOptionPane.showMessageDialog(null, "Viesti lähetetty postittamolle.\nKirja lähtee asiakkaalle.\nKirjojen lukumäärä vähentynyt yhdellä.");
			}
		});
		panelKauppias.add(btnLahetaKirja);
		
		JLabel lblNewLabel_4 = new JLabel("Kirjat");
		lblNewLabel_4.setBounds(461, 11, 73, 14);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelKauppias.add(lblNewLabel_4);
		
		JLabel label = new JLabel("Tekij\u00E4");
		label.setBounds(461, 36, 55, 16);
		panelKauppias.add(label);
		
		txtTekija2 = new JTextField();
		txtTekija2.setBounds(568, 34, 148, 20);
		txtTekija2.setColumns(10);
		panelKauppias.add(txtTekija2);
		
		JLabel label_1 = new JLabel("Kirjan nimi");
		label_1.setBounds(461, 62, 81, 16);
		panelKauppias.add(label_1);
		
		txtKirjaNimi2 = new JTextField();
		txtKirjaNimi2.setBounds(568, 60, 148, 20);
		txtKirjaNimi2.setColumns(10);
		panelKauppias.add(txtKirjaNimi2);
		
		JLabel label_2 = new JLabel("Julkaisija");
		label_2.setBounds(461, 89, 81, 16);
		panelKauppias.add(label_2);
		
		txtJulkaisija2 = new JTextField();
		txtJulkaisija2.setBounds(568, 87, 148, 20);
		txtJulkaisija2.setColumns(10);
		panelKauppias.add(txtJulkaisija2);
		
		JLabel label_3 = new JLabel("Kategoria");
		label_3.setBounds(461, 115, 68, 16);
		panelKauppias.add(label_3);

		JLabel lblTallennettu = new JLabel("");
		lblTallennettu.setForeground(Color.GREEN);
		lblTallennettu.setBounds(617, 587, 113, 14);
		panelKauppias.add(lblTallennettu);
		
		DefaultListModel<String> listModel2 = new DefaultListModel<String>();;
		
		JButton btnHaku = new JButton("Hae kirjoista");
		btnHaku.setBounds(496, 149, 163, 23);
		btnHaku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nimi = "";
				String tekija = "";
				String julkaisija = "";
				String kateg = "";
				
				try {
					nimi = txtKirjaNimi2.getText().trim();
					tekija = txtTekija2.getText().trim();
					julkaisija = txtJulkaisija2.getText().trim();
					kateg = cmbCateg.getSelectedItem().toString().trim();
				} catch (Exception ex) {}
				
				loydetytKirjat1.clear();
				loydetytKirjat2.clear();
				loydetytKirjat3.clear();
				loydetytKirjat4.clear();
				
				lblTallennettu.setText("");
				
				if (tekija.equals("") == false) loydetytKirjat1.addAll(kirjat.annaKirjatKirjoittaja(tekija));
				if (julkaisija.equals("") == false) loydetytKirjat2.addAll(kirjat.annaKirjatJulkaisija(julkaisija));
				if (kateg.equals("") == false) loydetytKirjat3.addAll(kirjat.annaKirjatKategoriaNimi(kateg));

				
				if (!tekija.equals("") && !julkaisija.equals("") && !kateg.equals("")) {					
					
					for (Kirja k3 : loydetytKirjat3) {
						if (k3.getJulkaisija().equals(julkaisija) && k3.getKirjoittaja().equals(tekija)) {
							if (nimi.equals("") == false) {
											
								if (k3.getNimi().equals(nimi) && loydetytKirjat4.contains(k3) == false) loydetytKirjat4.add(k3);
													
												
							} else {
											
								if (loydetytKirjat4.contains(k3) == false) loydetytKirjat4.add(k3);
							}
										
						}
					}
						
				} else if (tekija.equals("") && !julkaisija.equals("") && !kateg.equals("")) {
					
					
					for (Kirja k3 : loydetytKirjat3) {
						if (k3.getJulkaisija().equals(julkaisija)) {
							if (nimi.equals("") == false) {
								// Jos kaikki hakuehdot täsmäävät yhtäaikaa, lisätään kirja lopullisiin läydettyihin kirjoihin
								if (k3.getNimi().equals(nimi)) {
												
									if (k3.getNimi().equals(nimi) && loydetytKirjat4.contains(k3) == false) loydetytKirjat4.add(k3);
								}
											
							} else {
											
								if (loydetytKirjat4.contains(k3) == false) loydetytKirjat4.add(k3);
							}
						}
					}
							
				} else if (julkaisija.equals("") && !tekija.equals("") && !kateg.equals("")) {
					
					
					for (Kirja k3 : loydetytKirjat3) {
						if (k3.getKirjoittaja().equals(tekija)) {
							if (nimi.equals("") == false) {
										
								if (k3.getNimi().equals(nimi) && loydetytKirjat4.contains(k3) == false) loydetytKirjat4.add(k3);
									
							} else {
											
								if (loydetytKirjat4.contains(k3) == false) loydetytKirjat4.add(k3);
																					
							}
						}
					}
						
					
					
				} else if (kateg.equals("") && !tekija.equals("") && !julkaisija.equals("")) {
				
					// Tekijä
					for (Kirja k1 : loydetytKirjat1) {
						if (k1.getJulkaisija().equals(julkaisija)) {
																		
									
							// Onko myäs nimen mukaan haku?
							if (nimi.equals("") == false) {
											
								if (k1.getNimi().equals(nimi) && loydetytKirjat4.contains(k1) == false) loydetytKirjat4.add(k1);
											
											
							} else {
											
								if (loydetytKirjat4.contains(k1) == false) loydetytKirjat4.add(k1);
											
							}
						}
									
								
					}
						
					
				} else if (kateg.equals("") && julkaisija.equals("") && !tekija.equals("")) {
					// Tekijä
					for (Kirja k1 : loydetytKirjat1) {

							if (nimi.equals("") == false) {
								// Jos kaikki hakuehdot täsmäävät yhtäaikaa, lisätään kirja lopullisiin läydettyihin kirjoihin
								if (k1.getNimi().equals(nimi)) {
									
									if (k1.getNimi().equals(nimi) && loydetytKirjat4.contains(k1) == false) loydetytKirjat4.add(k1); 
								}
							} else {
									if (loydetytKirjat4.contains(k1) == false) loydetytKirjat4.add(k1);
							}

					}
				} else if (kateg.equals("") && !julkaisija.equals("") && tekija.equals("")) {
					// Julkaisija 
					for (Kirja k2 : loydetytKirjat2) {
						if (nimi.equals("") == false) {
							// Jos kaikki hakuehdot täsmäävät yhtäaikaa, lisätään kirja lopullisiin läydettyihin kirjoihin
							if (k2.getNimi().equals(nimi)) {
																
								if (loydetytKirjat4.contains(k2) == false) loydetytKirjat4.add(k2);
							}
						} else {
								if (loydetytKirjat4.contains(k2) == false) loydetytKirjat4.add(k2);
						}
						
					}
					// haetaan kategorian mukaa sekä mahdollisesti kirjan nimen ja kuntoluokan mukaan
				} else if (!kateg.equals("") && julkaisija.equals("") && tekija.equals("")) {
							
					// Kategoria
					for (Kirja k3 : loydetytKirjat3) {
						if (k3.getKategoriaNimi().equals(kateg)) {
							
							// Onko haettu kirjan nimen perusteella?
							if (nimi.equals("") == false) {
								// Jos nimi vastaa kirjaan nimeä, jatketaan
								if (k3.getNimi().equals(nimi)) {
							
									if (loydetytKirjat4.contains(k3) == false) loydetytKirjat4.add(k3);
								}
							} else {
								if (loydetytKirjat4.contains(k3) == false) loydetytKirjat4.add(k3);
							}
								
						}
					}
				} else if (tekija.equals("") && julkaisija.equals("") && kateg.equals("")) {
					loydetytKirjat4.add(kirjat.annaKirjaNimi(nimi));
					
				}
				
				ArrayList<Kirja> laitetutKirjat = new ArrayList<Kirja>();
				laitetutKirjat.clear();
				listModel2.clear();
				
				for (Kirja k : loydetytKirjat4) {
					try {
						if (!laitetutKirjat.contains(k)) {
							listModel2.addElement(k.getNimi()+"\n");
							laitetutKirjat.add(k);
						}
					} catch (Exception ex) {}
				}
				
			}
		});
		panelKauppias.add(btnHaku);

		JScrollPane listScroller2 = new JScrollPane();	
		final JList<String> listKirjat = new JList<String>(listModel2);
		listScroller2.setBounds(472, 183, 258, 262);
		
		listKirjat.setBounds(472, 183, 258, 262);
		listScroller2.setRowHeaderView(listKirjat);
		listKirjat.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listKirjat.setLayoutOrientation(JList.VERTICAL);
		listScroller2.setViewportView(listKirjat);
		panelKauppias.add(listScroller2);
		
		JTextPane txtEditKirja = new JTextPane();
		txtEditKirja.setEditable(false);
		txtEditKirja.setBounds(461, 456, 269, 119);
		panelKauppias.add(txtEditKirja);
		
		
		JButton btnNewButton = new JButton("Tallenna muutokset");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				float hinta = -1;
				
				try {
					hinta = Float.parseFloat(txtHinta.getText().trim());
					int indeksi = listKirjat.getSelectedIndex();
					String kirjannimi = listModel2.elementAt(indeksi);

					// trim() poistaa rivinvaihdon nimestä
					Kirja k = kirjat.annaKirjaNimi(kirjannimi.trim());
					k.setHinta(hinta);
					lblTallennettu.setText("Tallennettu.");
				} catch (NumberFormatException ex) {}
			}
		});
		btnNewButton.setBounds(461, 612, 173, 23);
		panelKauppias.add(btnNewButton);
		

		
		JLabel lblHinta = new JLabel("Hinta:");
		lblHinta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHinta.setBounds(461, 587, 55, 14);
		panelKauppias.add(lblHinta);
		

		
		JLabel lblNewLabel_5 = new JLabel("Lis\u00E4\u00E4 kirjakategoria");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(753, 265, 163, 17);
		panelKauppias.add(lblNewLabel_5);
		
		JTextField txtUusiKategoria = new JTextField();
		txtUusiKategoria.setBounds(825, 293, 163, 22);
		panelKauppias.add(txtUusiKategoria);
		
		JLabel lblNewLabel_6 = new JLabel("Nimi:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setBounds(783, 298, 46, 14);
		panelKauppias.add(lblNewLabel_6);

		JComboBox<String> cmbCategory = new JComboBox<String>();
		cmbCategory.setBounds(875, 524, 205, 20);
		
		for (int i = 0; i < kategoriat.getKirjakategoriat().size(); i++) {
			cmbCategory.addItem(kategoriat.getKirjakategoriat().get(i).getKategoriaNimi());
		}
		
		panelKauppias.add(cmbCategory);
		
		JButton btnLisaa = new JButton("Lis\u00E4\u00E4 kirjakategoria");
		btnLisaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String kateg = "";
				kateg = txtUusiKategoria.getText().trim();
				
				if (kateg.equals("") == false) {
					Kirjakategoria kategoria;
					kategoria = new Kirjakategoria(kateg);
					kategoriat.lisaaUusiKirjakategoria(kategoria);
					// Jos lisätyssä kategoriassa ei ole kirjoja,
					// lukumäärä täytyy asettaa 0:ksi
					kategoria.setKirjoja(0);
					
					// Lisätään myäs kirjan lisäämisen yhteyteen uusi kategoria
					cmbCategory.addItem(kateg);
					// Lisätään kategoria myäs kauppiaan kirjahakuun
					cmbCateg.addItem(kateg);
					// Listään kategoria myäs asiakkaan puolelle
					cmbCat.addItem(kateg);
				}
			}
		});
		btnLisaa.setBounds(825, 320, 163, 23);
		panelKauppias.add(btnLisaa);
		
		JLabel lblNewLabel_7 = new JLabel("Kirjakategoriat");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_7.setBounds(753, 13, 187, 25);
		panelKauppias.add(lblNewLabel_7);

		List listKategoriat = new List();
		listKategoriat.setBounds(750, 78, 227, 181);
		panelKauppias.add(listKategoriat);
		
		//
		// Listataan kategoriat
		//
		
		JButton btnListaa = new JButton("Listaa kirjakategoriat");
		btnListaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String kategor = kategoriat.listaaKategoriatiedot();
				String kateg[] = kategor.split(";");
				
				listKategoriat.removeAll();
				
				for (int i = 0; i < kateg.length; i++) {
					listKategoriat.add(kateg[i]);
				}
				
			}
		});
		btnListaa.setBounds(753, 49, 173, 23);
		panelKauppias.add(btnListaa);
		
		JButton btnPoistaKategoria = new JButton("Poista kategoria");
		btnPoistaKategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indeksi = listKategoriat.getSelectedIndex();
				String kateg = listKategoriat.getItem(indeksi);
				kateg = kateg.substring(0, kateg.indexOf(" ("));
				if (kategoriat.poistaKategoria(kateg) == false) JOptionPane.showMessageDialog(null, "Kategoria ei voi poistaa.\nKategoriassa on kirjoja"); else JOptionPane.showMessageDialog(null, "Kategorian poisto onnistui.");
			}
		});
		btnPoistaKategoria.setBounds(825, 354, 163, 23);
		panelKauppias.add(btnPoistaKategoria);
		
		JButton btnTilaukset = new JButton("Katso nykyiset tilaukset");
		btnTilaukset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				asiakaslistamoodiKaikkiAsiakkaat = false;
				
				listModel.clear();
				
				for (int i = 0; i < tilaukset.annaTilaukset().size(); i++) {
					listModel.addElement(tilaukset.annaTilaukset().get(i).tulostaTilaus());
				}
			}
		});
		btnTilaukset.setBounds(209, 59, 231, 23);
		panelKauppias.add(btnTilaukset);
		
		JLabel lblAddNewBook = new JLabel("Lis\u00E4\u00E4 uusi kirja");
		lblAddNewBook.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAddNewBook.setBounds(753, 392, 173, 20);
		panelKauppias.add(lblAddNewBook);
		
		JLabel lblName = new JLabel("Kirjan nimi:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblName.setBounds(753, 423, 112, 14);
		panelKauppias.add(lblName);
		
		JTextField txtAuthor = new JTextField();
		txtAuthor.setBounds(875, 456, 205, 22);
		panelKauppias.add(txtAuthor);
		
		JTextField txtPublisher = new JTextField();
		txtPublisher.setBounds(875, 489, 205, 22);
		panelKauppias.add(txtPublisher);
		
		JLabel lblAuthor = new JLabel("Tekij\u00E4:");
		lblAuthor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAuthor.setBounds(753, 461, 112, 14);
		panelKauppias.add(lblAuthor);
		
		JLabel lblPublisher = new JLabel("Julkaisija:");
		lblPublisher.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPublisher.setBounds(751, 494, 101, 14);
		panelKauppias.add(lblPublisher);
		

		
		JLabel lblCategory = new JLabel("Kategoria:");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCategory.setBounds(753, 527, 112, 14);
		panelKauppias.add(lblCategory);
		
		
		txtPages = new JTextField();
		txtPages.setBounds(875, 586, 86, 20);
		panelKauppias.add(txtPages);
		
		JComboBox<String> cmbCondition = new JComboBox<String>();
		cmbCondition.setBounds(875, 555, 65, 20);
		cmbCondition.addItem("5");
		cmbCondition.addItem("4");
		cmbCondition.addItem("3");
		cmbCondition.addItem("2");
		cmbCondition.addItem("1");
		
		panelKauppias.add(cmbCondition);
		
		JLabel lblKunto = new JLabel("Kuntoluokka:");
		lblKunto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblKunto.setBounds(753, 561, 99, 14);
		panelKauppias.add(lblKunto);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(875, 613, 86, 20);
		panelKauppias.add(txtPrice);

		JTextField txtBookName = new JTextField();
		txtBookName.setBounds(875, 425, 205, 20);
		panelKauppias.add(txtBookName);
		
		JLabel lblPrice = new JLabel("Hinta:");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrice.setBounds(753, 616, 99, 14);
		panelKauppias.add(lblPrice);
		
		JButton btnAdd = new JButton("Lis\u00E4\u00E4");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nimi = txtBookName.getText().trim();
				String author = txtAuthor.getText().trim();
				String publisher = txtPublisher.getText().trim();
				String cat = cmbCategory.getSelectedItem().toString();
				int pages = -1;
				int cond = -1;
				float hinta = -1;
				boolean ok = false;
				
				try {
					cond = Integer.parseInt(cmbCondition.getSelectedItem().toString());
				} catch (NumberFormatException ex) {}
				
				try {
					pages = Integer.parseInt(txtPages.getText());
				} catch (NumberFormatException ex) {}
				
				try {
					hinta = Float.parseFloat((txtPrice.getText().trim()));
				} catch (NumberFormatException ex) {}
				
				if (cond > -1 && hinta > -1 && !nimi.equals("") && !author.equals("") && !publisher.equals("")) {
					Kirja kirja = new Kirja(nimi, author, publisher, pages, cond, cat, hinta);
					
					kirjat.lisaaKirja(kirja);
					
					Kirjakategoria kategoria = new Kirjakategoria(cat);
					
					// jos kategoriaa ei ole, se lisätään, muutoin lisätään vain kirjojen
					// lukumäärää kategoriassa
					kategoriat.lisaaUusiKirjakategoria(kategoria);
					
					lblYhteensa.setText(Integer.toString(kirjat.getKirjatLKM()));
					kirjoitaKirjaLevylle(kirja);
					ok = true;
				}
				
				if (ok == false) {
					JOptionPane.showMessageDialog(null, "Täytä kaikki kentät vaadituilla tiedoilla.");
					return;
				}
			}
		});
		btnAdd.setBounds(862, 655, 126, 23);
		panelKauppias.add(btnAdd);
		
		JLabel lblPages = new JLabel("Sivum\u00E4\u00E4r\u00E4:");
		lblPages.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPages.setBounds(753, 589, 99, 14);
		panelKauppias.add(lblPages);
		
		
		JLabel lblTotal = new JLabel("Kirjoja yhteens\u00E4");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTotal.setBounds(568, 11, 96, 14);
		panelKauppias.add(lblTotal);
		
		lblYhteensa.setText(Integer.toString(kirjat.getKirjatLKM()));
		

		
		listKirjat.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				
				try {	
					int indeksi = listKirjat.getSelectedIndex();
					String kirjannimi = listModel2.elementAt(indeksi);

					// trim() poistaa rivinvaihdon nimestä
					Kirja k = kirjat.annaKirjaNimi(kirjannimi.trim());
				
				
					txtEditKirja.setText("Tekijä: " + k.getKirjoittaja() + "\n" + "Teoksennimi: " + k.getNimi() + "\n" + "Julkaisija: " + k.getJulkaisija() + "\n" + "Kategoria: " + k.getKategoria() + "\n" + "Kuntoluokka: " + k.getKuntoluokka() + "\n" + "Sivumäärä: " + Integer.toString(k.getSivumaara()) + "\n" + "Varastossa KPL: " + k.getLukumaara());
					txtHinta.setText(Float.toString(k.getHinta()));
					lblTallennettu.setText("");
				} catch (Exception ex) {} 
			}
		});
		
		
		contentPane.add(tabbedPane);
		
	}
	
	private static void asetaKirjautumistieto(String email) {
		lblKirjautunut.setText("Olet kirjautunut sisään: " + email);
	}
	
	private void lueKirjat() {

		// Käytettävä merkistä
		Charset charset = Charset.forName("UTF-8");
		
		// Luetaan riveittäin "kuvat.dat", jossa on tiedot kuvista
		try (BufferedReader reader = Files.newBufferedReader(Paths.get("data//kirjat.dat"), charset)) {
		    String rivi = null;
		    String[] tiedot;
		    boolean ok = true;	// kertoo, onko tiedoston lukeminen ok, ts. saadaanko parsittua mielekkäästi tietorakenne
		    
		    while ((rivi = reader.readLine()) != null) {
		    	
		    	// Kirjoittaja, kirjan nimi, kustantaja, sivumäärä, kategoria, kuntoluokka, hinta
		    	tiedot = rivi.split(";");
		    	
		    	if (tiedot.length != 7) ok = false;
		    	if (ok) {
		    			
		    			String kategoria = tiedot[4];
		    			Kirjakategoria kirjakategoria = new Kirjakategoria(kategoria);
		    			kategoriat.lisaaUusiKirjakategoria(kirjakategoria);

		    			try {
		    				Kirja kirja = new Kirja(tiedot[1], tiedot[0], tiedot[2], Integer.parseInt(tiedot[3]), Integer.parseInt(tiedot[5]), tiedot[4], Float.parseFloat(tiedot[6]));
		    				kirjat.lisaaKirja(kirja);
		    			} catch (Exception ex) {
		    				System.out.println("Kirjat.dat-tiedoston parsimessa ongelma.");
		    			}
		    		
		    	}
		    }
		    
		    
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		
	}
	
	private void lueAsiakasrekisteri() {

		try {
			FileReader fr = new FileReader("data//asiakasrekisteri.dat");
			BufferedReader br = new BufferedReader(fr);
			
		    String rivi = null;
		    String[] tiedot;
		    boolean ok = true;	// kertoo, onko tiedoston lukeminen ok, ts. saadaanko parsittua mielekkäästi tietorakenne
	    	

		    while ((rivi = br.readLine()) != null) {
		    			    	
			    // Kirjoittaja, kirjan nimi, kustantaja, sivumäärä, kategoria, kuntoluokka, hinta, asiakasnumero
		    	tiedot = rivi.split(";");
		    	
		    	
		    	if (tiedot.length != 7) ok = false;
		    	if (ok) {		    		
		    			
		    			try {
		    				Asiakas asiakas = new Asiakas(tiedot[1], tiedot[0], tiedot[2], tiedot[4], Integer.parseInt(tiedot[3]), tiedot[5], Integer.parseInt(tiedot[6]));
		    				rekisteri.lisaaAsiakas(asiakas);

		    				asiakasnumero = Integer.parseInt(tiedot[6]);
		    				
		    			} catch (Exception ex) {
		    				System.out.println("Asiakasrekisteri.dat-tiedoston parsimessa ongelma.");
		    			}
		    		
		    	}
		    }
				br.close();    
		        fr.close();   
		        
		} catch (IOException ex) { return; }    
          

		
	}
	
	private void kirjoitaKirjaLevylle(Kirja kirja) {
			
		     try{
		          OutputStream os = new FileOutputStream("data//kirjat.dat", true);
		          PrintWriter pw = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
		    	  // Tässä näkyy asiakasrekisteri.dat'in tietorakenne samalla
		    	  pw.write(kirja.getKirjoittaja() + ";" +kirja.getNimi() + ";" + kirja.getJulkaisija() + ";" + kirja.getSivumaara() + ";" + kirja.getKategoria() + ";" + kirja.getKuntoluokka() + ";" + kirja.getHinta() +"\n");
		    	  pw.close();

		       }catch(IOException ioe){
		    	   System.out.println("I/O-virhe tiedostonkirjoituksessa (kirjat.dat)");
		    	   ioe.printStackTrace();
		      }
		
	}
}
