package com.clover.tools.frame.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.JList;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.clover.tools.common.InitColumn;
import com.clover.tools.common.InitFrameData;
import com.clover.tools.common.InitTable;
import com.clover.tools.opt.InsertCode;

import javax.swing.ListSelectionModel;

import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.FlowLayout;

public class MainFrame {
	private JFrame frame;
	private JTable table;
	private JTextField funcNoTxt;
	private JTextField interTxt;
	private JTextField fileUrlTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws Exception
	 */
	public MainFrame() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("serial")
	private void initialize() throws Exception {
		frame = new JFrame();

		// ��ʼ��frame��������
		frame.setTitle("Clover - �Զ�����(Thinkive)");
		frame.setResizable(false);
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((size.width - 900) / 2, (size.height - 600) / 2);

		// ������panel��������ʾ���ݿ�͵�ǰѡ�����ݿ��µ��������ݿ��
		JPanel dbPanel = new JPanel();
		dbPanel.setBorder(new LineBorder(Color.WHITE, 5));
		dbPanel.setBackground(Color.WHITE);
		frame.getContentPane().add(dbPanel, BorderLayout.WEST);
		dbPanel.setLayout(new BorderLayout(0, 0));

		// ��ʼ��JComboBox����ʾ���е����ݿ�
		InitFrameData init = new InitFrameData();
		final List<Map<String, String>> dbList = init.getDbList();
		String[] dbs = new String[dbList.size() + 1];
		dbs[0] = "��ѡ�����ݿ�~~~";
		for (int i = 0; i < dbList.size(); i++) {
			dbs[i + 1] = (i + 1) + ":" + dbList.get(i).get("user").toUpperCase() + "��" + dbList.get(i).get("desc") + "��";
		}
		final JComboBox dbBox = new JComboBox(dbs);
		dbBox.setEditable(true);
		dbPanel.add(dbBox, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(Color.WHITE, 5));
		dbPanel.add(scrollPane, BorderLayout.CENTER);

		final JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);

		// JComboBox��Ӽ����¼�
		dbBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String s = e.getItem().toString();
					InitTable initTb = new InitTable(s, dbList);
					final List<String> tableList = initTb.getTbList();

					list.setModel(new AbstractListModel() {
						String[] values = getValues(tableList);

						public int getSize() {
							return values.length;
						}

						public Object getElementAt(int index) {
							return values[index];
						}

						public String[] getValues(List<String> list) {
							String[] tb = new String[list.size()];
							for (int i = 0; i < tb.length; i++) {
								tb[i] = list.get(i);
							}
							return tb;
						}
					});
				}
			}
		});

		// ��ʾ���ݿ�������-���
		JPanel colPanel = new JPanel();
		frame.getContentPane().add(colPanel, BorderLayout.CENTER);
		colPanel.setLayout(new BorderLayout(0, 0));

		final JScrollPane colScrollPanel = new JScrollPane();
		colPanel.add(colScrollPanel, BorderLayout.CENTER);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBorder(new LineBorder(SystemColor.BLACK, 1, true));
		table.setForeground(Color.BLACK);
		table.setBackground(SystemColor.control);
		table.setCellSelectionEnabled(false);
		table.setColumnSelectionAllowed(false);
		// ע�����ݿ��ѡ���¼�
		list.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
				String dbUser = dbBox.getSelectedItem().toString();
				if (list.getSelectedValue() == null) {
					return;
				}
				String tbName = list.getSelectedValue().toString();
				InitColumn col = new InitColumn(tbName, dbUser, dbList);
				List<Map<String, String>> colList = col.getColList();
				Object[][] cols = new Object[colList.size()][5];
				for (int i = 0; i < colList.size(); i++) {
					cols[i][0] = "N".equals(colList.get(i).get("nullable"));
					cols[i][1] = colList.get(i).get("column");
					cols[i][2] = colList.get(i).get("type");
					cols[i][3] = colList.get(i).get("length");
					cols[i][4] = colList.get(i).get("nullable");
				}
				table.setModel(new DefaultTableModel(cols, new String[] { "����", "����", "����", "����", "�ܷ�Ϊ��" }) {
					@SuppressWarnings("rawtypes")
					Class[] columnTypes = new Class[] { Boolean.class, Object.class, Object.class, Object.class, Object.class };

					@SuppressWarnings({ "unchecked", "rawtypes" })
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				DefaultTableCellRenderer render = new DefaultTableCellRenderer();
				render.setHorizontalAlignment(SwingConstants.CENTER);
				table.getColumn("�ܷ�Ϊ��").setCellRenderer(render);
				colScrollPanel.setViewportView(table);
			}
		});

		// �������
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(SystemColor.textInactiveText));
		colPanel.add(panel, BorderLayout.SOUTH);

		// ���ܺ�
		JLabel funcNoLabel = new JLabel("���ܺţ�");
		funcNoTxt = new JTextField();
		funcNoTxt.setText("10002");
		funcNoTxt.setColumns(8);
		panel.add(funcNoLabel);
		panel.add(funcNoTxt);

		// �ӿ�����
		JLabel interLabel = new JLabel("�ӿڣ�");
		interTxt = new JTextField();
		interTxt.setText("InterService");
		interTxt.setColumns(8);
		panel.add(interLabel);
		panel.add(interTxt);

		// ���ɴ����Ŀ¼
		JLabel fileUrlLabel = new JLabel("����Ŀ¼��");
		fileUrlTxt = new JTextField();
		fileUrlTxt.setText("E:\\setting\\code\\");
		fileUrlTxt.setColumns(20);
		panel.add(fileUrlLabel);
		panel.add(fileUrlTxt);

		// ������ť
		JButton addBtn = new JButton("���ɲ������");
		JButton queryBtn = new JButton("���ɲ�ѯ����");
		JButton delBtn = new JButton("����ɾ������");
		JButton updateBtn = new JButton("���ɸ��´���");
		JButton checkAllBtn = new JButton("ȫѡ");
		JButton uncheckAllBtn = new JButton("��ѡ");

		// ��ӵ��������
		JPanel optPanel = new JPanel();
		optPanel.setBackground(new Color(224, 255, 255));
		optPanel.setBorder(new LineBorder(Color.GRAY));
		optPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 8));
		optPanel.add(addBtn);
		optPanel.add(queryBtn);
		optPanel.add(delBtn);
		optPanel.add(updateBtn);
		optPanel.add(checkAllBtn);
		optPanel.add(uncheckAllBtn);
		frame.getContentPane().add(optPanel, BorderLayout.SOUTH);

		// ���밴ťע���¼�
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getRowCount();
				if (row == 0) {
					return;
				}
				String funcNo = funcNoTxt.getText();
				if (funcNo == null || funcNo.equals("")) {
					JOptionPane.showMessageDialog(frame, "���ܺŲ���Ϊ�գ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
					return;
				}
				String inter = interTxt.getText();
				if (inter == null || inter.equals("")) {
					JOptionPane.showMessageDialog(frame, "�ӿ������ֲ���Ϊ�գ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
					return;
				}
				String fileUrl = fileUrlTxt.getText();
				if (fileUrl == null || fileUrl.equals("")) {
					JOptionPane.showMessageDialog(frame, "�ļ�Ŀ¼����Ϊ�գ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
					return;
				}
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				List<Map<String, Object>> tList = new ArrayList<Map<String, Object>>();
				Map<String, Object> tempMap = new HashMap<String, Object>();
				tempMap.put("funcNo", funcNo);
				tempMap.put("inter", inter);
				tempMap.put("fileurl", fileUrl);
				tList.add(tempMap);
				for (int i = 0; i < row; i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("flag", model.getValueAt(i, 0));
					map.put("column", model.getValueAt(i, 1));
					tList.add(map);
				}
				//���ɴ���
				InsertCode.create(tList);
			}
		});

		// ȫѡע���¼�
		checkAllBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getRowCount();
				if (row == 0) {
					return;
				}
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				for (int i = 0; i < row; i++) {
					model.setValueAt(true, i, 0);
				}
			}
		});
		// ��ѡע���¼�
		uncheckAllBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getRowCount();
				if (row == 0) {
					return;
				}
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				for (int i = 0; i < row; i++) {
					Object nullable = model.getValueAt(i, 4);
					model.setValueAt(nullable.equals("N"), i, 0);
				}
			}
		});
	}
}
