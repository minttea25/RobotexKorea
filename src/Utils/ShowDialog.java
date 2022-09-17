package Utils;

import ConstantValues.ErrorCode;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ShowDialog {

    public static void ShowErrorDialog(ErrorCode error, String additionalMsg, Component parent, Method callback, Object callbackObject) {
        int ok = JOptionPane.showConfirmDialog(
                parent,
                error.msg + additionalMsg,
                error.code,
                JOptionPane.DEFAULT_OPTION
        );
        if (ok == JOptionPane.OK_OPTION) {
            try {
                callback.invoke(callbackObject);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
