/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba;

import com.github.strikerx3.jxinput.*;
import com.github.strikerx3.jxinput.enums.XInputAxis;
import com.github.strikerx3.jxinput.enums.XInputButton;
import com.github.strikerx3.jxinput.exceptions.XInputNotLoadedException;
import com.github.strikerx3.jxinput.listener.*;
import javax.swing.*;

public class Prueba {
    private static int total = 0;
    private static JLabel totalLabel;
    private static JLabel connectionLabel;

    public static void main(String[] args) throws XInputNotLoadedException {
        // Crear el JFrame
        JFrame frame = new JFrame("Sumador de Gatillos");
        totalLabel = new JLabel("Total: 0");
        connectionLabel = new JLabel("Mando desconectado");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.add(totalLabel);
        frame.add(connectionLabel);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        XInputDevice[] devices = XInputDevice.getAllDevices();
        XInputDevice device = devices[0]; // Suponemos que solo hay un dispositivo conectado

        device.addListener(new SimpleXInputDeviceListener() {
            @Override
            public void connected() {
                System.out.println("Mando conectado.");
                connectionLabel.setText("Mando conectado");
            }

            @Override
            public void disconnected() {
                System.out.println("Mando desconectado.");
                connectionLabel.setText("Mando desconectado");
            }

            @Override
            public void buttonChanged(XInputButton button, boolean pressed) {
                // No necesitamos manejar los eventos de botones
            }

            public void axisChanged(XInputAxis axis, double value) {
                if (axis == XInputAxis.RIGHT_TRIGGER) {
                    if (value > 0.5) {
                        total += 5;
                        System.out.println("Gatillo derecho presionado. Total: " + total);
                        totalLabel.setText("Total: " + total);
                    }
                } else if (axis == XInputAxis.LEFT_TRIGGER) {
                    if (value > 0.5) {
                        total -= 5;
                        System.out.println("Gatillo izquierdo presionado. Total: " + total);
                        totalLabel.setText("Total: " + total);
                    }
                }
            }
        });

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
