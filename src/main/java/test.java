package main.java;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.beans.PropertyVetoException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JDesktopPane;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class test extends JFrame {

    private JPanel contentPane;
    private JInternalFrame internalFrame;
    private JInternalFrame internalFrame_1;
    private JScrollPane scrollPane;
    private JLabel lblNewLabel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    test frame = new test();
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
    public test() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 623, 516);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JDesktopPane desktopPane = new JDesktopPane();
        contentPane.add(desktopPane, BorderLayout.CENTER);
        
        internalFrame_1 = new JInternalFrame("New JInternalFrame",true, false, true, true);
        internalFrame_1.setBounds(386, 28, 150, 193);
        desktopPane.setLayout(null);

        
        internalFrame = new JInternalFrame("New JInternalFrame", true, false, true, true);
        internalFrame.setBounds(24, 29, 217, 402);
                desktopPane.add(internalFrame);
                
                scrollPane = new JScrollPane();
                internalFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
                
                lblNewLabel = new JLabel("New label");
                lblNewLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        /*
                            try {
                                test.getURLSource("https://www.google.com/search?q=the&source=lnms&tbm=isch&sa=X#imgrc=_N1Xqc_QE0d24M:");
                            } catch (IOException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                                System.exit(1);
                            }
                            */
                            String imageUrl = "https://vignette.wikia.nocookie.net/the-jh-movie-collection-official/images/e/e6/Dora_and_the_lost_city_of_gold_ver3_xxlg.jpg/revision/latest?cb=20190709205813";
                          //urlPath = address of your picture on internet
                            URL url = null;
                            try {
                                url = new URL(imageUrl);

                            } catch (MalformedURLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                                System.exit(1);
                            }
                            URLConnection urlConnection = null;
                            try {
                                urlConnection = url.openConnection();
                            } catch (IOException e2) {
                                // TODO Auto-generated catch block
                                e2.printStackTrace();
                                System.exit(1);
                            }
                            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

                            BufferedImage c = null;
                            try {
                                c = ImageIO.read(url);
                            } catch (IOException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                                System.exit(1);
                            }
                            ImageIcon image = new ImageIcon(c);
                            ImageIcon imageIcon = new ImageIcon(image.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
                            lblNewLabel.setIcon(imageIcon);
                        }
                    }
                );
                scrollPane.setViewportView(lblNewLabel);
                desktopPane.add(internalFrame_1);
                
                JSeparator separator = new JSeparator();
                separator.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                separator.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        internalFrame.setSize(10,10);
                        internalFrame_1.setSize(10, 10);
                    }
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        //desktopPane.setCursor(cursor);
                    }
                });
                separator.setOrientation(SwingConstants.VERTICAL);
                separator.setBounds(265, 16, 17, 237);
                desktopPane.add(separator);
                internalFrame.setVisible(true);
                internalFrame_1.setVisible(true);
                

                
                

        
    }
public static String getURLSource(String url) throws IOException
{
    URL urlObject = new URL(url);
    URLConnection urlConnection = urlObject.openConnection();
    urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

    return xtoString(urlConnection.getInputStream());
}
private static String xtoString(InputStream inputStream) throws IOException
{
    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")))
    {
        String inputLine;
        StringBuilder stringBuilder = new StringBuilder();
        while ((inputLine = bufferedReader.readLine()) != null)
        {
            stringBuilder.append(inputLine);
        }

        return stringBuilder.toString();
    }
}

}
