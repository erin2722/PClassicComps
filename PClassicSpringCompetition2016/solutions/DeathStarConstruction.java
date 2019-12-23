import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
//STUBIFY
import java.util.ArrayList;
import java.util.LinkedList;
//ENDSTUBIFY
class Point {
  int[] r;
  Point(int x, int y, int z) {
    r = new int[3];
    r[0] = x;
    r[1] = y;
    r[2] = z;
  }
  //STUBIFY
  Point neg() {
    return new Point(-r[0], -r[1], -r[2]);
  }
  Point add(Point p) {
    return new Point(r[0] + p.r[0], r[1] + p.r[1], r[2] + p.r[2]);
  }
  Point minus(Point p) {
    return add(p.neg());
  }
  int dot(Point p) {
    int sum = 0;
    for (int i = 0; i < 3; i++) {
      sum += r[i]*p.r[i];
    }
    return sum;
  }
  Point cross(Point p) {
    return new Point(r[1]*p.r[2] - r[2]*p.r[1],
                     -(r[0]*p.r[2] - r[2]*p.r[0]),
                     r[0]*p.r[1] - r[1]*p.r[0]);
  }
  public String toString() {
    return "(" + r[0] + ", " + r[1] + ", " + r[2] + ")";
  }
  //ENDSTUBIFY
}
class LineSeg {
  Point a, b;
  LineSeg(Point a, Point b) {
    this.a = a;
    this.b = b;
  }
}
//STUBIFY
class Node {
  ArrayList<Node> adj;
  int id;
  int color = 0;
  Node(int id) {
    this.id = id;
    adj = new ArrayList<Node>();
    this.color = 0;
  }
  public boolean contains_cycle() {
    color = 1;
    for (int i = 0; i < adj.size(); i++) {
      if (adj.get(i).color == 0) {
        if (adj.get(i).contains_cycle()) return true;
      } else if (adj.get(i).color == 1) {
        return true;
      }
    }
    color = 2;
    return false;
  }
}


//ENDSTUBIFY
public class DeathStarConstruction {
  // Before submitting, make sure the main method hasn't been changed!
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("DeathStarConstructionIN.txt"));
    while (br.ready()) {
      String[] data = br.readLine().split(" ");
      int n = Integer.parseInt(data[0]);
      LineSeg[] beams = new LineSeg[n];
      Point[] pts = new Point[2];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < 2; j++) {
          int x = Integer.parseInt(data[1+3*(2*i+j)]);
          int y = Integer.parseInt(data[2+3*(2*i+j)]);
          int z = Integer.parseInt(data[3+3*(2*i+j)]);
          pts[j] = new Point(x, y, z);
        }
        beams[i] = new LineSeg(pts[0], pts[1]);
      }
      System.out.println(canRemoveBeams(beams));
    }
    br.close();
  }
  //STUBIFY
  public static int orientation(Point a, Point b, Point c) {
    int crossz = (b.r[0] - a.r[0]) * (c.r[1] - b.r[1]) - (b.r[1] - a.r[1]) * (c.r[0] - b.r[0]);
    if (crossz == 0) return 0; //Colinnear
    if (crossz < 0) return 1; // Clockwise
    return 2; // Counter - Clockwise
  }
  public static boolean segContains(Point a1, Point a2, Point p) {
    return (p.r[0] <= Math.max(a1.r[0], a2.r[0]) && p.r[0] >= Math.min(a1.r[0], a2.r[0]) &&
            p.r[1] <= Math.max(a1.r[1], a2.r[1]) && p.r[1] >= Math.min(a1.r[1], a2.r[1]));
  }
  //Standard way of checking the intersection between two lines. This can be
  //found in many places
  public static boolean overlap(Point u1, Point u2, Point v1, Point v2) {
    int o1 = orientation(u1, u2, v1);
    int o2 = orientation(u1, u2, v2);
    int o3 = orientation(v1, v2, u1);
    int o4 = orientation(v1, v2, u2);
    if (o1 != o2 && o3 != o4) return true;

    if (o1 == 0 && segContains(u1, u2, v1)) return true;
    if (o1 == 0 && segContains(u1, u2, v2)) return true;
    if (o1 == 0 && segContains(v1, v2, u1)) return true;
    if (o1 == 0 && segContains(v1, v2, u2)) return true;

    return false;
  }
  public static boolean onTop(Point u1, Point u2, Point v1, Point v2) {
    Point plane_norm = v1.minus(u1).cross(v2.minus(u1));
    if (plane_norm.r[2] < 0) plane_norm = plane_norm.neg();
    Point sep = u2.minus(u1);
    int dp = plane_norm.dot(sep);
    if (dp == 0) { //Here the lines are collinear
      if (u1.r[2] > v1.r[2] && u1.r[2] > v2.r[2] ||
          u2.r[2] > v1.r[2] && u2.r[2] > v2.r[2]) {
        return true;
      }
      if (v1.r[2] > u1.r[2] && v1.r[2] > u2.r[2] ||
          v2.r[2] > u1.r[2] && v2.r[2] > u2.r[2]) {
        return false;
      }
      Point u_vec = u2.minus(u1);
      Point u_left_vec = u_vec.cross(plane_norm);
      if (u_left_vec.r[2] < 0) u_left_vec = u_left_vec.neg();
      int v1_x = v1.minus(u1).dot(u_vec);
      int v1_y = v1.minus(u1).dot(u_left_vec);
      int v2_x = v2.minus(u2).dot(u_vec);
      int v2_y = v2.minus(u1).dot(u_left_vec);
      int u2_x = u_vec.dot(u_vec);
      if (v2_y < 0) {
        int tmp = v1_x;
        v1_x = v2_x;
        v2_x = tmp;
        tmp = v1_y;
        v1_y = v2_y;
        v2_y = tmp;
      }
      return (v2_y-v1_y)*u2_x < (v2_y - v1_y)*v2_x - (v2_x - v1_x)*v1_y;
    }    
    return plane_norm.dot(sep) > 0;
  }
  
  //ENDSTUBIFY
  public static boolean canRemoveBeams(LineSeg[] beams) {
    //STUBIFY
    int N = beams.length;
    //Construct Graph
    ArrayList<Node> nodes = new ArrayList<Node>();
    for (int i = 0; i < N; i++) {
      nodes.add(new Node(i));
    }
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (i != j) {
          if (overlap(beams[i].a, beams[i].b, beams[j].a, beams[j].b)
              && onTop(beams[i].a, beams[i].b, beams[j].a, beams[j].b)) {
            nodes.get(i).adj.add(nodes.get(j));
          }
        }
      }
    }
    //Check for back edges

    for (int i = 0; i < N; i++) {

      if (nodes.get(i).color == 0 && nodes.get(i).contains_cycle()) return false;
    }
    /*LinkedList<Node> s = new LinkedList<Node>();
    int[] visited = new int[N];
    for (int i = 0; i < N; i++) visited[i] = N+1;
    int iter = 1, head = 0;
    s.push(nodes.get(head));
    while (!s.isEmpty()) {
      Node e  = s.pop();
      e.color = 2;
      for (int i = 0; i < e.adj.size(); i++) {
        if (e.adj.get(i).color == 0) {
          s.push(e.adj.get(i));
          e.adj.get(i).color = 1;
        } else if (e.adj.get(i).color == 1) {
          return false; //found a cycle
        }
      }
      if (s.isEmpty()) {
        while (head < N && nodes.get(head).color > 0) head++;
        if (head == N) return true;
        s.push(nodes.get(head));
      }
    }*/
    return true; //This will never happen
    //ENDSTUBIFY
    //STUBIFY_INSERT
    ////fill out the body of this method
    //return false;
    //ENDSTUBIFY_INSERT
  }
}
