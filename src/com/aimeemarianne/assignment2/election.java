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
        import javax.swing.JTextArea;

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
        panel.setBackground(Color.orange);
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

                        //if password is correct display the voting form

                        displayVoteForm(utext.getText());
                    } else {

                        // if password is incorrect display the error form
                        displayError();
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

        String url = "http://impresserve.co.uk/oop/election.php?method=set&username="+user+"&vote="+vote;

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

            boolean check = true;
            if (obj2.getInt("voted") != 0) {

                check = false;
            }
            return check;
        } else {
            return false;
        }

    }

    public static void displayVoteForm(String username) {

        JFrame frame1 = new JFrame("PRESIDENT VOTE");
        BoxLayout boxLayout = new BoxLayout(frame1.getContentPane(), BoxLayout.Y_AXIS);
        frame1.setLayout(boxLayout);
        frame1.setSize(300, 300);
        frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.blue);

        //button group for the radio buttons. this is for the voting
        ButtonGroup bg1 = new ButtonGroup();

        JRadioButton option1 = new JRadioButton("Jack");
        JRadioButton option2 = new JRadioButton("Harry");
        JRadioButton option3 = new JRadioButton("Dylan");

        //adding the radio buttons to the button group
        bg1.add(option1);
        bg1.add(option2);
        bg1.add(option3);

        //adding the radio buttons to the panel
        panel1.add(option1);
        panel1.add(option2);
        panel1.add(option3);

        //creating the submit button for users to submit vote
        JButton Submit = new JButton("Submit");

        frame1.add(panel1);
        frame1.add(Submit);
        frame1.setVisible(true);
        frame1.add(panel1);

        //action listener selects radio button which is selected 
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
                try {

                    sendVote(username, selected);

                } catch (Exception p) {
                }
                JFrame frame3 = new JFrame("Vote Submitted");
                frame3.setSize(300, 300);
                frame3.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                JLabel label = new JLabel("Your vote has been successfully submitted!!! :)");
                label.setFont(new Font("Serif", Font.PLAIN, 14));
                frame3.add(label);
                frame3.setBackground(Color.white);
                frame3.setForeground(Color.orange);

                frame3.setVisible(true);
            }

        });
    }

    public static void displayError() {
        // displays the error for the wrong password and if the user has already voted
        JFrame frame2 = new JFrame("AN ERROR OCCURED!");
        frame2.setSize(300, 300);
        frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel4 = new JPanel();
        frame2.add(panel4);
        frame2.setBackground(Color.white);
        //changes the background colour

        //added text area for error message
        JTextArea ta = new JTextArea("An error occured while trying to log you in. This could be because you have already voted or incorrect login details.");
        ta.setFont(new Font("Serif", Font.PLAIN, 14));
        frame2.add(ta);
        ta.setLineWrap(true);
        frame2.setVisible(true);
    }
}