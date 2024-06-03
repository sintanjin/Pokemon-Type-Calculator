import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI extends JFrame {
    private int count = 0;
    // the number of clicking buttons; cannot click more than twice
    private final String[] typeName = {"normal","fighting","flying","poison","ground",
            "rock","bug","ghost","steel","fire","water",
            "grass","electric","psychic","ice","dragon",
            "dark","fairy"};
    // name of eighteen types; array element ordered according to type serial number
    private final String[] times = {"x4","x2","x1","x0.5","x0.25","x0"};
    // weakness magnification; Input Type is...
    // x4; very vulnerable at (Types)         x2; vulnerable at (Types)
    // x1; get normal damage at (Types)       x0.5; get half damage at (Types)
    // x0.25; get quarter damage at (Types)   x0; get zero damage at (Types)
    private JLabel[] timesArray = new JLabel[6];
    //
    private JButton reset = new JButton("reset");
    // making 'reset' label button
    private static JLabel[] labArray = new JLabel[6];
    // output of GUI; printing weaknesses of input types
    private JButton[] btnArray = new JButton[18];
    private double[] weak= new double[18];

    public GUI(String str) {
        super(str);// named swing frame
        setLayout(null);//Remove layout, place components in desired coordinates
        int index = 0;

        for(int i=0;i<btnArray.length;i++){
            btnArray[i]=new JButton(typeName[i]);
            btnArray[i].addActionListener(new MyActionListener1());
        }//set JButton components

        for(int i=0;i<6;i++){
            for(int j=0;j<3;j++){
                String address="image/"+btnArray[index].getText()+".PNG";
                setImage(btnArray[index],address);// set button's background
                btnArray[index].setBounds(10+110*j,10+110*i,100,100);//set button's location
                add(btnArray[index]);
                btnArray[index].setIconTextGap(JButton.CENTER);
                index++;
            }
        }
        reset.addActionListener(new MyActionListener2());
        reset.setBounds(10, 680, 320, 50);
        reset.setBackground(Color.white);
        add(reset);
        // make a button to reset count and labels

        for (int i = 0; i< timesArray.length; i++){
            timesArray[i] = new JLabel(times[i], SwingConstants.CENTER);
            timesArray[i].setBounds(380,10+110*i,40,20);
            addLabel(timesArray[i]);    // invoke addLabel method (line 75)
        }// Add a label that represents the times of a weakness
        for (int i = 0; i < labArray.length; i++){
            labArray[i]= new JLabel("",SwingConstants.LEFT);
            labArray[i].setBounds(380,20+110*i,600,60);
            labArray[i].setFont(new Font("Serif",Font.BOLD,20));
            add(labArray[i]);
        }// Add labels represents defense weaknesses
        setResizable(false);
        // cannot change size of the screen
        setSize(1000, 800);
        // size of GUI screen
        setVisible(true);
        // allow for users to see GUI screen
    }

    static public JLabel getLabArray(int n){
        return labArray[n-1]; //label getter
    }
    public void addLabel(JLabel label){
        label.setOpaque(true);              // essential code in order to change colors
        label.setBackground(Color.CYAN);    // set colors into sky blue
        add(label);
    }   // Function; set the color of a button into sky blue
    public void setImage(JButton b,String s){
        ImageIcon normal_icon = new ImageIcon(s);
        Image img = normal_icon. getImage();
        Image changeImg = img.getScaledInstance(108,100, Image.SCALE_SMOOTH);
        ImageIcon changeIcon = new ImageIcon(changeImg);
        b.setIcon(changeIcon);
    }   // Function; insert an image into a button
    private class MyActionListener1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            JButton b = (JButton)e.getSource();
            if(e.getSource()==b&&count==0) {
                Main.setStr1(b.getText());
                weak = Main.typeMatch(Main.getStr1());
                Main.print(weak);
                count++;
                b.setEnabled(false);//button is became disabled
            }//Operation when the first button is pressed
            else if(e.getSource()==b&&count==1){
                Main.setStr2(b.getText());
                double[] weak1 = new double[18];
                weak1= Main.typeMatch(Main.getStr2());
                Main.print(weak,weak1);
                count++;
                b.setEnabled(false);
            }//Operation when the second button is pressed
        }
    }
    private class MyActionListener2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton)e.getSource();
            if(e.getSource()==b) {
                for(int i=0;i<btnArray.length;i++){
                    btnArray[i].setEnabled(true);
                }//Reactivate the disabled button
                count = 0;
                for(int i=0;i<labArray.length;i++){
                    labArray[i].setText("");
                }//Initialize the label
            }
        }
    }
}