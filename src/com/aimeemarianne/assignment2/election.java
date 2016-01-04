package com.aimeemarianne.assignment2;

;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mur07114879 on 24/11/2015.
 */
public class election {

    public static void main(String[] args) {

        JFrame frame = new JFrame("PRESIDENT");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        layout(panel);
        panel.setBackground(Color.yellow);
        frame.setVisible(true);
    }
private static void layout(JPanel layout) {


    JLabel username = new JLabel("USERNAME");
    username.setBounds(10, 10, 100, 25);
    layout.add(username);
    JTextField utext = new JTextField(20);
    utext.setBounds(10, 10, 100, 25);
    layout.add(utext);

    JLabel password = new JLabel("PASSWORD");
    password.setBounds(10, 10, 100, 25);
    layout.add(password);
    JTextField ptext = new JTextField(20);
    ptext.setBounds(10, 10, 100, 25);
    layout.add(ptext);

    JButton login = new JButton("LOGIN");
    login.setBounds(10, 80, 80, 25);
    layout.add(login);
    login.setVisible(true);}












    }










