/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzy;

import java.util.ArrayList;
import java.util.List;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;
//import net.sourceforge.jFuzzyLogic.rule.FuzzyRuleSet;

/**
 *
 * @author marina
 */
public class Fuzzy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // Load from 'FCL' file
        String fileName = "regras.fcl";
        FIS fis = FIS.load(fileName, true);
                
        // Error while loading?
        if (fis == null) {
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }

        FunctionBlock functionBlock = fis.getFunctionBlock(null);
        // Show 
        //JFuzzyChart.get().chart(functionBlock);

        // Set inputs
        fis.setVariable("natalidade", 15.08);
        fis.setVariable("mortalidade", 6.36);

        // Evaluate
        fis.evaluate();

        // Show output variable's chart
        //JFuzzyChart.get().chart(functionBlock.getVariable("crescimento"), functionBlock.getVariable("crescimento").getDefuzzifier(), true);

        // Print ruleSet
        //System.out.println(fis);
        System.out.println("Output value:" + fis.getVariable("crescimento").getValue());
    }
}
