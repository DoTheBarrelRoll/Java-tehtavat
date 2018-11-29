import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;


public class Tietokantasovellus{
	private static JTextField kirjanNimi;
	private static JTextField kirjanTekijä;
	private static JTextField julkaisuVuosi;

	

	public static void main(String[] args) throws SQLException {
		
		ArrayList<Object[]> tulokset = new ArrayList<Object[]>();
		ArrayList<Kirja> kirjat = new ArrayList<Kirja>();
		
		
		//yhteys tietokantaan
		Connection con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net/sql7265166", "sql7265166", "cWlWs3N7kd");
		if (con != null) {
			System.out.println("Yhteys muodostettu!");
		}

		//kyselyn tekeminen
		Statement haku = con.createStatement();
		ResultSet alkutulokset = haku.executeQuery("select * from kirja");
		
		
		//lisätään kyselyn rivit tulokset objecteina tulokset ArrayList olioon
		while (alkutulokset.next()) {
			
			
			tulokset.add( new Object[] { alkutulokset.getString(1), alkutulokset.getString(2), alkutulokset.getString(3) } );
			
		
		}
		
		
		DefaultTableModel malli = new DefaultTableModel();
		malli.addColumn("Kirjan nimi");
		malli.addColumn("Kirjoittaja");
		malli.addColumn("Julkaisuvuosi");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 0, 0);

		

		JTable table = new JTable(malli);


		for (int i=0; i < tulokset.size(); i++ ) {
			malli.addRow(tulokset.get(i));
		}

		JFrame ikkuna = new JFrame();
		ikkuna.setSize(200, 200);
		ikkuna.setTitle("Tietokantasovellus");
		
		JButton btnNewButton = new JButton("Lis\u00E4\u00E4 rivi");
		btnNewButton.setBounds(0, 0, 120, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				

			}
});
		ikkuna.getContentPane().setLayout(new BorderLayout(0, 0));

		ikkuna.getContentPane().add(scrollPane, BorderLayout.NORTH);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		ikkuna.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("Kirjan nimi");
		panel.add(lblNewLabel);
		
		kirjanNimi = new JTextField();
		panel.add(kirjanNimi);
		kirjanNimi.setColumns(10);
		
		JLabel lblTekij = new JLabel("Tekijä");
		panel.add(lblTekij);
		
		kirjanTekijä = new JTextField();
		panel.add(kirjanTekijä);
		kirjanTekijä.setColumns(10);
		
		JLabel lblJulkaisuvuosi = new JLabel("Julkaisuvuosi");
		panel.add(lblJulkaisuvuosi);
		
		julkaisuVuosi = new JTextField();
		panel.add(julkaisuVuosi);
		julkaisuVuosi.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Lisää tietokantaa");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Kirja uusi;
				
				String nimi = kirjanNimi.getText();
				String tekija = kirjanTekijä.getText();
				int vuosi = Integer.parseInt(julkaisuVuosi.getText());
				
				uusi = new Kirja(nimi, tekija, vuosi);
				
				kirjat.add(uusi);
				
				tulokset.add( new Object[] { uusi.getNimi(), uusi.getTekija(), uusi.getJulkaisuvuosi() } );
				
				try {
					Statement lisäys = con.createStatement();
					lisäys.executeUpdate("INSERT INTO `kirja`(`kirjan_nimi`, `tekijä`, `julkaisuvuosi`) VALUES ('" + nimi + "', '" + tekija + "', " + vuosi + ")");
					ResultSet uus = lisäys.executeQuery("SELECT * FROM `kirja` WHERE `kirjan_nimi` = '" + nimi + "'");
					while (uus.next()) {
						Object [] uusikirja = new Object[] {uus.getString(1), uus.getString(2), uus.getString(3)};
						tulokset.add(uusikirja);
					}
					
					malli.addRow(tulokset.get((tulokset.size() - 1)));

					
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
				
				System.out.print(uusi);
			}
		});
		panel.add(btnNewButton_3);

		ikkuna.pack();
		ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ikkuna.setVisible(true);



	}
	
	

}
