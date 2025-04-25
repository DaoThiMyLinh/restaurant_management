package gui;

import model.EmployeeEntity;
import service.*;
import util.HostNameUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private final String SERVER_HOST_NAME = "MSI";
    public static CategoryService categoryService;
    public static ItemService itemService;
    public static FloorService floorService;
    public static TableService tableService;
    public static CustomerService customerService;
    public static OrderService orderService;
    public static OrderDetailService orderDetailService;
    public static EmployeeService employeeService;
    public static RoleService roleService;
    public static EmployeeEntity employee;


    public LoginForm() throws Exception {

        categoryService = (CategoryService) Naming.lookup(HostNameUtil.getURI(SERVER_HOST_NAME, CategoryService.class));
        itemService = (ItemService) Naming.lookup(HostNameUtil.getURI(SERVER_HOST_NAME, ItemService.class));
        floorService = (FloorService) Naming.lookup(HostNameUtil.getURI(SERVER_HOST_NAME, FloorService.class));
        tableService = (TableService) Naming.lookup(HostNameUtil.getURI(SERVER_HOST_NAME, TableService.class));
        customerService = (CustomerService) Naming.lookup(HostNameUtil.getURI(SERVER_HOST_NAME, CustomerService.class));
        orderService = (OrderService) Naming.lookup(HostNameUtil.getURI(SERVER_HOST_NAME, OrderService.class));
        orderDetailService = (OrderDetailService) Naming.lookup(HostNameUtil.getURI(SERVER_HOST_NAME, OrderDetailService.class));
        employeeService = (EmployeeService) Naming.lookup(HostNameUtil.getURI(SERVER_HOST_NAME, EmployeeService.class));
        roleService = (RoleService) Naming.lookup(HostNameUtil.getURI(SERVER_HOST_NAME, RoleService.class));



        setTitle("Đăng nhập");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        // Tên đăng nhập
        JPanel userPanel = new JPanel(new FlowLayout());
        userPanel.add(new JLabel("Tên đăng nhập:"));
        usernameField = new JTextField(15);
        usernameField.setText(employeeService.getAll().get(0).getUsername());
        userPanel.add(usernameField);
        add(userPanel);

        // Mật khẩu
        JPanel passPanel = new JPanel(new FlowLayout());
        passPanel.add(new JLabel("Mật khẩu:"));
        passwordField = new JPasswordField(15);
        passwordField.setText(employeeService.getAll().get(0).getPassword());
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
                String username = null;
                String password = null;

                try {
                    username = usernameField.getText();
                    password = passwordField.getText();
                    employee = employeeService.findByUsername(username);
                    // Tài khoản mẫu
                    if (employeeService.checkAccount(username, password)) {
                        JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");
                        new FoodOrder().setVisible(true);
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Sai tên đăng nhập hoặc mật khẩu.");
                    }
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new LoginForm().setVisible(true);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

}