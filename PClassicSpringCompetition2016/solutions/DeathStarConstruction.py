from __future__ import division

class vec(tuple):
    def _binaryop(self, other, op):
        try:
            return vec(op(a, b) for a,b in zip(self, other))
        except:
            raise NotImplementedError
    
    def __add__(self, other):
        return self._binaryop(other, lambda a,b: a + b)

    def __sub__(self, other):
        return self._binaryop(other, lambda a, b: a - b)

    def __mul__(self, other):
        return vec(a * other for a in self)

def ccw(x1, y1, x2, y2, x3, y3):
    t = (x1 * y2 + x2 * y3 + x3 * y1 - x2 * y1 - x3 * y2 - x1 * y3)
    if t == 0:
        return 0
    elif t > 0:
        return 1
    else:
        return -1

def overlap(line1, line2):
    ((x1, y1, z1), (x2, y2, z2)) = line1
    ((x3, y3, z3), (x4, y4, z4)) = line2
    if (
        ccw(x1, y1, x2, y2, x3, y3) == 0 or ccw(x1, y1, x2, y2, x4, y4) == 0 or
        ccw(x1, y1, x3, y3, x4, y4) == 0 or ccw(x2, y2, x3, y3, x4, y4) == 0
    ):
        return True
    return (
        (ccw(x1, y1, x2, y2, x3, y3) != ccw(x1, y1, x2, y2, x4, y4)) and
        (ccw(x3, y3, x4, y4, x2, y2) != ccw(x3, y3, x4, y4, x1, y1))
    )

def between(x1, y1, x2, y2, x3, y3):
    return x3 <= max(x1, x2) and x3 >= min(x1, x2) and y3 <= max(y1, y2) and y3 >= min(y1, y2) and ccw(x1, y1, x2, y2, x3, y3) == 0

def flatten2(v):
    (x, y, z) = v
    return vec((x, y))

def getz(v):
    (x, y, z) = v
    return z

def dfs(adj, visited, x, p):
    visited[x] = 1
    for i in range(len(adj[x])):
        if i == p or not adj[x][i]:
            continue
        if visited[i] == 1:
            return False
        if not dfs(adj, visited, i, x):
            return False
    visited[x] = 2
    return True

def has_cycle(lines):
    adj = [[False for j in range(len(lines))] for i in range(len(lines))]
    visited = [0 for i in range(len(lines))]
    for i, l1 in enumerate(lines):
        for j, l2 in enumerate(lines):
            if i == j or not overlap(l1, l2):
                continue
            a1, b1 = l1
            a2, b2 = l2

            c1, c2 = flatten2(a2) - flatten2(a1)
            u1, u2 = flatten2(b1 - a1)
            v1, v2 = flatten2(b2 - a2)
            if (u1 * v2 - v1 * u2) == 0:
                x1, y1, z1 = a1
                x2, y2, z2 = b1
                x3, y3, z3 = a2
                x4, y4, z4 = b2
                def calc_adj(p1, p2, p3):
                    xa, ya, za = p1
                    xb, yb, zb = p2
                    xc, yc, zc = p3
                    if yb == ya:
                        return zc < (za + (zb - za) * (xc - xa) / (xb - xa))
                    else:
                        return zc < (za + (zb - za) * (yc - ya) / (yb - ya))
                if between(x1, y1, x2, y2, x3, y3):
                    adj[i][j] = calc_adj(a1, b1, a2)
                elif between(x1, y1, x2, y2, x4, y4):
                    adj[i][j] = calc_adj(a1, b1, b2)
                elif between(x3, y3, x4, y4, x1, y1):
                    adj[i][j] = calc_adj(a2, b2, a1)
                elif between(x3, y3, x4, y4, x2, y2):
                    adj[i][j] = calc_adj(a2, b2, b1)
            else:
                s = (c1 * v2 - v1 * c2) / (u1 * v2 - v1 * u2)
                t = (c1 * u2 - c2 * u1) / (u1 * v2 - v1 * u2)
                z1 = getz(a1 + (b1 - a1) * s)
                z2 = getz(a2 + (b2 - a2) * t)
                if z1 < z2:
                    adj[i][j] = True
    for i in range(len(lines)):
        if visited[i] == 2:
            continue
        visited[i] = 1
        if dfs(adj, visited, i, -1) == False:
            return False
    return True

if __name__ == "__main__":
    with open("DeathStarConstructionIN.txt", "r") as f:
        while True:
            s = f.readline()
            if s == "":
                break
            data = s.split(" ")
            N = int(data[0])
            points = []
            lines = []
            for i in range(2 * N):
                points.append(vec(map(int, (data[3*i + 1], data[3*i + 2], data[3*i + 3]))))
            for i in range(N):
                lines.append((points[2*i], points[2*i + 1]))
            if has_cycle(lines):
                print("true")
            else:
                print("false")
