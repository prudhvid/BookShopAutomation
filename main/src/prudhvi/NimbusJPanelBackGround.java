
package prudhvi;
import java.awt.*;
import javax.swing.*;
//import javax.swing.plaf.InsetsUIResource;


public class NimbusJPanelBackGround {

    public NimbusJPanelBackGround() {
        JFrame f = new JFrame();
        JButton btn = new JButton("  Whatever  ");
        JButton btn1 = new JButton("  Whatever  ");
        JPanel p = new JPanel();
        p.add(btn);
        //UIManager.getLookAndFeelDefaults().put("Button.contentMargins", new InsetsUIResource(0, 0, 0, 0));
        //SwingUtilities.updateComponentTreeUI(f);
        p.add(btn1);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.add(p, BorderLayout.CENTER);
        f.setSize(200, 100);
        f.setLocation(150, 150);
        f.setVisible(true);
    }

    public static void main(String[] args) {

        /*try {
        for (UIManager.LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(laf.getName())) {
        UIManager.setLookAndFeel(laf.getClassName());
        UIManager.getLookAndFeelDefaults().put("Panel.background", Color.white);
        }
        }
        } catch (Exception e) {
        e.printStackTrace();
        }*/

        try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }


        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                NimbusJPanelBackGround nimbusJPanelBackGround = new NimbusJPanelBackGround();
            }
        });
    }
}