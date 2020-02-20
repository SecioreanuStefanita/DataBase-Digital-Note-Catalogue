import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MainFrame extends JFrame {

  private TextPanel textPanel;
  private TextPanelDate textPanelDate;
  private TextPanelNote textPanelNote;
  private JTextField textField;
  private JButton buton;
  private String ID;


  public MainFrame() {


    super("Catalog Electronic");
    setLayout(new BorderLayout());
    textField = new JTextField();
    buton = new JButton("Afiseaza Date ID din CAMP TEXT de sus ");
    buton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        ID = textField.getText();
        textPanel = new TextPanel(textField.getText());
        textPanelDate = new TextPanelDate(textField.getText());
        textPanelNote = new TextPanelNote(textField.getText());
        add(textPanel, BorderLayout.CENTER);
        add(textPanelDate, BorderLayout.WEST);
        add(textPanelNote, BorderLayout.EAST);
      }
    });

    add(buton, BorderLayout.PAGE_END);
    add(textField, BorderLayout.PAGE_START);


    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setSize(600, 500);
  }


}
