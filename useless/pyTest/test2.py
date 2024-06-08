import networkx as nx
import matplotlib.pyplot as plt
import numpy as np
import random

# Create a new graph
G = nx.Graph()

# Add vertices and edges
stars = [
    (1, [2, 20, 3,4]),
    (2, [3, 1, 4,5]),
    (3, [4, 2, 5,6]),
    (4, [5, 3, 6,7]),
    (5, [6, 4, 7,8]),
    (6, [7, 5, 8,9]),
    (7, [8, 6, 9,10]),
    (8, [9, 7, 10,11]),
    (9, [10, 8, 11,12]),
    (10, [11, 9, 12,13]),
    (11, [12, 10, 13,14]),
    (12, [13, 11, 14,15]),
    (13, [14, 12, 15,16]),
    (14, [15, 13, 16,17]),
    (15, [16, 14, 17]),
    (16, [17, 15, 18]),
    (17, [18, 16, 19]),
    (18, [19, 17, 20]),
    (19, [20, 18, 1]),
    (20, [1, 19, 2])
]

edge_labels = {}
edge_count = 1
for star, connections in stars:
    G.add_node(star)
    for connection in connections:
        if G.has_edge(star, connection):
            continue
        G.add_edge(star, connection)
        edge_labels[(star, connection)] = edge_count
        edge_count += 1

# Draw the graph
pos = nx.spring_layout(G)
nx.draw_networkx_nodes(G, pos, node_color='blue', node_size=500)

# Draw each edge with a different color
colors = plt.cm.rainbow(np.linspace(0, 1, len(G.edges)))
for edge, color in zip(G.edges, colors):
    nx.draw_networkx_edges(G, pos, edgelist=[edge], edge_color=color)

nx.draw_networkx_labels(G, pos, font_color='white')
nx.draw_networkx_edge_labels(G, pos, edge_labels=edge_labels, font_color='black')
plt.show()