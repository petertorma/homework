package hu.codingmentor;

import java.util.UUID;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MobileDTO {

    @Min(36)
    @Max(36)
    private String id;

    @NotNull
    @Min(3)
    private String type;

    public MobileDTO(String type, String manufacturer, int price, int piece) {
        this.id = (UUID.randomUUID().toString());
        this.type = type;
        this.manufacturer = manufacturer;
        this.price = price;
        this.piece = piece;
    }

    @NotNull
    @Min(3)
    private String manufacturer;

    @Min(1)
    private int price;

    @Min(0)
    private int piece;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPiece() {
        return piece;
    }

    public void setPiece(int piece) {
        this.piece = piece;
    }
}
