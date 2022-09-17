package Utils;

import ConstantValues.ErrorCode;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class AlertDialog {
    public static void showUnloadedImages(
            Set<String> set,
            Component component) {
        if (set.size() != 0) {
            StringBuilder errMsg = new StringBuilder(ErrorCode.Unloaded_Images.msg);
            for (String s : set) {
                errMsg.append(s).append("\n");
            }

            JOptionPane.showConfirmDialog(
                    component,
                    errMsg,
                    ErrorCode.Unloaded_Images.code,
                    JOptionPane.DEFAULT_OPTION
            );
        }
    }

    public static void showAlertDialog(Component frame, String msg, String title) {
        JOptionPane.showConfirmDialog(
                frame, msg, title, JOptionPane.DEFAULT_OPTION
        );
    }

    public static int showAlertDialogWait(Component frame, String msg, String title) {
        return JOptionPane.showConfirmDialog(
                frame, msg, title, JOptionPane.DEFAULT_OPTION
        );
    }
}
