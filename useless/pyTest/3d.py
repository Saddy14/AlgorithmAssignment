# import matplotlib.pyplot as plt
# import networkx as nx
# from mpl_toolkits.mplot3d import Axes3D
# import numpy as np

# # Create a new graph
# G = nx.Graph()

# # Add vertices and edges


# for star, connections in stars:
#     G.add_node(star)
#     for connection in connections:
#         G.add_edge(star, connection)

# # Create 3D positions for each node
# pos = nx.spring_layout(G, dim=3)

# # Get node positions
# x, y, z = zip(*pos.values())

# # Create 3D figure
# fig = plt.figure(figsize=(10, 10))
# ax = fig.add_subplot(111, projection='3d')

# # Draw nodes
# ax.scatter(x, y, z, s=100)

# # Draw edges
# for edge in G.edges:
#     x = np.array((pos[edge[0]][0], pos[edge[1]][0]))
#     y = np.array((pos[edge[0]][1], pos[edge[1]][1]))
#     z = np.array((pos[edge[0]][2], pos[edge[1]][2]))
#     ax.plot(x, y, z, color='b')

# # Show the plot
# plt.show()

import matplotlib.pyplot as plt
import networkx as nx
from mpl_toolkits.mplot3d import Axes3D
import numpy as np

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

for star, connections in stars:
    G.add_node(star)
    for connection in connections:
        G.add_edge(star, connection)

# Create 3D positions for each node
pos = nx.spring_layout(G, dim=3)

# Get node positions
x, y, z = zip(*pos.values())

# Create 3D figure
fig = plt.figure(figsize=(10, 10))
ax = fig.add_subplot(111, projection='3d')

# Draw nodes
ax.scatter(x, y, z, s=100)

# Draw edges and edge labels
edge_count = 1
for edge in G.edges:
    x = np.array((pos[edge[0]][0], pos[edge[1]][0]))
    y = np.array((pos[edge[0]][1], pos[edge[1]][1]))
    z = np.array((pos[edge[0]][2], pos[edge[1]][2]))
    ax.plot(x, y, z, color='b')
    ax.text(x.mean(), y.mean(), z.mean(), str(edge_count), fontsize=10, color='k')
    edge_count += 1

# Draw node labels
for key, value in pos.items():
    ax.text(value[0], value[1], value[2], str(key), fontsize=10, color='r')

# Show the plot
plt.show()