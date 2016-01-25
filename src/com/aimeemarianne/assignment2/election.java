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

                try {

                    boolean test = sendRequest(utext.getText(), ptext.getText());
                    System.out.println(test);
                    if (test) {

                        //if password is correct

                        displayVoteForm(utext.getText());

                    } else {

                        // if not....
                        JFrame frame = new JFrame("PRESIDENT ELECTION VOTE");
                        frame.setSize(300, 300);
                        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                        JPanel panel = new JPanel();
                        frame.add(panel);
                        layout(panel);
                        panel.setBackground(Color.blue);
                        frame.setVisible(true);

                        JFrame frame2 = new JFrame("INCORRECT LOG IN DETAILS");
                        frame2.setSize(300, 300);
                        frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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


    public static boolean sendVote(String user, int vote) throws Exception {

        String url = "http://impresserve.co.uk/oop/election.php?method=set&username=abc123456&vote=1" + user + vote;

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

           return true;


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

        if (obj2.getString("pass").equals(pass)) {

            return true;
        } else {
            return false;
        }

    }

    public static void displayVoteForm(String username){

        JFrame frame1 = new JFrame("PRESIDENT VOTE");
        BoxLayout boxLayout = new BoxLayout(frame1.getContentPane(),BoxLayout.Y_AXIS);
        frame1.setLayout(boxLayout);
        frame1.setSize(300, 300);
        frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.blue);


        ButtonGroup bg1 = new ButtonGroup();

        JRadioButton option1 = new JRadioButton("Jack");
        JRadioButton option2 = new JRadioButton("Harry");
        JRadioButton option3 = new JRadioButton("Dylan");

        bg1.add(option1);
        bg1.add(option2);
        bg1.add(option3);

        panel1.add(option1);
        panel1.add(option2);
        panel1.add(option3);

        JButton Submit = new JButton("Submit");

        frame1.add(panel1);
        frame1.add(Submit);
        frame1.setVisible(true);
        frame1.add(panel1);
        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = 0;
             String radioText = "";

                if (option1.isSelected()) {
                    selected = 1;
                }

                    if (option2.isSelected()) {
                        selected = 2;
                    }

                        if (option3.isSelected()) {
                            selected = 3;
                        }

                    {
                        frame1.setVisible(true);
                    }
                try{

                    sendVote(username, selected);

                }
                catch (Exception p) {
                }
                JFrame frame = new JFrame("Vote Submitted");
                frame.setSize(300, 300);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                JPanel panel = new JPanel();
                frame.add(panel);
                layout(panel);
                panel.setBackground(Color.blue);
                frame.setVisible(true);
            }

});
    };}