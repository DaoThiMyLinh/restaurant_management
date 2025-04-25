package gui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
public class FoodOrder extends JFrame{

    public FoodOrder() {

            setTitle("Hóa Đơn Bán Hàng");
            setSize(1200, 700);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(new BorderLayout(10, 10));

            // ===== Panel trên: Thông tin chi tiết hóa đơn =====
            JPanel topPanel = new JPanel(new GridBagLayout());
            topPanel.setBorder(BorderFactory.createTitledBorder("Thông tin chi tiết hóa đơn"));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            JLabel lblMaSP = new JLabel("Mã SP:");
            JTextField txtMaSP = new JTextField(10);
            JButton btnTim = new JButton("Tìm");
            JLabel lblDonGia = new JLabel("Đơn giá:");
            JTextField txtDonGia = new JTextField(10);

            JLabel lblSoLuong = new JLabel("Số lượng:");
            JTextField txtSoLuong = new JTextField(10);
            JTextField txtThanhTien = new JTextField(10);
            txtThanhTien.setEditable(false);

            JButton btnThem = new JButton("Thêm");
            JButton btnXoa = new JButton("Xóa");
            JButton btnSua = new JButton("Sửa");
            JButton btnReset = new JButton("Reset");

            // Row 1
            gbc.gridx = 0;
            gbc.gridy = 0;
            topPanel.add(lblMaSP, gbc);
            gbc.gridx = 1;
            gbc.gridy = 0;
            topPanel.add(txtMaSP, gbc);
            gbc.gridx = 2;
            gbc.gridy = 0;
            topPanel.add(btnTim, gbc);
            gbc.gridx = 3;
            gbc.gridy = 0;
            topPanel.add(lblDonGia, gbc);
            gbc.gridx = 4;
            gbc.gridy = 0;
            topPanel.add(txtDonGia, gbc);

            // Row 2
            gbc.gridx = 0;
            gbc.gridy = 1;
            topPanel.add(lblSoLuong, gbc);
            gbc.gridx = 1;
            gbc.gridy = 1;
            topPanel.add(txtSoLuong, gbc);
            gbc.gridx = 3;
            gbc.gridy = 1;
            topPanel.add(new JLabel("Thành tiền:"), gbc);
            gbc.gridx = 4;
            gbc.gridy = 1;
            topPanel.add(txtThanhTien, gbc);

            // Row 3 (nút bấm)
            gbc.gridx = 1;
            gbc.gridy = 2;
            topPanel.add(btnThem, gbc);
            gbc.gridx = 2;
            topPanel.add(btnXoa, gbc);
            gbc.gridx = 3;
            topPanel.add(btnSua, gbc);
            gbc.gridx = 4;
            topPanel.add(btnReset, gbc);

            // ===== Panel giữa: Bảng chi tiết sản phẩm =====
            String[] columnNames = {"Sản phẩm", "Khuyến Mãi", "Thuế VAT", "Đơn giá", "Số lượng", "Thành tiền"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
            JTable table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);

            // ===== Panel phải: Thông tin thanh toán =====
            JPanel rightPanel = new JPanel(new GridBagLayout());
            rightPanel.setBorder(BorderFactory.createTitledBorder("Thông tin thanh toán"));
            GridBagConstraints gbc2 = new GridBagConstraints();
            gbc2.insets = new Insets(5, 5, 5, 5);
            gbc2.fill = GridBagConstraints.HORIZONTAL;

            JTextField txtMaNV = new JTextField(10);
            JTextField txtSoDT = new JTextField(10);
            JTextField txtDiem = new JTextField(10);
            JTextField txtTenKH = new JTextField(10);
            JTextField txtMaKH = new JTextField(10);
            JTextField txtTongTien = new JTextField(10);
            JTextField txtTienDung = new JTextField(10);
            JTextArea txtGhiChu = new JTextArea(3, 10);

            JButton btnThemHoaDon = new JButton("Thêm hóa đơn");

            String[] labels = {"Mã NV:", "Số điện thoại:", "Điểm tích lũy:", "Tên KH:", "Mã KH:", "Tổng tiền:", "Tiền dùng:", "Ghi chú:"};
            JTextField[] fields = {txtMaNV, txtSoDT, txtDiem, txtTenKH, txtMaKH, txtTongTien, txtTienDung};

            for (int i = 0; i < labels.length; i++) {
                gbc2.gridx = 0;
                gbc2.gridy = i;
                rightPanel.add(new JLabel(labels[i]), gbc2);

                gbc2.gridx = 1;
                rightPanel.add(fields[i], gbc2);
            }

            // Ghi chú
            gbc2.gridx = 0;
            gbc2.gridy = labels.length;
            rightPanel.add(new JLabel("Ghi chú:"), gbc2);
            gbc2.gridx = 1;
            rightPanel.add(new JScrollPane(txtGhiChu), gbc2);

            // Nút Thêm hóa đơn
            gbc2.gridx = 1;
            gbc2.gridy = labels.length + 1;
            rightPanel.add(btnThemHoaDon, gbc2);

            // ===== Layout tổng =====
            add(topPanel, BorderLayout.NORTH);
            add(scrollPane, BorderLayout.CENTER);
            add(rightPanel, BorderLayout.EAST);

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FoodOrder().setVisible(true);
        });
    }
}
