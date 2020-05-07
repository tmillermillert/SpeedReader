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
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
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
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.event.ChangeEvent;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.Component;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;


public class SpeedReaderGUI extends JFrame {

    private JPanel contentPane;
    private final int MaxTabs = 5;
    private JMenuBar menuBar;
    private JMenu mnFile;
    private JMenuItem mntmOpen;
    private File file;
    private JTabbedPane tabbedPane;
    private JMenuItem mntmClose;
    private int numberOfTabs;
    private boolean isFirst;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {

                SpeedReaderGUI frame = null;
                try {
                    frame = new SpeedReaderGUI();
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
    public SpeedReaderGUI() {
        isFirst = true;
        numberOfTabs = 1;

        setTitle("Spead Reader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 964, 498);
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        mnFile = new JMenu("File");
        menuBar.add(mnFile);
        mntmOpen = new JMenuItem("Open");
        mnFile.add(mntmOpen);
        mntmClose = new JMenuItem("Close");

        mnFile.add(mntmClose);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
                        .addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 942, Short.MAX_VALUE).addContainerGap()));
        gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup().addContainerGap()
                        .addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE).addContainerGap()));
//////////////////////
        JTextArea textArea = new JTextArea();
        textArea.setSelectionColor(new Color(0, 255, 255));
        textArea.setDisabledTextColor(new Color(0, 255, 255));
        textArea.setBackground(new Color(255,203,120));
        


        JProgressBar progressBar = new JProgressBar();
        JLabel lblWord = new JLabel("Word Being Read");
        JToggleButton tglbtnButton = new JToggleButton("Stopped: Press to start reading");

        JPanel panel_2 = new JPanel();

        tabbedPane.addTab("New tab", null, panel_2, null);
        tabbedPane.setSelectedIndex(numberOfTabs-1);
        progressBar.setStringPainted(true);

        Number value = new Float(1.1);
        Number step = new Float(.1);
        Comparable<Float> start = new Float(0);
        Comparable<Float> end = new Float(2);
        SpinnerNumberModel model = new SpinnerNumberModel(value, start, end, step);

        JSpinner spinner = new JSpinner(model);

        spinner.setToolTipText("Time Delay (seconds)");

        JLabel lblTimeDelayseconds = new JLabel("Time Delay (seconds):");

        JPanel panel_3 = new JPanel();

        JLabel lblSearch = new JLabel("Search");

        JTextField textField = new JTextField();

        textField.setColumns(10);

        JButton btnNext = new JButton("next");
        

        JButton btnPrev = new JButton("prev");

        GroupLayout gl_panel_2 = new GroupLayout(panel_2);
        gl_panel_2
                .setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_2.createSequentialGroup().addContainerGap().addGroup(gl_panel_2
                                .createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2.createSequentialGroup()
                                        .addGap(6).addComponent(lblSearch).addPreferredGap(ComponentPlacement.RELATED)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(ComponentPlacement.RELATED).addComponent(btnNext)
                                        .addPreferredGap(ComponentPlacement.RELATED).addComponent(btnPrev).addGap(528))
                                .addGroup(gl_panel_2.createSequentialGroup().addGroup(gl_panel_2
                                        .createParallelGroup(Alignment.LEADING)
                                        .addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 909, Short.MAX_VALUE)
                                        .addGroup(gl_panel_2.createSequentialGroup()
                                                .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 189,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addGap(31)
                                                .addComponent(tglbtnButton, GroupLayout.PREFERRED_SIZE, 391,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addGap(18)
                                                .addComponent(lblTimeDelayseconds, GroupLayout.PREFERRED_SIZE, 147,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED).addComponent(spinner,
                                                        GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)))
                                        .addContainerGap()))));
        gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2
                .createSequentialGroup().addContainerGap()
                .addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(lblSearch)
                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNext).addComponent(btnPrev))
                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_2.createSequentialGroup()
                                .addComponent(lblTimeDelayseconds, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(gl_panel_2.createSequentialGroup()
                                .addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
                                        .addComponent(tglbtnButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE)
                                        .addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                                        .addComponent(spinner))
                                .addGap(6)))));

        JSplitPane splitPane = new JSplitPane();
        splitPane.setResizeWeight(0.5);
        GroupLayout gl_panel_3 = new GroupLayout(panel_3);
        gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_3.createSequentialGroup().addContainerGap()
                        .addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE).addContainerGap()));
        gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_3.createSequentialGroup().addContainerGap()
                        .addComponent(splitPane, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(28, Short.MAX_VALUE)));
        
        JPanel panel = new JPanel();
        splitPane.setLeftComponent(panel);
        panel.setBackground(new Color(255,203,120));

        lblWord.setFont(new Font("Lucida Grande", Font.PLAIN, 45));
        lblWord.setHorizontalAlignment(SwingConstants.CENTER);
        lblWord.setBackground(Color.LIGHT_GRAY);
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup().addContainerGap()
                        .addComponent(lblWord, GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE).addContainerGap()));
        gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
                gl_panel.createSequentialGroup().addContainerGap()
                        .addComponent(lblWord, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE).addContainerGap()));
        panel.setLayout(gl_panel);

        JPanel panel_1 = new JPanel();
        splitPane.setRightComponent(panel_1);
        panel_1.setBackground(new Color(255,203,120));

        JScrollPane scrollPane = new JScrollPane();

        scrollPane.setViewportView(textArea);
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
                gl_panel_1.createSequentialGroup().addContainerGap()
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE).addContainerGap()));
        gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE).addContainerGap()));
        panel_1.setLayout(gl_panel_1);
        panel_3.setLayout(gl_panel_3);
        panel_2.setLayout(gl_panel_2);

        ///////////////////
        
        mntmClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (numberOfTabs > 0) {
                    tabbedPane.removeTabAt(tabbedPane.getSelectedIndex());
                    numberOfTabs--;
                }
            }
        });

        mntmOpen.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                JFileChooser chooser = new JFileChooser();

                FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(mntmOpen);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    Thread thread = new Thread() {
                        Integer number_of_lines = new Integer(0);
                        Long time_delay = (long) 1000;
                        Integer counter = 0;
                        String words[] = new String[100000];
                        int numWords = 0;
                        

                        JTextArea textArea = new JTextArea();
                        Highlighter h = textArea.getHighlighter();
                        JProgressBar progressBar = new JProgressBar();
                        JLabel lblWord = new JLabel("Word Being Read");
                        JToggleButton tglbtnButton = new JToggleButton("Stopped: Press to start reading");

                        JPanel panel_2 = new JPanel();
                        Boolean isStopped = true;
                        int cursorPos = 0;
                        
                        HashMap<String, ArrayList<Integer>> dict = new HashMap<String, ArrayList<Integer>>();
                        HashMap<Integer, int[]> dict2 = new HashMap<Integer, int[]>();
                        HashMap<Integer, Integer> dict3 = new HashMap<Integer, Integer>();
                        String lastWord = null;
                        ArrayList<Integer> list = null;
                        int searchIndex = 0;
                        
                        

                        public void run() {
                            
                            if (isFirst == true) {
                                if (numberOfTabs > 0) {
                                    tabbedPane.removeTabAt(0);
                                    numberOfTabs--;
                                }
 
                                isFirst = false;
                            }
                            

                            tabbedPane.addTab("New tab", null, panel_2, null);
                            tabbedPane.setSelectedIndex(numberOfTabs);
                            numberOfTabs++;
                            
                            progressBar.setStringPainted(true);

                            Number value = new Float(1.1);
                            Number step = new Float(.1);
                            Comparable<Float> start = new Float(0);
                            Comparable<Float> end = new Float(2);
                            SpinnerNumberModel model = new SpinnerNumberModel(value, start, end, step);

                            JSpinner spinner = new JSpinner(model);
                            spinner.setToolTipText("Time Delay (seconds)");
                            JLabel lblTimeDelayseconds = new JLabel("Time Delay (seconds):");
                            

                            spinner.addChangeListener(new ChangeListener() {
                                        
                                public void stateChanged(ChangeEvent e) {
                                    time_delay = (long) (Float.valueOf(spinner.getValue().toString()) * 1000f);
                                    textArea.requestFocus();
                                }
                             });

                                    

                             tglbtnButton.addActionListener(new ActionListener() {

                                 public void actionPerformed(ActionEvent e) {

                                     if (tglbtnButton.isSelected() == true) {
                                         tglbtnButton.setText("Playing: Press to stop reading");
                                         isStopped = false;

                                     } else {
                                         tglbtnButton.setText("Stopped: Press to start reading");
                                         isStopped = true;

                                     }
                                     textArea.requestFocus();
                                 }
                             });
                             
                             textArea.addMouseListener(new MouseAdapter() {
                                 @Override
                                 public void mouseClicked(MouseEvent e) {
                                     
                                 //}
                            // });
                            // textArea.addCaretListener(new CaretListener() {
                               //  public void caretUpdate(CaretEvent e) {
                                     System.out.printf("%s%n", textArea.getCaret().getMark());
                                     if (dict3.get(textArea.getCaret().getMark()) != null) {
                                         isStopped = true;
                                         tglbtnButton.setText("Stopped: Press to start reading");
                                         tglbtnButton.setSelected(false);
                                         counter = dict3.get(textArea.getCaret().getMark());
                                         int bounds[] = dict2.get(counter);
                                         
                                         try {
                                            lblWord.setText(textArea.getText(bounds[0], bounds[1] - bounds[0] + 1));
                                            textArea.setSelectionStart(bounds[0]);
                                            textArea.setSelectionEnd(bounds[1]+1);
                                            //h.removeAllHighlights();
                                            //h.addHighlight(bounds[0], bounds[1]+1, DefaultHighlighter.DefaultPainter);
                                        } catch (BadLocationException e1) {
                                            // TODO Auto-generated catch block
                                            e1.printStackTrace();
                                            System.exit(1);
                                        }
                                        counter = (counter + 1) % numWords;
                                     }
                                 }
                             });
                             

                            JPanel panel_3 = new JPanel();

                            JLabel lblSearch = new JLabel("Search");

                            JTextField textField = new JTextField();
                            textField.setColumns(10);

                            JButton btnNext = new JButton("next");

                            JButton btnPrev = new JButton("prev");
                            GroupLayout gl_panel_2 = new GroupLayout(panel_2);
                            gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
                                    .addGroup(gl_panel_2.createSequentialGroup().addContainerGap().addGroup(gl_panel_2
                                            .createParallelGroup(Alignment.LEADING)
                                            .addGroup(gl_panel_2.createSequentialGroup().addGap(6)
                                                    .addComponent(lblSearch).addPreferredGap(ComponentPlacement.RELATED)
                                                    .addComponent(textField, GroupLayout.PREFERRED_SIZE,
                                                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(ComponentPlacement.RELATED).addComponent(btnNext)
                                                    .addPreferredGap(ComponentPlacement.RELATED).addComponent(btnPrev)
                                                    .addGap(528))
                                            .addGroup(gl_panel_2.createSequentialGroup().addGroup(gl_panel_2
                                                    .createParallelGroup(Alignment.LEADING)
                                                    .addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 909,
                                                            Short.MAX_VALUE)
                                                    .addGroup(gl_panel_2.createSequentialGroup()
                                                            .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 189,
                                                                    GroupLayout.PREFERRED_SIZE)
                                                            .addGap(31)
                                                            .addComponent(tglbtnButton, GroupLayout.PREFERRED_SIZE, 391,
                                                                    GroupLayout.PREFERRED_SIZE)
                                                            .addGap(18)
                                                            .addComponent(lblTimeDelayseconds,
                                                                    GroupLayout.PREFERRED_SIZE, 147,
                                                                    GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(ComponentPlacement.RELATED)
                                                            .addComponent(spinner, GroupLayout.PREFERRED_SIZE, 47,
                                                                    GroupLayout.PREFERRED_SIZE)))
                                                    .addContainerGap()))));
                            gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
                                    .addGroup(gl_panel_2.createSequentialGroup().addContainerGap()
                                            .addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 291,
                                                    GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(ComponentPlacement.RELATED)
                                            .addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
                                                    .addComponent(lblSearch)
                                                    .addComponent(textField, GroupLayout.PREFERRED_SIZE,
                                                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btnNext).addComponent(btnPrev))
                                            .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
                                                    Short.MAX_VALUE)
                                            .addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
                                                    .addGroup(gl_panel_2.createSequentialGroup()
                                                            .addComponent(lblTimeDelayseconds, GroupLayout.DEFAULT_SIZE,
                                                                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addContainerGap())
                                                    .addGroup(gl_panel_2.createSequentialGroup().addGroup(gl_panel_2
                                                            .createParallelGroup(Alignment.TRAILING, false)
                                                            .addComponent(tglbtnButton, GroupLayout.DEFAULT_SIZE,
                                                                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 43,
                                                                    Short.MAX_VALUE)
                                                            .addComponent(spinner)).addGap(6)))));

                            JSplitPane splitPane = new JSplitPane();
                            splitPane.setResizeWeight(0.5);
                            GroupLayout gl_panel_3 = new GroupLayout(panel_3);
                            gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
                                    .addGroup(gl_panel_3.createSequentialGroup().addContainerGap()
                                            .addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE)
                                            .addContainerGap()));
                            gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
                                    .addGroup(gl_panel_3
                                            .createSequentialGroup().addContainerGap().addComponent(splitPane,
                                                    GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)
                                            .addContainerGap(28, Short.MAX_VALUE)));
                            JPanel panel = new JPanel();
                            splitPane.setLeftComponent(panel);
                            panel.setBackground(new Color(255,203,120));//not showing up

                            lblWord.setFont(new Font("Lucida Grande", Font.PLAIN, 45));
                            lblWord.setHorizontalAlignment(SwingConstants.CENTER);
                            lblWord.setBackground(Color.LIGHT_GRAY);
                            GroupLayout gl_panel = new GroupLayout(panel);
                            gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                    .addGroup(gl_panel.createSequentialGroup().addContainerGap()
                                            .addComponent(lblWord, GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
                                            .addContainerGap()));
                            gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                    .addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup().addContainerGap()
                                            .addComponent(lblWord, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                                            .addContainerGap()));
                            panel.setLayout(gl_panel);

                            JPanel panel_1 = new JPanel();
                            splitPane.setRightComponent(panel_1);
                            panel_1.setBackground(new Color(255,203,120));

                            JScrollPane scrollPane = new JScrollPane();

                            scrollPane.setViewportView(textArea);
                            GroupLayout gl_panel_1 = new GroupLayout(panel_1);
                            gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(
                                    Alignment.TRAILING,
                                    gl_panel_1.createSequentialGroup().addContainerGap()
                                            .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                                            .addContainerGap()));
                            gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                    .addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
                                            .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                                            .addContainerGap()));
                            panel_1.setLayout(gl_panel_1);
                            panel_3.setLayout(gl_panel_3);
                            panel_2.setLayout(gl_panel_2);
                            textArea.setBackground(new Color(255,203,120));
                            textArea.setDisabledTextColor(new Color(0, 255, 255));
                            textArea.setSelectionColor(new Color(0, 255, 255));
                            Highlighter highlighter = textArea.getHighlighter();
                            HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);
                            //highlighter.addHighlight(0 , 6, painter);
                            // }

                            textField.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    String word = textField.getText();
                                    if (!word.equals(lastWord)) {
                                        list = dict.get(word);
                                        if (list != null) {
                                            searchIndex = -1;
                                        }
                                    }
                                    lastWord = word;
                                    if (list == null || list.size() == 0) {
                                        return;
                                    }
                                    
                                    searchIndex = (searchIndex + 1) % list.size();
                                    int wordNumber = list.get(searchIndex);
                                    
                                    
                                    
                                    int startEnd[] = dict2.get(wordNumber);
                                    //textArea.setSelectionStart(startEnd[0]);
                                    //textArea.setSelectionEnd(startEnd[1]);
                                    isStopped = true;
                                    tglbtnButton.setText("Stopped: Press to start reading");
                                    tglbtnButton.setSelected(false);
                                    
                                    try {
                                        lblWord.setText(textArea.getText(startEnd[0], startEnd[1] - startEnd[0] + 1));
                                        textArea.setSelectionStart(startEnd[0]);
                                        textArea.setSelectionEnd(startEnd[1]+1);
                                           //h.removeAllHighlights();
                                           //h.addHighlight(bounds[0], bounds[1]+1, DefaultHighlighter.DefaultPainter);
                                    } catch (BadLocationException e1) {
                                           // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                        System.exit(1);
                                    }
                                    counter = (wordNumber + 1) % numWords;
                                    textArea.requestFocus();
                                    
                                    
                                }
                            });
                            
                            btnNext.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    String word = textField.getText();
                                    if (!word.equals(lastWord)) {
                                        list = dict.get(word);
                                        if (list != null) {
                                            searchIndex = -1;
                                        }
                                    }
                                    lastWord = word;
                                    if (list == null || list.size() == 0) {
                                        return;
                                    }
                                    
                                    searchIndex = (searchIndex + 1) % list.size();
                                    int wordNumber = list.get(searchIndex);
                                    
                                    
                                    
                                    int startEnd[] = dict2.get(wordNumber);
                                    //textArea.setSelectionStart(startEnd[0]);
                                    //textArea.setSelectionEnd(startEnd[1]);
                                    isStopped = true;
                                    tglbtnButton.setText("Stopped: Press to start reading");
                                    tglbtnButton.setSelected(false);
                                    
                                    try {
                                        lblWord.setText(textArea.getText(startEnd[0], startEnd[1] - startEnd[0] + 1));
                                        textArea.setSelectionStart(startEnd[0]);
                                        textArea.setSelectionEnd(startEnd[1]+1);
                                           //h.removeAllHighlights();
                                           //h.addHighlight(bounds[0], bounds[1]+1, DefaultHighlighter.DefaultPainter);
                                    } catch (BadLocationException e1) {
                                           // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                        System.exit(1);
                                    }
                                    counter = (wordNumber + 1) % numWords;
                                    textArea.requestFocus();
                                    
                                    
                                }
                            });
                            
                            btnPrev.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    String word = textField.getText();
                                    if (!word.equals(lastWord)) {
                                        list = dict.get(word);
                                        if (list != null) {
                                            searchIndex = list.size();
                                        }
                                    }
                                    lastWord = word;
                                    if (list == null || list.size() == 0) {
                                        return;
                                    }
                                    
                                    searchIndex = (searchIndex - 1);
                                    if (searchIndex < 0) {
                                        searchIndex = list.size() - 1;
                                    }
                                    
                                    int wordNumber = list.get(searchIndex);

                                    
                                    int startEnd[] = dict2.get(wordNumber);
                                    //textArea.setSelectionStart(startEnd[0]);
                                    //textArea.setSelectionEnd(startEnd[1]);
                                    isStopped = true;
                                    tglbtnButton.setText("Stopped: Press to start reading");
                                    tglbtnButton.setSelected(false);
                                    
                                    try {
                                        lblWord.setText(textArea.getText(startEnd[0], startEnd[1] - startEnd[0] + 1));
                                        textArea.setSelectionStart(startEnd[0]);
                                        textArea.setSelectionEnd(startEnd[1]+1);
                                           //h.removeAllHighlights();
                                           //h.addHighlight(bounds[0], bounds[1]+1, DefaultHighlighter.DefaultPainter);
                                    } catch (BadLocationException e1) {
                                           // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                        System.exit(1);
                                    }
                                    counter = (wordNumber + 1) % numWords;
                                    textArea.requestFocus();
                                    
                                    
                                }
                            });
                            
                            tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), chooser.getSelectedFile().getName());
                            Thread thread2 = new Thread() {
                                public void run() {
                                    file = chooser.getSelectedFile();
                                    int startPos = -1;
                                    int endPos = -1;
                                    
                                    StringBuilder s = new StringBuilder();
                                    try {
                                        BufferedReader br = new BufferedReader(new FileReader(file));
                                        int c;
                                        String symbol;
                                        cursorPos = 0;
                                        boolean started = false;
                                        //char currentWord[] = new char[100];

                                        while ((c = br.read()) != -1) {
                                            symbol =  Character.toString((char)c);
                                            s.append(symbol);
                                            //textArea.setText(textArea.getText() + symbol);
                                            if (symbol.equals(" ") || symbol.equals("\n") || symbol.equals("\t")) {
                                                if (started == true) {
                                                    int startEnd [] = new int[2];
                                                    startEnd[0] = startPos;
                                                    startEnd[1] = endPos;
                                            
                                                    //if (dict.get(textArea.getText(startPos, endPos-startPos + 1)) == null){
                                                    if (dict.get(s.substring(startPos, endPos+1)) == null){
                                                        ArrayList<Integer> list = new ArrayList<Integer>();
                                                        list.add(numWords);
                                                        //dict.put(textArea.getText(startPos, endPos-startPos + 1), list);
                                                        dict.put(s.substring(startPos, endPos+1), list);
                                                        
                                                    }
                                                    else {
                                                        //dict.get(textArea.getText(startPos, endPos-startPos + 1)).add(numWords);
                                                        dict.get(s.substring(startPos, endPos+1)).add(numWords);
                                                    }
                                                    dict2.put(numWords, startEnd);
                                                    dict3.put(startPos, numWords);
                                                    numWords++;
                                                    started = false;
                                                }
                                            }
                                            else if(started == false){
                                                startPos = cursorPos;
                                                endPos = cursorPos;
                                                started = true;
                                            }
                                            else {
                                                endPos = cursorPos;
                                            }
                                            cursorPos++;
                                        }
                                        textArea.setText(s.toString());
                                        br.close();
                                
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                        System.exit(1);
                                    }
                                    //textArea.select(0, 1);
                                    textArea.requestFocus();
                                    while (true) {
                                        progressBar.setString(
                                                Integer.toString((int) (((float) counter * 100) / numWords)) + "%");
                                        progressBar.setValue((int) (((float) counter * 100) / numWords));
                                        if (!isStopped) {

                                            try {
                                                TimeUnit.MILLISECONDS.sleep(time_delay);
                                            } catch (Exception ex) {
                                                ex.printStackTrace();
                                                System.exit(1);
                                            }

                                            int startEnd[] = dict2.get(counter);
                                            counter = (counter + 1) % numWords;
                                            try {
                                                lblWord.setText(textArea.getText(startEnd[0], startEnd[1] - startEnd[0] + 1));
                                                textArea.setSelectionStart(startEnd[0]);
                                                textArea.setSelectionEnd(startEnd[1]+1);
                                                
                                                //h.removeAllHighlights();
                                                //h.addHighlight(startEnd[0], startEnd[1]+1, DefaultHighlighter.DefaultPainter);
                                                
                                                
                                                
                                                
                                            }
                                            catch(Exception ex) {
                                                ex.printStackTrace();
                                                System.exit(1);
                                            }
                                            //lblWord.setSize(100, 100);
                                            lblWord.repaint();
                                        }


                                    }
                                    /*
                                    counter = 0;
                                    isStopped = true;
                                    tglbtnButton.setSelected(false);
                                    tglbtnButton.setText("Stopped: Press to start");
                                    progressBar.setValue(100);
                                    progressBar.setString("100%");
                                    */
                                }

                            };
                            thread2.start();
                        }
                    };
                    thread.start();
                }
                //thread.start();

            }
        });

        contentPane.setLayout(gl_contentPane);

    }
}
