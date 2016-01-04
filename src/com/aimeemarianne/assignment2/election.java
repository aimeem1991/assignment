package com.aimeemarianne.assignment2;

;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mur07114879 on 24/11/2015.
 */
public class election {

    public static void main(String[] args) {

        JFrame frame = new JFrame("PRESIDENT ELECTION LOGIN");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        layout(panel);
        panel.setBackground(Color.yellow);
        frame.setVisible(true);
    }


    private static void layout(JPanel layout) {


        JLabel username = new JLabel("Username");
        username.setBounds(10, 10, 100, 25);
        layout.add(username);
        JTextField utext = new JTextField(20);
        utext.setBounds(10, 10, 100, 25);
        layout.add(utext);

        JLabel password = new JLabel("Password");
        password.setBounds(10, 10, 100, 25);
        layout.add(password);
        JTextField ptext = new JTextField(20);
        ptext.setBounds(10, 10, 100, 25);
        layout.add(ptext);

        JButton login = new JButton("Login");
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("PRESIDENT ELECTION VOTE");
                frame.setSize(300, 300);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setVisible(true);
                sendRequest()


            }
        });
        login.setBounds(10, 80, 80, 25);
        layout.add(login);
        login.setVisible(true);}




        public static boolean sendRequest() throws Exception {

    String url = "http://impresserve.co.uk/android/json.php";

    URL obj = new URL(url);

    HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

    connection.setRequestMethod("GET");
    int rCode = connection.getResponseCode();

    System.out.println("Response code is......" + rCode);

    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

    String inputLine;

    StringBuffer response = new StringBuffer();

    while ((inputLine = in.readLine()) != null){

        response.append(inputLine);
    }
    in.close();

    System.out.println(response.toString());

    parseJson(response.toString());

}



}










