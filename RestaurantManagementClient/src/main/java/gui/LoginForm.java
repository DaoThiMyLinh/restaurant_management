package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginForm() {
        setTitle("Đăng nhập");
        setSize(300, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        // Tên đăng nhập
        JPanel userPanel = new JPanel(new FlowLayout());
        userPanel.add(new JLabel("Tên đăng nhập:"));
        usernameField = new JTextField(15);
        userPanel.add(usernameField);
        add(userPanel);

        // Mật khẩu
        JPanel passPanel = new JPanel(new FlowLayout());
        passPanel.add(new JLabel("Mật khẩu:"));
        passwordField = new JPasswordField(15);
        passPanel.add(passwordField);
        add(passPanel);

        // Nút đăng nhập
        JPanel buttonPanel = new JPanel();
        JButton loginButton = new JButton("Đăng nhập");
        buttonPanel.add(loginButton);
        add(buttonPanel);

        // Thông báo
        JLabel messageLabel = new JLabel("", SwingConstants.CENTER);
        add(messageLabel);

        // Xử lý đăng nhập
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Tài khoản mẫu
                if (username.equals("admin") && password.equals("123")) {
                    messageLabel.setText("Đăng nhập thành công!");
                } else {
                    messageLabel.setText("Sai tên đăng nhập hoặc mật khẩu.");
                }
            }
        });
    }
}
