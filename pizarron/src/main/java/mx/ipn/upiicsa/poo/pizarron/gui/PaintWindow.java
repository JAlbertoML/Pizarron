package mx.ipn.upiicsa.poo.pizarron.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import mx.ipn.upiicsa.poo.pizarron.dao.BlackboardDao;
import mx.ipn.upiicsa.poo.pizarron.exception.DrawingException;
import mx.ipn.upiicsa.poo.pizarron.model.Circle;
import mx.ipn.upiicsa.poo.pizarron.model.Line;
import mx.ipn.upiicsa.poo.pizarron.model.Pencil;
import mx.ipn.upiicsa.poo.pizarron.model.Rectangle;
import mx.ipn.upiicsa.poo.pizarron.model.Shape;
import mx.ipn.upiicsa.poo.pizarron.model.Square;
import mx.ipn.upiicsa.poo.pizarron.model.Text;
import mx.ipn.upiicsa.poo.pizarron.model.Triangle;

public class PaintWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Integer UNSELECTED_TOOL = -1;
	private static final Integer BTN_CIRCLE = 0;
	private static final Integer BTN_TRIANGLE = 1;
	private static final Integer BTN_SQUARE = 2;
	private static final Integer BTN_RECTANGLE = 3;
	private static final Integer BTN_POLYGON = 4;
	private static final Integer BTN_TEXT = 5;
	private static final Integer BTN_IMAGE = 6;
	private static final Integer BTN_PENCIL = 7;
	private static final Integer BTN_SELECT = 8;
	private static final Integer BTN_LINE = 9;

	private static final Integer BTN_RED = 0;
	private static final Integer BTN_BLACK = 1;
	private static final Integer BTN_BLUE = 2;
	private static final Integer BTN_CYAN = 3;
	private static final Integer BTN_DARK_GRAY = 4;
	private static final Integer BTN_GRAY = 5;
	private static final Integer BTN_GREEN = 6;
	private static final Integer BTN_LIGTH_GRAY = 7;
	private static final Integer BTN_MAGENTA = 8;
	private static final Integer BTN_ORANGE = 9;
	private static final Integer BTN_PINK = 10;
	private static final Integer BTN_WHITE = 11;
	private static final Integer BTN_YELOW = 12;
	private static final Integer BTN_COLORS = 13;

	private static final Integer BTN_BORDER_2 = 0;
	private static final Integer BTN_BORDER_5 = 1;
	private static final Integer BTN_BORDER_10 = 2;
	private static final Integer BTN_BORDER_15 = 3;
	private static final Integer BTN_BORDER_20 = 4;
	private static final Integer BTN_BORDER_OTRO = 5;
	private static final Integer BTN_BORDER_COLOR = 6;

	private JPanel toolPanel;
	private JPanel dashboardPanel;
	private JPanel logPanel;
	private JPanel shapePanel;
	private JPanel colorPanel;
	private JPanel borderSizePanel;
	/*private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private JMenu submenu;*/

	private JButton[] toolButton = new JButton[] { new JButton(new ImageIcon(processIcon("img/circle.png"))),
			new JButton(new ImageIcon(processIcon("img/triangle.png"))),
			new JButton(new ImageIcon(processIcon("img/square.png"))),
			new JButton(new ImageIcon(processIcon("img/rectangle.png"))),
			new JButton(new ImageIcon(processIcon("img/polygon.png"))),
			new JButton(new ImageIcon(processIcon("img/text.png"))),
			new JButton(new ImageIcon(processIcon("img/image.png"))),
			new JButton(new ImageIcon(processIcon("img/pencil.png"))),
			new JButton(new ImageIcon(processIcon("img/select.png"))),
			new JButton(new ImageIcon(processIcon("img/line.png"))) };

	private JButton[] colorButton = new JButton[] { new JButton(), new JButton(), new JButton(), new JButton(),
			new JButton(), new JButton(), new JButton(), new JButton(), new JButton(), new JButton(), new JButton(),
			new JButton(), new JButton(), new JButton(new ImageIcon(processIcon("img/color.png"))) };

	private JButton[] borderButton = new JButton[] { new JButton(new ImageIcon("img/stroke2.png")),
			new JButton(new ImageIcon("img/stroke5.png")), new JButton(new ImageIcon("img/stroke10.png")),
			new JButton(new ImageIcon("img/stroke15.png")), new JButton(new ImageIcon("img/stroke20.png")),
			new JButton("Stroke chooser"), new JButton("Border Color") };

	private Integer selectedTool = UNSELECTED_TOOL;
	private Boolean drawingStateActive = false;
	private Boolean selectedStateActive = false;
	private Integer shapeSelected = null;
	private Boolean resizingStateActive = false;
	private Boolean isResizingArea = false;

	private Shape pencilTmp;
	private Shape shapeTmp;
	private Integer sideResize;

	private Color selectedButtonColor = new Color(184, 207, 229);

	private List<Shape> shapeList = new ArrayList<Shape>();

	BlackboardDao dao = new BlackboardDao();

	public PaintWindow() {
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(1000, 600));
		setTitle("Paint chafisimo");
		instantiateComponents();
		//buildMenu();
		buildLayout();
		initializeListener();
	}

	private void instantiateComponents() {
		toolPanel = new JPanel();
		toolPanel.setBackground(Color.DARK_GRAY);
		dashboardPanel = new JPanel();
		dashboardPanel.setBackground(Color.WHITE);
		logPanel = new JPanel();
		borderSizePanel = new JPanel();
		unselectButtons();
		colorButton[BTN_RED].setBackground(Color.RED);
		colorButton[BTN_BLACK].setBackground(Color.BLACK);
		colorButton[BTN_BLUE].setBackground(Color.BLUE);
		colorButton[BTN_CYAN].setBackground(Color.CYAN);
		colorButton[BTN_DARK_GRAY].setBackground(Color.DARK_GRAY);
		colorButton[BTN_GRAY].setBackground(Color.GRAY);
		colorButton[BTN_GREEN].setBackground(Color.GREEN);
		colorButton[BTN_LIGTH_GRAY].setBackground(Color.LIGHT_GRAY);
		colorButton[BTN_MAGENTA].setBackground(Color.MAGENTA);
		colorButton[BTN_ORANGE].setBackground(Color.ORANGE);
		colorButton[BTN_PINK].setBackground(Color.PINK);
		colorButton[BTN_WHITE].setBackground(Color.WHITE);
		colorButton[BTN_YELOW].setBackground(Color.YELLOW);
		colorButton[BTN_COLORS].setBackground(Color.WHITE);
		shapePanel = new JPanel();
		colorPanel = new JPanel();
		/*menuBar = new JMenuBar();
		menu = new JMenu("File");
		menuItem = new JMenuItem();*/
	}

	/*private void buildMenu() {
		menuBar.add(menu);
		submenu = new JMenu("Open diagram");
		submenu.setIcon(new ImageIcon(processIcon("img/open.png")));
		List<Diagram> diagramList;
		diagramList = dao.getDiagrams();
		if (diagramList != null) {
			for (Diagram d : diagramList) {
				menuItem = new JMenuItem(d.getName(), new ImageIcon(processIcon("img/diagram.png")));
				menuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("Abriendo " + d.getName());
					}
				});
				submenu.add(menuItem);
			}
		}
		
		menu.add(submenu);
		menu.addSeparator();
		menuItem = new JMenuItem("Save diagram", new ImageIcon(processIcon("img/save.png")));
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Saving");
				String name = JOptionPane.showInputDialog(null, "Define un nombre para el diagrama");
				Date creationDate = new Date();
				creationDate.getTime();
				Gson gson = new Gson();
				String diagramJson = gson.toJson(shapeList);
				// TODO solucionar error del json
				Diagram diagram = new Diagram(name, "Diagrama", creationDate, creationDate, diagramJson);
				dao.saveDiagram(diagram);
				JOptionPane.showMessageDialog(null, "Diagram saved");
			}
		});
		menu.add(menuItem);
		submenu = new JMenu("Export diagram as...");
		submenu.setIcon(new ImageIcon(processIcon("img/export.png")));
		menuItem = new JMenuItem("Imagen png", new ImageIcon(processIcon("img/imageIcon.png")));
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Exportando como PNG");
				//TODO Cuando se solucione el problema del json, utilizarlo para exportar
			}
		});
		submenu.add(menuItem);
		menuItem = new JMenuItem("Imagen jpg", new ImageIcon(processIcon("img/imageIcon.png")));
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Exportando como JPG");
				//TODO Cuando se solucione el problema del json, utilizarlo para exportar
			}
		});
		submenu.add(menuItem);
		menu.add(submenu);
		menu.addSeparator();
		menuBar.add(menu);
		submenu = new JMenu("Remove diagram from database");
		submenu.setIcon(new ImageIcon(processIcon("img/remove.png")));
		if (diagramList != null) {
			for (Diagram d : diagramList) {
				menuItem = new JMenuItem(d.getName(), new ImageIcon(processIcon("img/diagram.png")));
				menuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("Eliminando " + d.getName());
						dao.removeDiagram(d);
						JOptionPane.showMessageDialog(null, "Diagram deleted");
					}
				});
				submenu.add(menuItem);
			}
		}
		menu.add(submenu);
		setJMenuBar(menuBar);
	}*/

	private void buildLayout() {
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());
		toolPanel.setLayout(new GridLayout(3, 1));
		shapePanel.setLayout(new GridLayout(5, 2));
		colorPanel.setLayout(new GridLayout(5, 4));
		borderSizePanel.setLayout(new GridLayout(7, 1));

		for (int i = 0; i < borderButton.length; i++) {
			borderSizePanel.add(borderButton[i]);
			borderButton[i].setFocusable(false);
		}

		for (int i = 0; i < colorButton.length; i++) {
			colorPanel.add(colorButton[i]);
			colorButton[i].setFocusable(false);
		}
		for (int i = 0; i < toolButton.length; i++) {
			shapePanel.add(toolButton[i]);
			toolButton[i].setFocusable(false);
		}
		toolPanel.add(shapePanel);
		toolPanel.add(colorPanel);
		toolPanel.add(borderSizePanel);
		pane.add(toolPanel, BorderLayout.WEST);
		pane.add(dashboardPanel, BorderLayout.CENTER);
		pane.add(logPanel, BorderLayout.SOUTH);
	}

	private void unselectButtons() {
		for (int i = 0; i < toolButton.length; i++) {
			toolButton[i].setBackground(Color.WHITE);
		}
		for (int i = 0; i < borderButton.length; i++) {
			borderButton[i].setBackground(Color.white);
		}
	}

	private void initializeListener() {

		toolButton[BTN_CIRCLE].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedTool = BTN_CIRCLE;
				unselectButtons();
				toolButton[BTN_CIRCLE].setBackground(selectedButtonColor);
			}
		});

		toolButton[BTN_TRIANGLE].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedTool = BTN_TRIANGLE;
				unselectButtons();
				toolButton[BTN_TRIANGLE].setBackground(selectedButtonColor);
			}
		});

		toolButton[BTN_SQUARE].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedTool = BTN_SQUARE;
				unselectButtons();
				toolButton[BTN_SQUARE].setBackground(selectedButtonColor);
			}
		});

		toolButton[BTN_RECTANGLE].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedTool = BTN_RECTANGLE;
				unselectButtons();
				toolButton[BTN_RECTANGLE].setBackground(selectedButtonColor);
			}
		});

		toolButton[BTN_POLYGON].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedTool = BTN_POLYGON;
				unselectButtons();
				toolButton[BTN_POLYGON].setBackground(selectedButtonColor);
			}
		});

		toolButton[BTN_TEXT].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedTool = BTN_TEXT;
				unselectButtons();
				toolButton[BTN_TEXT].setBackground(selectedButtonColor);
			}
		});

		toolButton[BTN_IMAGE].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedTool = BTN_IMAGE;
				unselectButtons();
				toolButton[BTN_IMAGE].setBackground(selectedButtonColor);
			}
		});

		toolButton[BTN_PENCIL].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedTool = BTN_PENCIL;
				unselectButtons();
				toolButton[BTN_PENCIL].setBackground(selectedButtonColor);
			}
		});

		toolButton[BTN_LINE].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedTool = BTN_LINE;
				unselectButtons();
				toolButton[BTN_LINE].setBackground(selectedButtonColor);
			}
		});

		toolButton[BTN_SELECT].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedTool = BTN_SELECT;
				drawingStateActive = false;
				unselectButtons();
				toolButton[BTN_SELECT].setBackground(selectedButtonColor);
			}
		});

		colorButton[BTN_RED].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedStateActive) {
					changeFillColor(Color.RED);
				}
			}
		});

		colorButton[BTN_BLACK].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedStateActive) {
					changeFillColor(Color.BLACK);
				}
			}
		});

		colorButton[BTN_BLUE].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedStateActive) {
					changeFillColor(Color.BLUE);
				}
			}
		});

		colorButton[BTN_CYAN].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedStateActive) {
					changeFillColor(Color.CYAN);
				}
			}
		});

		colorButton[BTN_DARK_GRAY].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedStateActive) {
					changeFillColor(Color.DARK_GRAY);
				}
			}
		});

		colorButton[BTN_GRAY].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedStateActive) {
					changeFillColor(Color.GRAY);
				}
			}
		});

		colorButton[BTN_GREEN].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedStateActive) {
					changeFillColor(Color.GREEN);
				}
			}
		});

		colorButton[BTN_LIGTH_GRAY].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedStateActive) {
					changeFillColor(Color.LIGHT_GRAY);
				}
			}
		});

		colorButton[BTN_MAGENTA].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedStateActive) {
					changeFillColor(Color.MAGENTA);
				}
			}
		});

		colorButton[BTN_ORANGE].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedStateActive) {
					changeFillColor(Color.ORANGE);
				}
			}
		});

		colorButton[BTN_PINK].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedStateActive) {
					changeFillColor(Color.PINK);
				}
			}
		});

		colorButton[BTN_WHITE].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedStateActive) {
					changeFillColor(Color.WHITE);
				}
			}
		});

		colorButton[BTN_YELOW].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedStateActive) {
					changeFillColor(Color.YELLOW);
				}
			}
		});

		colorButton[BTN_COLORS].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedTool == BTN_SELECT && shapeSelected != null) {
					Color color = JColorChooser.showDialog(null, "Selecciona un Color", Color.BLUE);
					changeFillColor(color);
				}
			}
		});

		borderButton[BTN_BORDER_2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeBorderStroke(3);
			}
		});

		borderButton[BTN_BORDER_5].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeBorderStroke(6);
			}
		});

		borderButton[BTN_BORDER_10].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeBorderStroke(11);
			}
		});

		borderButton[BTN_BORDER_15].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeBorderStroke(16);
			}
		});

		borderButton[BTN_BORDER_20].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeBorderStroke(21);
			}
		});

		borderButton[BTN_BORDER_OTRO].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedTool == BTN_SELECT && shapeSelected != null) {
					String tamaño = JOptionPane.showInputDialog("Ingresa el número");
					changeBorderStroke(Integer.parseInt(tamaño));
				}
			}
		});

		borderButton[BTN_BORDER_COLOR].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedTool == BTN_SELECT && shapeSelected != null) {
					Color color = JColorChooser.showDialog(null, "Selecciona un Color", Color.BLUE);
					changeBorderColor(color);
				}
			}
		});

		dashboardPanel.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (drawingStateActive && selectedTool == BTN_PENCIL) {
					pencilTmp = null;
				}

				if (selectedStateActive) {
					shapeTmp = null;
				}

				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (drawingStateActive && selectedTool == BTN_PENCIL) {
					pencilTmp = getShape(e.getX(), e.getY());
				}

				if (isResizingArea) {
					resizingStateActive = true;
				} else {
					resizingStateActive = false;
				}

				if (selectedTool == BTN_SELECT) {
					shapeSelected = getShapeSelected(e);
					if (shapeSelected != null) {
						shapeTmp = shapeList.get(shapeSelected);
						shapeTmp.setSelected(true);
						shapeTmp.paint(dashboardPanel.getGraphics());
						for (int i = 0; i < shapeList.size(); i++) {
							if (i != shapeSelected) {
								removeSelected(i);
							}
						}
					} else {
						for (int i = 0; i < shapeList.size(); i++) {
							removeSelected(i);
							selectedStateActive = false;
						}
					}
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				drawingStateActive = false;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				drawingStateActive = true;
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (drawingStateActive && selectedTool != BTN_SELECT) {
					@SuppressWarnings("unused")
					Shape shape = getShape(e.getX(), e.getY());
					for (int i = 0; i < shapeList.size(); i++) {
						removeSelected(i);
					}
				}
			}
		});

		dashboardPanel.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				if (selectedStateActive) {
					if (isResizingArea(e, shapeList.get(shapeSelected))) {
						isResizingArea = true;
					} else {
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						isResizingArea = false;
					}
				}
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				if (drawingStateActive && selectedTool == BTN_PENCIL) {
					Pencil pencil = (Pencil) pencilTmp;
					pencil.addPoint(e.getX(), e.getY());
					pencil.paint(dashboardPanel.getGraphics());
				}

				if (selectedStateActive && resizingStateActive) {
					eraseShape(shapeList.get(shapeSelected));
					shapeList.get(shapeSelected).resize(dashboardPanel.getGraphics(), e.getX(), e.getY(), sideResize);
				} else if (selectedStateActive) {
					setCursor(new Cursor(Cursor.MOVE_CURSOR));
					moveShape(e.getX(), e.getY());
				}
			}
		});
	}

	public Boolean isResizingArea(MouseEvent e, Shape s) {
		Boolean isResizingArea = false;
		if ((e.getX() < s.getX() && e.getX() > s.getX() - 5)
				&& (e.getY() > s.getY() && e.getY() < s.getY() + s.getHeight())) {
			isResizingArea = true;
			setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
			sideResize = Shape.W_RESIZE;
		} else if ((e.getX() > s.getX() + s.getWidth() && e.getX() < s.getX() + s.getWidth() + 5)
				&& (e.getY() > s.getY() && e.getY() < s.getY() + s.getHeight())) {
			isResizingArea = true;
			setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
			sideResize = Shape.E_RESIZE;
		} else if (((e.getY() < s.getY() && e.getY() > s.getY() - 5)
				&& (e.getX() > s.getX() && e.getX() < s.getX() + s.getWidth()))) {
			isResizingArea = true;
			setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
			sideResize = Shape.N_RESIZE;
		} else if ((e.getY() > s.getY() + s.getHeight() && e.getY() < s.getY() + s.getHeight() + 5)
				&& (e.getX() > s.getX() && e.getX() < s.getX() + s.getWidth())) {
			isResizingArea = true;
			setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
			sideResize = Shape.S_RESIZE;
		}
		return isResizingArea;
	}

	public void removeSelected(Integer index) {
		shapeTmp = shapeList.get(index);
		eraseShape(shapeTmp);
		shapeTmp.setSelected(false);
		shapeTmp.paint(dashboardPanel.getGraphics());
	}

	public void changeFillColor(Color color) {
		shapeTmp = shapeList.get(shapeSelected);
		eraseShape(shapeTmp);
		shapeTmp.setFillColor(color);
		shapeTmp.paint(dashboardPanel.getGraphics());
	}

	public void changeBorderColor(Color color) {
		shapeTmp = shapeList.get(shapeSelected);
		eraseShape(shapeTmp);
		shapeTmp.setBorderColor(color);
		shapeTmp.paint(dashboardPanel.getGraphics());
	}

	public void changeBorderStroke(Integer stroke) {
		shapeTmp = shapeList.get(shapeSelected);
		eraseShape(shapeTmp);
		shapeTmp.setStroke(stroke);
		shapeTmp.paint(dashboardPanel.getGraphics());
	}

	private void moveShape(Integer x, Integer y) {
		shapeTmp = shapeList.get(shapeSelected);
		eraseShape(shapeTmp);
		shapeTmp.setX(x);
		shapeTmp.setY(y);
		shapeTmp.paint(dashboardPanel.getGraphics());
	}

	private void eraseShape(Shape shapeTmp) {
		Shape s = (Shape) shapeTmp.clone();
		s.setSelectColor(Color.WHITE);
		s.setFillColor(Color.WHITE);
		s.setBorderColor(Color.WHITE);
		s.paint(dashboardPanel.getGraphics());
	}

	private Shape getShape(Integer x, Integer y) {
		Shape shape = null;
		String message = "";
		try {
			shape = getShapeDraw(x, y);
			shape.paint(dashboardPanel.getGraphics());
			message = "Shape added";
		} catch (Exception e1) {
			message = "Exception";
		} finally {
			System.out.println(message);
		}
		return shape;
	}

	private Integer getShapeSelected(MouseEvent e) {
		Integer shape = null;
		int i = 0;
		for (Shape s : shapeList) {
			if (e.getX() > s.getX() - 5 && e.getX() < (s.getX() + s.getWidth() + 5)) {
				if (e.getY() > s.getY() - 5 && e.getY() < (s.getY() + s.getHeight() + 5)) {
					System.out.println("seleccionado figura " + i);
					shape = i;
					selectedStateActive = true;
					break;
				}
			}
			i++;
		}
		return shape;
	}

	private Shape getShapeDraw(Integer x, Integer y) throws DrawingException {
		Shape shape = null;
		if (selectedTool == UNSELECTED_TOOL) {
			throw new DrawingException();
		} else if (selectedTool == BTN_CIRCLE) {
			shape = Circle.getDefault(x, y);
			shapeList.add(shape);
		} else if (selectedTool == BTN_TRIANGLE) {
			shape = Triangle.getDefault(x, y);
			shapeList.add(shape);
		} else if (selectedTool == BTN_SQUARE) {
			shape = Square.getDefault(x, y);
			shapeList.add(shape);
		} else if (selectedTool == BTN_RECTANGLE) {
			shape = Rectangle.getDefault(x, y);
			shapeList.add(shape);
		} else if (selectedTool == BTN_POLYGON) {

		} else if (selectedTool == BTN_TEXT) {
			shape = Text.getDefault(x, y);
			shapeList.add(shape);
		} else if (selectedTool == BTN_IMAGE) {
			Image image = selectImage();
			shape = mx.ipn.upiicsa.poo.pizarron.model.Image.getDefault(x, y, image);
			shapeList.add(shape);
		} else if (selectedTool == BTN_PENCIL) {
			shape = Pencil.getDefault(x, y);
			shapeList.add(shape);
			// TODO Las figuras que se agregan despues de el pincel no se pueden seleccionar
			// pues el algoritmo de selección falla con el pincel
		} else if (selectedTool == BTN_LINE) {
			shape = Line.getDefault(x, y);
			shapeList.add(shape);
		}
		return shapeList.get(shapeList.size() - 1);
	}

	private Image processIcon(String pathname) {
		File file = new File(pathname);
		Image image = null;
		try {
			image = ImageIO.read(file);
			image = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		} catch (IOException e) {
		}
		return image;
	}

	private Image selectImage() {
		Image image = null;
		File file;
		String pathname = null;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("Images", "jpg", "gif", "png", "jpeg");
		fileChooser.setFileFilter(imgFilter);
		int result = fileChooser.showOpenDialog(this);
		if (result != JFileChooser.CANCEL_OPTION) {
			File fileName = fileChooser.getSelectedFile();
			if ((fileName == null) || (fileName.getName().equals(""))) {
				pathname = "...";
			} else {
				pathname = fileName.getAbsolutePath();
				file = new File(pathname);
				try {
					image = ImageIO.read(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return image;
	}

	public List<Shape> getShapeList() {
		return shapeList;
	}

	public void setShapeList(ArrayList<Shape> shapesList) {
		this.shapeList = shapesList;
	}

}
