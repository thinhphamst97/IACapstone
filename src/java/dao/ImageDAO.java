package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbutils.DBUtils;
import dto.ImageDTO;
import dto.KernelDTO;

public class ImageDAO {

    public static ArrayList<ArrayList<String>> getImageTable() {
        ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();
        for (int i = 1;; i++) {
            ArrayList<String> result = new ArrayList<String>();
            ImageDTO image = ImageDAO.getImage(i);
            if (image != null) {
                KernelDTO kernel = KernelDAO.getKernel(image.getKernelId());
                String kernelName = null;
                if (kernel != null) {
                    kernelName = kernel.getName();
                }
                result.add(image.getName());
                result.add(kernelName);
                result.add(image.getDateCreated().toString());
                if (image.isActive()) {
                    result.add("Active");
                } else {
                    result.add("Inactive");
                }
                resultList.add(result);
            } else {
                break;
            }
        }
        return resultList;
    }

    public static ImageDTO getImage(int id) {
        Connection c = null;
        PreparedStatement preState = null;
        ResultSet rs = null;
        ImageDTO result = null;

        try {
            c = DBUtils.ConnectDB();
            if (c != null) {
                String sql = "select `name`, `description`, `isActive`, `dateCreated`, `kernelId` from `image` where `id`=?";
                preState = c.prepareStatement(sql);
                preState.setInt(1, id);
                rs = preState.executeQuery();
                if (rs != null && rs.next()) {
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    boolean isActive = rs.getBoolean("isActive");
                    Date dateCreated = rs.getDate("dateCreated");
                    int kernelId = rs.getInt("kernelId");
                    result = new ImageDTO(id, name, description, isActive, dateCreated, kernelId);
                }
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static boolean checkDuplicate(int id) {
        boolean isDuplicate = false;
        Connection c = null;
        try {
            c = DBUtils.ConnectDB();
            if (c != null) {
                String sql = "select `id` from `image` where `id`=?";
                PreparedStatement preState = c.prepareStatement(sql);
                preState.setInt(1, id);
                ResultSet rs = preState.executeQuery();
                if (rs != null && rs.next()) {
                    c.close();
                    isDuplicate = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDuplicate;
    }

    public static int addImage(int id, String name, String description, boolean isActive, Date dateCreated, int kernelId) {
        int result = 0;
        try {
            boolean isDuplicate = checkDuplicate(id);
            Connection c = DBUtils.ConnectDB();
            if (c != null && !isDuplicate) {
                String sql = "insert into `image`(`id`, `name`, `description`, `isActive`, `dateCreated`, `kernelId`) values(?, ?, ?, ?, ?, ?)";
                PreparedStatement preState = c.prepareStatement(sql);
                preState.setInt(1, id);
                preState.setString(2, name);
                preState.setString(3, description);
                preState.setBoolean(4, isActive);
                preState.setDate(5, dateCreated);
                preState.setInt(6, kernelId);
                result = preState.executeUpdate();
                c.close();
            }
            if (isDuplicate) {
                // Show error
                System.out.println("Image id duplicated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean deleteImage(int id) {
        Connection c = null;
        PreparedStatement preState = null;
        int effect = 0;
        boolean result = false;

        try {
            c = DBUtils.ConnectDB();
            if (c != null) {
                String sql = "delete from `image` where `id`=?";
                preState = c.prepareStatement(sql);
                preState.setInt(1, id);
                effect = preState.executeUpdate();
                if (effect > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean updateImage(int id, String name, String description, boolean isActive, Date dateCreated, int kernelId) {
        Connection c = null;
        PreparedStatement preState = null;
        int effect = 0;
        boolean result = false;

        try {
            c = DBUtils.ConnectDB();
            if (c != null) {
                String sql = "update `image` set `name`=?, `description`=?, `isActive`=?, `dateCreated`=?, `kernelId`=? where `id`=?";
                preState = c.prepareStatement(sql);
                preState.setString(1, name);
                preState.setString(2, description);
                preState.setBoolean(3, isActive);
                preState.setDate(4, dateCreated);
                preState.setInt(5, kernelId);
                preState.setInt(6, id);
                effect = preState.executeUpdate();
                if (effect > 0) {
                    result = true;
                }
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
//		addImage("Test Image 2", "Test description 2", true, 0);
//		addImage("Test Image 3", "Test description 3", false, 0);
//		addImage("Test Image 4", "Test description 4", true, 0);
//		addImage("Test Image 5", "Test description 5", false, 0);
//		deleteImage("Test description 2");
//		deleteImage("Test description 3");
//		deleteImage("Test description 4");
//		deleteImage("Test description 5");
//		updateImage("Test Image", "Update description 2", true, 0);
//		ImageDTO temp = getImage("image1");
//		System.out.println(temp.getName());
//		System.out.println(temp.getDescription());
//		System.out.println(temp.isActive());
//		System.out.println(temp.getDateCreated());
//		System.out.println(temp.getKernelId());
//		Date date = Date.valueOf(LocalDate.now());
//		updateImage(temp.getName(), temp.getDescription(), temp.isActive(), date, temp.getKernelId());
    }

}
