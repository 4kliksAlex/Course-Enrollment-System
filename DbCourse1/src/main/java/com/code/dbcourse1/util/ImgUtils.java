package com.code.dbcourse1.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ImgUtils {
    /**
     * 指定路径读取图像
     * @param path 路径
     * @return 对应图像
     */
    public static BufferedImage getImg(String path){
        BufferedImage image = null;
        try {
            File inputFile = new File(path);
            image = ImageIO.read(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * 放缩图像到对应大小
     */
    public static BufferedImage cutImg(BufferedImage img, int width, int height){
        if(img == null) return null;
        if(width <=0 || height <= 0) return null;
        BufferedImage rst = new BufferedImage(width, height, img.getType());
        rst.getGraphics().drawImage(img,
                0, 0, width, height,
                0, 0, img.getWidth(), img.getHeight(),
                null);
        return rst;
    }

    /**
     * 读取图片并放缩到对应大小
     */
    public static BufferedImage getImg(String path,int width, int height){
        return cutImg(getImg(path),width,height);
    }

    /**
     * 图片转byte
     */
    public static byte[] toBytes(BufferedImage img) {
        ByteArrayOutputStream out = null;
        if (img != null) {
            out = new ByteArrayOutputStream();
            try {
                ImageIO.write(img, "png", out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (out != null) {
            return out.toByteArray();
        }
        return null;
    }
}
