package com.xp.render;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.jfinal.kit.LogKit;
import com.jfinal.render.Render;
import com.jfinal.render.RenderException;
import com.xp.util.barcode.BarCodeUtil;

public class BarcodeRender extends Render{
	private String text;
	private int width;
	private int height;
	
	public BarcodeRender(String text,int width,int height){
		this.text = text;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void render() {
		response.setHeader("Pragma","no-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		
        //二维码的图片格式  
        Hashtable<EncodeHintType,Object> hints = new Hashtable<>();  
        //内容所使用编码  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.MARGIN, 0);
        BitMatrix bitMatrix = null;
		try {
			bitMatrix = new MultiFormatWriter().encode(text,  
			        BarcodeFormat.QR_CODE, width, height, hints);
			bitMatrix = updateBit(bitMatrix, 0);
		} catch (WriterException e) {
			e.printStackTrace();
		}  

		ServletOutputStream sos = null;
		//生成二维码 流
		BufferedImage image = BarCodeUtil.toBufferedImage(bitMatrix);
		try {
			sos = response.getOutputStream();
			ImageIO.write(image, "jpeg", sos);
		} catch (IOException e) {
			if (getDevMode()) {
				throw new RenderException(e);
			}
		} catch (Exception e) {
			throw new RenderException(e);
		} finally {
			if (sos != null) {
				try {sos.close();} catch (IOException e) {LogKit.logNothing(e);}
			}
		}
	}

	private BitMatrix updateBit(BitMatrix matrix, int margin){
        int tempM = margin*2;
       int[] rec = matrix.getEnclosingRectangle();   //获取二维码图案的属性
            int resWidth = rec[2] + tempM;
            int resHeight = rec[3] + tempM;
            BitMatrix resMatrix = new BitMatrix(resWidth, resHeight); // 按照自定义边框生成新的BitMatrix
            resMatrix.clear();
        for(int i= margin; i < resWidth- margin; i++){   //循环，将二维码图案绘制到新的bitMatrix中
            for(int j=margin; j < resHeight-margin; j++){
                if(matrix.get(i-margin + rec[0], j-margin + rec[1])){
                    resMatrix.set(i,j);
                }
            }
        }
         return resMatrix;
    }
}
