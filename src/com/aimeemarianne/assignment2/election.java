package com.aimeemarianne.assignment2;

        ;

        import org.json.JSONException;
        import org.json.JSONObject;

        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import javax.swing.ButtonGroup;

/**
 * Created by mur07114879 on 24/11/2015.
 */
public class election {

    public static void main(String[] args) throws Exception {

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
                try {

                    boolean test = sendRequest(utext.getText(), ptext.getText());

                    if (test) {

                        //if password is correct

                        JFrame frame1 = new JFrame("PRESIDENT VOTE");
                        frame1.setSize(300, 300);
                        frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                        JPanel panel = new JPanel();
                        frame1.add(panel);
                        layout(panel);
                        panel.setBackground(Color.blue);


                        ButtonGroup bg1 = new ButtonGroup();

                        JRadioButton option1 = new JRadioButton("Jack");
                        JRadioButton option2 = new JRadioButton("Harry");
                        JRadioButton option3 = new JRadioButton("Dylan");
                        JRadioButton option4 = new JRadioButton("Fred");

                        bg1.add(option1);
                        bg1.add(option2);
                        bg1.add(option3);
                        bg1.add(option4);

                        frame.add(bg1);


                        frame.setVisible(true);

                    } else {

                        // if not....


                        JFrame frame2 = new JFrame("INCORRECT LOG IN DETAILS");
                        frame2.setSize(300, 300);
                        frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                        JPanel panel = new JPanel();
                        frame2.add(panel);
                        layout(panel);
                        panel.setBackground(Color.blue);
                        frame2.setVisible(true);

                    }
                } catch (Exception i) {


                }

            }
        });
        login.setBounds(10, 80, 80, 25);
        layout.add(login);
        login.setVisible(true);
    }


    public static boolean sendRequest(String user, String pass) throws Exception {

        String url = "http://impresserve.co.uk/oop/election.php?method=get&username=" + user;

        URL obj = new URL(url);

        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod("GET");
        int rCode = connection.getResponseCode();

        System.out.println("Response code is......" + rCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String inputLine;

        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {

            response.append(inputLine);
        }
        in.close();

        System.out.println(response.toString());

        boolean check = parseJson(response.toString(), pass);
        if (check) {
            return true;
        } else {
            return false;
        }
    }

    //pass the line to the parse json
    public static boolean parseJson(String rawJson, String pass) throws JSONException {

        JSONObject obj = new JSONObject(rawJson);
        JSONObject obj2 = obj.getJSONObject("1");

        System.out.println(obj2.getString("pass"));

        if (obj2.getString("pass") == pass) {

            return true;
        } else {
            return false;
        }

    }
}