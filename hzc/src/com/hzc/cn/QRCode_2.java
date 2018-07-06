package com.hzc.cn;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;

public class QRCode_2 {
	public static void main(String[] args) throws UnsupportedEncodingException {
		File file = new File("2.jpg");
		BufferedImage read = null;
		try {
			read = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		QRCodeDecoder qrCodeDecoder = new QRCodeDecoder();
		byte[] decode = qrCodeDecoder.decode(new MyQRCodeImage(read));
		String str1 = new String(decode,"utf-8");
		System.out.println(str1);
	}
}
