package cart.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/user/authcode")
public class AuthCodeServlet extends HttpServlet {

	// 自訂認證碼 0~9 a-z A-Z
	private String generateAuthCode() {
		String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer authcode = new StringBuffer();
		Random random = new Random();
		for(int i=0;i<4;i++) {
			int index = random.nextInt(chars.length()); // 隨機取位置
			authcode.append(chars.charAt(index)); // 取得該位置的資料
		}
		return authcode.toString();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Random random = new Random();
		//String authcode = String.format("%04d", random.nextInt(10000));
		
		String authcode = generateAuthCode();
		
		HttpSession session = req.getSession(); 
		ImageIO.write(getAuthCodeImage(authcode), "JPEG", resp.getOutputStream());
		
	}

	// 利用 JAVA2D 產生動態圖像
	private BufferedImage getAuthCodeImage(String authcode) {
		//
		BufferedImage img = new BufferedImage(80, 30, BufferedImage.TYPE_INT_RGB);

		Graphics g = img.getGraphics();

		g.setColor(Color.YELLOW);

		g.fillRect(0, 0, 80, 30);

		g.setColor(Color.BLACK);

		g.setFont(new Font("Arial", Font.BOLD, 22));

		g.drawString(authcode, 18, 22);
		
		g.setColor(Color.RED);
		Random random = new Random();
		for(int i = 0 ; i<10;i++) {
			int x1 = random.nextInt(80);
			int y1 = random.nextInt(30);
			int x2 = random.nextInt(80);
			int y2 = random.nextInt(30);
			g.drawLine(x1, y1, x2, y2);
		}
		
		return img;
	}

}
