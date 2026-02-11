import javax.swing.*;
import java.awt.*;

public class ColorfulTrueFern extends JPanel {

    // Глибина рекурсії
    private int K = 10;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Згладжування
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Чорний фон для контрасту
        this.setBackground(Color.BLACK);

        double startX = getWidth() / 2.0;
        double startY = getHeight() - 20;
        double initialLength = getHeight() / 5.5;
        double startAngle = -Math.PI / 2.0;

        // Починаємо з зеленого кольору (hue = 0.33)
        drawFern(g2d, startX, startY, initialLength, startAngle, K, 0.33f);
    }

    // Додаємо параметр 'hue' (відтінок) у рекурсію
    private void drawFern(Graphics2D g, double x, double y, double length, double angle, int depth, float hue) {
        if (depth == 0) return;

        double xEnd = x + length * Math.cos(angle);
        double yEnd = y + length * Math.sin(angle);

        // Встановлюємо колір для поточного сегмента ("листка")
        // hue % 1.0f гарантує, що значення завжди буде в межах 0..1
        g.setColor(Color.getHSBColor(hue % 1.0f, 1.0f, 1.0f));

        // Товщина лінії
        g.setStroke(new BasicStroke(depth > 4 ? 2 : 1));

        g.drawLine((int)x, (int)y, (int)xEnd, (int)yEnd);

        // --- РЕКУРСІЯ З РІЗНИМИ КОЛЬОРАМИ ---

        // 1. ЦЕНТРАЛЬНА ГІЛКА (продовження стебла)
        // Колір трохи змінюється, щоб був градієнт
        drawFern(g, xEnd, yEnd, length * 0.85, angle + 0.04, depth - 1, hue + 0.02f);

        // 2. ЛІВА ГІЛКА ("листок")
        // Кардинально інший колір (зсув на 0.3)
        drawFern(g, xEnd, yEnd, length * 0.35, angle - 1.4, depth - 1, hue + 0.3f);

        // 3. ПРАВА ГІЛКА ("листок")
        // Ще інший колір (зсув на 0.6)
        drawFern(g, xEnd, yEnd, length * 0.35, angle + 1.4, depth - 1, hue + 0.6f);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Лабораторна №5: Різнобарвна Папороть");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800);

        ColorfulTrueFern panel = new ColorfulTrueFern();
        frame.add(panel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}