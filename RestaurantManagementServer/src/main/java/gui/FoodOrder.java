package gui;

import model.*;
import service.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.rmi.RemoteException;
import java.util.Set;

public class FoodOrder extends JFrame {

    private final FloorService floorService = LoginForm.floorService;
    private final TableService tableService = LoginForm.tableService;
    private DefaultTableModel model;


    public FoodOrder() throws RemoteException {
        // Thiết lập JFrame
        setTitle("Quản lý đơn hàng");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        // Panel phía trên (Thông tin đơn hàng)
        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Thông tin đơn hàng
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblMaSp = new JLabel("MÃ SP:");
        topPanel.add(lblMaSp, gbc);
        gbc.gridx = 1;
        JTextField txtMaSP = new JTextField(10);
        topPanel.add(txtMaSP, gbc);
        gbc.gridx = 2;
        JLabel soLuongField = new JLabel("SỐ LƯỢNG:");
        topPanel.add(soLuongField, gbc);
        gbc.gridx = 3;
        JTextField txtSoLuong = new JTextField( 5);
        topPanel.add(txtSoLuong, gbc);
        gbc.gridx = 4;
        JButton btnTim = new JButton("Tìm ");

        topPanel.add(btnTim, gbc);

        gbc.gridx = 5;
        gbc.gridy = 0;
        JLabel donGiaField = new JLabel("Đơn giá:");
        topPanel.add(donGiaField, gbc);
        gbc.gridx = 6;
        JTextField txtDonGia = new JTextField(10);
        topPanel.add(txtDonGia, gbc);

        // Dropdown chọn bàn và tầng
        gbc.gridx = 0;
        gbc.gridy = 1;
        topPanel.add(new JLabel("Tầng:"), gbc);
        gbc.gridx = 1;

        // Lấy danh sách tầng từ DB
        List<FloorEntity> floors = LoginForm.floorService.getAll();
        String[] floorNames = floors.stream().map(FloorEntity::getName).toArray(String[]::new);

// Tạo JComboBox tầng
        JComboBox<String> floorDropdown = new JComboBox<>(floorNames);
        gbc.gridx = 0;
        gbc.gridy = 1;
        topPanel.add(new JLabel("Tầng:"), gbc);
        gbc.gridx = 1;
        topPanel.add(floorDropdown, gbc);

// Label Bàn
        gbc.gridx = 2;
        topPanel.add(new JLabel("Bàn:"), gbc);

// Khởi tạo JComboBox bàn (rỗng trước)
        gbc.gridx = 3;
        JComboBox<String> tableDropdown = new JComboBox<>();
        topPanel.add(tableDropdown, gbc);
// Fill bàn theo tầng mặc định ban đầu (chỉ bàn AVAILABLE)
        if (!floors.isEmpty()) {
            Long defaultFloorId = floors.get(0).getFloorId();
            List<TableEntity> defaultTables = LoginForm.tableService.getAvailableByFloor(defaultFloorId);
            String[] tableNames = defaultTables.stream().map(TableEntity::getName).toArray(String[]::new);
            tableDropdown.setModel(new DefaultComboBoxModel<>(tableNames));
        }

// Xử lý sự kiện chọn tầng → chỉ load bàn AVAILABLE
        floorDropdown.addActionListener(e -> {
            String selectedFloorName = (String) floorDropdown.getSelectedItem();
            floors.stream()
                    .filter(f -> f.getName().equals(selectedFloorName))
                    .findFirst()
                    .ifPresent(floor -> {
                        try {
                            List<TableEntity> tables = LoginForm.tableService.getAvailableByFloor(floor.getFloorId());
                            String[] tableNames = tables.stream().map(TableEntity::getName).toArray(String[]::new);
                            tableDropdown.setModel(new DefaultComboBoxModel<>(tableNames));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Lỗi khi tải bàn: " + ex.getMessage());
                        }
                    });
        });


        // Nút chức năng
        gbc.gridx = 4;
        gbc.gridy = 1;
        JButton btnThem = new JButton("Thêm");
        topPanel.add(btnThem, gbc);


        gbc.gridx = 5;
        topPanel.add(new JButton("Xóa"), gbc);
        gbc.gridx = 6;
        topPanel.add(new JButton("Reset"), gbc);

        // Thêm topPanel vào JFrame
        add(topPanel, BorderLayout.NORTH);

        // Bảng sản phẩm
        String[] columns = {"Sản phẩm", "Đơn giá", "Số lượng"};
        model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);


        // Panel bên phải (Thông tin khách hàng)
        JPanel rightPanel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        rightPanel.add(new JLabel("MÃ NV:"), gbc);
        gbc.gridx = 1;
        JTextField txtNV = new JTextField( 10);
        txtNV.setText(LoginForm.employee.getUsername());
        rightPanel.add(txtNV, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        rightPanel.add(new JLabel("Số điện thoại:"), gbc);
        gbc.gridx = 1;
        JTextField txtKH = new JTextField(10);
        rightPanel.add(txtKH, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        rightPanel.add(new JLabel("Tên KH:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // giãn dài field
        JTextField txtTenKh = new JTextField(15);
        rightPanel.add(txtTenKh, gbc);

// Reset lại gridwidth
        gbc.gridwidth = 1;

// Nút tìm khách hàng ở dòng mới
        gbc.gridx = 1;
        gbc.gridy = 4;
        JButton btnTimKH = new JButton("Tìm");
        rightPanel.add(btnTimKH, gbc);


        btnTimKH.addActionListener(e -> {
            String phone = txtKH.getText().trim();
            try {
                var customer = LoginForm.customerService.findByPhone(phone);
                if (customer != null) {
                    JOptionPane.showMessageDialog(null,
                            "Tìm thấy khách hàng:\nTên: " + customer.getName() + "\nEmail: " + customer.getEmail());
                    txtTenKh.setText(customer.getName());
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng với số điện thoại này!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi tìm khách hàng: " + ex.getMessage());
            }
        });


        gbc.gridx = 0;
        gbc.gridy = 5;
        rightPanel.add(new JLabel("Tổng tiền:"), gbc);
        gbc.gridx = 1;
        JTextField txtTongTien = new JTextField("", 10);
        rightPanel.add(txtTongTien, gbc);


        // Thêm nút "Thêm dòng" và "Tìm" trên cùng một hàng
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.CENTER;

        btnTim.addActionListener(e -> {
            String maSP = txtMaSP.getText().trim();
            try {
                ItemEntity item = LoginForm.itemService.findById(Long.parseLong(maSP));
                if (item != null) {
                    txtDonGia.setText( item.getSellingPrice() + "");

                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi tìm sản phẩm: " + ex.getMessage());
            }
        });


        gbc.gridx = 1;
        gbc.gridy = 8;
        JButton btnAdd = new JButton("Thêm hóa đơn");
        rightPanel.add(btnAdd, gbc);

        btnAdd.addActionListener(e -> {
            try {
                // 1. Tạo OrderEntity
                OrderEntity order = new OrderEntity();
                order.setCustomer(LoginForm.customerService.findByPhone(txtKH.getText()));
                order.setEmployee(LoginForm.employee); // từ login

                String tenBan = (String) tableDropdown.getSelectedItem();
                TableEntity tableEntity = LoginForm.tableService.findByName(tenBan); // cần tạo hàm này trong DAO

                order.setTable(tableEntity);

                Set<OrderDetailEntity> detailEntities = new HashSet<>();

                for (int i = 0; i < model.getRowCount(); i++) {
                    String tenSp = (String) model.getValueAt(i, 0);
                    ItemEntity item = LoginForm.itemService.findByName(tenSp);

                    int soLuong = Integer.parseInt(model.getValueAt(i, 2).toString());
                    double donGia = item.getSellingPrice();

                    OrderDetailEntity detail = new OrderDetailEntity();
                    detail.setOrder(order);
                    detail.setItem(item);
                    detail.setQuantity(soLuong);
                    detailEntities.add(detail);

                }
                order.setOrderDetails(detailEntities);
                LoginForm.orderService.save(order);


                JOptionPane.showMessageDialog(null, "Tạo hóa đơn thành công!");

                // Reset form nếu cần
                model.setRowCount(0);
                txtTongTien.setText("");

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi tạo hóa đơn: " + ex.getMessage());
            }
        });


        btnThem.addActionListener(e -> {
            try {
                String maSP = txtMaSP.getText();
                ItemEntity item = LoginForm.itemService.findById(Long.parseLong(maSP));
                double donGia = 0;

                if (item != null) {
                    txtDonGia.setText(item.getSellingPrice() + "");
                    donGia = item.getSellingPrice();
                }

                int soLuong = Integer.parseInt(txtSoLuong.getText());
                double thanhTien = donGia * soLuong;
                String tenSP = item.getName();

                model.addRow(new Object[]{
                        tenSP,
                        String.format("%,.2f VND", donGia),
                        soLuong,
                        thanhTien
                });

                double tong = 0;
                for (int i = 0; i < model.getRowCount(); i++) {
                    tong += Double.parseDouble(model.getValueAt(i, 3).toString());
                }
                txtTongTien.setText(String.format("%,.2f VND", tong));

                JOptionPane.showMessageDialog(null, "Thêm thành công");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi thêm sản phẩm: " + ex.getMessage());
            }
        });




        // Thêm rightPanel vào JFrame
        add(rightPanel, BorderLayout.EAST);

        setVisible(true);
    }


    public void loadFloor() {

    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(FoodOrder::new);
//    }
}
