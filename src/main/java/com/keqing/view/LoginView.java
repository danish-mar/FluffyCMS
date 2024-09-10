package com.keqing.view;

import com.keqing.Utils.DisplayUtils;

import javax.swing.*;

public class LoginView extends JFrame{
    private JPanel panel1;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginButton;

    public LoginView() {
        comboBox1.setSelectedIndex(0);
        comboBox1.setEditable(true);
        setTitle("Login");
        add(panel1);
        setSize(300, 300);
        setBounds(DisplayUtils.getCenterOfScreen(300,300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initComboBox(String[] ips) {
        for (String ip : ips) {
            comboBox1.addItem(ip);
        }
    }

    public static void main(String[] args) {

        new LoginView();

    }

}
