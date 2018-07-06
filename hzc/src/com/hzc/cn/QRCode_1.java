package com.hzc.cn;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class QRCode_1 {
	public static void main(String[] args) throws IOException {
		String str = "BEGIN:VCARD\r\n" + 
		   "FN:姓名:贺子朝\r\n"+
		   "EMAIL;HOME:1798778989@qq.com\r\n" + 
		   "PHOTO;VALUE=1.jpg\r\n" + 
		   "TITLE:学生\r\n" + 
		   "TEL;HOME;VOICE:12345678911\r\n"+
		   "URL:http://www.baidu.com\r\n"+
		   "EMAIL;HOME:1798778989@qq.com\r\n" + 
		   "PHOTO;VALUE=.....\r\n" + 
		   "END:VCARD";
		createQrcode(15, str, "2.jpg", 255, 0, 100, 0,200, 255);
	}

	public static void createQrcode(int version, String str, String path,
			int startR, int startG, int startB, int endR, int endG, int endB) throws IOException {
		Qrcode qrcode = new Qrcode();
		qrcode.setQrcodeVersion(version);
		qrcode.setQrcodeErrorCorrect('B');
		int imgSize = 67 + (version - 1) * 12;
		BufferedImage bi = new BufferedImage(imgSize, imgSize,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D gs = bi.createGraphics();
		gs.setBackground(Color.WHITE);
		gs.clearRect(0, 0, imgSize, imgSize);
		boolean[][] calQrcode = qrcode.calQrcode(str.getBytes("utf-8"));
		Color color;
		int pixoff = 2;
		for (int i = 0; i < calQrcode.length; i++) {
			for (int j = 0; j < calQrcode[i].length; j++) {
				int r = startR + (endR - startR) * (i + 1) / calQrcode.length;
				int g = startG + (endG - startG) * (i + 1) / calQrcode.length;
				int b = startB + (endB - startB) * (i + 1) / calQrcode.length;
				color = new Color(r, g, b);
				gs.setColor(color);
				if (calQrcode[i][j]) {
					gs.fillRect(i * 3 + pixoff, j * 3 + pixoff, 3, 3);
				}
			}
		}
		int size = imgSize /5;
		int len = imgSize/2-size/2;
		System.out.println(len);
		try {
			Image img = ImageIO.read(new File("1.jpg"));
			gs.drawImage(img,len,len,size,size,null);
			bi.flush();
			gs.dispose();
			
			ImageIO.write(bi, "jpg", new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
