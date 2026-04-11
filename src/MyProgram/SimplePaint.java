package MyProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class SimplePaint extends JFrame {
    private int startX, startY, lastX, lastY;
    private Color currentColor = Color.BLACK;
    private int brushSize = 5;
    private String currentTool = "pencil"; // "pencil", "line", "straightLine", "fill"

    private BufferedImage canvas;
    private JPanel drawPanel;
    private BufferedImage tempCanvas; // Временный холст для предпросмотра

    // Храним ссылки на кнопки для подсветки
    private JButton currentColorBtn = null;
    private JButton currentSizeBtn = null;
    private JButton currentToolBtn = null;

    // Храним ссылки на кнопки толщины чтобы управлять их видимостью
    private JButton thinBtn, mediumBtn, thickBtn, bigBtn, verybigBtn;

    public SimplePaint() {
        setTitle("рисовалка");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Создаём холст
        drawPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (canvas != null) {
                    g.drawImage(canvas, 0, 0, null);
                }
                // Рисуем временный холст поверх (для предпросмотра)
                if (tempCanvas != null) {
                    g.drawImage(tempCanvas, 0, 0, null);
                }
            }
        };

        drawPanel.setBackground(Color.WHITE);

        // Инициализируем холст при изменении размера
        drawPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                initCanvas();
                initTempCanvas();
            }
        });

        // Обработчики мыши
        drawPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startX = e.getX();
                startY = e.getY();
                lastX = startX;
                lastY = startY;

                if (currentTool.equals("fill")) {
                    floodFill(e.getX(), e.getY(), currentColor);
                    drawPanel.repaint();
                } else if (currentTool.equals("pencil")) {
                    drawPoint(e.getX(), e.getY());
                    drawPanel.repaint();
                }
                // Для линий пока ничего не делаем - ждём отпускания мыши
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (currentTool.equals("line") || currentTool.equals("straightLine")) {
                    // Когда отпускаем мышь - рисуем финальную линию
                    int endX = e.getX();
                    int endY = e.getY();

                    if (currentTool.equals("straightLine")) {
                        // Для прямой линии выравниваем
                        int[] alignedPoints = alignLine(startX, startY, endX, endY);
                        endX = alignedPoints[0];
                        endY = alignedPoints[1];
                    }

                    drawLine(startX, startY, endX, endY);
                    clearTempCanvas(); // Очищаем предпросмотр
                    drawPanel.repaint();
                }
            }
        });

        drawPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (currentTool.equals("pencil")) {
                    drawLine(lastX, lastY, e.getX(), e.getY());
                    lastX = e.getX();
                    lastY = e.getY();
                    drawPanel.repaint();
                } else if (currentTool.equals("line")) {
                    // Показываем предпросмотр свободной линии
                    drawTempLine(startX, startY, e.getX(), e.getY());
                    drawPanel.repaint();
                } else if (currentTool.equals("straightLine")) {
                    // Показываем предпросмотр прямой линии
                    int endX = e.getX();
                    int endY = e.getY();

                    // Выравниваем для предпросмотра
                    int[] alignedPoints = alignLine(startX, startY, endX, endY);
                    endX = alignedPoints[0];
                    endY = alignedPoints[1];

                    drawTempLine(startX, startY, endX, endY);
                    drawPanel.repaint();
                }
            }
        });

        // ОСНОВНАЯ ПАНЕЛЬ ИНСТРУМЕНТОВ
        JPanel mainToolPanel = new JPanel();
        mainToolPanel.setLayout(new BorderLayout());

        // Верхняя панель - инструменты и цвета
        JPanel topPanel = new JPanel();

        // Кнопки инструментов
        JButton pencilBtn = new JButton("Карандаш");
        JButton lineBtn = new JButton("Линия");
        JButton straightLineBtn = new JButton("Прямая линия");
        JButton fillBtn = new JButton("Заливка");
        JButton clearBtn = new JButton("Очистить");

        // Кнопки цветов
        JButton blackBtn = new JButton("Чёрный");
        JButton redBtn = new JButton("Красный");
        JButton blueBtn = new JButton("Синий");
        JButton greenBtn = new JButton("Зелёный");
        JButton whiteBtn = new JButton("Ластик");

        // Нижняя панель - толщина кисти
        JPanel sizePanel = new JPanel();

        // Кнопки толщины кисти (сохраняем ссылки)
        thinBtn = new JButton("Тонкая");
        mediumBtn = new JButton("Средняя");
        thickBtn = new JButton("Толстая");
        bigBtn = new JButton("Большая");
        verybigBtn = new JButton("Очень большая");

        // Обработчики инструментов
        pencilBtn.addActionListener(e -> {
            currentTool = "pencil";
            updateToolButton(pencilBtn);
            showSizeButtons(true);
        });

        lineBtn.addActionListener(e -> {
            currentTool = "line";
            updateToolButton(lineBtn);
            showSizeButtons(true);
        });

        straightLineBtn.addActionListener(e -> {
            currentTool = "straightLine";
            updateToolButton(straightLineBtn);
            showSizeButtons(true);
        });

        fillBtn.addActionListener(e -> {
            currentTool = "fill";
            updateToolButton(fillBtn);
            showSizeButtons(false);
        });

        clearBtn.addActionListener(e -> {
            initCanvas();
            initTempCanvas();
            drawPanel.repaint();
        });

        // Обработчики цветов
        blackBtn.addActionListener(e -> {
            currentColor = Color.BLACK;
            updateColorButton(blackBtn);
        });

        redBtn.addActionListener(e -> {
            currentColor = Color.RED;
            updateColorButton(redBtn);
        });

        blueBtn.addActionListener(e -> {
            currentColor = Color.BLUE;
            updateColorButton(blueBtn);
        });

        greenBtn.addActionListener(e -> {
            currentColor = Color.GREEN;
            updateColorButton(greenBtn);
        });

        whiteBtn.addActionListener(e -> {
            currentColor = Color.WHITE;
            updateColorButton(whiteBtn);
        });

        // Обработчики толщины
        thinBtn.addActionListener(e -> {
            brushSize = 2;
            updateSizeButton(thinBtn);
        });

        mediumBtn.addActionListener(e -> {
            brushSize = 5;
            updateSizeButton(mediumBtn);
        });

        thickBtn.addActionListener(e -> {
            brushSize = 10;
            updateSizeButton(thickBtn);
        });

        bigBtn.addActionListener(e -> {
            brushSize = 20;
            updateSizeButton(bigBtn);
        });

        verybigBtn.addActionListener(e -> {
            brushSize = 30;
            updateSizeButton(verybigBtn);
        });

        // Добавляем инструменты и цвета на верхнюю панель
        topPanel.add(new JLabel("Инструменты:"));
        topPanel.add(pencilBtn);
        topPanel.add(lineBtn);
        topPanel.add(straightLineBtn);
        topPanel.add(fillBtn);
        topPanel.add(new JLabel("Цвета:"));
        topPanel.add(blackBtn);
        topPanel.add(redBtn);
        topPanel.add(blueBtn);
        topPanel.add(greenBtn);
        topPanel.add(whiteBtn);
        topPanel.add(clearBtn);

        // Добавляем кнопки толщины на нижнюю панель
        sizePanel.add(new JLabel("Толщина кисти:"));
        sizePanel.add(thinBtn);
        sizePanel.add(mediumBtn);
        sizePanel.add(thickBtn);
        sizePanel.add(bigBtn);
        sizePanel.add(verybigBtn);

        // Собираем главную панель
        mainToolPanel.add(topPanel, BorderLayout.NORTH);
        mainToolPanel.add(sizePanel, BorderLayout.SOUTH);

        // Компоновка
        setLayout(new BorderLayout());
        add(mainToolPanel, BorderLayout.NORTH);
        add(drawPanel, BorderLayout.CENTER);

        // Инициализируем холст и подсветку по умолчанию
        initCanvas();
        initTempCanvas();
        updateToolButton(pencilBtn);
        updateColorButton(blackBtn);
        updateSizeButton(mediumBtn);
    }

    // НОВЫЙ МЕТОД: Выравнивание линии по горизонтали, вертикали или диагонали
    private int[] alignLine(int startX, int startY, int endX, int endY) {
        int dx = Math.abs(endX - startX);
        int dy = Math.abs(endY - startY);

        // Определяем ближайшее направление с порогом
        if (dx > dy * 2) {
            // Горизонтальная линия (движение по X значительно больше чем по Y)
            endY = startY;
        } else if (dy > dx * 2) {
            // Вертикальная линия (движение по Y значительно больше чем по X)
            endX = startX;
        } else {
            // Диагональная линия 45° - делаем одинаковое смещение по X и Y
            int delta = Math.min(dx, dy);
            if (endX > startX) {
                endX = startX + delta;
            } else {
                endX = startX - delta;
            }
            if (endY > startY) {
                endY = startY + delta;
            } else {
                endY = startY - delta;
            }
        }

        return new int[]{endX, endY};
    }

    // Метод для показа/скрытия кнопок толщины
    private void showSizeButtons(boolean show) {
        thinBtn.setEnabled(show);
        mediumBtn.setEnabled(show);
        thickBtn.setEnabled(show);
        bigBtn.setEnabled(show);
        verybigBtn.setEnabled(show);

        if (show) {
            // Показываем толщину - включаем подсветку текущей кнопки
            updateSizeButton(currentSizeBtn);
        } else {
            // Скрываем толщину - убираем подсветку со всех кнопок
            thinBtn.setBackground(null);
            mediumBtn.setBackground(null);
            thickBtn.setBackground(null);
            bigBtn.setBackground(null);
            verybigBtn.setBackground(null);
        }
    }

    // Методы для подсветки кнопок
    private void updateToolButton(JButton activeTool) {
        if (currentToolBtn != null) {
            currentToolBtn.setBackground(null);
        }
        currentToolBtn = activeTool;
        currentToolBtn.setBackground(Color.GRAY);
    }

    private void updateColorButton(JButton activeColor) {
        if (currentColorBtn != null) {
            currentColorBtn.setBackground(null);
        }
        currentColorBtn = activeColor;
        currentColorBtn.setBackground(Color.GRAY);
    }

    private void updateSizeButton(JButton activeSize) {
        if (currentSizeBtn != null) {
            currentSizeBtn.setBackground(null);
        }
        currentSizeBtn = activeSize;
        // Подсвечиваем только если выбран карандаш или линия
        if (currentTool.equals("pencil") || currentTool.equals("line") || currentTool.equals("straightLine")) {
            currentSizeBtn.setBackground(Color.GRAY);
        }
    }

    private void initCanvas() {
        if (drawPanel.getWidth() > 0 && drawPanel.getHeight() > 0) {
            canvas = new BufferedImage(drawPanel.getWidth(), drawPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = canvas.createGraphics();
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            g2d.dispose();
        }
    }

    private void initTempCanvas() {
        if (drawPanel.getWidth() > 0 && drawPanel.getHeight() > 0) {
            tempCanvas = new BufferedImage(drawPanel.getWidth(), drawPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
            clearTempCanvas();
        }
    }

    private void clearTempCanvas() {
        if (tempCanvas != null) {
            Graphics2D g2d = tempCanvas.createGraphics();
            g2d.setComposite(AlphaComposite.Clear);
            g2d.fillRect(0, 0, tempCanvas.getWidth(), tempCanvas.getHeight());
            g2d.setComposite(AlphaComposite.SrcOver);
            g2d.dispose();
        }
    }

    private void drawPoint(int x, int y) {
        Graphics2D g2d = canvas.createGraphics();
        g2d.setColor(currentColor);
        g2d.setStroke(new BasicStroke(brushSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine(x, y, x, y);
        g2d.dispose();
    }

    private void drawLine(int x1, int y1, int x2, int y2) {
        Graphics2D g2d = canvas.createGraphics();
        g2d.setColor(currentColor);
        g2d.setStroke(new BasicStroke(brushSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine(x1, y1, x2, y2);
        g2d.dispose();
    }

    private void drawTempLine(int x1, int y1, int x2, int y2) {
        clearTempCanvas();
        Graphics2D g2d = tempCanvas.createGraphics();
        g2d.setColor(currentColor);
        g2d.setStroke(new BasicStroke(brushSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine(x1, y1, x2, y2);
        g2d.dispose();
    }

    private void floodFill(int startX, int startY, Color newColor) {
        if (canvas == null) return;

        int targetColor = canvas.getRGB(startX, startY);
        int replacementColor = newColor.getRGB();

        if (targetColor == replacementColor) return;

        java.util.Queue<Point> queue = new java.util.LinkedList<>();
        queue.add(new Point(startX, startY));

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int x = p.x;
            int y = p.y;

            if (x < 0 || y < 0 || x >= canvas.getWidth() || y >= canvas.getHeight())
                continue;

            if (canvas.getRGB(x, y) != targetColor)
                continue;

            canvas.setRGB(x, y, replacementColor);

            queue.add(new Point(x + 1, y));
            queue.add(new Point(x - 1, y));
            queue.add(new Point(x, y + 1));
            queue.add(new Point(x, y - 1));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SimplePaint().setVisible(true);
        });
    }
}