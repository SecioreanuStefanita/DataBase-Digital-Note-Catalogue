import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class TextPanel extends JPanel {
  private JTextArea textArea;
  private JButton button2;

  public TextPanel(String ID) {
    button2 = new JButton("Afiseaza Colegi");
    textArea = new JTextArea();
    textArea.setEditable(false);
    setLayout(new BorderLayout());
    add(new JScrollPane(textArea), BorderLayout.CENTER);
    add(button2, BorderLayout.SOUTH);

    button2.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        textArea.setText("");
        appendText(ID);

      }
    });
  }

  public void appendText(String ID) {
    String url = "jdbc:mysql://localhost:3306/catalog_electronic?autoReconnect=true&useSSL=false";
    String user = "root";
    String password = "1234";
    try {
      Connection myConn = DriverManager.getConnection(url, user, password);
      Statement myStmt = myConn.createStatement();
      String sql = "SELECT * FROM elevi where clasa_id_clasa=(select clasa_id_clasa from elevi where id_elev=" + ID + ")";
      ResultSet rs = myStmt.executeQuery(sql);
      textArea.append("COLEGII ELEVULUI SUNT : ");
      textArea.append("\n");
      textArea.append("-----------------------------------------");
      textArea.append("\n");
      while (rs.next()) {

        textArea.append(rs.getString("nume"));
        textArea.append(" ");
        textArea.append(rs.getString("prenume"));
        textArea.append(" ");
        textArea.append(rs.getString("clasa_id_clasa"));
        textArea.append("\n");

      }
    } catch (SQLException a) {
      a.printStackTrace();
    }
  }

}
