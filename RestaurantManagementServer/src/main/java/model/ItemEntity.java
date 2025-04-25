/*
 * @ (#) ItemEntity.java      1.0      1/17/2025
 *
 * Copyright (c) 2025 IUH. ALL rights reserved.
 */
package model;

/*
 * @description: ItemEntity
 * @author: Trần Ngọc Huyền
 * @date: 1/17/2025
 * @version: 1.0
 */
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Data
@Entity
@Table(name = "items")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NamedQueries({
    @NamedQuery(name = "ItemEntity.findAll", query = "select i from ItemEntity i"),
    @NamedQuery(name = "ItemEntity.findByCategory", query = "select i from ItemEntity i where i.category.categoryId = :categoryId"),
    @NamedQuery(name = "ItemEntity.findByName", query = "select i from ItemEntity i where i.name = :name")
})
public class ItemEntity extends BaseEntity implements Serializable {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long itemId;

    @Column(name = "name", nullable = false, columnDefinition = "nvarchar(255)")
    private String name;

    @Column(name = "cost_price", nullable = false)
    private double costPrice;

    @Column(name = "selling_price", nullable = false)
    private double sellingPrice;

    @Column(name = "stock_quantity", nullable = false)
    private int stockQuantity;

    @Column(name = "description", columnDefinition = "nvarchar(255)")
    private String description;

    private final double VAT = 0.2;

    @Column(name = "img", columnDefinition = "nvarchar(3000)")
    private String img;

    @Column(name = "active", nullable = false)
    private boolean active;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    public ItemEntity() {

    }


    public ItemEntity(Long itemId, String name, double costPrice, double sellingPrice,
                      int stockQuantity, String description, String img, boolean active, CategoryEntity category) {
        this.itemId = itemId;
        this.name = name;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.stockQuantity = stockQuantity;
        this.description = description;
        this.img = img;
        this.active = active;
        this.category = category;
    }

    public ItemEntity(Long itemId) {
        setItemId(itemId);
    }



    public void setSellingPrice() {
        double sizePrice = 0;
        this.sellingPrice = this.costPrice * (2 + getVAT()) + 50000;
    }

    public void setName(String name) throws Exception {
        if (name.trim().isEmpty() || name.trim().isBlank()) {
            throw new Exception("Tên sản phẩm không được để trống");
        }
        this.name = name;
    }

    public void setCostPrice(double costPrice) {
        if (costPrice < 0) {
            this.costPrice = 0;
        } else {
            this.costPrice = costPrice;
        }
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) {
            this.stockQuantity = 0;
        } else {
            this.stockQuantity = stockQuantity;
        }
    }
}
