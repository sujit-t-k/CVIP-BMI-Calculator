/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bmicalculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Sujit T K
 */
public class BMICalculator extends javax.swing.JFrame {
    
    private JPanel paneBase = new JPanel();
    private JTextArea txtResult = null;
    
    private void setupFrame() {
        this.setTitle("BMI Calculator");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.paneBase = new JPanel();
        this.paneBase.setPreferredSize(new Dimension(400,400));
        this.paneBase.setBackground(new Color(212, 96, 212));
        this.paneBase.setLocation(0, 0);
        this.paneBase.setLayout(null);
        this.add(paneBase);
    }
    
    private JLabel getLabel(final String text, final int X, final int Y, final int width, final int height, final Color clrText, final Font f) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(X, Y, width, height);
        lbl.setFont(f);
        lbl.setForeground(clrText);
        return lbl;
    }
    
    private JTextField getTextBox(final int X, final int Y, final int width, final int height) {
        JTextField tField = new JTextField();
        tField.setBounds(X, Y, width, height);
        tField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                final String text = tField.getText();
                tField.setText(text.replaceAll("[^0-9.]", ""));                
                if(text.replaceAll("[^.]", "").length() > 1) {
                    tField.setText(text.substring(0,text.length()-1));
                }
                if (text.length() > 6) {
                    tField.setText(text.substring(0,6));
                }
                if(text.length() > 3 && text.charAt(3) != '.' && text.charAt(2) != '.') {
                    tField.setText(text.substring(0,2));
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                tField.setText(tField.getText().replaceAll("[^0-9.]", ""));
            }            
        });
        return tField;
    }
    
    private JButton getButton(final String text, final int X, final int Y) {
        JButton btn = new JButton(text);
        btn.setBounds(X, Y, 110, 40);
        btn.setBackground(new Color(44, 30, 51));
        btn.setForeground(Color.WHITE);
        btn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    btn.setBackground(new Color(44, 30, 51));                    
                    btn.setForeground(Color.WHITE);
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    btn.setBackground(new Color(31, 30, 250));
                    btn.setForeground(Color.YELLOW);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setBackground(new Color(44, 30, 51));
                    btn.setForeground(Color.WHITE);
                }
            });
        return btn;
    }
    
    private void showResult(final float HEIGHT, final float WEIGHT) {
        if(this.txtResult != null) {
            this.paneBase.remove(this.txtResult);
        }
        final float BMI = ((int)(((WEIGHT/(HEIGHT*HEIGHT)) * 10_000f)*100)) / 100.0f;
        this.txtResult = new JTextArea("Your BMI value is : " + BMI + "\nYou are " + ((BMI < 17.0f) ? "underweight" : BMI > 25.0f ? "overweight" : "healthy. Maintain it up."));
        this.txtResult.setForeground(BMI < 17.0f || BMI > 25.0f ? Color.RED : Color.GREEN);
        this.txtResult.setBackground(Color.BLACK);
        this.txtResult.setFont(new Font("Calibri", 20, 20));
        this.txtResult.setEditable(false);
        this.txtResult.setMargin(new Insets(10, 10, 10, 10));
        this.txtResult.setBounds(50, 275, 300, 60);
        this.paneBase.add(this.txtResult);
        this.repaint();
    }
    
    private void addUIComponents() {
        this.paneBase.add(this.getLabel("BMI Calculator", 88, 50, 300, 40, Color.DARK_GRAY, new Font("Calibri", 20, 35)));
        this.paneBase.add(this.getLabel("Check your body health status", 75, 66, 300, 40, Color.DARK_GRAY, new Font("Consolas", 20, 14)));
        this.paneBase.add(this.getLabel("Enter your height (in cms)  : ", 45, 110, 300, 40, Color.BLACK, new Font("Times New Roman", 20, 17)));
        this.paneBase.add(this.getLabel("Enter your weight (in Kgs) : ", 45, 160, 300, 40, Color.BLACK, new Font("Times New Roman", 20, 17)));
        
        JTextField txtHeight = this.getTextBox(240, 114, 90, 30);
        this.paneBase.add(txtHeight);
        JTextField txtWeight = this.getTextBox(240, 164, 90, 30);
        this.paneBase.add(txtWeight);
        
        JButton btnClear = this.getButton("CLEAR", 52, 220);
        btnClear.addActionListener(e-> {
            txtHeight.setText("");
            txtWeight.setText("");
            if(this.txtResult != null) {
                this.paneBase.remove(this.txtResult);
                this.repaint();
                this.txtResult = null;
            }
        });
        this.paneBase.add(btnClear);
        
        JButton btnCalculate = this.getButton("CALCULATE", 212, 220);
        btnCalculate.addActionListener(e-> {
            if(!txtHeight.getText().isEmpty() && !txtWeight.getText().isEmpty()) this.showResult(Float.parseFloat(txtHeight.getText()), Float.parseFloat(txtWeight.getText()));
        });
        this.paneBase.add(btnCalculate);        
        this.repaint();
    }    

    private void showGUI() {
        this.setupFrame();
        this.addUIComponents();
    }
    
    public static void main(String[] args) {
        new BMICalculator().showGUI();
    }    
    
}
