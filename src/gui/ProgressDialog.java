package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by WFA_ORO_BH on 10/24/2015.
 */
public class ProgressDialog extends JDialog {

    private JProgressBar progressbar;
    private JButton cancelButton;

    public ProgressDialog(Window parent){
        super(parent, "Messages Downloading...", ModalityType.APPLICATION_MODAL);

        progressbar = new JProgressBar();
        progressbar.setStringPainted(true);
        progressbar.setString("Retrieving Messages...");
        cancelButton = new JButton("Cancel");

        setLayout(new FlowLayout());

        Dimension dimension = cancelButton.getPreferredSize();
        dimension.width = 400;
        progressbar.setPreferredSize(dimension);

        add(progressbar);
        add(cancelButton);


        pack();
        setLocationRelativeTo(parent);
    }

    public void setMaximum(int max){
        progressbar.setMaximum(max);
    }

    public void setMinimum(int min){
        progressbar.setMinimum(min);
    }

    public void setValue(int value){
        int progress = 100 * value/progressbar.getMaximum();
        progressbar.setString(String.format("%d%% complete",progress));
        progressbar.setValue(value);
    }

    @Override
    public void setVisible(final boolean visible) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if(!visible){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    progressbar.setValue(0);
                }
                ProgressDialog.super.setVisible(visible);
            }
        });
    }
}
