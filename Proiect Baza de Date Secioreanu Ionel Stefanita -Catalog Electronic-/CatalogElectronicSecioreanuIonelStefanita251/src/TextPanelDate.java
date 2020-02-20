import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class TextPanelDate extends JPanel {

  private JTextArea textAreaDate;
  private JButton button1;


  public TextPanelDate(String ID) {

    button1 = new JButton("       Date ID       ");
    textAreaDate = new JTextArea();
    textAreaDate.setEditable(false);
    setLayout(new BorderLayout());
    add(new JScrollPane(textAreaDate), BorderLayout.CENTER);
    add(button1, BorderLayout.SOUTH);

    button1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        textAreaDate.setText("");
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
      String sql = "select * from catalog_electronic.elevi where id_elev=" + ID;
      ResultSet rs = myStmt.executeQuery(sql);
      textAreaDate.append("NUME COMPLET : ");
      textAreaDate.append("\n");
      while (rs.next()) {


        textAreaDate.append(rs.getString("nume"));
        textAreaDate.append(" ");
        textAreaDate.append(rs.getString("prenume"));
        textAreaDate.append(" ");
        textAreaDate.append("\n");
        textAreaDate.append("-----------------------------------------");
        textAreaDate.append("\n");
        textAreaDate.append(("CLASA : "));
        textAreaDate.append("\n");
        textAreaDate.append(rs.getString("clasa_id_clasa"));
        textAreaDate.append("\n");
        textAreaDate.append("-----------------------------------------");
        textAreaDate.append("\n");

      }
      Statement myStmtDate = myConn.createStatement();
      String sql2 = "SELECT * FROM date_personale where Elevi_id_elev=" + ID;
      ResultSet rs2 = myStmtDate.executeQuery(sql2);
      while (rs2.next()) {
        textAreaDate.append("CNP :");
        textAreaDate.append("\n");
        textAreaDate.append(rs2.getString(2));
        textAreaDate.append("\n");
        textAreaDate.append("-----------------------------------------");
        textAreaDate.append("\n");
        textAreaDate.append("ADRESA : ");
        textAreaDate.append("\n");
        textAreaDate.append(rs2.getString(3));
        textAreaDate.append("\n");
        textAreaDate.append("-----------------------------------------");
        textAreaDate.append("\n");
        textAreaDate.append("TELEFON : ");
        textAreaDate.append("\n");
        textAreaDate.append(rs2.getString(4));
        textAreaDate.append("\n");
        textAreaDate.append("-----------------------------------------");
        textAreaDate.append("\n");


      }


    } catch (SQLException a) {
      a.printStackTrace();
    }
  }

}
