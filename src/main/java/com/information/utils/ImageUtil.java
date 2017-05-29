package com.information.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.information.constant.CommonConstant;
import com.jfinal.render.Render;
/**
 * 生成验证码
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年4月22日 下午4:54:32
 */
@SuppressWarnings("unused")
public class ImageUtil extends Render{
	
    public static final int WIDTH=114;
    
    public static final int HEIGHT=46;
    
	private Color color=new Color(255, 200, 188);
	
	@Override
	public void render() {
		BufferedImage image=new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics graphics =image.getGraphics();
		graphics.setColor(color);
		setBackground(graphics);//设置背景
		setBorder(graphics);
		setRandomLine(graphics);
		String num=serRandomNum(graphics);
		request.getSession().setAttribute(CommonConstant.IMAGE_CODE, num);
		response.setHeader("Pragma","no-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		try {
			ImageIO.write(image, "jpg", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成随机数
	 * @param graphics
	 * @return
	 */
	private String serRandomNum(Graphics graphics) {
		graphics.setFont(new Font("",Font.PLAIN, 22));
		String number="ABCDEFGHIJKLMNRSTZXOPQWY";
		int x=8;
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<4;i++){
			graphics.setColor(new Color(new Random().nextInt(80),new Random().nextInt(120),new Random().nextInt(200)));
			String c=number.charAt(new Random().nextInt(number.length()))+"";
			sb.append(c);
			graphics.drawString(c, x, 28);
			x+=28;
		}
		return sb.toString();
	}

	/**
	 * 设置线条
	 * @param graphics
	 */
	private void setRandomLine(Graphics graphics) {
		graphics.setColor(Color.BLUE);
		for(int i=0;i<5;i++){
			int x1=new Random().nextInt(WIDTH);
			int x2=new Random().nextInt(WIDTH);
			int y1=new Random().nextInt(HEIGHT);
			int y2=new Random().nextInt(HEIGHT);
			graphics.drawLine(x1, y1, x2, y2);
		}
	}

	/**
	 * 设置边框
	 * @param graphics
	 */
	private void setBorder(Graphics graphics) {
		graphics.setColor(color);
		graphics.drawRect(1, 1, WIDTH-2, HEIGHT-2);
	}

	/**
	 * 设置背景颜色
	 * @param graphics
	 */
	private void setBackground(Graphics graphics) {
		graphics.setColor(color);
		graphics.fillRect(0,0, WIDTH, HEIGHT);
	}

}
