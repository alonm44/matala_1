import static org.junit.Assert.*;

import org.junit.Test;

public class BestPathTest {

	@Test
	public void testE() {
		In in = new In("D:\\tinyEWD.txt");
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
		// compute shortest paths
		int bl[]={};
		DijkstraSP sp = new DijkstraSP(G,4,bl);
		double result= sp.distTo(5);
		assertEquals(0.35, result,0);
		int bl1[]={2};
		DijkstraSP sp1 = new DijkstraSP(G,6,bl1);
		double result1= sp1.distTo(7);
		boolean b=sp1.hasPathTo(7);
		assertTrue(b);
		assertEquals(1.3, result1,0);
		int bl2[]={3};
		DijkstraSP sp2 = new DijkstraSP(G,1,bl2);
		boolean b1=sp2.hasPathTo(6);
		assertFalse(b1);
		int bl3[]={4};
		DijkstraSP sp3 = new DijkstraSP(G,1,bl3);
		boolean b2=sp3.hasPathTo(3);
		assertTrue(b2);
		double result2= sp3.distTo(3);
		assertEquals(0.29, result2,0);
		int bl4[]={};
		DijkstraSP sp4 = new DijkstraSP(G,6,bl4);
		boolean b3=sp4.hasPathTo(4);
		assertTrue(b3);
		double result3= sp4.distTo(4);
		assertEquals(0.93, result3,0);
		
		int bl5[]={};
		DijkstraSP sp5 = new DijkstraSP(G,6,bl5);
		boolean b5=sp5.hasPathTo(3);
		assertTrue(b5);
		double result4= sp5.distTo(3);
		assertEquals(1.13, result4,0);
		
		int bl6[]={2};
		DijkstraSP sp6 = new DijkstraSP(G,6,bl6);
		double result5= sp6.distTo(3);
		assertEquals(1.69, result5,0);
		
		
		In in1 = new In("D:\\mediumEWD.txt");
		EdgeWeightedDigraph G1 = new EdgeWeightedDigraph(in1);
		int bl7[]={};
		DijkstraSP sp7 = new DijkstraSP(G1,240,bl7);
		double result7= sp7.distTo(245);
		assertEquals(0.48252000000000006, result7,0);
		
		int bl8[]={202};
		DijkstraSP sp8 = new DijkstraSP(G1,209,bl8);
		double result8= sp8.distTo(211);
		assertEquals(0.01269, result8,0);
		
		int bl9[]={22};
		DijkstraSP sp9 = new DijkstraSP(G1,222,bl9);
		double result9= sp9.distTo(3);
		assertEquals(0.52594,result9,0);
		
		int bl10[]={186};
		DijkstraSP sp10 = new DijkstraSP(G1,203,bl10);
		double result10= sp10.distTo(222);
		assertEquals(0.78966,result10,0);
		
		int bl11[]={248};
		DijkstraSP sp11 = new DijkstraSP(G1,187,bl11);
		double result11= sp11.distTo(226);
		assertEquals(0.0366,result11,0);
		
		
		In in2 = new In("D:\\largeEWD.txt");
		EdgeWeightedDigraph G2 = new EdgeWeightedDigraph(in2);
		int bl12[]={1};
		DijkstraSP sp12 = new DijkstraSP(G2,0,bl12);
		double result12= sp12.distTo(200);
		assertEquals(0.027943310000000006, result12,0);
		
		int bl13[]={14,44,128};
		DijkstraSP sp13 = new DijkstraSP(G2,1,bl13);
		double result13= sp13.distTo(201);
		assertEquals(0.35244173999999984, result13,0);
		
		int bl14[]={};
		DijkstraSP sp14 = new DijkstraSP(G2,2,bl14);
		double result14= sp14.distTo(202);
		assertEquals(0.27941732000000014, result14,0);
		
		int bl15[]={1,2,3,4,5,6,7,8};
		DijkstraSP sp15 = new DijkstraSP(G2,9,bl15);
		double result15= sp15.distTo(204);
		assertEquals(0.7448945699999994, result15,0);
		
		int bl16[]={14,13,129,36,223};
		DijkstraSP sp16 = new DijkstraSP(G2,100,bl16);
		double result16= sp16.distTo(0);
		assertEquals(0.6164415799999996, result16,0);
	}
}


