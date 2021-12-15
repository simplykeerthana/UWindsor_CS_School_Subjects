word(prolog, p, r, o, l, o, g).
word(online, o, n, l, i, n, e).
word(google, g, o, o, g, l, e).
word(python, p, y, t, h, o, n).
word(parser, p, a, r, s, e, r).
word(kernel, k, e, r, n, e, l).
word(web, w, e, b).
word(gnu, g, n, u).
word(nfs, n, f, s).
word(sql, s, q, l).
word(mac, m, a, c).
word(xml, x, m, l).
word(api, a, p, i).
word(perl, p, e, r, l).
word(java, j, a, v, a).
word(coop, c, o, o, p).
word(loop, l, o, o, p).
word(fork, f, o, r, k).
word(fifo, f, i, f, o).
word(pipe, p, i, p, e).
word(emacs, e, m, a, c, s).
word(linux, l, i, n, u, x).
word(mouse, m, o, u, s, e).



crossword(W1, W2, W3, W4, W5, W6, W7, W8, W9, W10, W11) :-
word(W1, X1Y6, _, X3Y6, _, _, X6Y6),
word(W2, X3Y4, _, X5Y4, X6Y4, X7Y4),
word(W3, X7Y3, _, X9Y3),
word(W4, X5Y2, _, X7Y2),
word(W5, _, X3Y1, _),
word(W6, X1Y6, _, _, _),
word(W7, X3Y6, _, X3Y4, _, _, X3Y1),
word(W8, X5Y4, _, X5Y2),
word(W9, X6Y6, _, X6Y4),
word(W10, X7Y4, X7Y3, X7Y2),
word(W11, _, _, _, X9Y3, _).
