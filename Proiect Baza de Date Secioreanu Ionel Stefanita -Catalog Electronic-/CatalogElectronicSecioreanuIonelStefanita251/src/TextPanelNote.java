import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class TextPanelNote extends JPanel {
  private JTextArea textArea;
  private JButton button3;

  public TextPanelNote(String ID) {
    button3 = new JButton("Afiseaza Note");
    textArea = new JTextArea();
    textArea.setEditable(false);
    setLayout(new BorderLayout());
    add(new JScrollPane(textArea), BorderLayout.CENTER);
    add(button3, BorderLayout.SOUTH);

    button3.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        textArea.setText("");
        appendText(ID);

      }
    });
  }

  public void appendText(String ID) {
    System.out.println(ID);
    String url = "jdbc:mysql://localhost:3306/catalog_electronic?autoReconnect=true&useSSL=false";
    String user = "root";
    String password = "1234";
    try {
      Connection myConn = DriverManager.getConnection(url, user, password);
      Statement myStmt = myConn.createStatement();
      String sql = "select * from medii where Elevi_id_elev=" + ID;
      ResultSet rs = myStmt.executeQuery(sql);
      textArea.append("NOTELE ELEVULUI SUNT : ");
      textArea.append("\n");
      textArea.append("-----------------------------------------");
      textArea.append("\n");
      while (rs.next()) {
        textArea.append("\n");
        textArea.append(rs.getString(2));
        textArea.append(" ");
        textArea.append(rs.getString(4));
        textArea.append(" ");
        textArea.append("\n");

      }
    } catch (SQLException a) {
      a.printStackTrace();
    }
  }

}
