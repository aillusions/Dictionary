package com.aillusions.dictionary.view.components;

import javax.swing.JPanel;

import com.aillusions.dictionary.view.listener.MainDictPanelListener;

public class KeyBoardPanel extends JPanel {

	public static final int BTN_HEIGHT = 21;
	public static final int BTN_WIDTH = 24;

	private static final long serialVersionUID = 1L;

	private KeyBoardButton jButton_a;
	private KeyBoardButton jButton_b;
	private KeyBoardButton jButton_c;
	private KeyBoardButton jButton_d;
	private KeyBoardButton jButton_e;
	private KeyBoardButton jButton_f;

	private KeyBoardButton jButton_g;
	private KeyBoardButton jButton_h;
	private KeyBoardButton jButton_i;
	private KeyBoardButton jButton_j;
	private KeyBoardButton jButton_k;
	private KeyBoardButton jButton_l;
	private KeyBoardButton jButton_m;
	private KeyBoardButton jButton_n;
	private KeyBoardButton jButton_o;
	private KeyBoardButton jButton_p;

	private KeyBoardButton jButton_q;
	private KeyBoardButton jButton_r;
	private KeyBoardButton jButton_s;
	private KeyBoardButton jButton_t;
	private KeyBoardButton jButton_u;
	private KeyBoardButton jButton_v;
	private KeyBoardButton jButton_w;
	private KeyBoardButton jButton_x;
	private KeyBoardButton jButton_y;
	private KeyBoardButton jButton_z;
	private KeyBoardButton jButton_colon;
	private KeyBoardButton jButton_quoter;
	private KeyBoardButton jButton_space;
	private KeyBoardButton jButton_left_p;
	private KeyBoardButton jButton_right_p;

	public KeyBoardPanel(final MainDictPanelListener mainDictPanelListener) {
		
		setLayout(null);

		jButton_a = new KeyBoardButton("a", mainDictPanelListener);
		jButton_b = new KeyBoardButton("b", mainDictPanelListener);
		jButton_c = new KeyBoardButton("c", mainDictPanelListener);
		jButton_d = new KeyBoardButton("d", mainDictPanelListener);
		jButton_e = new KeyBoardButton("e", mainDictPanelListener);
		jButton_f = new KeyBoardButton("f", mainDictPanelListener);
		jButton_g = new KeyBoardButton("g", mainDictPanelListener);
		jButton_h = new KeyBoardButton("h", mainDictPanelListener);
		jButton_i = new KeyBoardButton("i", mainDictPanelListener);
		jButton_j = new KeyBoardButton("j", mainDictPanelListener);
		jButton_k = new KeyBoardButton("k", mainDictPanelListener);
		jButton_l = new KeyBoardButton("l", mainDictPanelListener);
		jButton_m = new KeyBoardButton("m", mainDictPanelListener);
		jButton_n = new KeyBoardButton("n", mainDictPanelListener);
		jButton_o = new KeyBoardButton("o", mainDictPanelListener);
		jButton_p = new KeyBoardButton("p", mainDictPanelListener);
		jButton_q = new KeyBoardButton("q", mainDictPanelListener);
		jButton_r = new KeyBoardButton("r", mainDictPanelListener);
		jButton_s = new KeyBoardButton("s", mainDictPanelListener);
		jButton_t = new KeyBoardButton("t", mainDictPanelListener);
		jButton_u = new KeyBoardButton("u", mainDictPanelListener);
		jButton_v = new KeyBoardButton("v", mainDictPanelListener);
		jButton_w = new KeyBoardButton("w", mainDictPanelListener);
		jButton_x = new KeyBoardButton("x", mainDictPanelListener);
		jButton_y = new KeyBoardButton("y", mainDictPanelListener);
		jButton_z = new KeyBoardButton("z", mainDictPanelListener);
		jButton_colon = new KeyBoardButton(":", mainDictPanelListener);
		jButton_space = new KeyBoardButton(" ", mainDictPanelListener);
		jButton_left_p = new KeyBoardButton("(", mainDictPanelListener);
		jButton_right_p = new KeyBoardButton(")", mainDictPanelListener);
		jButton_quoter = new KeyBoardButton("'", mainDictPanelListener);

		jButton_a.setBounds(27, 33, BTN_WIDTH, BTN_HEIGHT);
		jButton_b.setBounds(148, 59, BTN_WIDTH, BTN_HEIGHT);
		jButton_c.setBounds(92, 59, BTN_WIDTH, BTN_HEIGHT);
		jButton_d.setBounds(82, 33, BTN_WIDTH, BTN_HEIGHT);
		jButton_e.setBounds(61, 7, BTN_WIDTH, BTN_HEIGHT);
		jButton_f.setBounds(137, 33, BTN_WIDTH, BTN_HEIGHT);
		jButton_g.setBounds(110, 33, BTN_WIDTH, BTN_HEIGHT);
		jButton_h.setBounds(165, 33, BTN_WIDTH, BTN_HEIGHT);
		jButton_i.setBounds(204, 7, BTN_WIDTH, BTN_HEIGHT);
		jButton_j.setBounds(193, 33, BTN_WIDTH, BTN_HEIGHT);
		jButton_k.setBounds(220, 33, BTN_WIDTH, BTN_HEIGHT);
		jButton_l.setBounds(248, 33, BTN_WIDTH, BTN_HEIGHT);
		jButton_m.setBounds(204, 59, BTN_WIDTH, BTN_HEIGHT);
		jButton_n.setBounds(176, 59, BTN_WIDTH, BTN_HEIGHT);
		jButton_o.setBounds(232, 7, BTN_WIDTH, BTN_HEIGHT);
		jButton_p.setBounds(261, 7, BTN_WIDTH, BTN_HEIGHT);
		jButton_q.setBounds(5, 7, BTN_WIDTH, BTN_HEIGHT);
		jButton_r.setBounds(90, 7, BTN_WIDTH, BTN_HEIGHT);
		jButton_s.setBounds(54, 33, BTN_WIDTH, BTN_HEIGHT);
		jButton_t.setBounds(118, 7, BTN_WIDTH, BTN_HEIGHT);
		jButton_u.setBounds(175, 7, BTN_WIDTH, BTN_HEIGHT);
		jButton_v.setBounds(120, 59, BTN_WIDTH, BTN_HEIGHT);
		jButton_w.setBounds(33, 7, BTN_WIDTH, BTN_HEIGHT);
		jButton_x.setBounds(64, 59, BTN_WIDTH, BTN_HEIGHT);
		jButton_y.setBounds(147, 7, BTN_WIDTH, BTN_HEIGHT);
		jButton_z.setBounds(37, 59, BTN_WIDTH, BTN_HEIGHT);
		jButton_colon.setBounds(276, 33, BTN_WIDTH, BTN_HEIGHT);
		jButton_quoter.setBounds(304, 33, BTN_WIDTH, BTN_HEIGHT);
		jButton_space.setBounds(234, 59, 84, BTN_HEIGHT);
		jButton_left_p.setBounds(289, 7, BTN_WIDTH, BTN_HEIGHT);
		jButton_right_p.setBounds(318, 7, BTN_WIDTH, BTN_HEIGHT);

		add(jButton_q);
		add(jButton_w);
		add(jButton_e);
		add(jButton_r);
		add(jButton_t);
		add(jButton_y);
		add(jButton_u);
		add(jButton_i);
		add(jButton_o);
		add(jButton_p);
		add(jButton_left_p);
		add(jButton_right_p);
		
		add(jButton_a);
		add(jButton_s);
		add(jButton_d);
		add(jButton_f);
		add(jButton_g);
		add(jButton_h);
		add(jButton_j);
		add(jButton_k);
		add(jButton_l);
		add(jButton_colon);
		add(jButton_quoter);
		
		add(jButton_z);
		add(jButton_x);
		add(jButton_c);
		add(jButton_v);
		add(jButton_b);
		add(jButton_n);
		add(jButton_m);
		add(jButton_space);
	}
}
