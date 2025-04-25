package util;

import dao.*;
import jakarta.persistence.EntityManager;
import model.*;
import model.enums.TableStatusEnum;
import net.datafaker.Faker;

import java.time.LocalDateTime;
import java.util.*;

/*
 * @description: DataGenerator
 * @author: Trần Ngọc Huyền
 * @date: 4/25/2025
 * @version: 1.0
 */

public class DataGenerator {
    private Faker faker = new Faker(new Locale("vi"));
    private final Random rand = new Random();
    private final EntityManager em = JPAUtil.getEntityManager();
    private final CategoryDAO categoryDAO = new CategoryDAO(em);
    private final ItemDAO itemDAO = new ItemDAO(em);
    private final EmployeeDAO employeeDAO = new EmployeeDAO(em);
    private final RoleDAO roleDAO = new RoleDAO(em);
    private final CustomerDAO customerDAO = new CustomerDAO(em);
    private final FloorDAO floorDAO = new FloorDAO(em);
    private final TableDAO tableDAO = new TableDAO(em);
    private final OrderDAO orderDAO = new OrderDAO(em);
    private final OrderDetailDAO orderDetailDAO = new OrderDetailDAO(em);

    // CategoryEntity
    public CategoryEntity generateCategoryEntity() {
        CategoryEntity category = new CategoryEntity();
        try {
            String name = faker.commerce().department();
            String description = faker.lorem().sentence(5);
            category.setName(name);
            category.setDescription(description);
            category.setActive(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }


    // ItemEntity
    public ItemEntity generateItemEntity(CategoryEntity category) {
        ItemEntity item = new ItemEntity();
        try {
            // Tên sản phẩm không được trống
            item.setName(faker.commerce().productName());

            // Giá vốn ngẫu nhiên, đảm bảo không âm
            double cost =rand.nextDouble() * 150 + 50;
            item.setCostPrice(cost);

            // Giá bán được tính theo hàm setSellingPrice
            item.setSellingPrice(); // sử dụng logic: cost * (2 + VAT) + 50,000

            // Mô tả
            item.setDescription(faker.lorem().sentence(6));

            // Ảnh ngẫu nhiên (có thể dùng ảnh tĩnh hoặc placeholder)
            item.setImg("https://picsum.photos/200?random=" + faker.number().randomDigit());

            // Số lượng tồn kho
            item.setStockQuantity(faker.number().numberBetween(0, 100));

            // Trạng thái hoạt động
            item.setActive(true);

            // Liên kết category
            item.setCategory(category);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return item;
    }

    //Address
    public Address generateAddress() {
        Address address = new Address();
        address.setStreet(faker.address().streetAddress());
        String[] districts = {"Ba Đình", "Hoàn Kiếm", "Tây Hồ", "Hai Bà Trưng", "Đống Đa", "Cầu Giấy", "Hà Đông", "Long Biên"};
        address.setDistrict(districts[rand.nextInt(districts.length)]);
        String[] wards = {"Phường Trúc Bạch", "Phường Cửa Đông", "Phường Yên Phụ", "Phường Phan Chu Trinh", "Phường Ngọc Hà"};
        address.setWard(wards[rand.nextInt(wards.length)]);
        String[] cities = {
                "Hà Nội", "Hồ Chí Minh", "Đà Nẵng", "Bến Tre", "Nha Trang",
                "Cần Thơ", "Hải Phòng", "Đồng Nai", "Bình Dương", "Vũng Tàu",
                "Quy Nhơn", "Phan Thiết", "Huế", "Ninh Bình", "Quảng Ninh",
                "Vĩnh Long", "Long An", "An Giang", "Sóc Trăng", "Tiền Giang",
                "Kiên Giang", "Bắc Giang", "Lào Cai", "Thái Nguyên", "Nam Định",
                "Hạ Long", "Tây Ninh", "Bà Rịa", "Bạc Liêu", "Móng Cái",
                "Lâm Đồng", "Gia Lai", "Kon Tum", "Hòa Bình", "Quảng Nam", "Quảng Ngãi"
        };
        address.setCity(cities[rand.nextInt(cities.length)]);
        return address;
    }

    // EmployeEntity
    public EmployeeEntity generateEmployeeEntity(RoleEntity role) {
        EmployeeEntity employee = new EmployeeEntity();

        employee.setPassword(faker.internet().password(8, 16));
        employee.setFullname(faker.name().fullName());
        employee.setPhoneNumber(generateVietnamesePhoneNumber());
        employee.setEmail(faker.internet().emailAddress());
        employee.setAddress(generateAddress());
        employee.setActive(true);
        employee.setRole(role);

        return employee;
    }

    // CustomerEntity
    public CustomerEntity generateCustomerEntity() {
        CustomerEntity customer = new CustomerEntity();
        customer.setName(faker.name().fullName());
        customer.setEmail(faker.internet().emailAddress());
        customer.setPhone(generateVietnamesePhoneNumber());
        LocalDateTime dayOfBirth = LocalDateTime.now().minusYears(18 + rand.nextInt(42));
        customer.setDayOfBirth(dayOfBirth);
        customer.setAddress(generateAddress());
        return customer;
    }

    // FloorEntity
    public FloorEntity generateFloorEntity(int capacityFloor) {
        FloorEntity floor = new FloorEntity();
        int numberOfFloor = floorDAO.getAll().size() + 1;
        floor.setName("Tầng " + numberOfFloor);
        floor.setCapacity(capacityFloor);
        Set<TableEntity> tables = new HashSet<>();
        floor.setTables(tables);
        return floor;
    }

    //TableEntity
    public TableEntity generateTableEntity(FloorEntity floor) {
        TableEntity table = new TableEntity();
        int numberOfTable = tableDAO.getAll().size() + 1;
        table.setName("Bàn " + numberOfTable);
        table.setCapacity(rand.nextInt(6) + 2);
        table.setTableStatus(TableStatusEnum.AVAILABLE);
        table.setFloor(floor);
        floor.getTables().add(table);
        return table;
    }

    //OrderEntity
    private String generateVietnamesePhoneNumber() {
        String[] prefixes = {"03", "07", "08", "09", "056", "058", "070", "079", "077", "076", "078"};
        String prefix = prefixes[new Random().nextInt(prefixes.length)];
        String suffix = String.format("%07d", new Random().nextInt(10000000)); // 7 chữ số ngẫu nhiên
        return prefix + suffix;
    }

    public void generateSimpleData() {
        RoleEntity staff = new RoleEntity(null, "Staff", null);
        RoleEntity management = new RoleEntity(null, "Management", null);
        roleDAO.save(staff);
        roleDAO.save(management);

        List<CategoryEntity> categories = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            CategoryEntity category = generateCategoryEntity();
            categoryDAO.save(category);
            categories.add(category);
        }

        for (int i = 0; i < 20; i++) {
            ItemEntity item = generateItemEntity(categories.get(0));
            itemDAO.save(item);
        }
        for (int i = 1; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
                ItemEntity item = generateItemEntity(categories.get(i));
                itemDAO.save(item);
            }
        }

        for (int i = 0; i < 10; i++) {
            CustomerEntity customer = generateCustomerEntity();
            customerDAO.save(customer);
        }

        EmployeeEntity emp1 = generateEmployeeEntity(staff);
        employeeDAO.save(emp1);
        EmployeeEntity emp2 = generateEmployeeEntity(management);
        employeeDAO.save(emp2);

        for (int i = 0; i < 3; i++) {
            FloorEntity floor = generateFloorEntity(20);
            floorDAO.save(floor);
            for (int j = 0; j < 10; j++) {
                TableEntity table = generateTableEntity(floor);
                tableDAO.save(table);
            }
        }
    }

    public static void main(String[] args) {
        DataGenerator gen = new DataGenerator();
        gen.generateSimpleData();
    }
}
