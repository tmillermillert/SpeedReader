package main.java;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.TimeUnit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.ChangeEvent;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SpeadReaderGUI extends JFrame {

    private JPanel contentPane;
    private JLabel lblWord;
    public String words[];
    public int counter;
    public int number_of_lines;
    public long time_delay;
    private JSpinner spinner;
    private JToggleButton tglbtnButton;
    private boolean isStopped;
    private Thread thread;
    private JProgressBar progressBar;
    private JMenuBar menuBar;
    private JMenu mnFile;
    private JMenuItem mntmOpen;
    private JPanel panel_1;
    private File file;
    private JPanel panel_2;
    private JTabbedPane tabbedPane;
     
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                SpeadReaderGUI frame = null;
                try {
                    frame = new SpeadReaderGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public SpeadReaderGUI() {
        number_of_lines = 0;
        time_delay = 1000;
        isStopped = true;
        counter = 0;
        words = new String[100000];
        setTitle("Spead Reader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 964, 470);
        
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        mnFile = new JMenu("File");
        menuBar.add(mnFile);
        
        mntmOpen = new JMenuItem("Open");

        mnFile.add(mntmOpen);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        
        Number value = new Float(1.1);
        Number step = new Float(.1);
        Comparable<Float> start = new Float(0);
        Comparable<Float> end = new Float(2);
        SpinnerNumberModel model = new SpinnerNumberModel(value, start, end, step);
        
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
       
        
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE)
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        panel_2 = new JPanel();
        tabbedPane.addTab("New tab", null, panel_2, null);
        JPanel panel = new JPanel();
        
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        
        JSlider slider = new JSlider();
        
        JLabel lblTimeDelayseconds = new JLabel("Time Delay (seconds):");
        spinner = new JSpinner(model);
        
        lblWord = new JLabel("Word Being Read");
        lblWord.setFont(new Font("Lucida Grande", Font.PLAIN, 45));
        lblWord.setHorizontalAlignment(SwingConstants.CENTER);
        lblWord.setBackground(Color.LIGHT_GRAY);
        tglbtnButton = new JToggleButton("Stopped: Press to start reading");
        
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
                        .addComponent(lblWord, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                        .addComponent(progressBar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                        .addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
                            .addComponent(slider, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(lblTimeDelayseconds)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(spinner, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
                        .addComponent(tglbtnButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
                    .addContainerGap())
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(tglbtnButton)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(lblWord, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
                        .addComponent(slider, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                            .addComponent(spinner, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                            .addComponent(lblTimeDelayseconds)))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        panel.setLayout(gl_panel);
        
        panel_1 = new JPanel();
        
        JScrollPane scrollPane = new JScrollPane();
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
            gl_panel_1.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_panel_1.setVerticalGroup(
            gl_panel_1.createParallelGroup(Alignment.TRAILING)
                .addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                    .addContainerGap())
        );
        
        JTextArea textArea = new JTextArea();
        //textArea.setText("");
        scrollPane.setViewportView(textArea);
        panel_1.setLayout(gl_panel_1);
        GroupLayout gl_panel_2 = new GroupLayout(panel_2);
        gl_panel_2.setHorizontalGroup(
            gl_panel_2.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_2.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel, GroupLayout.PREFERRED_SIZE, 411, GroupLayout.PREFERRED_SIZE)
                    .addGap(5)
                    .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                    .addContainerGap())
        );
        gl_panel_2.setVerticalGroup(
            gl_panel_2.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_2.createSequentialGroup()
                    .addGap(5)
                    .addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
                        .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        panel_2.setLayout(gl_panel_2);
        
        
        ///////////Event 
        ///////////////////////////
        ///////////////////////////
        
        spinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                time_delay = (long) (Float.valueOf(spinner.getValue().toString()) * 1000f);
            }
        });
        

        
        tglbtnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if(tglbtnButton.isSelected() == true) {
                    tglbtnButton.setText("Playing: Press to stop reading");
                    isStopped = false;
                    synchronized(thread) {
                        thread.notify();
                    }
                    
                }
                else {
                    tglbtnButton.setText("Stopped: Press to start reading");
                    isStopped = true;

                }
            }
        });
        contentPane.setLayout(gl_contentPane);
        

        
        mntmOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              
                final JFileChooser chooser = new JFileChooser();
              
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "txt files", "txt");
                    chooser.setFileFilter(filter);
                    int returnVal = chooser.showOpenDialog(mntmOpen);
                    if(returnVal == JFileChooser.APPROVE_OPTION) {
                       System.out.println("You chose to open this file: " +
                            chooser.getSelectedFile().getName());
                              file = chooser.getSelectedFile();
                              tabbedPane.setTitleAt(0, chooser.getSelectedFile().getName());
                    
                    //frame.file = new File("100_West_By_53_North.txt");
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(file)); 
                        String st; 
                        
                        while ((st = br.readLine()) != null) {
                            textArea.setText(textArea.getText() + "\n" + st);
                            System.out.println(st);
                            if (st.equals("")) {
                                continue;
                            }
                            String words_in_line[] = st.split("\\s+");
                            for(String s: words_in_line) {
                                if(s.equals("")) {
                                    continue;
                                }
                                words[number_of_lines++] = s;
                            }
                            
                        } 
                        br.close();
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                        System.exit(1);
                    }
                    }
                    thread = new Thread(){
                        public void run(){

                          while(counter < number_of_lines) {
                              progressBar.setString(Integer.toString((int)(((float)counter*100)/number_of_lines)) + "%");
                              progressBar.setValue((int)(((float)counter*100)/number_of_lines));
                              if(isStopped) {
                                  try {
                                      synchronized(thread) {
                                          this.wait();
                                      }
                                  }
                                  catch(Exception ex) {
                                      ex.printStackTrace();
                                      System.exit(1);
                                  }
                              }
                              try {
                                  TimeUnit.MILLISECONDS.sleep(time_delay);
                              }
                              catch(Exception ex) {
                                  ex.printStackTrace();
                                  System.exit(1);
                              }
                              lblWord.setText(words[counter++]);
                              lblWord.setSize(100, 100);
                              lblWord.repaint();
                          }
                          counter = 0;
                          isStopped = true;
                          tglbtnButton.setSelected(false);
                          tglbtnButton.setText("Stopped: Press to start");
                          progressBar.setValue(100);
                          progressBar.setString("100%");                       
                        }
                      };

                      thread.start();
                
            }
        });

    }
}
