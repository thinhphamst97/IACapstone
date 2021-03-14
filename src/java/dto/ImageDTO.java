package dto;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class ImageDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String description;
    private boolean isActive;
    private Date dateCreated;
    private int kernelId;

    public ImageDTO(int id, String name, String description, boolean isActive, Date dateCreated, int kernelId) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.dateCreated = dateCreated;
        this.kernelId = kernelId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getKernelId() {
        return kernelId;
    }

    public void setKernelId(int kernelId) {
        this.kernelId = kernelId;
    }

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        System.out.println(Date.valueOf(now).toString());
    }

}
