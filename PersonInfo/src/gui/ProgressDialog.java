package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by WFA_ORO_BH on 10/24/2015.
 */
public class ProgressDialog extends JDialog  {

    private JProgressBar progressbar;
    private JButton cancelButton;
    private ProgressDialogListener listener;

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

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(listener!=null){
                    listener.cancelProgress();
                }
            }
        });


        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(listener != null){
                    listener.cancelProgress();
                }
            }
        });


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

                if(visible){
                    progressbar.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                }else{
                    progressbar.setCursor(Cursor.getDefaultCursor());
                }
                ProgressDialog.super.setVisible(visible);
            }
        });
    }

    public void setProgressDialogListener(ProgressDialogListener lsnr){
        this.listener = lsnr;
    }
}
