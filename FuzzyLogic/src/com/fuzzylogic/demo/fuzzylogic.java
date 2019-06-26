/**
 * @ref 	https://jfuzzylogic.sourceforge.net
 */

package com.fuzzylogic.demo;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;

public class fuzzylogic {
	public static void main(String[] args) throws Exception 
	{
		String filename = "scenario.fcl";
		FIS fis = FIS.load(filename, true);

		if (fis == null) {
			System.err.println("Can't load file: '" + filename + "'");
			System.exit(1);
		}

		// Get default function block
		FunctionBlock fb = fis.getFunctionBlock(null);

		// Set inputs
		fb.setVariable("system", 10);
		fb.setVariable("network", 10);
		fb.setVariable("webapp", 10);
		fb.setVariable("malware", 70);

		// Evaluate
		fb.evaluate();

		// Show output variable's chart
		fb.getVariable("scenario").defuzzify();

		// Print ruleSet
		// System.out.println(fb); FCL 파일 보여주기
		
		// FCL에서  결과 메소드를 Center Of Gravity을 통해 추출하므로 결과가 소스점까지 반환
		// 결과 형 변환: double -> long -> int
		double row_result = fb.getVariable("scenario").getValue();
		int result = (int)Math.round( row_result );
		
		System.out.println( "Fuzzy Result: " + result );
	}
}