import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class LiczbaRzymskaTest {
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void test() 
	{
		assertEquals("I", new LiczbaRzymska(1).toString());
		assertEquals("II", new LiczbaRzymska(2).toString());
		assertEquals("III", new LiczbaRzymska(3).toString());
		assertEquals("IV", new LiczbaRzymska(4).toString());
		assertEquals("V", new LiczbaRzymska(5).toString());
		assertEquals("VI", new LiczbaRzymska(6).toString());
		assertEquals("VII", new LiczbaRzymska(7).toString());
		assertEquals("VIII", new LiczbaRzymska(8).toString());
		assertEquals("IX", new LiczbaRzymska(9).toString());
		assertEquals("X", new LiczbaRzymska(10).toString());
		assertEquals("XL", new LiczbaRzymska(40).toString());
		assertEquals("L", new LiczbaRzymska(50).toString());
		assertEquals("C", new LiczbaRzymska(100).toString());
		assertEquals("D", new LiczbaRzymska(500).toString());
		assertEquals("M", new LiczbaRzymska(1000).toString());
		assertEquals("XXXI", new LiczbaRzymska(31).toString());
		assertEquals("CXLVIII", new LiczbaRzymska(148).toString());
		assertEquals("CCXCIV", new LiczbaRzymska(294).toString());
		assertEquals("CCCXII", new LiczbaRzymska(312).toString());
		assertEquals("CDXXI", new LiczbaRzymska(421).toString());
		assertEquals("DXXVIII", new LiczbaRzymska(528).toString());
		assertEquals("DCXXI", new LiczbaRzymska(621).toString());
		assertEquals("DCCLXXXII", new LiczbaRzymska(782).toString());
		assertEquals("DCCCLXX", new LiczbaRzymska(870).toString());
		assertEquals("CMXLI", new LiczbaRzymska(941).toString());
		assertEquals("MXLIII", new LiczbaRzymska(1043).toString());
		assertEquals("MCX", new LiczbaRzymska(1110).toString());
		assertEquals("MCCXXVI", new LiczbaRzymska(1226).toString());
		assertEquals("MCCCI", new LiczbaRzymska(1301).toString());
		assertEquals("MCDLXXXV", new LiczbaRzymska(1485).toString());
		assertEquals("MDIX", new LiczbaRzymska(1509).toString());
		assertEquals("MDCVII", new LiczbaRzymska(1607).toString());
		assertEquals("MDCCLIV", new LiczbaRzymska(1754).toString());
		assertEquals("MDCCCXXXII", new LiczbaRzymska(1832).toString());
		assertEquals("MCMXCIII", new LiczbaRzymska(1993).toString());
		//assertEquals("MMLXXIV", new LiczbaRzymska(2074).toString());
		//assertEquals("MMCLII", new LiczbaRzymska(2152).toString());
//		assertEquals("MMCCXII", new LiczbaRzymska(2212).toString());
//		assertEquals("MMCCCXLIII", new LiczbaRzymska(2343).toString());
//		assertEquals("MMCDXCIX", new LiczbaRzymska(2499).toString());
//		assertEquals("MMDLXXIV", new LiczbaRzymska(2574).toString());
//		assertEquals("MMDCXLVI", new LiczbaRzymska(2646).toString());		
//		assertEquals("MMDCCXXIII", new LiczbaRzymska(2723).toString());
//		assertEquals("MMDCCCXCII", new LiczbaRzymska(2892).toString());
//		assertEquals("MMCMLXXV", new LiczbaRzymska(2975).toString());
//		assertEquals("MMMLI", new LiczbaRzymska(3051).toString());
//		assertEquals("MMMCLXXXV", new LiczbaRzymska(3185).toString());
//		assertEquals("MMMCCL", new LiczbaRzymska(3250).toString());
//		assertEquals("MMMCCCXIII", new LiczbaRzymska(3313).toString());
//		assertEquals("MMMCDVIII", new LiczbaRzymska(3408).toString());
//		assertEquals("MMMDI", new LiczbaRzymska(3501).toString());
//		assertEquals("MMMDCX", new LiczbaRzymska(3610).toString());
//		assertEquals("MMMDCCXLIII", new LiczbaRzymska(3743).toString());	
//		assertEquals("MMMDCCCXLIV", new LiczbaRzymska(3844).toString());
//		assertEquals("MMMDCCCLXXXVIII", new LiczbaRzymska(3888).toString());
//		assertEquals("MMMCMXL", new LiczbaRzymska(3940).toString());
//		assertEquals("MMMCMXCIX", new LiczbaRzymska(3999).toString());
		
	}

}
