package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by WFA_ORO_BH on 10/24/2015.
 */
public class ProgressDialog extends JDialog {

    public ProgressDialog(Window parent){
        super(parent, "Messages Downloading...", ModalityType.APPLICATION_MODAL);
        setSize(400,400);

    }
}
